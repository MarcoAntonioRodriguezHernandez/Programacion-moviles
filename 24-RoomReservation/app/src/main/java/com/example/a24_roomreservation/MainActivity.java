package com.example.a24_roomreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del XML
        EditText editTextFechaEntrada = findViewById(R.id.editTextDate);
        EditText editTextFechaSalida = findViewById(R.id.editTextDate2);
        EditText editTextNumeroPersonas = findViewById(R.id.editTextNumber2);
        Button buttonReservar = findViewById(R.id.button3);

        // Evento click para el botón "Reservar"
        buttonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados
                String fechaEntrada = editTextFechaEntrada.getText().toString().trim();
                String fechaSalida = editTextFechaSalida.getText().toString().trim();
                String numeroPersonas = editTextNumeroPersonas.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (fechaEntrada.isEmpty() || fechaSalida.isEmpty() || numeroPersonas.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                } else {
                    // Construcción del mensaje con los datos ingresados
                    String mensaje = "Fecha de Entrada: " + fechaEntrada +
                            "\nFecha de Salida: " + fechaSalida +
                            "\nNúmero de Personas: " + numeroPersonas;

                    // Mostrar los datos en un AlertDialog (Pop-up)
                    mostrarDialogo("Reserva de Habitación", mensaje);
                }
            }
        });
    }

    // Método para mostrar un AlertDialog (Pop-up)
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
