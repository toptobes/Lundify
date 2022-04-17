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
import com.lundify.navigation.NavController
import com.lundify.ui.screens.Screen

@Composable
fun Background(
    navController: NavController,
    bg: Screen.Background? = null,
    initVal: Float = .15f,
    targetVal: Float = .4f,
    blendMode: BlendMode = BlendMode.Color
) {
    val currentScreen by remember { navController.currentScreen }
    val background = bg ?: currentScreen.background

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = background.color.copy(initVal),
        targetValue = background.color.copy(targetVal),
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(background.path),
        contentDescription = "Background",
        colorFilter = ColorFilter.tint(color, blendMode),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize().clip(shape = RoundedCornerShape(10.dp))
    )
}