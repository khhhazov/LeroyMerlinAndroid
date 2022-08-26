package com.example.leroymerlin.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.leroymerlin.R
import com.example.leroymerlin.presentation.theme.*

@Preview(showBackground = true)
@Composable
fun ProductListScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (toolBar, rowList, filter, columnList) = createRefs()
        createVerticalChain(
            toolBar,
            rowList,
            filter,
            columnList,
            chainStyle = ChainStyle.Packed)

        TopAppBar(
            backgroundColor = Color.Transparent,
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
            .height(88.dp)
            .constrainAs(rowList) {
                linkTo(parent.start, parent.end)
                linkTo(toolBar.bottom, filter.top)
            }
            .padding(vertical = 8.dp),)
        FiltersToolBar(modifier = Modifier
            .fillMaxWidth()
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
            })
    }
}

@Composable
fun ColumnList(modifier: Modifier) {
    LazyColumn(modifier = modifier,
        content = {
            item {
                CategoriesRowItem()
            }
            item {
                CategoriesRowItem()
            }
        })
}

@Composable
fun RowList(modifier: Modifier) {
    LazyRow(modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp),
        content = {
            item {
                CategoriesRowItem()
            }
            item {
                CategoriesRowItem()
            }
            item {
                CategoriesRowItem()
            }
            item {
                CategoriesRowItem()
            }
        })
}

@Composable
fun FiltersToolBar(modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesRowItem() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {

        }
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
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
                    painter = painterResource(id = R.drawable.im_categories),
                    contentDescription = null)
            }
            Text(text = "Шпаклевки\nбазовые",
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
