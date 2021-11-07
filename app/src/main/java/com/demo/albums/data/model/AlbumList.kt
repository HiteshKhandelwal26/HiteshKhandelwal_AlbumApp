package com.demo.albums.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

class AlbumList : ArrayList<AlbumList.AlbumsItem>() {
    @Keep
    class AlbumsItem(
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("title")
        var title: String = "",
    )
}
