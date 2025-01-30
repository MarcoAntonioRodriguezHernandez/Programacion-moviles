package com.example.a28_paywithcard;

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

        EditText editTextNumeroTarjeta = findViewById(R.id.editTextNumberSigned);
        EditText editTextFechaExpiracion = findViewById(R.id.editTextNumber3);
        EditText editTextCVV = findViewById(R.id.editTextNumber4);
        Button buttonPagar = findViewById(R.id.button8);

        buttonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroTarjeta = editTextNumeroTarjeta.getText().toString().trim();
                String fechaExpiracion = editTextFechaExpiracion.getText().toString().trim();
                String cvv = editTextCVV.getText().toString().trim();

                if (TextUtils.isEmpty(numeroTarjeta) || TextUtils.isEmpty(fechaExpiracion) || TextUtils.isEmpty(cvv)) {
                    mostrarDialogo("Error", "Por favor, completa todos los campos.");
                    return;
                }

                if (numeroTarjeta.length() != 16) {
                    mostrarDialogo("Error", "El número de tarjeta debe tener 16 dígitos.");
                    return;
                }

                if (cvv.length() < 3 || cvv.length() > 4) {
                    mostrarDialogo("Error", "El CVV debe tener 3 o 4 dígitos.");
                    return;
                }

                String mensaje = "Número de Tarjeta: **** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4) +
                        "\nFecha de Expiración: " + fechaExpiracion +
                        "\nCVV: ***";

                mostrarDialogo("Pago Realizado", mensaje);
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
