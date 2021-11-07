package com.demo.albums.ui.main.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.albums.R
import com.demo.albums.data.model.AlbumList
import com.demo.albums.databinding.FragmentDashboardBinding
import com.demo.albums.local.AppDatabase
import com.demo.albums.local.entity.Album
import com.demo.albums.ui.main.adapter.AlbumListAdapter
import com.demo.albums.ui.main.viewmodel.MainViewModel
import com.demo.albums.utils.Common
import com.demo.albums.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*Setting up the view class - the dashboard that loads the list of albums from the API response
* @AndroidEntryPoint - Means,Hilt should provide all the dependencies to this class that it asks for.
* */
@AndroidEntryPoint
class DashboardFragment : Fragment() {
    var mBinding: FragmentDashboardBinding? = null
    private var horizontalLayout: LinearLayoutManager? = null
    private var recyclerViewLayoutManager: RecyclerView.LayoutManager? = null
    var mSnackBar: Snackbar? = null
    var movieListItems: AlbumList? = null
    private val viewModel: MainViewModel by viewModels()
    private val mainLooper = Looper.getMainLooper()
    private var albumsListData: List<Album?>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    @DelicateCoroutinesApi
    private fun setupObservers() {
        if (activity?.let { Common.isNetworkConnected(it) }!!) {
            viewModel.getAlbumList().observe(
                viewLifecycleOwner,
                {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mBinding?.listview?.visibility = View.VISIBLE
                                mBinding?.progressBar?.visibility = View.GONE
                                resource.data?.let { albumList -> initView(albumList) }
                            }
                            Status.ERROR -> {
                                mBinding?.listview?.visibility = View.GONE
                                mBinding?.progressBar?.visibility = View.GONE
                                loadSnackBar(it.message)
                            }
                            Status.LOADING -> {
                                mBinding?.progressBar?.visibility = View.VISIBLE
                                mBinding?.listview?.visibility = View.GONE
                            }
                        }
                    }
                }
            )
        } else {
            loadLocalData()
        }
    }

    /*Load data from local DB*/
    @DelicateCoroutinesApi
    private fun loadLocalData() {
        GlobalScope.launch {
            activity?.let {
                albumsListData = AppDatabase.getDatabase(it).daoAccess().getEntireAlbumList()
            }
            Handler(mainLooper).post {
                if (albumsListData?.size == 0) {
                    loadSnackBar(resources.getString(R.string.noLocalData))
                } else {
                    loadSnackBar(resources.getString(R.string.localData))
                    loadAdapter(albumsListData)
                }
            }
        }
    }

    /*Load data from Remote*/
    @DelicateCoroutinesApi /*--a delicate declaration*/
    private fun initView(listItems: AlbumList?) {
        movieListItems = listItems
        movieListItems?.sortWith(compareBy { it.title })

        GlobalScope.launch {
            if (viewModel.getEntireAlbumList().isNotEmpty()) {
                viewModel.deleteAllAlbum()
            }
            val albumPojo = Album()
            for (i in 0 until (listItems?.size ?: -1)) {
                albumPojo.tittle = movieListItems?.get(i)?.title
                viewModel.insertAlbum(albumPojo)
            }
            albumsListData = viewModel.getEntireAlbumList()

            Handler(mainLooper).post {
                loadAdapter(albumsListData)
            }
        }
    }

    /*Load the adapter to show the album list*/
    private fun loadAdapter(albumItems: List<Album?>?) {
        recyclerViewLayoutManager = LinearLayoutManager(activity)
        mBinding?.listview?.layoutManager = recyclerViewLayoutManager
        mBinding?.listview?.visibility = View.VISIBLE
        horizontalLayout = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter =
            activity?.let {
                AlbumListAdapter(
                    albumItems
                )
            }
        mBinding?.listview?.layoutManager = horizontalLayout
        mBinding?.listview?.adapter = adapter
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
