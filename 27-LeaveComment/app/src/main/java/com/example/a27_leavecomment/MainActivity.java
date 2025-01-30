package com.example.a27_leavecomment;

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

        EditText editTextNombre = findViewById(R.id.editTextText6);
        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextComentario = findViewById(R.id.editTextTextMultiLine2);
        Button buttonEnviar = findViewById(R.id.button7);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String comentario = editTextComentario.getText().toString().trim();

                if (nombre.isEmpty() || email.isEmpty() || comentario.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                } else {
                    String mensaje = "Nombre: " + nombre +
                            "\nEmail: " + email +
                            "\nComentario:\n" + comentario;

                    mostrarDialogo("Comentario Enviado", mensaje);
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
