package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipsCalculator {
                TipsTotal(sales = 10f, percentage = 0.05f)
            }
        }
    }
}

@Composable
fun TipsCalculator(component: @Composable () -> Unit) {
    TipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            component()
        }
    }
}

@Composable
fun TipsTotal(sales: Float, percentage: Float, split: Int = 1) {
    val total : Float = sales * split / split
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
//        Text(text = "Total Per Person")
        Text(text = "${String.format("$%.2f", total)}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipsCalculator {
        TipsTotal(sales = 10f, percentage = 0.05f)
    }
}