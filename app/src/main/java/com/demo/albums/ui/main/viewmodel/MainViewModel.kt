package com.demo.albums.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.albums.data.repository.MainRepository
import com.demo.albums.local.entity.Album
import com.demo.albums.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/*
* Setting up the ViewModel class that acts as an intermediate between the View and Model
* To inject ViewModel, have used @HiltViewModel, earlier we used to use ViewFactory class for the same.
* But Hilt makes it more easier now, also helping in to remove the boiler plate code as well
*
**/
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getAlbumList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAlbumList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertAlbum(album: Album) {
        mainRepository.insertAlbum(album)
    }

    fun getEntireAlbumList(): List<Album?> {
        return mainRepository.getEntireAlbumList()
    }

    fun deleteAllAlbum() {
        mainRepository.deleteAll()
    }
}
