package com.example.a22_satisfactionsurvey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del XML
        EditText editTextNombre = findViewById(R.id.editTextText);
        RatingBar ratingBarSatisfaccion = findViewById(R.id.ratingBar);
        EditText editTextComentarios = findViewById(R.id.editTextTextMultiLine);
        Button buttonEnviar = findViewById(R.id.button);

        // Evento click para el botón "Enviar"
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados
                String nombre = editTextNombre.getText().toString().trim();
                float nivelSatisfaccion = ratingBarSatisfaccion.getRating();
                String comentarios = editTextComentarios.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || nivelSatisfaccion == 0 || comentarios.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos antes de enviar.");
                } else {
                    // Construcción del mensaje con los datos ingresados
                    String mensaje = "Nombre: " + nombre +
                            "\nNivel de Satisfacción: " + nivelSatisfaccion + " estrellas" +
                            "\nComentarios: " + comentarios;

                    // Mostrar los datos en un AlertDialog (Pop-up)
                    mostrarDialogo("Encuesta Enviada", mensaje);
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
                dialog.dismiss(); // Cierra el pop-up al presionar "Aceptar"
            }
        });
        builder.show();
    }
}
