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
package com.example.androiddevchallenge.component.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.value.Value
import com.example.androiddevchallenge.component.main.ui.MainLightPreview
import com.example.androiddevchallenge.component.root.Root
import com.example.androiddevchallenge.utils.Content
import com.example.androiddevchallenge.utils.crossfade
import com.example.androiddevchallenge.utils.fakeRouterState

@Composable
fun RootUi(root: Root) {
    Children(routerState = root.routerState, animation = crossfade()) { child, _ ->
        child()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun RootPreview() {
    RootUi(RootComponentPreview)
}

object RootComponentPreview : Root {
    override val routerState: Value<RouterState<*, Content>> =
        fakeRouterState { MainLightPreview() }
}
