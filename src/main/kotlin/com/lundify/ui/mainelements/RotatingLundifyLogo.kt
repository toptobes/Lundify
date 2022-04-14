package com.lundify.ui.mainelements

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RotatingLundifyLogo(size: Dp, state: NavBarState, onClick: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000)
        )
    )

    var alpha by remember { mutableStateOf(if (state.visibilityLock == true) 200 else 120) }

    Box(
        modifier = Modifier.offset(y = (-10).dp, x = 2.dp)
            .size(size - 20.dp, size - 34.dp)
            .clip(CircleShape)
            .background(color = Color(34, 197, 94, alpha))
            .clickable {
                onClick()
                alpha = if (state.visibilityLock == true) 200 else 120
            }
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource("logos/LundifyLogoWithoutBG.png"),
            contentDescription = "Lundify Logo",
            modifier = Modifier.size(size - 20.dp)
                .rotate(rotation)
        )
    }
}