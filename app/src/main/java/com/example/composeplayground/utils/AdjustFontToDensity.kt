package com.example.composeplayground.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun adjustFontToDensity(widthOfContainerDp: Dp, reductionRate: Dp) = with(LocalDensity.current) {
    (widthOfContainerDp / reductionRate).toSp()
}
