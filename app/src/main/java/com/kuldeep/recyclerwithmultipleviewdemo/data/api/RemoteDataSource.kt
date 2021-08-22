package com.kuldeep.recyclerwithmultipleviewdemo.data.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    companion object{
        private const val Base_Url="https://60d194a45b017400178f3e51.mockapi.io/"
    }

    fun <Api> buildApi(api: Class<Api>): Api{
          return Retrofit.Builder()
              .baseUrl(Base_Url)
              .client(getRetrofitClient())
              .addConverterFactory(GsonConverterFactory.create())
              .build()
              .create(api)
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}