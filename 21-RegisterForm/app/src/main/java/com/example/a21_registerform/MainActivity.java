package com.example.a21_registerform;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editTextNombre = findViewById(R.id.editTextText);
        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        Button buttonRegistrarse = findViewById(R.id.button);

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los EditText
                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validar que no estén vacíos
                if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                } else {
                    // Construir el mensaje con los datos ingresados
                    String mensaje = "Nombre: " + nombre + "\nEmail: " + email + "\nContraseña: " + password;
                    mostrarDialogo("Datos Ingresados", mensaje);
                }
            }
        });
    }
    private void mostrarDialogo(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cierra el pop-up al presionar el botón
            }
        });
        builder.show();
    }
}