package com.developer.android.dev.technologia.androidapp.mvvmunittesting.repository

import com.developer.android.dev.technologia.androidapp.mvvmunittesting.api.ProductAPI
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.model.ProductListItem
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.utils.NetworkResult

class ProductRepository (private val productAPI: ProductAPI){

    suspend fun getProduct():NetworkResult<List<ProductListItem>>{
        val response = productAPI.getProduct()

        return  if(response.isSuccessful){
            val responseBody = response.body()
            if(responseBody!=null){
                NetworkResult.Success(responseBody)
            }else{
                NetworkResult.Error("Something went wrong")
            }
        }else{
            NetworkResult.Error("Something went wrong")
        }
    }
}