package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import conexion.Autos_Amistosos_BD;
import custom_adapter.CustomAdapter;
import entities.Modelo;

public class ActivityListaModelos extends Activity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;



    ArrayList<Modelo> datos = null;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_modelos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        recyclerView = findViewById(R.id.recyclerVehiculos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String filtro = getIntent().getStringExtra("filtro");
        String valor = getIntent().getStringExtra("valor");

        cargarDatos(filtro, valor);
    }

    private void cargarDatos(String filtro, String valor) {

        new Thread(() -> {

            switch (filtro) {

                case "id":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .mostrarPorId(Integer.parseInt(valor));
                    break;

                case "nombre":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorNombre(valor);
                    break;

                case "fabricante":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorFabricante(valor);
                    break;

                case "puertas":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorPuertas(Integer.parseInt(valor));
                    break;

                case "pasajeros":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorPasajeros(Integer.parseInt(valor));
                    break;

                case "pais":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorPais(valor);
                    break;

                case "cilindros":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorCilindros(Integer.parseInt(valor));
                    break;

                case "anio":
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .buscarPorAnio(Integer.parseInt(valor));
                    break;

                case "todos" :
                    datos = (ArrayList<Modelo>) bd.modeloDAO()
                            .mostrarTodos();
                    break;
            }

            runOnUiThread(() -> {
                adapter = new CustomAdapter(datos);
                recyclerView.setAdapter(adapter);
            });

        }).start();
    }

}
