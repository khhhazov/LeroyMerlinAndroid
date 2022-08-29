package com.example.leroymerlin.presentation.screens

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.leroymerlin.R
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import com.example.leroymerlin.presentation.main.ProductViewModel
import com.example.leroymerlin.presentation.theme.*

@Composable
fun ProductInfoScreen(
    product: ProductListEntity,
    context: Context,
    navController: NavController,
    viewModel: ProductViewModel
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .padding(bottom = 56.dp)
    ) {
        /*val ims = context.assets.open(product.imageUrl.toString())
        val bitmap = BitmapFactory.decodeStream(ims)
        */
        val (toolBar, image, ic_scroll, info, price) = createRefs()
        InfoToolBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(toolBar) {
                    linkTo(parent.start, parent.end)
                    linkTo(parent.top, image.top, bias = 0f)
                },
            navController = navController
        )
        /*Image(modifier = Modifier
            .requiredSize(229.dp)
            .constrainAs(image) {
                linkTo(parent.start, parent.end)
                linkTo(toolBar.bottom, ic_scroll.top)
            },
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null)
        Box(modifier = Modifier.padding(vertical = 21.dp)
            .constrainAs(ic_scroll) {
                linkTo(parent.start, parent.end)
                linkTo(image.bottom, info.top)
                height = Dimension.fillToConstraints
            },
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.ic_image_scroll),
                contentDescription = null)
        }
        */
        ProductImage(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(image) {
                linkTo(parent.start, parent.end)
                linkTo(toolBar.bottom, info.top)
                height = Dimension.fillToConstraints
            }, product = product,
            context = context)
        ProductInfo(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .constrainAs(info) {
                linkTo(parent.start, parent.end)
                linkTo(image.bottom, price.top)
            }, product = product)
        ProductPrice(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .constrainAs(price) {
                linkTo(parent.start, parent.end)
                linkTo(info.bottom, parent.bottom)
            },
            viewModel = viewModel,
            product = product
        )
    }
}

@Composable
fun ProductPrice(
    modifier: Modifier,
    product: ProductListEntity,
    viewModel: ProductViewModel
) {
    Row(modifier = modifier.padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
        Text(text = "65₽ / шт.",
            color = TextPrimary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.12.sp,
            fontSize = 18.sp)
        Button(
            modifier = Modifier.requiredHeight(48.dp)
                .requiredWidth(125.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MenuPrimary),
            onClick = {
                val setOfProduct: HashSet<ProductListEntity> = viewModel.setOfProduct
                if (setOfProduct.add(product)) {
                    viewModel.cartProduct.value = setOfProduct
                } else {
                    setOfProduct.remove(product)
                    viewModel.cartProduct.value = setOfProduct
                }
            }
        ) {
            Text(text = "В корзину",
                color = White,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ProductInfo(modifier: Modifier, product: ProductListEntity) {
    Column(modifier = modifier.padding(start = 16.dp),
    horizontalAlignment = Alignment.Start) {
        Text(text = "ЛМ 82733340",
            color = TextSecondary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 2.sp,
            fontSize = 12.sp,
            modifier = Modifier.clickable {

            }
        )
        Text(text = product.name.toString(),
            modifier = Modifier.padding(top = 10.dp, end = 64.dp),
            color = TextPrimary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.15.sp,
            fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)
                .clickable {

            }
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_stars),
                contentDescription = null,
                tint = Color(0xFFF4BE55)
            )
            Text(text = "13 отзывов",
                color = TextPrimary,
                modifier = Modifier.padding(start = 8.dp),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp,
                fontSize = 14.sp)
        }
        Box(modifier = Modifier
            .padding(top = 23.dp)
        ) {
            Box(modifier = Modifier
                .height(0.6.dp)
                .fillMaxWidth()
                .background(ButtonMinor))
        }
    }
}

@Composable
fun ProductImage(modifier: Modifier, product: ProductListEntity, context: Context) {
    val ims = context.assets.open(product.imageUrl.toString())
    val bitmap = BitmapFactory.decodeStream(ims)
    Column(modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Image(modifier = Modifier.requiredSize(229.dp),
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null)
        Box(modifier = Modifier.padding(vertical = 21.dp),
        contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.ic_image_scroll),
                contentDescription = null)
        }
    }
}

@Composable
fun InfoToolBar(modifier: Modifier, navController: NavController) {
    Row(
        modifier = modifier.background(White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = IconPrimary
            )
        }

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