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
package com.example.androiddevchallenge.component.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.example.androiddevchallenge.component.account.Account.Model

class AccountComponent(
    private val componentContext: ComponentContext
) : Account, ComponentContext by componentContext {

    override val model: State<Model> =
        mutableStateOf(
            Model(
                balance = "$73,587.01",
                changedToday = "+412.35",
                isChangedTodayPositive = true
            )
        )

    override fun onTransactClick() {
    }
}
