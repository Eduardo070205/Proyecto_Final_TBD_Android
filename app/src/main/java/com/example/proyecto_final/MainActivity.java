package com.example.proyecto_final;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText cajaUsuario, cajaContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cajaUsuario = findViewById(R.id.cajaIniciarSesionUsuario);

        cajaContra = findViewById(R.id.cajaInicioSesionContraseña);

    }

    public void abrirActivity(View v) {

        if(cajaUsuario.getText().toString().equals("eduardo") && cajaContra.getText().toString().equals("Eduardo10")){

            Intent i = null;

            if (v.getId() == R.id.btnIniciarSesion) {

                i = new Intent(this, ActivityMenu.class);

            }


            startActivity(i);

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Usuario o contraseña incorrectos");
            builder.setPositiveButton("OK", null);
            builder.show();

        }



    }

    public void restaurar(View v){

        cajaUsuario.setText("");
        cajaContra.setText("");

    }

}