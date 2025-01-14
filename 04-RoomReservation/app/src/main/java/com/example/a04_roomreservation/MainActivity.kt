package com.example.a04_roomreservation

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a04_roomreservation.ui.theme._04RoomReservationTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _04RoomReservationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        onReserve = { entryDate, exitDate, people ->
                            Toast.makeText(
                                this,
                                "Reserva realizada para $people persona(s) del $entryDate al $exitDate",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    onReserve: (String, String, Int) -> Unit
) {
    var entryDate by remember { mutableStateOf(LocalDate.now()) }
    var exitDate by remember { mutableStateOf(LocalDate.now().plusDays(1)) }
    var peopleCount by remember { mutableStateOf("1") }

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reserva de Hotel",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Fecha de entrada: ${entryDate.format(dateFormatter)}",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {
                showDatePickerDialog(
                    context = context,
                    initialDate = entryDate,
                    onDateSelected = { newDate -> entryDate = newDate }
                )
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Seleccionar fecha de entrada")
        }

        Text(
            text = "Fecha de salida: ${exitDate.format(dateFormatter)}",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {
                showDatePickerDialog(
                    context = context,
                    initialDate = exitDate,
                    onDateSelected = { newDate -> exitDate = newDate }
                )
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Seleccionar fecha de salida")
        }

        OutlinedTextField(
            value = peopleCount,
            onValueChange = { newValue ->
                if (newValue.toIntOrNull() != null || newValue.isEmpty()) {
                    peopleCount = newValue
                }
            },
            label = { Text("Cantidad de personas") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Button(
            onClick = {
                val people = try {
                    peopleCount.toInt()
                } catch (e: NumberFormatException) {
                    1
                }
                onReserve(
                    entryDate.format(dateFormatter),
                    exitDate.format(dateFormatter),
                    people
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reservar")
        }
    }
}

fun showDatePickerDialog(
    context: android.content.Context,
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(initialDate.year, initialDate.monthValue - 1, initialDate.dayOfMonth)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _04RoomReservationTheme {
        Greeting { _, _, _ -> }
    }
}
