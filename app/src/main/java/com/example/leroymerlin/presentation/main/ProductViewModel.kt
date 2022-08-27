package com.example.leroymerlin.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leroymerlin.data.product.list.ProductListRepository
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    productListRepository: ProductListRepository
): ViewModel() {

    val products: MutableState<List<ProductListEntity>> = mutableStateOf(listOf())

    /*private val _products: MutableLiveData<ArrayList<ProductListEntity>> = MutableLiveData()
    val products: LiveData<ArrayList<ProductListEntity>> get() = _products
    */

    init {
        viewModelScope.launch {
            /*productListRepository.initDataBase(ProductListInit().defaultListProduct())*/
            products.value = productListRepository.fetchProductList()
        }
    }
}