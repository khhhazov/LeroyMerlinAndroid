package com.example.leroymerlin.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leroymerlin.data.categories.list.CategoryListRepository
import com.example.leroymerlin.data.categories.list.defaultListCategory
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity
import com.example.leroymerlin.data.product.list.ProductListRepository
import com.example.leroymerlin.data.product.list.defaultListProduct
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    productListRepository: ProductListRepository,
    categoryListRepository: CategoryListRepository
): ViewModel() {

    val products: MutableState<List<ProductListEntity>> = mutableStateOf(listOf())
    val categories: MutableState<List<CategoryListEntity>> = mutableStateOf(listOf())

    /*private val _products: MutableLiveData<ArrayList<ProductListEntity>> = MutableLiveData()
    val products: LiveData<ArrayList<ProductListEntity>> get() = _products
    */

    init {
        viewModelScope.launch {
            productListRepository.initDataBase(defaultListProduct())
            categoryListRepository.initDataBase(defaultListCategory())

            products.value = productListRepository.fetchProductList()
            categories.value = categoryListRepository.fetchProductList()
        }
    }
}