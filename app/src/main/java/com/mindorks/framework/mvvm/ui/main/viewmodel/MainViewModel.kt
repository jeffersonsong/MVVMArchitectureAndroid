package com.mindorks.framework.mvvm.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.Resource
import com.mindorks.framework.mvvm.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<List<User>>>()

    val loading: LiveData<Int> = Transformations.map(users) {
        if (it.status == Status.LOADING) View.VISIBLE
        else View.GONE
    }

    val dataAvailable: LiveData<Int> = Transformations.map(users) {
        if (it.status == Status.SUCCESS) View.VISIBLE
        else View.GONE
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(Resource.loading(null))

        val call = mainRepository.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                if (response!!.isSuccessful) {
                    val userList = response.body()
                    users.postValue(Resource.success(userList))
                } else {
                    users.postValue(Resource.error("Something Went Wrong", null))
                }
            }

            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        })
    }

    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }
}
