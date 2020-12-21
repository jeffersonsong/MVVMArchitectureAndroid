package com.mindorks.framework.mvvm.data.repository

import com.mindorks.framework.mvvm.data.api.ApiService
import com.mindorks.framework.mvvm.data.model.User
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call

class MainRepositoryTest {
    @InjectMocks private lateinit var sut : MainRepository
    @Mock private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetUsers() {
        val call: Call<List<User>> = mock();
        `when`(apiService.getUsers()).thenReturn(call)
        val result = sut.getUsers()
        assertThat(result, `is`(call))
    }
}