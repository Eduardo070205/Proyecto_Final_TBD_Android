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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Recursos.Restablecer;
import adapters.ModeloAdapter;
import conexion.Autos_Amistosos_BD;
import entities.Modelo;

public class ActivityListaModelos extends Activity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    EditText cajaFiltro;

    Restablecer restablecer = new Restablecer();

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
                        datos = (ArrayList<Modelo>) bd.modeloDAO().buscarPorNombre(cajaFiltro.getText().toString().toUpperCase());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                adapter = new ModeloAdapter(datos);
                                recyclerView.setAdapter(adapter);

                            }
                        });

                    }
                }).start();

            }
        });

        restablecer.restablecer(cajaFiltro);

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
                adapter = new ModeloAdapter(datos);
                recyclerView.setAdapter(adapter);
            });

        }).start();
    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListaModelos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de Cerrar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityListaModelos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null);

        builder.show();


    }

    public void otraConsulta(View v){

        Intent i = new Intent(ActivityListaModelos.this, ActivityConsultasModelos.class);

        startActivity(i);

    }

}
