package com.example.a26_preferencesurvey;

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

        EditText editTextColorFavorito = findViewById(R.id.editTextText4);
        EditText editTextComidaFavorita = findViewById(R.id.editTextText5);
        Button buttonEnviar = findViewById(R.id.button6);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados
                String colorFavorito = editTextColorFavorito.getText().toString().trim();
                String comidaFavorita = editTextComidaFavorita.getText().toString().trim();

                if (colorFavorito.isEmpty() || comidaFavorita.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                } else {
                    String mensaje = "Color Favorito: " + colorFavorito +
                            "\nComida Favorita: " + comidaFavorita;

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
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
