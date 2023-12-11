package com.example.composeplayground.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureUnconstrainedViewSize(
    viewToMeasure: (@Composable () -> Unit)?,
    content: @Composable (measuredWidth: Dp, measuredHeight: Dp) -> Unit,
) {
    SubcomposeLayout { constraints ->
        val (measuredWidth, measuredHeight) = viewToMeasure?.let { viewToMeasure ->
            subcompose("viewToMeasure", viewToMeasure)[0]
                .measure(Constraints()).let {
                    listOf(it.width.toDp(), it.height.toDp())
                }
        } ?: listOf(constraints.maxWidth.toDp(), constraints.maxHeight.toDp())

        val contentPlaceable = subcompose("content") {
            content(measuredWidth, measuredHeight)
        }[0].measure(constraints)
        layout(contentPlaceable.width, contentPlaceable.height) {
            contentPlaceable.place(0, 0)
        }
    }
}

