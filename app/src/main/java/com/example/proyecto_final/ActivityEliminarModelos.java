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

public class ActivityEliminarModelos extends Activity {

    EditText cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    Restablecer restablecer = new Restablecer();

    ArrayList<Modelo> lista = null;

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

        restablecer.restablecer(cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais, spinnerAnio, spinnerCilindros);

    }

    public void eliminarModelo(View v) {

        if(!cajaid.getText().isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEliminarModelos.this);
            builder.setTitle("Eliminar");
            builder.setMessage("¿Estás seguro de eliminar este modelo?");

            builder.setPositiveButton("Sí", (dialog, which) -> {


                new Thread(() -> {

                    int numFilas = bd.modeloDAO()
                            .eliminarModeloPirId(Integer.parseInt(cajaid.getText().toString()));

                    runOnUiThread(() -> {
                        if (numFilas == 1) {
                            Toast.makeText(ActivityEliminarModelos.this, "Eliminación correcta", Toast.LENGTH_LONG).show();

                            restablecer.restablecer(cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais, spinnerAnio, spinnerCilindros);


                        } else {
                            Toast.makeText(ActivityEliminarModelos.this, "No se encontró el modelo", Toast.LENGTH_LONG).show();
                        }
                    });

                }).start();

            });

            builder.setNegativeButton("No", null);

            builder.show();

        }else{

            Toast.makeText(getBaseContext(), "No se encontró el Modelo, el capo esta vacío", Toast.LENGTH_LONG).show();

        }

    }


    public void buscarModelo(View v) {

        String valorBuscado;
        ArrayAdapter adapter;
        int posicion;

        if(!cajaid.getText().isEmpty()){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    lista = (ArrayList<Modelo>) bd.modeloDAO().mostrarPorId(Integer.parseInt(cajaid.getText().toString()));

                    runOnUiThread(() -> {

                        if (lista == null || lista.isEmpty()) {
                            Toast.makeText(getBaseContext(), "No se encontró el modelo", Toast.LENGTH_LONG).show();
                            return;
                        }

                        Modelo m = lista.get(0);

                        cajaNombre.setText(m.getNombre_modelo());
                        cajaFabricante.setText(m.getFabricante());
                        cajaPuertas.setText(String.valueOf(m.getNumero_puertas()));
                        cajaPeso.setText(String.valueOf(m.getPeso()));
                        cajaPasajeros.setText(String.valueOf(m.getCapacidad_pasajeros()));
                        cajaColor.setText(m.getColor_base());
                        cajaPais.setText(m.getPais_fabricacion());


                        String valorAnio = String.valueOf(m.getAnio_modelo());
                        ArrayAdapter adapterAnio = (ArrayAdapter) spinnerAnio.getAdapter();
                        int posAnio = adapterAnio.getPosition(valorAnio);
                        spinnerAnio.setSelection(posAnio);


                        String valorCil = String.valueOf(m.getNumero_cilindros());
                        ArrayAdapter adapterCil = (ArrayAdapter) spinnerCilindros.getAdapter();
                        int posCil = adapterCil.getPosition(valorCil);
                        spinnerCilindros.setSelection(posCil);

                    });

                }
            }).start();

        }else{

            Toast.makeText(getBaseContext(), "No se encontró el modelo", Toast.LENGTH_LONG).show();


        }




    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEliminarModelos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de Salir?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityEliminarModelos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null); // Solo cierra el diálogo

        builder.show();


    }
}
