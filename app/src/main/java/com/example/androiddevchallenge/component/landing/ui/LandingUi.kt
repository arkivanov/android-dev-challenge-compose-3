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
package com.example.androiddevchallenge.component.landing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.component.landing.Landing
import com.example.androiddevchallenge.ui.MyButton
import com.example.androiddevchallenge.ui.MyButtonStyle
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LandingUi(landing: Landing) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.welcome_bg),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo image",
            modifier = Modifier.align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            MyButton(
                text = stringResource(R.string.get_started),
                onClick = landing::onGetStartedClicked,
                modifier = Modifier.weight(1F)
            )

            Spacer(modifier = Modifier.width(8.dp))

            MyButton(
                text = stringResource(R.string.log_in),
                onClick = landing::onLoginClick,
                modifier = Modifier.weight(1F),
                style = MyButtonStyle.OUTLINED
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LandingPreview() {
    MyTheme {
        LandingUi(LandingComponentPreview)
    }
}

object LandingComponentPreview : Landing {
    override fun onGetStartedClicked() {}
    override fun onLoginClick() {}
}
