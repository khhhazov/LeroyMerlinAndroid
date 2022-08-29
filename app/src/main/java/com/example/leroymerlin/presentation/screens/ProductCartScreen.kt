package com.example.leroymerlin.presentation.screens

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.leroymerlin.R
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import com.example.leroymerlin.presentation.main.ProductViewModel
import com.example.leroymerlin.presentation.navigation.navigate
import com.example.leroymerlin.presentation.theme.*

@Composable
fun ProductCartScreen(
    context: Context,
    viewModel: ProductViewModel,
    navController: NavController
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .padding(bottom = 56.dp)
    ) {
        val (toolBar, title, column, total, line) = createRefs()
        CartToolBar(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .constrainAs(toolBar) {
                linkTo(parent.start, parent.end)
                linkTo(parent.top, title.top, bias = 0f)
            })
        Text(text = "Корзина",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    linkTo(parent.start, parent.end)
                    linkTo(toolBar.bottom, line.top)
                }
                .padding(16.dp),
            color = TextPrimary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.25.sp,
            fontSize = 34.sp,
        )
        Box(modifier = Modifier
            .height(0.6.dp)
            .padding(start = 16.dp)
            .fillMaxWidth()
            .background(ButtonMinor)
            .constrainAs(line) {
                linkTo(parent.start, parent.end)
                linkTo(title.bottom, column.top)
            })
        ColumnCartProduct(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(column) {
                linkTo(parent.start, parent.end)
                linkTo(line.bottom, total.top)
                height = Dimension.fillToConstraints
            },
            viewModel = viewModel,
            context = context,
            navController = navController)
        TotalBar(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(total) {
                linkTo(parent.start, parent.end)
                linkTo(column.bottom, parent.bottom)
            })
    }
}

@Composable
fun TotalBar(modifier: Modifier) {
    Column(modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp, bottom = 26.dp)
    ) {
        Text(
            text = "Итого без доставки",
            modifier = Modifier.fillMaxWidth(),
            color = TextMinor,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.2.sp,
            fontSize = 12.sp
        )
        Row(modifier = Modifier
            .padding(top = 7.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "999 товаров весом 6,5 кг",
                color = TextPrimary,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp,
                fontSize = 14.sp
            )
            Text(
                text = "9 999 999 ₽",
                color = TextPrimary,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp,
                fontSize = 22.sp
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 17.dp)
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MenuPrimary),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Оформить заказ",
                color = White,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp,
                fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnCartProductItem(
    product: ProductListEntity,
    context: Context,
    navController: NavController
) {
    val ims = context.assets.open(product.imageUrl.toString())
    val bitmap = BitmapFactory.decodeStream(ims)
    Card(modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(0.dp),
        onClick = {
            navController.navigate("info", bundleOf("product" to product))
        }
    ) {
        ConstraintLayout(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            val (info, image) = createRefs()
            Image(bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(87.dp)
                    .constrainAs(image) {
                        linkTo(parent.start, info.start)
                        linkTo(info.top, info.bottom, bias = 0f)
                    }
            )
            Column(modifier = Modifier
                .wrapContentHeight()
                .constrainAs(info) {
                    linkTo(image.end, parent.end)
                    linkTo(parent.top, parent.bottom, bias = 0f)
                    width = Dimension.fillToConstraints
                }
            ) {
                Row(modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name.toString(),
                        color = TextPrimary,
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.25.sp,
                        fontSize = 14.sp
                    )
                }
                Row(modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .requiredHeight(40.dp)
                            .requiredWidth(79.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonMinor),
                        onClick = {

                        }
                    ) {
                        Text(text = "1 шт.",
                            color = TextPrimary,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.25.sp,
                            fontSize = 14.sp)
                    }
                    Text(
                        text = "28,32 ₽",
                        color = TextPrimary,
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.25.sp,
                        fontSize = 14.sp
                    )
                }
                Box(modifier = Modifier
                    .padding(top = 16.dp)
                ) {
                    Box(modifier = Modifier
                        .height(0.6.dp)
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                        .background(ButtonMinor)
                    )
                }
            }
        }
    }
}

/*fun LazyListState.disableScrolling(scope: CoroutineScope) {
    scope.launch {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            awaitCancellation()
        }
    }
}
*/

@Composable
fun ColumnCartProduct(
    modifier: Modifier,
    viewModel: ProductViewModel,
    context: Context,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val state = rememberLazyListState()
    /*state.disableScrolling(scope)*/

    val stateProduct = viewModel.cartProduct.value
    val cartProductList: List<ProductListEntity> = stateProduct.toList()
    LazyColumn(modifier = modifier, state = state, content = {
        cartProductList.forEach {
            item {
                ColumnCartProductItem(it, context, navController = navController)
            }
        }
    })
}

@Composable
fun CartToolBar(modifier: Modifier) {
    Row(
        modifier = modifier.background(White),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = null,
                tint = IconPrimary
            )
        }
    }
}