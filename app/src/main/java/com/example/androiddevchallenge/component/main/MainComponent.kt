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
package com.example.androiddevchallenge.component.main

import android.os.Parcelable
import android.view.View
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.Router
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.replaceCurrent
import com.arkivanov.decompose.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.example.androiddevchallenge.component.account.AccountComponent
import com.example.androiddevchallenge.component.account.ui.AccountUi
import com.example.androiddevchallenge.component.main.Main.Model
import com.example.androiddevchallenge.component.main.Main.Tab
import com.example.androiddevchallenge.component.positions.PositionsComponent
import com.example.androiddevchallenge.component.positions.ui.PositionsUi
import com.example.androiddevchallenge.utils.Content
import com.example.androiddevchallenge.utils.asContent
import kotlinx.parcelize.Parcelize

class MainComponent(
    private val componentContext: ComponentContext,
    private val onSetSystemUiVisibilityFlag: (mask: Int, value: Boolean) -> Unit
) : Main, ComponentContext by componentContext {

    private val router: Router<Child, Content> =
        router(
            initialConfiguration = Child.Account,
            componentFactory = ::childContent
        )

    override val routerState: Value<RouterState<*, Content>> = router.state

    override val model: Value<Model> =
        router.state.map { state ->
            Model(
                selectedTab = state.activeChild.configuration.toTab()
            )
        }

    override val positions: Content =
        PositionsComponent(
            componentContext = childContext(key = "positions")
        ).asContent { PositionsUi(it) }

    private fun childContent(child: Child, componentContext: ComponentContext): Content =
        when (child) {
            is Child.Account -> AccountComponent(componentContext = componentContext).asContent { AccountUi(it) }
        }

    override fun onTabClick(tab: Tab): Unit =
        when (tab) {
            Tab.ACCOUNT -> router.replaceCurrent(Child.Account)
            Tab.WATCH_LIST -> Unit
            Tab.PROFILE -> Unit
        }

    override fun onPositionsStateChanged(isExpanded: Boolean, isLightMode: Boolean) {
        onSetSystemUiVisibilityFlag(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR, isExpanded && isLightMode)
    }

    private fun Child.toTab(): Tab =
        when (this) {
            is Child.Account -> Tab.ACCOUNT
        }

    private sealed class Child : Parcelable {
        @Parcelize
        object Account : Child()
    }
}
