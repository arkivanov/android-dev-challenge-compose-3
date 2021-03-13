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
package com.example.androiddevchallenge.component.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.asState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.component.account.ui.AccountLightPreview
import com.example.androiddevchallenge.component.main.Main
import com.example.androiddevchallenge.component.main.Main.Model
import com.example.androiddevchallenge.component.main.Main.Tab
import com.example.androiddevchallenge.component.positions.ui.PositionsLightPreview
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.Content
import com.example.androiddevchallenge.utils.crossfade
import com.example.androiddevchallenge.utils.fakeRouterState
import com.example.androiddevchallenge.utils.toUpperCaseLocalized

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainUi(main: Main) {
    val model by main.model.asState()
    val isLightMode = MaterialTheme.colors.isLight
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val bottomSheetState =
        rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            confirmStateChange = {
                main.onPositionsStateChanged(
                    isExpanded = it == BottomSheetValue.Expanded,
                    isLightMode = isLightMode
                )
                true
            }
        )

    val snackbarHostState = remember { SnackbarHostState() }

    val bottomSheetScaffoldState =
        remember(isLightMode, drawerState, bottomSheetState, snackbarHostState) {
            BottomSheetScaffoldState(
                drawerState = drawerState,
                bottomSheetState = bottomSheetState,
                snackbarHostState = snackbarHostState
            )
        }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { main.positions() },
        modifier = Modifier.fillMaxSize(),
        sheetShape = RectangleShape,
        sheetPeekHeight = 64.dp,
        sheetElevation = 0.dp,
        content = {
            Column {
                TopNavBar(
                    selectedTab = model.selectedTab,
                    onClick = main::onTabClick
                )

                Children(routerState = main.routerState, animation = crossfade()) { child, _ ->
                    child()
                }
            }
        }
    )
}

@Composable
private fun TopNavBar(
    selectedTab: Tab,
    onClick: (Tab) -> Unit
) {
    Row {
        TabBarButton(
            text = stringResource(R.string.account),
            isSelected = selectedTab == Tab.ACCOUNT,
            onClick = { onClick(Tab.ACCOUNT) },
            modifier = Modifier.weight(1F)
        )

        TabBarButton(
            text = stringResource(R.string.watchlist),
            isSelected = selectedTab == Tab.WATCH_LIST,
            onClick = { onClick(Tab.WATCH_LIST) },
            modifier = Modifier.weight(1F)
        )

        TabBarButton(
            text = stringResource(R.string.profile),
            isSelected = selectedTab == Tab.PROFILE,
            onClick = { onClick(Tab.PROFILE) },
            modifier = Modifier.weight(1F)
        )
    }
}

@Composable
private fun TabBarButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val alpha = if (isSelected) ContentAlpha.high else ContentAlpha.medium
    CompositionLocalProvider(LocalContentAlpha provides alpha) {
        Text(
            text = text.toUpperCaseLocalized(),
            modifier = modifier
                .clickable(onClick = onClick)
                .paddingFromBaseline(top = 64.dp, bottom = 8.dp),
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun MainLightPreview() {
    MyTheme {
        MainUi(MainComponentPreview)
    }
}

@Preview
@Composable
fun MainDarkPreview() {
    MyTheme(darkTheme = true) {
        MainUi(MainComponentPreview)
    }
}

object MainComponentPreview : Main {
    override val routerState: Value<RouterState<*, Content>> =
        fakeRouterState { AccountLightPreview() }

    override val model: Value<Model> = MutableValue(Model())
    override val positions: Content = { PositionsLightPreview() }

    override fun onTabClick(tab: Tab) {}
    override fun onPositionsStateChanged(isExpanded: Boolean, isLightMode: Boolean) {}
}
