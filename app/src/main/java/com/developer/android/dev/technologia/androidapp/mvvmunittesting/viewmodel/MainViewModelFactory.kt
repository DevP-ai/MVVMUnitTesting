package com.developer.android.dev.technologia.androidapp.mvvmunittesting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.repository.ProductRepository

class MainViewModelFactory(private val productRepository: ProductRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(productRepository) as T
    }
}