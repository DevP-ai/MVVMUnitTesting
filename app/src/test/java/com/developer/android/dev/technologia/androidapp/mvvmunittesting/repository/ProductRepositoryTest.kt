package com.developer.android.dev.technologia.androidapp.mvvmunittesting.repository

import com.developer.android.dev.technologia.androidapp.mvvmunittesting.api.ProductAPI
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.model.ProductListItem
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {
    @Mock
    lateinit var productAPI: ProductAPI
    @Before
    fun setUp() {
       MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_EmptyList() = runTest{
        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProduct()
        Assert.assertEquals(true,result is NetworkResult.Success)
        Assert.assertEquals(0,result.data!!.size)
    }

    @Test
    fun testGetProducts_expectedProductList() = runTest{
        val productList = listOf<ProductListItem>(
            ProductListItem("","",1,"",40.3,"Prod-1"),
            ProductListItem("","",2,"",20.3,"Prod-2")
        )
        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.success(productList))

        val sut = ProductRepository(productAPI)
        val result = sut.getProduct()
        Assert.assertEquals(true,result is NetworkResult.Success)
        Assert.assertEquals(2,result.data!!.size)
        Assert.assertEquals("Prod-1",result.data!![0].title)
        Assert.assertEquals("Prod-2",result.data!![1].title)
    }

    @Test
    fun testGetProducts_expectedError() = runTest{

        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.error(401,"Unauthorized".toResponseBody()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProduct()
        Assert.assertEquals(true,result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong",result.messages)
    }

}