package com.developer.android.dev.technologia.androidapp.mvvmunittesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.model.ProductListItem
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.repository.ProductRepository
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository):ViewModel() {

    private val _product = MutableLiveData<NetworkResult<List<ProductListItem>>>()

    val product: LiveData<NetworkResult<List<ProductListItem>>>
        get() = _product

    fun getProducts(){
        viewModelScope.launch {
            val result = repository.getProduct()
            _product.postValue(result)
        }
    }
}