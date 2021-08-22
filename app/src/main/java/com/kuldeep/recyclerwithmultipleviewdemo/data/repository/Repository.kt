package com.kuldeep.recyclerwithmultipleviewdemo.data.repository

import com.kuldeep.recyclerwithmultipleviewdemo.data.api.Api
import com.kuldeep.recyclerwithmultipleviewdemo.data.api.SafeApiCall
import com.kuldeep.recyclerwithmultipleviewdemo.data.model.HomeRecyclerViewItem
import javax.inject.Inject

class Repository @Inject constructor(
private val api: Api
) : SafeApiCall {
    suspend fun getMovies() = safeApiCall { api.getMovies() }
    suspend fun getDirectors() = safeApiCall { api.getDirectors() }
}