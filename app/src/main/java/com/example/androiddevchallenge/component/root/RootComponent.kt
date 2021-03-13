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
package com.example.androiddevchallenge.component.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.Router
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.value.Value
import com.example.androiddevchallenge.component.landing.LandingComponent
import com.example.androiddevchallenge.component.landing.ui.LandingUi
import com.example.androiddevchallenge.component.login.LoginComponent
import com.example.androiddevchallenge.component.login.ui.LoginUi
import com.example.androiddevchallenge.component.main.MainComponent
import com.example.androiddevchallenge.component.main.ui.MainUi
import com.example.androiddevchallenge.utils.Content
import com.example.androiddevchallenge.utils.asContent
import kotlinx.parcelize.Parcelize

class RootComponent(
    private val componentContext: ComponentContext,
    private val onSetSystemUiVisibilityFlag: (mask: Int, value: Boolean) -> Unit
) : Root, ComponentContext by componentContext {

    private val router: Router<Child, Content> =
        router(
            initialConfiguration = Child.Landing,
            handleBackButton = true,
            componentFactory = ::childContent
        )

    override val routerState: Value<RouterState<*, Content>> = router.state

    private fun childContent(child: Child, componentContext: ComponentContext): Content =
        when (child) {
            is Child.Landing ->
                LandingComponent(
                    componentContext = componentContext,
                    onGetStarted = {},
                    onLogin = { router.push(Child.Login) }
                ).asContent { LandingUi(it) }

            is Child.Login ->
                LoginComponent(
                    componentContext = componentContext,
                    onLoggedIn = { router.navigate { listOf(Child.Main) } }
                ).asContent { LoginUi(it) }

            is Child.Main ->
                MainComponent(
                    componentContext = componentContext,
                    onSetSystemUiVisibilityFlag = onSetSystemUiVisibilityFlag
                ).asContent { MainUi(it) }
        }

    private sealed class Child : Parcelable {
        @Parcelize
        object Landing : Child()

        @Parcelize
        object Login : Child()

        @Parcelize
        object Main : Child()
    }
}
