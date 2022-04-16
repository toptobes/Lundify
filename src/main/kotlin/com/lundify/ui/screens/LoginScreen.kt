@file:Suppress("WrapUnaryOperator")
@file:OptIn(ExperimentalComposeUiApi::class)

package com.lundify.ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.PointerIconDefaults
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import com.lundify.ui.mainelements.RotatingLundifyLogo

@Composable
@Preview
fun LoginScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    Box(
        modifier = modifier.fillMaxSize().padding(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        val userName = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Column {
            LoginText()
            Spacer(Modifier.height(10.dp))
            UserNameTextField(userName)
            Spacer(Modifier.height(10.dp))
            PasswordTextField(password)
            Spacer(Modifier.height(20.dp))
            LoginButton(userName, password, navController)
        }
    }
}

@Composable
fun LoginButton(
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    navController: NavController
) {
    val isValid = username.isValidUserName() && password.isValidPassword()
            && username.value.text.isNotEmpty() && password.value.text.isNotEmpty()

    Row {
        Spacer(modifier = Modifier.width(220.dp))
        Box(
            modifier = Modifier.offset(y = -10.dp, x = 2.dp)
                .size(48.dp, 30.dp)
                .clip(CircleShape)
                .background(
                    if (isValid) Color(34, 197, 94, 220) else Color(34, 197, 94, 150)
                )
                .clickable {
                    if (isValid) {
                        navController.navigate(Screen.HomeScreen)
                    }
                }
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Login",
                tint = Color(255, 255, 255, 200),
                modifier = Modifier.size(48.dp, 30.dp)
            )
        }
    }
}

@Composable
private fun UserNameTextField(username: MutableState<TextFieldValue>) {
    LoginTextField(
        modifier = Modifier.height(50.dp),
        value = username.value,
        onValueChange = { if (username.value.text.length <= 16) username.value = it },
        label = {
            Text(
                text = if (username.isValidUserName()) "Username" else "Username (3-16 alphanumeric characters/underscores)",
                modifier = Modifier.absoluteOffset(y = 6.dp)
            )
        },
        leadingIcon = {
            LoginTextFieldIcon(icon = Icons.Filled.Person)
        },
        isValid = username.isValidUserName()
    )
}

private fun MutableState<TextFieldValue>.isValidUserName(): Boolean {
    return value.text.isBlank() || value.text.matches("\\w{3,16}".toRegex())
}

@Composable
private fun PasswordTextField(password: MutableState<TextFieldValue>) {

    var showPassword by remember { mutableStateOf(false) }

    LoginTextField(
        modifier = Modifier.height(50.dp),
        value = password.value,
        onValueChange = { if (password.value.text.length <= 32) password.value = it },
        label = {
            Text(
                text = if (password.isValidPassword()) "Password" else "Password (8-32 alphanumeric characters/underscores)",
                modifier = Modifier.absoluteOffset(y = 6.dp)
            )
        },
        leadingIcon = {
            LoginTextFieldIcon(
                icon = Icons.Filled.Lock,
                tint = if (showPassword) Color.White else Color(255, 255, 255, 150)
            ) {
                showPassword = !showPassword
                password.value = TextFieldValue("*" + password.value.text)
                password.value = TextFieldValue(password.value.text.substring(1))
            }
        },
        isValid = password.isValidPassword(),
        visualTransformation = {
            if (showPassword) {
                TransformedText(it, OffsetMapping.Identity)
            } else {
                TransformedText(AnnotatedString("*".repeat(32).take(it.length)), OffsetMapping.Identity)
            }
        }
    )
}

private fun MutableState<TextFieldValue>.isValidPassword(): Boolean {
    return value.text.isEmpty() || value.text.matches("\\w{8,32}".toRegex())
}


@Composable
private fun LoginText() {
    Row {
        RotatingLundifyLogo(
            size = 67.dp,
            bgColor = Color(34, 197, 94, 150),
            boxModifier = Modifier.offset(y = 16.dp),
            logoModifier = Modifier.offset(y = 1.25.dp)
        ) { }
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Login",
            style = TextStyle(
                fontSize = 40.sp,
                color = Color(34, 197, 94, 150)
            )
        )
    }
    Spacer(Modifier.height(5.dp))
    Row {
        Text(
            text = "Please enter your credentials, or ",
            style = TextStyle(
                fontSize = 10.sp,
                color = Color(34, 197, 94, 150)
            )
        )
        var createANewAccountColor by remember { mutableStateOf(Color(34, 197, 94, 150)) }
        Text(
            text = "create a new account",
            style = TextStyle(
                fontSize = 10.sp,
                color = createANewAccountColor,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.clickable { }
                .hoverable(
                    onEnter = { createANewAccountColor = Color(44, 102, 219, 255); true },
                    onExit = { createANewAccountColor = Color(34, 197, 94, 150); true }
                )
        )
    }
}

@Composable
private fun LoginTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isValid: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.clearFocusOnEsc(LocalFocusManager.current),
        label = label,
        leadingIcon = leadingIcon,
        isError = !isValid,
        visualTransformation = visualTransformation,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color(34, 197, 94, 150),
            unfocusedLabelColor = Color(255, 255, 255, 120),
            focusedLabelColor = Color(34, 197, 94, 150),
            unfocusedIndicatorColor = Color(34, 197, 94, 80),
            focusedIndicatorColor = Color(34, 197, 94, 150),
            textColor = Color(255, 255, 255, 200),
            backgroundColor = Color.Transparent,
            errorLabelColor = Color(191, 73, 73, 230),
            errorIndicatorColor = Color(191, 73, 73, 230),
            errorCursorColor = Color(191, 73, 73, 230),
        )
    )
}

@Composable
private fun LoginTextFieldIcon(
    icon: ImageVector = Icons.Filled.Done,
    tint: Color = Color(255, 255, 255, 200),
    onClick: () -> Unit = { }
) {
    Icon(
        icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .offset(y = 6.dp)
            .size(12.dp, 12.dp)
            .pointerHoverIcon(icon = PointerIconDefaults.Hand)
            .clickable(onClick = onClick)
    )
}

@Composable
private fun Modifier.clearFocusOnEsc(
    focusManager: FocusManager
) = onPreviewKeyEvent {
    if (it.key == Key.Escape && it.type == KeyEventType.KeyUp) {
        focusManager.clearFocus()
        true
    } else false
}


@Composable
private fun Modifier.hoverable(
    onEnter: () -> Boolean = { false },
    onExit: () -> Boolean = { false },
    onMove: (Offset) -> Boolean = { false },
) = this.pointerMoveFilter(onEnter = onEnter, onExit = onExit, onMove = onMove)
