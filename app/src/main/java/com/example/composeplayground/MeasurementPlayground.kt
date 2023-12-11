package com.example.composeplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.utils.MeasureUnconstrainedViewSize
import com.example.composeplayground.utils.TargetedSizeType
import com.example.composeplayground.utils.TnlFilledSizedBox

@Composable
fun MeasurementPlayground() {
    var sliderPosition by remember { mutableStateOf(1f) }
    val scrollState = rememberScrollState()

    var componentHeight = 32.dp * sliderPosition
    var defaultBallSize = 35.dp * sliderPosition
    var defaultSpaceSize = 4.dp * sliderPosition
    var targetWidthValue = 230.dp
    var targetWidth = targetWidthValue * sliderPosition

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Size modifier = $sliderPosition")
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 1f..2f
        )
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(targetWidth))
                Spacer(
                    modifier = Modifier
                        .width(defaultSpaceSize)
                        .height(32.dp)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.width(defaultSpaceSize))
                Text(modifier = Modifier.wrapContentSize(unbounded = true), text = targetWidthValue.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))

            FilledSizedBoxExample(targetWidth, defaultBallSize, defaultSpaceSize)
            Spacer(modifier = Modifier.height(16.dp))
            MeasureUnconstrainedViewSizeExample(defaultBallSize, componentHeight)
        }
    }
}

@Composable
fun FilledSizedBoxExample(targetWidth: Dp, defaultBallSize: Dp, defaultSpaceSize: Dp) {
    Column {
        Text(
            text = "FilledSizedBox Example",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "They can fill the remaining space",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("TargetedSizeType = MinimumSize")
        TnlFilledSizedBox(targetedSizeType = TargetedSizeType.MinimumSize(targetWidth)) { ratioToTarget ->
            Balls(
                ballSize = defaultBallSize * ratioToTarget,
                spaceSize = defaultSpaceSize * ratioToTarget
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("TargetedSizeType = AlwaysFill")
        TnlFilledSizedBox(targetedSizeType = TargetedSizeType.AlwaysFill(targetWidth)) { ratioToTarget ->
            Balls(
                ballSize = defaultBallSize * ratioToTarget,
                spaceSize = defaultSpaceSize * ratioToTarget
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "There are spaces occupied in the way",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("TargetedSizeType = MinimumSize")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.background(Color.Yellow), text = "Occupied")
            TnlFilledSizedBox(targetedSizeType = TargetedSizeType.MinimumSize(targetWidth)) { ratioToTarget ->
                Balls(
                    ballSize = defaultBallSize * ratioToTarget,
                    spaceSize = defaultSpaceSize * ratioToTarget
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1f)) {
                TnlFilledSizedBox(targetedSizeType = TargetedSizeType.MinimumSize(targetWidth)) { ratioToTarget ->
                    Balls(
                        ballSize = defaultBallSize * ratioToTarget,
                        spaceSize = defaultSpaceSize * ratioToTarget
                    )
                }
            }
            Text(modifier = Modifier.background(Color.Yellow), text = "Occupied")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("TargetedSizeType = AlwaysFill")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.background(Color.Yellow), text = "Occupied")
            TnlFilledSizedBox(targetedSizeType = TargetedSizeType.AlwaysFill(targetWidth)) { ratioToTarget ->
                Balls(
                    ballSize = defaultBallSize * ratioToTarget,
                    spaceSize = defaultSpaceSize * ratioToTarget
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1f)) {
                TnlFilledSizedBox(targetedSizeType = TargetedSizeType.AlwaysFill(targetWidth)) { ratioToTarget ->
                    Balls(
                        ballSize = defaultBallSize * ratioToTarget,
                        spaceSize = defaultSpaceSize * ratioToTarget
                    )
                }
            }
            Text(modifier = Modifier.background(Color.Yellow), text = "Occupied")
        }
    }
}

@Composable
fun MeasureUnconstrainedViewSizeExample(
    defaultBallSize: Dp,
    componentHeight: Dp
) {
    Column {
        Text(
            text = "MeasureUnconstrainedViewSize Example",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Measure composable with fixed size",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .width(defaultBallSize * 2.25f)
                    .height(componentHeight)
                    .background(Color.Red)
            )
            Box(modifier = Modifier.weight(1f)) {
                MeasureUnconstrainedViewSize(viewToMeasure = {
                    Text("Hi, I'm the view to be measured")
                }) { width, height ->
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Hi, I'm the view to be measured")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true, align = Alignment.Center),
                            text = "width = $width",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true, align = Alignment.Center),
                            text = "height = $height",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width(defaultBallSize * 2.25f)
                    .height(componentHeight)
                    .background(Color.Red)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Measure composable with unknown size",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .width(defaultBallSize * 2.25f)
                    .height(componentHeight)
                    .background(Color.Red)
            )
            Box(modifier = Modifier.weight(1f)) {
                MeasureUnconstrainedViewSize(viewToMeasure = null) { maxWidth, _ ->
                    MeasureUnconstrainedViewSize(viewToMeasure = {
                        Text(
                            modifier = Modifier.widthIn(0.dp, maxWidth),
                            text = "Hi, I'm the view to be measured"
                        )
                    }) { width, height ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Hi, I'm the view to be measured")
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                modifier = Modifier
                                    .wrapContentSize(unbounded = true, align = Alignment.Center),
                                text = "width = $width",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            )
                            Text(
                                modifier = Modifier
                                    .wrapContentSize(unbounded = true, align = Alignment.Center),
                                text = "height = $height",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width(defaultBallSize * 2.25f)
                    .height(componentHeight)
                    .background(Color.Red)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Measure remaining space that can be occupied",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .width(defaultBallSize)
                    .height(componentHeight)
                    .background(Color.Red)
            )
            Box(modifier = Modifier.weight(1f)) {
                MeasureUnconstrainedViewSize(viewToMeasure = null) { width, _ ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Yellow),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true, align = Alignment.Center),
                            text = "width = $width",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width(defaultBallSize)
                    .height(componentHeight)
                    .background(Color.Red)
            )
        }
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun Balls(ballSize: Dp, spaceSize: Dp) {
    Row(horizontalArrangement = Arrangement.spacedBy(spaceSize)) {
        repeat(6) {
            Box(contentAlignment = Alignment.Center) {
                Box(
                    Modifier
                        .size(ballSize)
                        .background(color = Color.Cyan, shape = CircleShape)
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize(unbounded = true, align = Alignment.Center),
                    text = (it + 1).toString()
                )
            }
        }
    }
}