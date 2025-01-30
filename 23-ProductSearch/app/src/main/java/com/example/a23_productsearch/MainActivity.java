package com.example.a23_productsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextProducto = findViewById(R.id.editTextText);
        Spinner spinnerCategoria = findViewById(R.id.spinner1);
        Button buttonBuscar = findViewById(R.id.button);

        String[] categorias = {"Electrónica", "Ropa", "Hogar", "Deportes", "Alimentos", "Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spinnerCategoria.setAdapter(adapter);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String producto = editTextProducto.getText().toString().trim();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                if (producto.isEmpty()) {
                    mostrarDialogo("Error", "Por favor, ingrese un nombre de producto.");
                } else {
                    String mensaje = "Producto: " + producto + "\nCategoría: " + categoria;

                    mostrarDialogo("Búsqueda de Producto", mensaje);
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
