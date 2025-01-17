package com.example.a05_fileupload

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a05_fileupload.ui.theme._05FileUploadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectFileLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedFilePath = uri.toString()
            }
        }

        setContent {
            _05FileUploadTheme {
                var fileName by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Formulario de Registro",
                        modifier = Modifier.padding(innerPadding),
                        fileName = fileName,
                        onFileSelect = {
                            selectFileLauncher.launch("*/*")
                        },
                        onFileNameChange = { newFileName ->
                            fileName = newFileName
                        }
                    )
                }
            }
        }
    }
    companion object {
        var selectedFilePath: String = ""
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    fileName: String,
    onFileSelect: () -> Unit,
    onFileNameChange: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Subir Archivos", modifier = Modifier.padding(8.dp))
            Text(text = "Seleccione un archivo", modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = fileName,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Button(
                onClick = {
                    onFileSelect()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Subir", color = Color.White)
            }
        }
    }
    LaunchedEffect(MainActivity.selectedFilePath) {
        if (MainActivity.selectedFilePath.isNotEmpty()) {
            onFileNameChange(MainActivity.selectedFilePath.substringAfterLast("/")) // Extrae el nombre del archivo
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _05FileUploadTheme {
        Greeting(
            name = "Android",
            fileName = "",
            onFileSelect = {},
            onFileNameChange = {}
        )
    }
}