package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.RetrofitCallMockUtils.Companion.mockFailure
import com.mindorks.framework.mvvm.utils.RetrofitCallMockUtils.Companion.mockResponse
import com.mindorks.framework.mvvm.utils.Status
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsCollectionContaining.hasItem
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
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
        whenever(mainRepository.getUsers()).thenReturn(call)

        val user: User = mock()
        val userList = arrayListOf(user)

        val response: Response<List<User>> = mock()
        whenever(response.isSuccessful).thenReturn(true)
        whenever(response.body()).thenReturn(userList)

        mockResponse(call, response)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.users.value!!
        assertThat(users.status, `is`(Status.SUCCESS))
        assertThat(users.data, hasItem(user))
    }

    @Test
    fun testUnsuccessfulResponse() {
        val call: Call<List<User>> = mock()
        whenever(mainRepository.getUsers()).thenReturn(call)

        val response: Response<List<User>> = mock()
        whenever(response.isSuccessful).thenReturn(false)

        mockResponse(call, response)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.users.value!!
        assertThat(users.status, `is`(Status.ERROR))
        assertNull(users.data)
        assertThat(users.message, `is`("Something Went Wrong"))
    }

    @Test
    fun testFailedResponse() {
        val call: Call<List<User>> = mock()
        whenever(mainRepository.getUsers()).thenReturn(call)

        val exception: Exception = mock()

        mockFailure(call, exception)

        viewModel = MainViewModel(mainRepository)

        val users = viewModel.users.value!!
        assertThat(users.status, `is`(Status.ERROR))
        assertNull(users.data)
        assertThat(users.message, `is`("Something Went Wrong"))
    }
}
