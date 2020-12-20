package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.User
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getUsers(): Call<List<User>> {
        return apiService.getUsers()
    }
}
