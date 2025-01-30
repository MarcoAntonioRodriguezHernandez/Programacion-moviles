package com.example.a29_eventinscription;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNombre = findViewById(R.id.editTextText8);
        EditText editTextEmail = findViewById(R.id.editTextText9);
        RadioButton radioSi = findViewById(R.id.radioButton);
        RadioButton radioNo = findViewById(R.id.radioButton2);
        Button buttonInscribirse = findViewById(R.id.button9);

        buttonInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String asistencia = "";

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(email)) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                    return;
                }

                if (radioSi.isChecked()) {
                    asistencia = "Sí, asistirá al evento.";
                } else if (radioNo.isChecked()) {
                    asistencia = "No asistirá al evento.";
                } else {
                    mostrarDialogo("Error", "Por favor, selecciona si asistirás al evento.");
                    return;
                }

                String mensaje = "Nombre: " + nombre +
                        "\nEmail: " + email +
                        "\nAsistencia: " + asistencia;

                mostrarDialogo("Inscripción Completa", mensaje);
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
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
