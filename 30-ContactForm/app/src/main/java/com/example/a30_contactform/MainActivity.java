package com.example.a30_contactform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNombre = findViewById(R.id.editTextText10);
        EditText editTextTelefono = findViewById(R.id.editTextPhone);
        EditText editTextMensaje = findViewById(R.id.editTextTextMultiLine3);
        Button buttonEnviar = findViewById(R.id.button10);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString().trim();
                String telefono = editTextTelefono.getText().toString().trim();
                String mensaje = editTextMensaje.getText().toString().trim();

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(telefono) || TextUtils.isEmpty(mensaje)) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                    return;
                }

                if (telefono.length() < 7) {
                    mostrarDialogo("Error", "El número de teléfono debe tener al menos 7 dígitos.");
                    return;
                }

                String mensajeFinal = "Nombre: " + nombre +
                        "\nTeléfono: " + telefono +
                        "\nMensaje:\n" + mensaje;

                mostrarDialogo("Mensaje Enviado", mensajeFinal);
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
