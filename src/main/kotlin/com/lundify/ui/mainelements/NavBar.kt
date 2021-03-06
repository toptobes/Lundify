@file:Suppress("WrapUnaryOperator")

package com.lundify.ui.mainelements

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lundify.navigation.NavController
import com.lundify.ui.screens.Screen

@Composable
fun NavBar(state: NavBarState, navController: NavController) {

    LaunchedEffect(key1 = navController.currentScreen.value) {
        if (state.visibilityLock != true) state.visibilityLock = navController.currentScreen.value.showNavBar
    }

    val width by animateDpAsState(
        targetValue = if (state.visible) 60.dp else 0.dp,
        animationSpec = tween(500)
    )

    val offset by animateOffsetAsState(
        targetValue = if (state.visible) Offset.Zero else Offset(-60f, 0f),
        animationSpec = tween(500)
    )

    NavigationRail(
        backgroundColor = Color.DarkGray,
        contentColor = Color.White,
        modifier = Modifier.clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
            .requiredWidth(width)
            .offset(offset.x.dp - 3.dp, offset.y.dp)
    ) {
        Screen.values().toList().forEach {
            if (!it.primary) return@forEach

            NavigationRailItem(
                selected = it == navController.currentScreen.value,
                onClick = {
                    navController.navigateTo(it)
                },
                label = {
                    if (width >= 50.dp) Text(it.label) else Text("")
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.label
                    )
                },
                alwaysShowLabel = false,
                selectedContentColor = Color(255, 255, 255, 150),
                unselectedContentColor = Color(255, 255, 255, 100)
            )
        }

        Spacer(Modifier.weight(1f))

        val alpha = if (state.visibilityLock == true) 200 else 120

        RotatingLundifyLogo(
            width,
            Color(34, 197, 94, alpha),
            boxModifier = Modifier.offset(y = -10.dp, x = 2.dp)
        ) {
            state.visibilityLock = if (state.visibilityLock == true) null else true
        }
    }
}

@Composable
fun rememberNavBarState() = rememberSaveable {
    NavBarState()
}

class NavBarState(
    private val _visibilityLock: MutableState<Boolean?> = mutableStateOf(false),
    private val _visible: MutableState<Boolean> = mutableStateOf(true)
) {
    var visibilityLock: Boolean?
        get() = _visibilityLock.value
        set(toggle) {
            _visibilityLock.value = toggle
        }
    var visible: Boolean
        get() = _visibilityLock.value ?: _visible.value
        set(toggle) {
            _visible.value = toggle
        }
}
