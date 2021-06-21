package com.mphasis.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mphasis.model.AlbumModel
import com.mphasis.repository.AlbumRepository
import kotlinx.coroutines.*

class AlbumViewModel constructor(private val repository: AlbumRepository) : ViewModel() {


    val errorMessage = MutableLiveData<String>()
    val albumList = MutableLiveData<List<AlbumModel>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllAlbums() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getAllAlbums()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    albumList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}