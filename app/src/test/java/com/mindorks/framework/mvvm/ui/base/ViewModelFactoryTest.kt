package com.mindorks.framework.mvvm.ui.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Call
import java.lang.IllegalArgumentException

class ViewModelFactoryTest  {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @InjectMocks private lateinit var sut: ViewModelFactory
    @Mock private val mainRepository: MainRepository = mock()

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun testCreateMainViewModel() {
        val call: Call<List<User>> = mock()
        whenever(mainRepository.getUsers()).thenReturn(call)

        val result = sut.create(MainViewModel::class.java)
        assertNotNull(result)
        assertTrue(result is MainViewModel)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCreateWithUnsupportedType() {
        val viewModel = TestViewModel()
        sut.create(viewModel::class.java)
    }

    private class TestViewModel : ViewModel()
}