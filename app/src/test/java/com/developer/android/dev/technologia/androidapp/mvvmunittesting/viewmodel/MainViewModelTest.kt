package com.developer.android.dev.technologia.androidapp.mvvmunittesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.getOrAwaitValue
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.repository.ProductRepository
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_GetProducts() = runTest{
        Mockito.`when`(repository.getProduct()).thenReturn(NetworkResult.Success(emptyList()))

        val sut = MainViewModel(repository)
        sut.getProducts()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.product.getOrAwaitValue()
        Assert.assertEquals(0,result.data!!.size)
    }

    @Test
    fun test_GetProduct_expectedError() = runTest {
        Mockito.`when`(repository.getProduct()).thenReturn(NetworkResult.Error("Something Went Wrong"))
        val sut = MainViewModel(repository)
        sut.getProducts()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.product.getOrAwaitValue()
        Assert.assertEquals(true,result is NetworkResult.Error)
        Assert.assertEquals("Something Went Wrong",result.messages)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}