package com.lundify.ui.mainelements

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lundify.ui.screens.Screen
import navcontroller.NavController

@Composable
fun NavBar(state: NavBarState, navController: NavController) {

    var selected by remember {
        mutableStateOf(Screen.HomeScreen)
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
            .width(width)
            .offset(offset.x.dp - 3.dp, offset.y.dp)
    ) {
        Screen.values().toList().forEach {
            if (!it.primary) return@forEach

            NavigationRailItem(
                selected = it == navController.currentScreen.value,
                onClick = {
                    selected = it
                    navController.navigate(it)
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

        RotatingLundifyLogo(width) {
            state.visibilityLock = !state.visibilityLock
        }
    }
}

@Composable
fun rememberNavBarState() = rememberSaveable(NavBarState.Saver()) {
    NavBarState()
}

class NavBarState(
    private val _visibilityLock: MutableState<Boolean> = mutableStateOf(true),
    private val _visible: MutableState<Boolean> = mutableStateOf(true)
) {
    var visibilityLock: Boolean
        get() = _visibilityLock.value
        set(toggle) {
            if (toggle) _visible.value = true
            _visibilityLock.value = toggle
        }
    var visible: Boolean
        get() = _visible.value
        set(toggle) {
            if (!visibilityLock) {
                _visible.value = toggle
            }
        }

    companion object {
        fun Saver() = Saver<NavBarState, NavBarState>(
            save = { it },
            restore = { it }
        )
    }
}
