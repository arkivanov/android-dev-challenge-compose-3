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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.toUpperCaseLocalized

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: MyButtonStyle = MyButtonStyle.FILLED
) {
    Button(
        onClick = onClick,
        border = when (style) {
            MyButtonStyle.OUTLINED -> BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
            MyButtonStyle.FILLED -> null
        },
        colors = buttonColors(
            backgroundColor = when (style) {
                MyButtonStyle.FILLED -> MaterialTheme.colors.primary
                MyButtonStyle.OUTLINED -> Color.Transparent
            },
            contentColor = when (style) {
                MyButtonStyle.FILLED -> Color.Black
                MyButtonStyle.OUTLINED -> MaterialTheme.colors.primary
            }
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        modifier = modifier.heightIn(min = 48.dp)
    ) {
        Text(
            text = text.toUpperCaseLocalized()
        )
    }
}

@Preview
@Composable
private fun MyButtonFilledLightPreview() {
    MyTheme {
        MyButton(text = "My button", style = MyButtonStyle.FILLED, onClick = {})
    }
}

@Preview
@Composable
fun MyButtonOutlinedLightPreview() {
    MyTheme {
        MyButton(text = "My button", style = MyButtonStyle.OUTLINED, onClick = {})
    }
}

@Preview
@Composable
private fun MyButtonFilledDarkPreview() {
    MyTheme(darkTheme = true) {
        MyButton(text = "My button", style = MyButtonStyle.FILLED, onClick = {})
    }
}

@Preview
@Composable
fun MyButtonOutlinedDarkPreview() {
    MyTheme(darkTheme = true) {
        MyButton(text = "My button", style = MyButtonStyle.OUTLINED, onClick = {})
    }
}

enum class MyButtonStyle {
    FILLED, OUTLINED
}
