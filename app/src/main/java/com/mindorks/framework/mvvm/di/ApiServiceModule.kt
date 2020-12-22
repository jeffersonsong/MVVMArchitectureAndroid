package com.mindorks.framework.mvvm.di

import com.mindorks.framework.mvvm.BuildConfig
import com.mindorks.framework.mvvm.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiServiceModule = module {
    single { provideRetrofit(BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
}

private fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)