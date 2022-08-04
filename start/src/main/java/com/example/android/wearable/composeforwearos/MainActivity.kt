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
package com.example.android.wearable.composeforwearos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.android.wearable.composeforwearos.theme.WearAppTheme

/**
 * This code lab is meant to help existing Compose developers get up to speed quickly on
 * Compose for Wear OS.
 *
 * The code lab walks through a majority of the simple composables for Wear OS (both similar to
 * existing mobile composables and new composables).
 *
 * It also covers more advanced composables like [ScalingLazyColumn] (Wear OS's version of
 * [LazyColumn]) and the Wear OS version of [Scaffold].
 *
 * Check out [this link](https://android-developers.googleblog.com/2021/10/compose-for-wear-os-now-in-developer.html)
 * for more information on Compose for Wear OS.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    WearAppTheme {

        val listState = rememberScalingLazyListState()
        Scaffold(
            timeText = { if (!listState.isScrollInProgress) TimeText() },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
        ) {
            val contentModifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            val iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)

            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                autoCentering = AutoCenteringParams(itemIndex = 0)
            ) {
                item { PhoneButton(contentModifier, iconModifier) }
                item { ShapeText(contentModifier) }
                item { MessageCard(contentModifier, iconModifier) }
                item { MeditationChip(contentModifier, iconModifier) }
                item { SoundToggleChip(contentModifier) }
            }
        }
    }
}

// Note: Preview in Android Studio doesn't support the round view yet (coming soon).
@Preview(
    widthDp = WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun WearAppPreview() {
    WearApp()
}
