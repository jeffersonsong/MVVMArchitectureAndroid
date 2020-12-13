package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.User
import io.reactivex.Single

class MainRepository(private val apiService: ApiService) {

    fun getUsers(): Single<List<User>> {
        return apiService.getUsers()
    }
}
