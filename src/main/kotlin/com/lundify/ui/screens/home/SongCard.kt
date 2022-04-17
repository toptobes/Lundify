package com.lundify.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lundify.data.models.Song
import kotlin.math.abs

@Composable
fun SongCard(song: Song, selected: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .drawBehind {
                if (!selected) return@drawBehind

                drawCircle(
                    brush = Brush.radialGradient(
                        radius = 300f,
                        colors = listOf(
                            Color.White,
                            Color.Transparent
                        )
                    ),
                    radius = 350f
                )
            },
        contentAlignment = Alignment.Center
    ) { }

    Card(
        modifier = modifier.size(300.dp)
            .scale(if (selected) 1.2f else 1f)
            .clip(RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        border = if (selected) {
            BorderStroke(width = 3.dp, color = Color.White)
        } else null,
        elevation = 5.dp,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = song.image,
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 30f
                        )
                    )
            ) { }

            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(200.dp))

                Row {
                    Text(
                        text = song.title,
                        style = TextStyle(color = Color(255, 255, 255, 200), fontSize = 20.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(210.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = song.length.toFormattedTime(),
                        style = TextStyle(color = Color(255, 255, 255, 200), fontSize = 20.sp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "(${song.artist})",
                    style = TextStyle(color = Color(255, 255, 255, 200), fontSize = 16.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(160.dp)
                )
            }
        }
    }
}


private fun Int.toFormattedTime(): String {
    val minutes = this / 60
    val seconds = this % 60

    return "$minutes:${seconds.toString().padStart(2, '0')}"
}
