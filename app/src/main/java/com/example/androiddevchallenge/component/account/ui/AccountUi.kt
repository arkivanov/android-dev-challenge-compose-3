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
package com.example.androiddevchallenge.component.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.component.account.Account
import com.example.androiddevchallenge.component.account.Account.Model
import com.example.androiddevchallenge.ui.MyButton
import com.example.androiddevchallenge.ui.MyChip
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun AccountUi(account: Account) {
    val model by account.model

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            balance = model.balance,
            changedToday = model.changedToday,
            isChangedTodayPositive = model.isChangedTodayPositive,
            onTransactClick = account::onTransactClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        ChipBar()

        Image(
            painter = painterResource(R.drawable.home_illos),
            contentDescription = "Main chart",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Header(
    balance: String,
    changedToday: String,
    isChangedTodayPositive: Boolean,
    onTransactClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(R.string.balance),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 32.dp, bottom = 8.dp)
        )

        Text(
            text = balance,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 48.dp, bottom = 24.dp)
        )

        Text(
            text = stringResource(R.string.changed_today, changedToday),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 16.dp, bottom = 32.dp),
            color = if (isChangedTodayPositive) MyTheme.customColors.custom1 else MyTheme.customColors.custom2
        )

        MyButton(
            text = stringResource(R.string.transact),
            onClick = onTransactClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ChipBar() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        MyChip(
            text = stringResource(R.string.week),
            onClick = {},
            icon = Icons.Default.ExpandMore
        )

        Spacer(modifier = Modifier.width(8.dp))

        MyChip(text = stringResource(R.string.etfs), onClick = {})

        Spacer(modifier = Modifier.width(8.dp))

        MyChip(text = stringResource(R.string.stocks), onClick = {})

        Spacer(modifier = Modifier.width(8.dp))

        MyChip(text = stringResource(R.string.funds), onClick = {})

        Spacer(modifier = Modifier.width(8.dp))

        MyChip(text = stringResource(R.string.extra), onClick = {})

        Spacer(modifier = Modifier.width(8.dp))

        MyChip(text = stringResource(R.string.extra), onClick = {})
    }
}

@Preview
@Composable
fun AccountLightPreview() {
    MyTheme {
        AccountUi(AccountComponentPreview)
    }
}

@Preview
@Composable
fun AccountDarkPreview() {
    MyTheme(darkTheme = true) {
        AccountUi(AccountComponentPreview)
    }
}

object AccountComponentPreview : Account {
    override val model: State<Model> =
        mutableStateOf(
            Model(
                balance = "$73,587.01",
                changedToday = "+412.35",
                isChangedTodayPositive = true
            )
        )

    override fun onTransactClick() {}
}
