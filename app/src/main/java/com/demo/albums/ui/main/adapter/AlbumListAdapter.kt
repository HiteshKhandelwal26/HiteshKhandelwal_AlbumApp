package com.demo.albums.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.albums.R
import com.demo.albums.databinding.ItemMovieListBinding
import com.demo.albums.local.entity.Album

/**
 * Setting up the Recycler view - Class defines adapter for Albums list
 */

class AlbumListAdapter(
    private val dataList: List<Album?>?
) : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemMovieListBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val binding: ItemMovieListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_movie_list, viewGroup, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.albumName.text = dataList?.get(position)?.tittle
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }
}
