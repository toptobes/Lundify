package com.lundify.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import com.lundify.ui.mainelements.RotatingLundifyLogo
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {

    val scale by remember {
        mutableStateOf(Animatable(.3f))
    }

    val alphaMultiplier by remember {
        mutableStateOf(Animatable(1f))
    }

    var background by remember {
        mutableStateOf(Screen.SplashScreen.background)
    }

    LaunchedEffect(Unit) {
        delay(4000)
        scale.animateTo(
            targetValue = 4f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )

        background = Screen.LoginScreen.background

        alphaMultiplier.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )

        navController.navigateTo(Screen.LoginScreen)
    }

    Background(navController, background, .5f, .8f, BlendMode.Darken)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.scale(5f * scale.value)
                .clip(CircleShape)
                .size(80.dp, 66.dp)
                .background(color = Color(34, 197, 94, (200 * alphaMultiplier.value).roundToInt()))
                .fillMaxSize()
        ) { }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            RotatingLundifyLogo(
                size = 100.dp,
                bgColor = Color.Transparent,
                boxModifier = modifier.scale(5f * scale.value),
                logoModifier = modifier.scale(3f * scale.value)
            ) {
                background = background.copy(color = Color(randRGBValue(), randRGBValue(), randRGBValue()))
            }
        }
    }
}

private fun randRGBValue(): Int {
    return Random.nextInt(0, 255)
}