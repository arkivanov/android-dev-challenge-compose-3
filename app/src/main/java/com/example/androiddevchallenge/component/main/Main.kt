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

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.example.androiddevchallenge.utils.Content

interface Main {

    val routerState: Value<RouterState<*, Content>>
    val model: Value<Model>
    val positions: Content

    fun onTabClick(tab: Tab)
    fun onPositionsStateChanged(isExpanded: Boolean, isLightMode: Boolean)

    data class Model(
        val selectedTab: Tab = Tab.ACCOUNT
    )

    enum class Tab {
        ACCOUNT, WATCH_LIST, PROFILE
    }
}
