package com.example.proyecto_final;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Recursos.Restablecer;
import conexion.Autos_Amistosos_BD;
import entities.Modelo;

public class ActivityAltasModelos extends Activity {


    EditText cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    Autos_Amistosos_BD bd;

    Restablecer restablecer = new Restablecer();

    long numFilas = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_modelos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaNombre = findViewById(R.id.cajaModelosNombreModeloAgregar);
        cajaFabricante = findViewById(R.id.cajaModelosFabricanteAgregar);
        cajaPuertas = findViewById(R.id.cajaModelosPuertasAgregar);
        cajaPeso = findViewById(R.id.cajaModelosPesoAgregar);
        cajaColor = findViewById(R.id.cajaModelosColorAgregar);
        cajaPasajeros = findViewById(R.id.cajaModelosPasajerosAgregar);
        cajaPais = findViewById(R.id.cajaModelosPaisAgregar);

        spinnerCilindros = findViewById(R.id.spinnerModelosCilindrosAgregar);
        spinnerAnio = findViewById(R.id.spinnerModelosAnioModeloAgregar);


        List<String> listaAnios = new ArrayList<>();

        for (int i = 2025; i >= 1900; i--) {
            listaAnios.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaAnios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnio.setAdapter(adapter);

    }



    public void agregarModelo(View v){

        if(v.getId() == R.id.btnModelosAgregarAgregar){

            String nombre = cajaNombre.getText().toString().toUpperCase();

            int anio = Integer.parseInt(spinnerAnio.getSelectedItem().toString());

            String fabricante = cajaFabricante.getText().toString().toUpperCase();

            int cilindros = Integer.parseInt(spinnerCilindros.getSelectedItem().toString());

            int puertas = Integer.parseInt(cajaPuertas.getText().toString());

            double peso = Integer.parseInt(cajaPeso.getText().toString());

            int pasajeros = Integer.parseInt(cajaPasajeros.getText().toString());

            String color = cajaColor.getText().toString();

            String pais = cajaPais.getText().toString();

            Modelo modelo = new Modelo(

                    nombre,
                    anio,
                    fabricante,
                    cilindros,
                    puertas,
                    peso,
                    pasajeros,
                    color,
                    pais

            );


            new Thread(new Runnable() {
                @Override
                public void run() {

                    numFilas = 0;

                    numFilas = bd.modeloDAO().agregarModelo(modelo);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if(numFilas == 1){

                                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasModelos.this);
                                builder.setTitle("Aviso");
                                builder.setMessage("Se agrego el Modelo Correctamente.");
                                builder.setPositiveButton("OK", null);
                                builder.show();

                                restablecer.restablecer(spinnerAnio, spinnerCilindros, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais);


                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasModelos.this);
                                builder.setTitle("Aviso");
                                builder.setMessage("No se pudo agregar el Modelo.");
                                builder.setPositiveButton("OK", null);
                                builder.show();


                            }


                        }
                    });


                }
            }).start();

        }

    }

    public void retablecer(View v){

        restablecer.restablecer(spinnerAnio, spinnerCilindros, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais);

    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasModelos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de continuar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                restablecer.restablecer(spinnerAnio, spinnerCilindros, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais);

                Intent i = new Intent(ActivityAltasModelos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null); // Solo cierra el diálogo

        builder.show();


    }


}
