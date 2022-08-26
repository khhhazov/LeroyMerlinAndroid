package com.example.leroymerlin.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.leroymerlin.R
import com.example.leroymerlin.presentation.theme.IconPrimary
import com.example.leroymerlin.presentation.theme.Purple200

@Composable
fun MenuToolBar(modifier: Modifier) {
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