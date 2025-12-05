package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import conexion.Autos_Amistosos_BD;
import custom_adapter.CustomAdapter;
import entities.Modelo;
import entities.Vehiculo;

public class ActivityListaModelos extends Activity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;



    ArrayList<Modelo> datos = null;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_modelos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        recyclerView = findViewById(R.id.recyclerModelos);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                datos = (ArrayList<Modelo>) bd.modeloDAO().mostrarTodos();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter = new CustomAdapter(datos);
                        recyclerView.setAdapter(adapter);

                    }
                });

            }
        }).start();


    }
}
