package com.example.leroymerlin.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.leroymerlin.R
import com.example.leroymerlin.presentation.ui.theme.IconPrimary
import com.example.leroymerlin.presentation.ui.theme.Purple200

@Composable
fun ProductListScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (toolBar) = createRefs()
        ToolBar(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .constrainAs(toolBar) {
                linkTo(parent.start, parent.end)
                top.linkTo(parent.top)
            })
    }
}

@Composable
fun ToolBar(modifier: Modifier) {
    Row(
        modifier = modifier.background(Purple200),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {

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