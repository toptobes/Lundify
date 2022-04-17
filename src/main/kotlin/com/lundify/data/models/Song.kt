package com.lundify.data.models

import androidx.compose.ui.graphics.painter.Painter
import java.io.Serializable

data class Song(
    val image: Painter,
    val title: String,
    val artist: String,
    val genre: String,
    val length: Int
) : Serializable