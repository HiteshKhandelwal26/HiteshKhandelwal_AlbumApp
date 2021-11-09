package com.demo.albums.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
class AlbumDetail(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("userId")
    var userId: Int = 0
)