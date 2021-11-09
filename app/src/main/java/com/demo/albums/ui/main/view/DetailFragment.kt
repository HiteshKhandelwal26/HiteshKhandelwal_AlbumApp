package com.demo.albums.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.albums.R
import com.demo.albums.data.model.AlbumDetail
import com.demo.albums.databinding.FragmentDetailBinding
import com.demo.albums.ui.main.viewmodel.DetailViewModel
import com.demo.albums.utils.Common
import com.demo.albums.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

/*Setting up the view class - the dashboard that loads the list of albums from the API response
* @AndroidEntryPoint - Means,Hilt should provide all the dependencies to this class that it asks for.
* */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var mBinding: FragmentDetailBinding? = null
    private val viewModel: DetailViewModel by viewModels()
    private var mSnackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    @DelicateCoroutinesApi
    private fun setupObservers() {
        if (activity?.let { Common.isNetworkConnected(it) }!!) {
            viewModel.getAlbumDetail().observe(
                viewLifecycleOwner,
                {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {

                                resource.data?.let { albumDetail -> initView(albumDetail) }
                            }
                            Status.ERROR -> {
                                loadSnackBar(it.message)
                            }
                            Status.LOADING -> {

                            }
                        }
                    }
                }
            )
        } else {
            loadSnackBar(resources.getString(R.string.noLocalData))
        }
    }

    /*Load data from Remote*/
    @DelicateCoroutinesApi
    /*--a delicate declaration*/
    private fun initView(listItems: AlbumDetail) {
        mBinding?.detailTV?.text = listItems.title
    }

    private fun loadSnackBar(message: String?) {
        mSnackBar = Common.getSnackBar(
            message,
            Snackbar.LENGTH_SHORT,
            mBinding!!.mainLayout
        )
        mSnackBar?.show()
    }
}
