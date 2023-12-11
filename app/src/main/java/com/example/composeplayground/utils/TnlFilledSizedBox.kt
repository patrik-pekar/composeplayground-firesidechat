package com.example.composeplayground.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * TargetedSizeType.MinimumSize scenario:
 * It tries to make its content to be placed in a targetedWidth size of Space.
 * If there is no space with targetedWidth size, then it will allocate the maximum it can.
 * The content gets the maxSize / targetWidth ratio in lambda param in this case, otherwise it's 1.
 *
 * TargetedSizeType.AlwaysFill scenario:
 * It always allocates the maximum space it can, and provides its content maxSize / targetWidth ratio in lambda param.
 */
@Composable
fun TnlFilledSizedBox(
    targetedSizeType: TargetedSizeType,
    modifier: Modifier = Modifier,
    content: @Composable (ratioToTarget: Float) -> Unit
) {
    MeasureUnconstrainedViewSize(
        viewToMeasure = null
    ) { maxWidth, _ ->
        val (minWidth, ratioToTarget) = when (targetedSizeType) {
            is TargetedSizeType.AlwaysFill -> {
                maxWidth to maxWidth / targetedSizeType.targetWidth
            }
            is TargetedSizeType.MinimumSize -> {
                if (maxWidth < targetedSizeType.targetWidth) {
                    maxWidth to maxWidth / targetedSizeType.targetWidth
                } else {
                    targetedSizeType.targetWidth to 1f
                }
            }
        }

        Box(modifier.width(minWidth)) {
            content(ratioToTarget)
        }
    }
}

sealed class TargetedSizeType {
    abstract val targetWidth: Dp

    data class AlwaysFill(override val targetWidth: Dp) : TargetedSizeType()
    data class MinimumSize(override val targetWidth: Dp) : TargetedSizeType()
}
