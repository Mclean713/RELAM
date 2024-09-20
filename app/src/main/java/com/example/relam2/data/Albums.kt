package com.example.relam2.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector


data class Album(
    @StringRes val title: Int,
    @DrawableRes val cover:Int,
    @StringRes val artist: Int
)
data class Song(
    @StringRes val title: Int,
    @DrawableRes val artwork:Int,
    @StringRes val artist: Int
)
data class icons(
    @StringRes val label: Int,
    val icon: ImageVector
)
