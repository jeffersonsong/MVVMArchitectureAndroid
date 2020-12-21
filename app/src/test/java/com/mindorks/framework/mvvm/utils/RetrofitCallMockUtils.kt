package com.mindorks.framework.mvvm.utils

import com.mindorks.framework.mvvm.data.model.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RetrofitCallMockUtils {
    companion object {
        fun mockResponse(call: Call<List<User>>, response: Response<List<User>>) {
            whenever(call.enqueue(any())).thenAnswer { invocation ->
                val callback = invocation.arguments[0] as Callback<List<User>>
                callback.onResponse(call, response)
                null
            }
        }

        fun mockFailure(call: Call<List<User>>, exception: Exception) {
            whenever(call.enqueue(any())).thenAnswer { invocation ->
                val callback = invocation.arguments[0] as Callback<List<User>>
                callback.onFailure(call, exception)
                null
            }
        }
    }
}
