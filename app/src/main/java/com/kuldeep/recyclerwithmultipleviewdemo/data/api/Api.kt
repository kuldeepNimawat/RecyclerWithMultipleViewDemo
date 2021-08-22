package com.kuldeep.recyclerwithmultipleviewdemo.data.api

import com.kuldeep.recyclerwithmultipleviewdemo.data.model.HomeRecyclerViewItem
import retrofit2.http.GET

interface Api {

    @GET("movies")
    suspend fun getMovies() : List<HomeRecyclerViewItem.Movie>

    @GET("directors")
    suspend fun getDirectors() : List<HomeRecyclerViewItem.Director>

}

