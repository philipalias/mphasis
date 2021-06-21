package com.mphasis.repository


import com.mphasis.retrofit.RetrofitService

class AlbumRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllAlbums() = retrofitService.getAllAlbums()
    }