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

@Suppress("WrapUnaryOperator")
@Composable
fun Taskbar(state: TaskbarState) {
    val offset by animateOffsetAsState(
        targetValue = if (state.visible) Offset.Zero else Offset(0f, -100f),
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd,
    ) {
        Box(
            modifier = Modifier.offset(-10.dp, offset.y.dp + 10.dp)
                .size(120.dp, 46.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.DarkGray)
        ) {
        }
    }
}

@Composable
fun rememberTaskbarState() = rememberSaveable(TaskbarState.Saver()) {
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

    companion object {
        fun Saver() = Saver<TaskbarState, TaskbarState>(
            save = { it },
            restore = { it }
        )
    }
}