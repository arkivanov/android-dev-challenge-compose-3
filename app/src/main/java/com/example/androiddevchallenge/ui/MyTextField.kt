/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.background(Color.Transparent),
        leadingIcon = icon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = hint,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        placeholder = { Text(text = hint, style = MaterialTheme.typography.body1) },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.onSurface,
            leadingIconColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = MaterialTheme.colors.onSurface,
            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
        ),
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun MyTextFieldWithTextAndIconLightPreview() {
    MyTheme {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "Some text", onValueChange = {}, hint = "Some hint", icon = Icons.Outlined.Email)
        }
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun MyTextFieldWithTextLightPreview() {
    MyTheme {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "Some text", onValueChange = {}, hint = "Some hint")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun MyTextFieldWithoutTextLightPreview() {
    MyTheme {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "", onValueChange = {}, hint = "Some hint")
        }
    }
}

@Preview
@Composable
private fun MyTextFieldWithTextAndIconDarkPreview() {
    MyTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "Some text", onValueChange = {}, hint = "Some hint", icon = Icons.Outlined.Email)
        }
    }
}

@Preview
@Composable
private fun MyTextFieldWithTextDarkPreview() {
    MyTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "Some text", onValueChange = {}, hint = "Some hint")
        }
    }
}

@Preview
@Composable
private fun MyTextFieldWithoutTextDarkPreview() {
    MyTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(40.dp)) {
            MyTextField(value = "", onValueChange = {}, hint = "Some hint")
        }
    }
}
