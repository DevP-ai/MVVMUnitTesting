package com.developer.android.dev.technologia.androidapp.mvvmunittesting.api

import com.developer.android.dev.technologia.androidapp.mvvmunittesting.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("/products")
    suspend fun getProduct(): Response<List<ProductListItem>>
}