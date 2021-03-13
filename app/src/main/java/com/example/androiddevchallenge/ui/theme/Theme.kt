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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val DarkColorPalette = darkColors(
    primary = yellow,
    onPrimary = gray900,
    background = gray900,
    onBackground = white,
    surface = gray700,
    onSurface = white
)

private val LightColorPalette = lightColors(
    primary = yellow,
    onPrimary = gray900,
    background = purple,
    onBackground = white,
    surface = white,
    onSurface = gray900
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val customColors = if (darkTheme) LightCustomColors else DarkCustomColors

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes
    ) {
        CompositionLocalProvider(
            LocalCustomColors provides customColors,
            LocalContentColor provides MaterialTheme.colors.onBackground,
        ) {
            content()
        }
    }
}

object MyTheme {

    val customColors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColors.current
}
