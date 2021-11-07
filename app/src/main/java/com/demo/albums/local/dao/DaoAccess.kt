package com.demo.albums.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.albums.local.entity.Album

/*DAO Interface*/
@Dao
interface DaoAccess {
    @Insert
    fun insertAlbum(album: Album?)

    @Query("DELETE FROM albums")
    fun deleteAll()

    @Query("SELECT * FROM albums")
    fun getEntireAlbumList(): List<Album?>
}
