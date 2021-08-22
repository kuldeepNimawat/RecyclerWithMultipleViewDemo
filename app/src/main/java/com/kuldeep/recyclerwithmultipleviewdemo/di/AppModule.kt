package com.kuldeep.recyclerwithmultipleviewdemo.di

import com.kuldeep.recyclerwithmultipleviewdemo.data.api.Api
import com.kuldeep.recyclerwithmultipleviewdemo.data.api.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTargetApi(
        remoteDataSource: RemoteDataSource
    ): Api {
        return remoteDataSource.buildApi(Api::class.java)
    }
}