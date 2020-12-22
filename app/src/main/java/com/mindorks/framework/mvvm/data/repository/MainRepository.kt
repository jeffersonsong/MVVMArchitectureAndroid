package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.User
import retrofit2.Call

class MainRepository(private val apiService: ApiService) {

    fun getUsers(): Call<List<User>> {
        return apiService.getUsers()
    }
}
