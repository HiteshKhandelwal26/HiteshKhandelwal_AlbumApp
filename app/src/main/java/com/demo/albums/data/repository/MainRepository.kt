package com.demo.albums.data.repository

import com.demo.albums.data.api.ApiHelper
import com.demo.albums.local.dao.DaoAccess
import com.demo.albums.local.entity.Album
import javax.inject.Inject

/*
* Setting up the Repository class-
* Have used @Inject as the Repository constructor so that whenever we need a
* Repository object hilt will provide us Repository Object
*
* The Repository class has two dependencies ApiHelper and DaoAccess
*
* The important point is if we want hilt to provide us Repository object we also have to tell hilt how to provide an
* instance of classes or interfaces on which our Repository class depends.
* We have already created AppModule which provides a method to get a ApiHelper & DAO object.
* So when providing us the Repository object hilt will automatically provide all the dependencies of the Repository class.
* */
class MainRepository @Inject constructor(
    private val apiService: ApiHelper,
    private val daoAccess: DaoAccess
) {
    suspend fun getAlbumList() = apiService.getAlbumList()

    fun insertAlbum(album: Album?) {
        daoAccess.insertAlbum(album)
    }

    fun deleteAll() {
        daoAccess.deleteAll()
    }

    fun getEntireAlbumList(): List<Album?> {
        return daoAccess.getEntireAlbumList()
    }
}
