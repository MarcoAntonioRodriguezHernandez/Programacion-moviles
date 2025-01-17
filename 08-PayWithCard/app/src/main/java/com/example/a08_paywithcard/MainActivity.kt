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
import com.example.a08_paywithcard.ui.theme._08_PayWithCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _08_PayWithCardTheme {
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
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pagar con tarjeta",
                modifier = Modifier.padding(top = 10.dp, bottom = 6.dp),
                fontSize = 30.sp
            )
            Text(text = "Número de tarjeta")
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(text = "Fecha de expiración")
            OutlinedTextField(
                value = expirationDate,
                onValueChange = { newValue ->
                    if (newValue.length <= 5 && newValue.all { it.isDigit() || it == '/' }) {
                        expirationDate = formatExpirationDate(newValue)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = ExpirationDateVisualTransformation()
            )

            Text(text = "CVV")
            OutlinedTextField(value = cvv, onValueChange = { cvv = it })

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Pagar")
            }
        }
    }
}

/**
 * Función para formatear la fecha de expiración automáticamente en el formato MM/YY
 */
fun formatExpirationDate(input: String): String {
    val cleaned = input.filter { it.isDigit() } // Solo permite números
    val maxLength = 4
    val trimmed = if (cleaned.length > maxLength) cleaned.substring(0, maxLength) else cleaned

    return when {
        trimmed.length > 2 -> "${trimmed.substring(0, 2)}/${trimmed.substring(2)}"
        else -> trimmed
    }
}

/**
 * VisualTransformation personalizada para mostrar la fecha como MM/YY
 */
class ExpirationDateVisualTransformation : VisualTransformation {
    override fun filter(text: androidx.compose.ui.text.AnnotatedString): TransformedText {
        val formattedText = formatExpirationDate(text.text)

        return TransformedText(
            text = androidx.compose.ui.text.AnnotatedString(formattedText),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = offset
                override fun transformedToOriginal(offset: Int): Int = offset
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _08_PayWithCardTheme {
        Greeting("Android")
    }
}
