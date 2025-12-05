package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import conexion.Autos_Amistosos_BD;

public class ActivityEliminarModelos extends Activity {

    EditText cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas_modelos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaid = findViewById(R.id.cajaModelosIDModeloBajas);
        cajaNombre = findViewById(R.id.cajaModelosNombreBajas);
        cajaFabricante = findViewById(R.id.cajaModelosFabricanteEliminar);
        cajaPuertas = findViewById(R.id.cajaModelosPuertasBajas);
        cajaPeso = findViewById(R.id.cajaModelosPesoBajas);
        cajaColor = findViewById(R.id.cajaModelosColorBajas);
        cajaPasajeros = findViewById(R.id.cajaModelosPasajerosBajas);
        cajaPais = findViewById(R.id.cajaModelosPaisBajas);

        spinnerCilindros = findViewById(R.id.spinnerModelosCilindrosBajas);
        spinnerAnio = findViewById(R.id.spinnerModelosAnioBajas);


        List<String> listaAnios = new ArrayList<>();

        for (int i = 2025; i >= 1900; i--) {
            listaAnios.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaAnios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnio.setAdapter(adapter);

    }

    public void eliminarModelo(View v){

        if(v.getId() == R.id.btnmodelos)

    }

}
