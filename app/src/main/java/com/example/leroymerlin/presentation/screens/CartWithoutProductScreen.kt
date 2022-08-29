package com.example.leroymerlin.presentation.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.leroymerlin.presentation.main.ProductViewModel
import com.example.leroymerlin.presentation.theme.*

@Composable
fun CartWithoutProductScreen() {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .padding(bottom = 56.dp)
    ) {
        val (title, loginText, loginButton) = createRefs()
        createVerticalChain(
            title,
            loginText,
            loginButton,
            chainStyle = ChainStyle.Packed)
        Text(text = "В корзине ничего нет",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    linkTo(parent.start, parent.end)
                    linkTo(parent.top, loginText.top, bias = 0f)
                }
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = (56 + 16).dp),
            color = TextPrimary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.25.sp,
            fontSize = 28.sp,
        )
        Text(
            text = "Войдите в профиль, если вы уже добавляли товары под своими именем",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(loginText) {
                    linkTo(parent.start, parent.end)
                    linkTo(title.bottom, loginButton.top)
                }
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            color = TextPrimary,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.15.sp,
            fontSize = 16.sp
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .height(48.dp)
                .constrainAs(loginButton) {
                    linkTo(parent.start, parent.end)
                    linkTo(loginText.bottom, parent.bottom)
                },
            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonMinor),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Войти",
                color = TextPrimary,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.15.sp,
                fontSize = 16.sp)
        }
    }
}