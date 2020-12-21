package com.mindorks.framework.mvvm.di

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt

class ApiServiceModuleTest {
    @Test
    fun testApiService() {
        val appContext: Context = mock()
        val url = "https://5e510330f2c0d300147c034c.mockapi.io/"
        `when`(appContext.getString(anyInt())).thenReturn(url)

        val apiService = ApiServiceModule.apiService(appContext)
        assertNotNull(apiService)
    }
}