package com.example.a02_satisfactionsurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a02_satisfactionsurvey.ui.theme._02SatisfactionSurveyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _02SatisfactionSurveyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Encuesta de satisfacción",
                modifier = Modifier.padding(16.dp),
                fontSize = 30.sp
            )
            Text(text = "Nombre:", modifier = modifier)
            OutlinedTextField(value = "", onValueChange = {})
            var sliderPosition by remember { mutableStateOf(1f) }
            Text(text = "Nivel de Satisfaccion", modifier = Modifier.padding(bottom = 8.dp))
            Text(text = sliderPosition.toInt().toString())
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 1f..5f,
                steps = 3,
            )
            Text(text = "Comentarios", modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(value = "", onValueChange = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _02SatisfactionSurveyTheme {
        Greeting("Android")
    }
}