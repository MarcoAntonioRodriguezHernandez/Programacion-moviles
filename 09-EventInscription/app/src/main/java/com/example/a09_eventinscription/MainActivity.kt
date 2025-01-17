package com.example.a09_eventinscription

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a09_eventinscription.ui.theme._09_EventInscriptionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _09_EventInscriptionTheme {
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
    var Name by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Asistencia by remember { mutableStateOf("Sí") } // Estado para el RadioButton

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Inscripción a un Evento",
                modifier = Modifier.padding(top = 10.dp, bottom = 6.dp),
                fontSize = 30.sp
            )
            Text(text = "Nombre")
            OutlinedTextField(
                value = Name,
                onValueChange = { Name = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(text = "Email")
            OutlinedTextField(
                value = Email,
                onValueChange = { Email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(text = "¿Asistirá al evento?")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = Asistencia == "Sí",
                    onClick = { Asistencia = "Sí" }
                )
                Text(text = "Sí", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = Asistencia == "No",
                    onClick = { Asistencia = "No" }
                )
                Text(text = "No", modifier = Modifier.padding(start = 4.dp))
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Inscribirse")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _09_EventInscriptionTheme {
        Greeting("Android")
    }
}