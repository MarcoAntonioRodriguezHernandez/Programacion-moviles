package com.example.a25_fileupload;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewRuta;
    private ActivityResultLauncher<Intent> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del XML
        Button btnSeleccionarArchivo = findViewById(R.id.selec_archivo);
        textViewRuta = findViewById(R.id.textViewRuta);

        // Inicializar el selector de archivos
        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            String rutaArchivo = uri.toString();
                            textViewRuta.setText("Archivo seleccionado:\n" + rutaArchivo);
                            mostrarDialogo("Archivo Seleccionado", "Ruta: " + rutaArchivo);
                        }
                    }
                });

        // Evento click para seleccionar archivo
        btnSeleccionarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorDeArchivos();
            }
        });
    }

    // Método para abrir el selector de archivos
    private void abrirSelectorDeArchivos() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");  // Permite seleccionar cualquier tipo de archivo
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(intent);
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
