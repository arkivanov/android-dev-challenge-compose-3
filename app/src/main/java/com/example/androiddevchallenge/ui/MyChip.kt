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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun MyChip(text: String, onClick: () -> Unit, icon: ImageVector? = null) {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(50),
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.onBackground,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)
    ) {
        Row(
            modifier = Modifier.clickable(onClick = onClick).padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp)
            )

            if (icon != null) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun MyChipWithIconLightPreview() {
    MyTheme {
        MyChip(text = "My chip", onClick = {}, icon = Icons.Default.ExpandMore)
    }
}

@Preview
@Composable
fun MyChipWithoutIconLightPreview() {
    MyTheme {
        MyChip(text = "My chip", onClick = {})
    }
}

@Preview
@Composable
fun MyChipWithIconDarkPreview() {
    MyTheme(darkTheme = true) {
        MyChip(text = "My chip", onClick = {}, icon = Icons.Default.ExpandMore)
    }
}

@Preview
@Composable
fun MyChipWithoutIconDarkPreview() {
    MyTheme(darkTheme = true) {
        MyChip(text = "My chip", onClick = {})
    }
}
