package com.mindorks.framework.mvvm.ui.main.viewmodel

import `mockito-extensions`.RetrofitCallMockUtils.Companion.mockFailure
import `mockito-extensions`.RetrofitCallMockUtils.Companion.mockResponse
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.Status
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response


class MainViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val mainRepository: MainRepository = mock()

    @Test
    fun testSuccessfulResponse() {
        val call: Call<List<User>> = mock()
        `when`(mainRepository.getUsers()).thenReturn(call)

        val user: User = mock()
        val userList = arrayListOf(user)

        val response: Response<List<User>> = mock()
        `when`(response.isSuccessful).thenReturn(true)
        `when`(response.body()).thenReturn(userList)

        mockResponse(call, response)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.getUsers().value!!
        assertThat(users.status, `is`(Status.SUCCESS))
        assertThat(users.data, hasItem(user))
    }

    @Test
    fun testUnsuccessfulResponse() {
        val call: Call<List<User>> = mock()
        `when`(mainRepository.getUsers()).thenReturn(call)

        val response: Response<List<User>> = mock()
        `when`(response.isSuccessful).thenReturn(false)

        mockResponse(call, response)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.getUsers().value!!
        assertThat(users.status, `is`(Status.ERROR))
        assertNull(users.data)
        assertThat(users.message, `is`("Something Went Wrong"))
    }

    @Test
    fun testFailedResponse() {
        val call: Call<List<User>> = mock()
        `when`(mainRepository.getUsers()).thenReturn(call)

        val exception: Exception = mock()

        mockFailure(call, exception)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.getUsers().value!!
        assertThat(users.status, `is`(Status.ERROR))
        assertNull(users.data)
        assertThat(users.message, `is`("Something Went Wrong"))
    }


}
