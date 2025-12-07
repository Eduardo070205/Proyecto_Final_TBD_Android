package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.VehiculoAdapter;
import conexion.Autos_Amistosos_BD;
import adapters.CustomAdapter;
import entities.Vehiculo;
public class ActivityListaVehiculos extends Activity {

    RecyclerView recyclerView;
    VehiculoAdapter adapter;

    ArrayList<Vehiculo> datos = new ArrayList<>();

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
    }
}