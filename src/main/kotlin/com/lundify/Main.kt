package com.lundify

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.lundify.navigation.CurrentScreen
import com.lundify.navigation.PointerTracker
import com.lundify.navigation.rememberNavController
import com.lundify.ui.mainelements.NavBar
import com.lundify.ui.mainelements.Taskbar
import com.lundify.ui.mainelements.rememberNavBarState
import com.lundify.ui.mainelements.rememberTaskbarState
import com.lundify.ui.screens.Screen

@Composable
fun App(frameWindowScope: FrameWindowScope) {

    val navController by rememberNavController(Screen.HomeScreen)
    val taskbarState = rememberTaskbarState()
    val navBarState = rememberNavBarState()

    PointerTracker {
        navBarState.visible = it.x < 80
        taskbarState.visible = it.x > frameWindowScope.window.width - 140 && it.y < 66
    }

    CurrentScreen(navController)

    frameWindowScope.WindowDraggableArea {
        NavBar(navBarState, navController)
    }

    Taskbar(taskbarState, frameWindowScope)
}

fun main() = application {
    val state = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        transparent = true,
        state = state,
        title = "Lundify",
        icon = painterResource("logos/LundifyLogo.png"),
    ) {
        App(this)
    }
}
