package com.mindorks.framework.mvvm.di

import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiServiceModuleTest {
    @Test
    fun testApiService() {
        val apiService = ApiServiceModule.provideApiService()
        assertNotNull(apiService)
    }
}