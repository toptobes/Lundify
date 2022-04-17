@file:Suppress("WrapUnaryOperator")

package com.lundify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lundify.data.DemoLibraryProvider
import com.lundify.data.models.Song
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import kotlin.math.abs
import kotlin.math.exp

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val library = DemoLibraryProvider.bigUserLibrary()

        var delta by rememberSaveable { mutableStateOf(0f) }

        val draggableState = rememberDraggableState {
            delta = (delta + it).coerceIn(-200f * library.size / 2 + 200, 200f * library.size / 2)
        }

        Box(
            modifier = Modifier.padding(100.dp)
                .wrapContentSize(Alignment.Center)
                .draggable(draggableState, Orientation.Horizontal)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val libraryCopy = mutableListOf<Pair<Song, Float>>()

            library.forEachIndexed { index, song ->
                val xOffset = 200f * (index - library.size / 2f) + delta - 1
                if (abs(xOffset) < 100) {
                    libraryCopy.add(song to xOffset)
                } else {
                    libraryCopy.add(0, song to xOffset)
                }
            }

            for (song in libraryCopy) {
                SongCard(
                    song = song.first,
                    selected = abs(song.second) < 100,
                    modifier = Modifier.offset(x = 10.dp + song.second.dp, y = song.second.getY().dp)
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.offset(y = 25.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
                    .size(width = 300.dp, height = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Personal Music Library",
                    style = TextStyle(
                        fontSize = 23.sp,
                        color = Color(255, 255, 255, 200)
                    )
                )
            }
        }
    }
}

private fun Float.getY(): Float {
    return 50 * exp(-.000035f * this * this) - 20
}