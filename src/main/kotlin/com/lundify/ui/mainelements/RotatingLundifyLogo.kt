@file:Suppress("WrapUnaryOperator")

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RotatingLundifyLogo(
    size: Dp,
    bgColor: Color,
    boxModifier: Modifier = Modifier,
    logoModifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000)
        )
    )

    Box(
        modifier = boxModifier.clip(CircleShape)
            .size(size - 20.dp, size - 34.dp)
            .background(color = bgColor)
            .fillMaxSize()
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painterResource("logos/LundifyLogoWithoutBG.png"),
            contentDescription = "Lundify Logo",
            modifier = logoModifier.size(size - 20.dp)
                .rotate(rotation)
        )
    }
}