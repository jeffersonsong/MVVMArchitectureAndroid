package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>
}
