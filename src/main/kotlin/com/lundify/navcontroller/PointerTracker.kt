@file:OptIn(ExperimentalComposeUiApi::class)

package com.lundify.navcontroller

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@Composable
fun PointerTracker(vararg onPointerEvent: (Offset) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
            .onPointerEvent(PointerEventType.Move) {
                val position = it.changes.first().position
                onPointerEvent.forEach {
                    it(position)
                }
            }
    )
}