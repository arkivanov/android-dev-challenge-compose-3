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
package com.example.androiddevchallenge.component.positions

import com.example.androiddevchallenge.R

class PositionsDataSource {

    fun getPositions(): List<Positions.Item> =
        getList() + getList()

    private fun getList(): List<Positions.Item> =
        listOf(
            Positions.Item(
                title = "ALK",
                description = "Alaska Air Group, Inc.",
                price = "$7,918",
                priceChange = "-0.54%",
                isPriceChangePositive = false,
                chartResId = R.drawable.home_alk
            ),
            Positions.Item(
                title = "BA",
                description = "Boeing Co.",
                price = "$1,293",
                priceChange = "+4.18%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_ba
            ),
            Positions.Item(
                title = "DAL",
                description = "Delta Airlines Inc.",
                price = "893.50",
                priceChange = "-0.54%",
                isPriceChangePositive = false,
                chartResId = R.drawable.home_dal
            ),
            Positions.Item(
                title = "EXPE",
                description = "Expedia Group Inc.",
                price = "$12,301",
                priceChange = "+2.51%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_exp
            ),
            Positions.Item(
                title = "EADSY",
                description = "Airbus SE",
                price = "$12,301",
                priceChange = "+1.38%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_ba
            ),
            Positions.Item(
                title = "JBLU",
                description = "Jetblue Airways Corp.",
                price = "$8,521",
                priceChange = "+1.56%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_jblu
            ),
            Positions.Item(
                title = "MAR",
                description = "Marriott International Inc.",
                price = "$521",
                priceChange = "+2.75%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_mar
            ),
            Positions.Item(
                title = "CCL",
                description = "Carnival Corp",
                price = "$5,481",
                priceChange = "+0.14%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_ccl
            ),
            Positions.Item(
                title = "RCL",
                description = "Royal Caribbean Cruises",
                price = "$9,184",
                priceChange = "+1.69%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_rcl
            ),
            Positions.Item(
                title = "TRVL",
                description = "Travelocity Inc.",
                price = "$654",
                priceChange = "+3.23%",
                isPriceChangePositive = true,
                chartResId = R.drawable.home_trvl
            )
        )
}
