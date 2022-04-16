package com.lundify.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp
import com.lundify.MainWindowState
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import kotlin.math.exp
import kotlin.math.expm1

fun main() {

}

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val infiniteTransition = rememberInfiniteTransition()
        val x by infiniteTransition.animateFloat(
            initialValue = with(LocalDensity.current) { -MainWindowState.size.width.toPx() / 2  },
            targetValue = with(LocalDensity.current) { MainWindowState.size.width.toPx() / 2  },
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
//                repeatMode = RepeatMode.Reverse
            )
        )

        val y = 60 * exp(-.000037*x*x) + 30

        Box(
            modifier = Modifier.size(50.dp)
                .offset(x = x.dp, y = y.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color(255, 255, 255, 200))
        ) {
            Text(text = "Card goes here")
        }
    }
}