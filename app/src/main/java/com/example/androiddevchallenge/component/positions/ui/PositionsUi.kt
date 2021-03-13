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
package com.example.androiddevchallenge.component.positions.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.example.androiddevchallenge.component.positions.Positions
import com.example.androiddevchallenge.component.positions.Positions.Item
import com.example.androiddevchallenge.component.positions.Positions.Model
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PositionsUi(positions: Positions) {
    val model by positions.model

    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onSurface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            Text(
                text = stringResource(R.string.positions),
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 24.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle1
            )

            LazyColumn {
                items(model.items) {
                    Column {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                        )
                        Position(item = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun Position(item: Item) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = item.price,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 0.dp),
                style = MaterialTheme.typography.body1
            )

            Text(
                text = item.priceChange,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body1,
                color = if (item.isPriceChangePositive) MyTheme.customColors.custom1 else MyTheme.customColors.custom2
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        Column(modifier = Modifier.weight(1F)) {
            Text(
                text = item.title,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 0.dp),
                style = MaterialTheme.typography.h3
            )

            Text(
                text = item.description,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body1
            )
        }

        Image(
            painter = painterResource(item.chartResId),
            contentDescription = "Item chart",
            modifier = Modifier.height(24.dp),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PositionsLightPreview() {
    MyTheme {
        PositionsUi(PositionsComponentPreview)
    }
}

@Preview
@Composable
fun PositionsDarkPreview() {
    MyTheme(darkTheme = true) {
        PositionsUi(PositionsComponentPreview)
    }
}

object PositionsComponentPreview : Positions {
    override val model: State<Model> =
        mutableStateOf(
            Model(
                items = List(10) {
                    Item(
                        title = "TITLE $it",
                        description = "Some description here $it",
                        price = "$7,918",
                        priceChange = if (it % 2 == 0) "+4.18%" else "-0.54%",
                        isPriceChangePositive = it % 2 == 0,
                        chartResId = R.drawable.home_alk
                    )
                }
            )
        )
}
