package com.example.proyecto_final;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Recursos.Restablecer;
import adapters.ModeloAdapter;
import adapters.VehiculoAdapter;
import conexion.Autos_Amistosos_BD;
import adapters.CustomAdapter;
import entities.Modelo;
import entities.Vehiculo;
public class ActivityListaVehiculos extends Activity {

    RecyclerView recyclerView;
    VehiculoAdapter adapter;

    ArrayList<Vehiculo> datos = new ArrayList<>();

    Restablecer restablecer = new Restablecer();

    EditText cajaFiltro;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);

        recyclerView = findViewById(R.id.recyclerVehiculos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        String tipo = getIntent().getStringExtra("tipo");
        String valor = getIntent().getStringExtra("valor");

        cajaFiltro = findViewById(R.id.cajaVehiculosIdLista);
        cajaFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        datos = (ArrayList<Vehiculo>) bd.vehiculoDAO().buscarPorIdVehiculo(cajaFiltro.getText().toString().toUpperCase());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter = new VehiculoAdapter(datos);
                                recyclerView.setAdapter(adapter);

                            }
                        });

                    }
                }).start();

            }
        });

        new Thread(() -> {

            if (tipo == null || tipo.equals("todos")) {
                datos = (ArrayList<Vehiculo>) bd.vehiculoDAO().mostrarTodos();

            } else {

                switch (tipo) {

                    case "idVehiculo":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorIdVehiculo(valor);
                        break;

                    case "numeroSerie":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorNumeroSerie(valor);
                        break;

                    case "idModelo":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorIdModelo(Integer.parseInt(valor));
                        break;

                    case "precio":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorPrecio(Double.parseDouble(valor));
                        break;

                    case "kilometraje":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorKilometraje(Integer.parseInt(valor));
                        break;

                    case "tipo":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorTipo(valor);
                        break;

                    case "estado":
                        datos = (ArrayList<Vehiculo>)
                                bd.vehiculoDAO().buscarPorEstado(valor);
                        break;
                }
            }

            runOnUiThread(() -> {
                adapter = new VehiculoAdapter(datos);
                recyclerView.setAdapter(adapter);
            });

        }).start();

        restablecer.restablecer(cajaFiltro);

    }
    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListaVehiculos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de Cerrar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityListaVehiculos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null);

        builder.show();


    }

    public void otraConsulta(View v){

        Intent i = new Intent(ActivityListaVehiculos.this, ActivityConsultasVehiculos.class);

        startActivity(i);

    }

}