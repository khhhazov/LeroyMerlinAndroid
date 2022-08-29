package com.example.leroymerlin.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leroymerlin.R
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import com.example.leroymerlin.presentation.screens.CartWithoutProductScreen
import com.example.leroymerlin.presentation.screens.ProductCartScreen
import com.example.leroymerlin.presentation.screens.ProductInfoScreen
import com.example.leroymerlin.presentation.screens.ProductListScreen
import com.example.leroymerlin.presentation.screens.models.BottomMenuItemModel
import com.example.leroymerlin.presentation.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels()
    private val context: Context = this

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    val bottomMenuItems = listOf(
                        BottomMenuItemModel("Главная", painterResource(id = R.drawable.ic_main)),
                        BottomMenuItemModel("Корзина", painterResource(id = R.drawable.ic_cart))
                    )
                    val navController = rememberNavController()

                    var selected by remember { mutableStateOf(0) }

                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                modifier = Modifier.requiredHeight(56.dp),
                                backgroundColor = White
                            ) {
                                bottomMenuItems.forEachIndexed { index, item  ->
                                    BottomNavigationItem(
                                        icon = { Icon(item.icon as Painter,
                                            contentDescription = null,
                                            tint = if (index == selected) {
                                                MenuPrimary
                                            } else {
                                                TextMinor
                                            }
                                        )},
                                        label = { Text(
                                            text = (item.label.toString()),
                                            color = if (index == selected) {
                                                MenuPrimary
                                            } else {
                                                TextMinor
                                            },
                                            fontSize = 10.sp,
                                            fontFamily = robotoFontFamily,
                                            fontWeight = FontWeight.Medium,
                                            letterSpacing = 0.12.sp) },
                                        selected = selected == index,
                                        onClick = {
                                            selected = index
                                            navController.navigate(item.label.toString())
                                        }
                                    )
                                }
                            }
                        }
                    ) {
                        NavHost(navController = navController, startDestination = "Главная") {
                            composable("Главная") {
                                ProductListScreen(context = context,
                                    viewModel = productViewModel,
                                    navController = navController
                                )
                            }
                            composable("Корзина") {
                                if (productViewModel.setOfProduct.isEmpty()) {
                                    CartWithoutProductScreen()
                                } else {
                                    ProductCartScreen(context = context,
                                        viewModel = productViewModel,
                                        navController = navController
                                    )
                                }
                            }
                            composable("info") {
                                navController
                                    .previousBackStackEntry
                                    ?.arguments
                                    ?.getParcelable<ProductListEntity>("product")
                                    ?.let {
                                        ProductInfoScreen(product = it,
                                            context = context,
                                            navController = navController,
                                            viewModel = productViewModel
                                        )
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}