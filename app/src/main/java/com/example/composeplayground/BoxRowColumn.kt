package com.example.composeplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.composeplayground.theme.ComposePreviewTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(25.dp))
                .padding(8.dp), text = "hello"
        )
        Text(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(25.dp))
                .padding(8.dp), text = "Chips"
        )
        repeat(20){
            Text(
                modifier = Modifier
                    .background(Color.Gray, RoundedCornerShape(25.dp))
                    .padding(8.dp), text = (it+1).toString()
            )
        }
        Text(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(25.dp))
                .padding(8.dp), text = "Longer text"
        )
        Text(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(25.dp))
                .padding(8.dp), text = "Another example"
        )
        Text(
            modifier = Modifier
                .background(Color.Gray, RoundedCornerShape(25.dp))
                .padding(8.dp), text = "I have no more idea for names"
        )
    }
}

@Preview
@Composable
fun FlowRowExamplePreview() {
    FlowRowExample()
}

@Composable
fun RowExample() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Composable
fun ColumnExample() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Composable
fun BoxExample() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
        ) {

        }
    }
}

@Composable
fun ColumnWithZIndex() {
    Column {
        Box(
            Modifier
                .size(100.dp)
                .zIndex(1f)
                .background(Color.Red, shape = CircleShape)
        )
        Text(
            modifier = Modifier
                .offset(y = -10.dp)
                .zIndex(2f)
                .background(Color.White), text = "123456"
        )
    }
}

@Composable
fun BoxWithoutZIndex() {
    var heightDp by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Box {
        Box(modifier = Modifier.width(140.dp)) {
            Text(
                modifier = Modifier
                    .padding()
                    .background(Color.White)
                    .onGloballyPositioned {
                        heightDp = with(localDensity) { it.size.height.toDp() } - 10.dp
                    },
                text = "1234564561451323524856165156165465151535"
            )
        }
        Box(
            Modifier
                .size(100.dp)
                .padding(heightDp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}

@Composable
fun BoxWithoutZIndex2() {
    Box(contentAlignment = Alignment.BottomStart) {
        Box(modifier = Modifier.width(50.dp)) {
            Text(
                modifier = Modifier
                    .padding(bottom = 90.dp)
                    .background(Color.White),
                text = "12345645614513235248561651561654616516516484168416415151535"
            )
        }
        Box(
            Modifier
                .size(100.dp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}

@Composable
fun Elements(content: @Composable RowScope.() -> Unit) {
    Row {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun BoxRowColumn() {
    ComposePreviewTheme {
        Row {
            Elements {
                Box(
                    Modifier
                        .background(Color.Red)
                        .weight(1f)
                        .height(15.dp)
                )
                Box(
                    Modifier
                        .background(Color.Blue)
                        .weight(3f)
                        .height(15.dp)
                )
            }
        }
    }
}