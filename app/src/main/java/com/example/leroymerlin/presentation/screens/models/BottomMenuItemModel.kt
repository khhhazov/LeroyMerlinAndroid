package com.example.leroymerlin.presentation.screens.models

import androidx.compose.ui.graphics.painter.Painter

class BottomMenuItemModel(label: String, icon: Painter?) {
    var label: String? = null
    var icon: Painter? = null

    init {
        this.label = label
        this.icon = icon
    }
}