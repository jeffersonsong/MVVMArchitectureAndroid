package com.mindorks.framework.mvvm.di

import com.mindorks.framework.mvvm.BuildConfig
import com.mindorks.framework.mvvm.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val retrofit = provideRetrofit()

        return retrofit.create(ApiService::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val url = BuildConfig.BASE_URL
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
