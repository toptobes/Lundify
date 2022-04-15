@file:Suppress("WrapUnaryOperator")

package com.lundify.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import kotlinx.coroutines.delay

@Composable
@Preview
fun LoginScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    val alpha by remember {
        mutableStateOf(Animatable(0f))
    }

    LaunchedEffect(Unit) {
        delay(500)

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = modifier.fillMaxSize().padding(40.dp).alpha(alpha.value),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(255, 255, 255, 150))
                .border(
                    width = 10.dp,
                    color = Color(255, 255, 255, 20),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(20.dp)
        ) {
            Text("Login screen here, I'll probably ditch this god-awful box")
        }
    }
}