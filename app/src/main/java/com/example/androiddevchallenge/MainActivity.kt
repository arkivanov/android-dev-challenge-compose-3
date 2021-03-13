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
package com.example.androiddevchallenge

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.rememberRootComponent
import com.example.androiddevchallenge.component.root.Root
import com.example.androiddevchallenge.component.root.RootComponent
import com.example.androiddevchallenge.component.root.ui.RootComponentPreview
import com.example.androiddevchallenge.component.root.ui.RootUi
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT

        setContent {
            MyTheme(darkTheme = true) {
                MyApp(
                    rememberRootComponent { componentContext ->
                        RootComponent(
                            componentContext = componentContext,
                            onSetSystemUiVisibilityFlag = ::onSetSystemUiVisibilityFlag
                        )
                    }
                )
            }
        }
    }

    private fun onSetSystemUiVisibilityFlag(mask: Int, value: Boolean) {
        window.decorView.apply {
            systemUiVisibility =
                if (value) {
                    systemUiVisibility or mask
                } else {
                    systemUiVisibility and mask.inv()
                }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(root: Root) {
    Surface(color = MaterialTheme.colors.background) {
        RootUi(root = root)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(RootComponentPreview)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(RootComponentPreview)
    }
}
