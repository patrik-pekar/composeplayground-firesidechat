package com.example.composeplayground.utils

import androidx.compose.ui.Modifier

inline fun Modifier.conditional(
    condition: Boolean,
    trueBlock: Modifier.() -> Modifier,
    falseBlock: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
    then(trueBlock(Modifier))
} else {
    then(falseBlock(Modifier))
}
