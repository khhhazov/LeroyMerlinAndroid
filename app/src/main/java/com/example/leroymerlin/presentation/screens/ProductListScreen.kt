package com.example.leroymerlin.presentation.screens

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.leroymerlin.R
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import com.example.leroymerlin.presentation.theme.*
import kotlin.math.roundToInt

/*реализация прокрутки раздела категорий имеет баг с размером LazyColumn,
  из-за чего она не вошла в финальную версию.
  файл находиться в дериктории screens/bugs и называется "RowListScrollingRealization.kt"
  */

@Composable
fun ProductListScreen(
    products: List<ProductListEntity>,
    categories: List<CategoryListEntity>,
    context: Context
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(White)
    ) {
        val (toolBar, rowList, filter, columnList) = createRefs()
        createVerticalChain(
            toolBar,
            rowList,
            filter,
            columnList,
            chainStyle = ChainStyle.Packed)
        TopAppBar(
            backgroundColor = White,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(toolBar) {
                    linkTo(parent.start, parent.end)
                    linkTo(parent.top, rowList.top, bias = 0f)
                },
            elevation = 0.dp,
            title = {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 8.dp)) {
                    Text(text = "Шпаклевки",
                        color = Black,
                        fontSize = 20.sp,
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Medium)
                }
            })
        RowList(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(88.dp)
            .constrainAs(rowList) {
                linkTo(parent.start, parent.end)
                linkTo(toolBar.bottom, filter.top)
            }
            .padding(vertical = 8.dp),
            categories = categories,
            context = context)
        FiltersToolBar(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(56.dp)
            .constrainAs(filter) {
                linkTo(parent.start, parent.end)
                linkTo(rowList.bottom, columnList.top)
            }
            .padding(horizontal = 8.dp))
        ColumnList(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(columnList) {
                linkTo(parent.start, parent.end)
                linkTo(filter.bottom, parent.bottom)
                height = Dimension.fillToConstraints
            },
            products = products,
            context = context)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductColumnItem(product: ProductListEntity, context: Context) {
    val ims = context.assets.open(product.imageUrl.toString())
    val bitmap = BitmapFactory.decodeStream(ims)
    Card(modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(0.dp),
        onClick = {

        }
    ) {
        ConstraintLayout(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()) {
            val (image, info) = createRefs()
            Image(bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(92.dp)
                    .constrainAs(image) {
                        linkTo(parent.start, info.start)
                        linkTo(parent.top, parent.bottom, bias = 0f)
                    })
            Column(modifier = Modifier
                .wrapContentHeight()
                .padding(start = 16.dp)
                .constrainAs(info) {
                    linkTo(image.end, parent.end)
                    linkTo(parent.top, parent.bottom)
                    width = Dimension.fillToConstraints
                }) {
                Text(text = product.name.toString(),
                    color = TextPrimary,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.25.sp,
                    fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp, top = 3.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_stars),
                        contentDescription = null,
                        tint = Color(0xFFF4BE55))
                    Text(text = "(62)",
                        color = TextSecondary,
                        modifier = Modifier.padding(start = 6.dp),
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.2.sp,
                        fontSize = 12.sp)
                }
                Text(text = "1 155 ₽ / шт.",
                    color = TextPrimary,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.15.sp,
                    fontSize = 16.sp)
                Text(text = "77 ₽ / шт.",
                    color = TextMinor,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.2.sp,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 12.dp))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        modifier = Modifier
                            .requiredHeight(40.dp)
                            .requiredWidth(108.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonMinor),
                        onClick = {
                            /*TODO*/
                        }
                    ) {
                        Text(text = "В корзину",
                            color = TextPrimary,
                            fontFamily = robotoFontFamily,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.25.sp,
                            fontSize = 14.sp)
                    }
//                IconToggleButton(
//                    checked = ,
//                    onCheckedChange =
//                ) {
//
//                }
                }
            }
        }
    }
}

@Composable
fun ColumnList(modifier: Modifier, products: List<ProductListEntity>, context: Context) {
    LazyColumn(modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp),
        content = {
            products.forEach {
                item {
                    ProductColumnItem(product = it, context)
                }
            }
        })
}

@Composable
fun RowList(modifier: Modifier, categories: List<CategoryListEntity>, context: Context) {
    LazyRow(modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp),
        content = {
            categories.forEach {
                item {
                    CategoriesRowItem(category = it, context)
                }
            }
        })
}

@Composable
fun FiltersToolBar(modifier: Modifier) {
    Box(modifier = modifier) {
        ConstraintLayout(modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()) {
            val (sort, filter) = createRefs()
            createHorizontalChain(sort, filter, chainStyle = ChainStyle.SpreadInside)
            TextButton(
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier.constrainAs(sort) {
                    linkTo(parent.start, filter.start)
                    linkTo(parent.top, parent.bottom)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = null,
                    tint = IconPrimary)
                Text(text = "Цена по возростанию",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.25.sp)
            }
            TextButton(
                onClick = {
                    /*TODO*/
                }, modifier = Modifier.constrainAs(filter) {
                    linkTo(sort.end, parent.end)
                    linkTo(parent.top, parent.bottom)
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_filters),
                    contentDescription = null,
                    tint = IconPrimary)
                Text(text = "Фильтры",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.25.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesRowItem(category: CategoryListEntity, context: Context) {
    val ims = context.assets.open(category.imageUrl.toString())
    val bitmap = BitmapFactory.decodeStream(ims)
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {

        }
    ) {
        ConstraintLayout(modifier = Modifier
            .requiredHeight(72.dp)
            .wrapContentWidth()
//            .fillMaxSize()
            .background(CardBackground)) {
            val (image, text) = createRefs()
            Box(modifier = Modifier
                .wrapContentSize()
                .constrainAs(image) {
                    linkTo(parent.top, parent.bottom)
                    linkTo(parent.start, text.start)
                }
                .padding(12.dp)
            ) {
                Image(
                    modifier = Modifier
                        .requiredSize(48.dp),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null)
            }
            Text(text = category.name.toString(),
                color = TextPrimary,
                fontSize = 12.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .constrainAs(text) {
                        linkTo(parent.top, parent.bottom)
                        linkTo(image.end, parent.end)
                    })
        }
    }
}

/*@Composable
fun TitleToolBar(modifier: Modifier) {
    ConstraintLayout(modifier = modifier
        .padding(horizontal = 24.dp)
    ) {
        val (text) = createRefs()
        Text(text = "Шпаклевки",
            modifier = Modifier.constrainAs(text) {
            linkTo(parent.top, parent.bottom)
            linkTo(parent.start, parent.end, bias = 0f)
        },
            color = Black,
            fontSize = 20.sp,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium
        )
    }
}
*/
