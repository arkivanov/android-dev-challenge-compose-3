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
package com.example.androiddevchallenge.component.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.component.login.Login
import com.example.androiddevchallenge.component.login.Login.Model
import com.example.androiddevchallenge.ui.MyButton
import com.example.androiddevchallenge.ui.MyTextField
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoginUi(login: Login) {
    val model by login.model

    Column(
        modifier = Modifier
            .fillMaxSize()
            .run { if (MaterialTheme.colors.isLight) background(MaterialTheme.colors.surface) else this }
    ) {
        Header()

        Spacer(modifier = Modifier.height(40.dp))

        Controls(
            email = model.email,
            password = model.password,
            onEmailChanged = login::onEmailChanged,
            onPasswordChanged = login::onPasswordChanged,
            onLoginClick = login::onLoginClick
        )
    }
}

@Composable
private fun Header() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.login_bg),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(R.string.welcome_back),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Controls(
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        val passwordFocusRequester = remember { FocusRequester() }

        MyTextField(
            value = email,
            onValueChange = onEmailChanged,
            hint = stringResource(R.string.email_address),
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
        )

        Spacer(modifier = Modifier.height(8.dp))

        MyTextField(
            value = password,
            onValueChange = onPasswordChanged,
            hint = stringResource(R.string.password),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            icon = Icons.Outlined.Password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onLoginClick() }),
        )

        Spacer(modifier = Modifier.height(16.dp))

        MyButton(
            text = stringResource(R.string.log_in),
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LoginLightPreview() {
    MyTheme {
        LoginUi(LoginComponentPreview)
    }
}

@Preview
@Composable
fun LoginDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginUi(LoginComponentPreview)
    }
}

object LoginComponentPreview : Login {
    override val model: State<Model> =
        mutableStateOf(
            Model(
                email = "name@domain.com",
                password = "password"
            )
        )

    override fun onEmailChanged(email: String) {}
    override fun onPasswordChanged(password: String) {}
    override fun onLoginClick() {}
}
