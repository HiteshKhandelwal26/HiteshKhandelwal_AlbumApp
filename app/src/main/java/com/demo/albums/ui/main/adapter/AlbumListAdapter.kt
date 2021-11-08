package com.demo.albums.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.albums.databinding.ItemsAlbumBinding
import com.demo.albums.local.entity.Album

class AlbumListAdapter(private val dataList: List<Album?>?) :
    RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: ItemsAlbumBinding =
            ItemsAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
        holder.bind(dataList?.get(position))

}

class AlbumViewHolder(private val itemBinding: ItemsAlbumBinding) :
    RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var album: Album

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Album?) {
        if (item != null) {
            this.album = item
        }
        itemBinding.albumName.text = item?.tittle
    }

    override fun onClick(v: View?) {
    }
}

