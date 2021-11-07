package com.demo.albums.data.api

import com.demo.albums.data.model.AlbumList
import retrofit2.http.GET

/*Retrofit Service class - defines the API services to fetch the data*/
interface ApiService {
    @GET("/albums")
    suspend fun getAlbumList(): AlbumList
}
