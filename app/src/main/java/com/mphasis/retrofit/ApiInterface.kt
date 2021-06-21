package com.mphasis.retrofit

import com.mphasis.model.AlbumModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("albums")
    suspend fun fetchAllAlbums(): List<AlbumModel>
}