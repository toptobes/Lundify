package com.lundify.ui.mainelements

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import kotlin.system.exitProcess

@Suppress("WrapUnaryOperator")
@Composable
fun Taskbar(state: TaskbarState, fws: FrameWindowScope) {
    val offset by animateOffsetAsState(
        targetValue = if (state.visible) Offset.Zero else Offset(0f, -100f),
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd,
    ) {
        fws.WindowDraggableArea {
            Row(
                modifier = Modifier.offset(-10.dp, offset.y.dp + 10.dp)
                    .size(120.dp, 46.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.DarkGray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                var greenButtonAlpha by rememberSaveable { mutableStateOf(140) }

                TitleBarButton(Color(34, 197, 94, greenButtonAlpha)) {
                    fws.window.isAlwaysOnTop = !fws.window.isAlwaysOnTop
                    greenButtonAlpha = if (fws.window.isAlwaysOnTop) 200 else 140
                }

                TitleBarButton(Color(251, 191, 36, 140)) {
                    fws.window.isMinimized = true
                }

                TitleBarButton(Color(190, 18, 60, 140)) {
                    exitProcess(0)
                }
            }
        }
    }
}

@Composable
private fun TitleBarButton(color: Color, onClick: () -> Unit) = Button(
    onClick = {
        onClick()
    },
    colors = ButtonDefaults.buttonColors(
        backgroundColor = color,
    ),
    modifier = Modifier.size(30.dp),
    elevation = null,
    shape = CircleShape
) { }


@Composable
fun rememberTaskbarState() = rememberSaveable {
    TaskbarState()
}

class TaskbarState(
    private val _visible: MutableState<Boolean> = mutableStateOf(false)
) {
    var visible: Boolean
        get() = _visible.value
        set(toggle) {
            _visible.value = toggle
        }
}