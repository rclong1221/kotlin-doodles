package com.example.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.components.InputField
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import com.example.tipcalculator.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(component: @Composable () -> Unit) {
    TipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            component()
        }
    }
}

@Preview
@Composable
fun Header(total: String = "0.00") {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "$${total}",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent() {
    Column(modifier = Modifier.padding(all = 12.dp)) {
        BillForm(

        ) {
                billAmt -> Log.d("AMT","MainContent: ${billAmt.toInt() * 100}")
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range: IntRange = 1..100,
//    splitState: MutableState<Int>,
//    tipState: MutableState<Float>,
//    totalState: MutableState<Float>,
    onValueChange: (String) -> Unit = {}
) {
    val totalPerPersonState = remember {
        mutableStateOf("")
    }
    val totalState = remember {
        mutableStateOf("")
    }
    val (splitState, setSplitState) = remember {
        mutableStateOf(1)
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Header(totalPerPersonState.value)
    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            )
        ) {
        Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (totalState.value.trim().isNotEmpty()) return@KeyboardActions
                    onValueChange(totalState.value.trim())
                    keyboardController?.hide()
                }
            )
            if (totalState.value.trim().isNotEmpty()) {
                val tipAmount : String = calcTip((totalState.value).toFloat(), sliderPositionState.value)

                Row(modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.Start) {
                    Text(text = "Split", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(imageVector = Icons.Default.Remove, onClick = { if (splitState > 1) setSplitState(splitState - 1) })
                        Text(
                            text = "${splitState}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp)
                            )
                        RoundIconButton(imageVector = Icons.Default.Add, onClick = { setSplitState(splitState + 1) })
                    }
                }
                Row(modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(200.dp))
                    Text(text = "$${tipAmount}", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${(sliderPositionState.value * 100).toInt()}%")
                    Spacer(modifier = Modifier.height(14.dp))
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal -> sliderPositionState.value = newVal},
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        steps = 10,
                        onValueChangeFinished = { totalPerPersonState.value = calcTotal(totalState.value, tipAmount, splitState) }
                    )
                }
            }
        }
    }
}

fun calcTotal(value: String, tipAmount: String, split: Int) : String {
    return "%.2f".format(value.toFloat() + tipAmount.toFloat() / split)

}

fun calcTip(sales: Float, percentage: Float) : String {
    return "%.2f".format(sales * percentage)
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MainContent()
    }
}