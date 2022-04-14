package com.lundify.ui.mainelements

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import navcontroller.NavController

@Composable
fun Background(navController: NavController) {

    val currentScreen by remember { navController.currentScreen }

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = currentScreen.background.color.copy(.1f),
        targetValue = currentScreen.background.color.copy(.35f),
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(currentScreen.background.path),
        contentDescription = "Background",
        colorFilter = ColorFilter.tint(color, BlendMode.Color),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize().clip(shape = RoundedCornerShape(10.dp))
    )
}