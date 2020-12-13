package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl(val url: String) : ApiService {

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get(url)
            .build()
            .getObjectListSingle(User::class.java)
    }
}
