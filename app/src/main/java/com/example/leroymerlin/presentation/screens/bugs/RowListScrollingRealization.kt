package com.example.leroymerlin.presentation.screens.bugs

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leroymerlin.data.categories.list.room.dao.CategoryListEntity
import com.example.leroymerlin.data.product.list.room.dao.ProductListEntity
import com.example.leroymerlin.presentation.screens.ColumnList
import com.example.leroymerlin.presentation.screens.FiltersToolBar
import com.example.leroymerlin.presentation.screens.RowList
import com.example.leroymerlin.presentation.theme.Black
import com.example.leroymerlin.presentation.theme.White
import com.example.leroymerlin.presentation.theme.robotoFontFamily
import kotlin.math.roundToInt

@Composable
fun ProductListScreenWithScroll(
    products: List<ProductListEntity>,
    categories: List<CategoryListEntity>,
    context: Context
) {
    val rowListHeight = 88.dp
    val rowListHeightPx = with(LocalDensity.current) {
        rowListHeight.roundToPx().toFloat()
    }
    val rowListOffsetHeightPx = remember {
        mutableStateOf(0f)
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = rowListOffsetHeightPx.value + delta
                rowListOffsetHeightPx.value =
                    newOffset.coerceIn(-rowListHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .nestedScroll(nestedScrollConnection)
    ) {
        Box(modifier = Modifier
            .padding(top = 56.dp)
            .offset {
                IntOffset(x = 0, y = rowListOffsetHeightPx.value.roundToInt())
            }) {
            RowList(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 8.dp),
                categories = categories,
                context = context)
            FiltersToolBar(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = (72 + 8).dp)
                .padding(horizontal = 8.dp))
            ColumnList(modifier = Modifier
                .fillMaxWidth()
                .padding(top = (72 + 8 + 56).dp),
                products = products,
                context = context)
        }
        TopAppBar(
            backgroundColor = White,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
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
    }
}