package com.mindorks.framework.mvvm.di

import android.content.Context
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiServiceModule {

    @Provides
    @Singleton
    fun apiService(@ApplicationContext appContext: Context): ApiService {
        val url = appContext.getString(R.string.service_url)
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }


}