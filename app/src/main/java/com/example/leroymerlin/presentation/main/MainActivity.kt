package com.example.leroymerlin.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.leroymerlin.presentation.screens.ProductListScreen
import com.example.leroymerlin.presentation.theme.LeroyMerlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = productViewModel.products.value
            val categories = productViewModel.categories.value

            products.forEach {
                Log.i("products", it.name.toString())
            }

            categories.forEach {
                Log.i("categories", it.name.toString())
            }

            LeroyMerlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProductListScreen(products, categories, this)
                }
            }
        }
    }
}