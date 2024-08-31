package com.developer.android.dev.technologia.androidapp.mvvmunittesting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.adapter.ProductAdapter
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.utils.NetworkResult
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.viewmodel.MainViewModel
import com.developer.android.dev.technologia.androidapp.mvvmunittesting.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.productList)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        val repository = (application as StoreApplication).productRepository

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getProducts()

        mainViewModel.product.observe(this, Observer {
            when(it){
                is NetworkResult.Success ->{
                    adapter = ProductAdapter(it.data!!)
                    recyclerView.adapter = adapter
                }

                is NetworkResult.Error ->{}
                is NetworkResult.Loading -> {}
            }
        })

    }
}