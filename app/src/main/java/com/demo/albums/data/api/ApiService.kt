package com.demo.albums.data.api

import com.demo.albums.data.model.AlbumDetail
import com.demo.albums.data.model.AlbumList
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/*Retrofit Service class - defines the API services to fetch the data*/
interface ApiService {
    @GET("/albums")
    suspend fun getAlbumList(): AlbumList

    @POST("/albums/")
    suspend fun getAlbumDetail(@Query("id") id: Int): AlbumDetail
}
