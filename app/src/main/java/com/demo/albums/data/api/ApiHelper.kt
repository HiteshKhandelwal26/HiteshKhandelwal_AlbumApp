package com.demo.albums.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAlbumList() = apiService.getAlbumList()
}
