package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.theme.ComposePreviewTheme
import com.example.composeplayground.utils.MeasureUnconstrainedViewSize
import com.example.composeplayground.utils.conditional

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePreviewTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    //Greeting("Android")
                    MeasurementPlayground()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ConditionalExample() {
    val condition = true
    Box(
        Modifier
            .size(15.dp)
            .background(Color.Blue)
            .run {
                takeUnless { condition } ?: padding(20.dp)
            }
            .conditional(condition = condition, trueBlock = { padding(20.dp) })
            .clickable {  }
    )
}


@Composable
fun UnboundedText() {
    val isContentUnbounded = true
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 70.dp)
            .background(Color.Blue)
            .padding(20.dp)
            .background(Color.Red)
    ) {
        Text(
            modifier = Modifier.run {
                this.takeUnless { isContentUnbounded }
                    ?: wrapContentWidth(unbounded = true, align = Alignment.CenterHorizontally)
            },
            text = "hellooooooo"
        )
    }
}

@Composable
fun MeasureWidth() {
    val maxLine = 10
    MeasureUnconstrainedViewSize(viewToMeasure = { Text("Line $maxLine") }) { componentWidth, _ ->
        Column {
            List(maxLine + 1) { index ->
                Row {
                    Text(
                        modifier = Modifier.width(componentWidth),
                        text = "Line $index"
                    )
                    Box(
                        Modifier
                            .size(15.dp)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePreviewTheme {
        //TargetWidth()
        //MeasureWidth()
        UnboundedText()
    }
}
