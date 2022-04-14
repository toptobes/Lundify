// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.lundify.navcontroller.PointerTracker
import com.lundify.ui.mainelements.Background
import com.lundify.ui.mainelements.NavBar
import com.lundify.ui.mainelements.rememberNavBarState
import com.lundify.ui.screens.Screen
import navcontroller.rememberNavController

@Composable
@Preview
fun App(frameWindowScope: FrameWindowScope) {

    val navBarState = rememberNavBarState()
    val navController by rememberNavController(Screen.HomeScreen)

    PointerTracker({
        navBarState.visible = it.x < 80
    })

    Background(navController)

    frameWindowScope.WindowDraggableArea {
        NavBar(navBarState, navController)
    }
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
