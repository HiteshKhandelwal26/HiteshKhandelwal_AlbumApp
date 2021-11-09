package com.demo.albums.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.albums.databinding.ItemsAlbumBinding
import com.demo.albums.local.entity.Album

class AlbumListAdapter(private val dataList: List<Album?>?, private val listener: AlbumItemListener) :
    RecyclerView.Adapter<AlbumViewHolder>() {

    interface AlbumItemListener {
        fun onItemClicked(mPosition:Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: ItemsAlbumBinding =
            ItemsAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
        holder.bind(dataList?.get(position))


}

class AlbumViewHolder(private val itemBinding: ItemsAlbumBinding,
                      private val listener: AlbumListAdapter.AlbumItemListener) :
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
        itemBinding.mainLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onItemClicked(this.album.id)
    }
}

