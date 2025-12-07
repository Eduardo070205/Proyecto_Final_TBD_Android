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

public class ActivityModificarModelos extends Activity {

    EditText cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    Restablecer restablecer = new Restablecer();

    ArrayList<Modelo> lista = null;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_modelos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaid = findViewById(R.id.cajaModelosIDModeloModificar);

        cajaNombre = findViewById(R.id.cajaModelosNombreModificar);

        cajaFabricante = findViewById(R.id.cajaModelosFabricanteModificar);

        cajaPuertas = findViewById(R.id.cajaModelosPuertasModificar);

        cajaPeso = findViewById(R.id.cajaModelosPesoModificar);

        cajaPasajeros = findViewById(R.id.cajaModelosPasajerosModificar);

        cajaColor = findViewById(R.id.cajaModelosColorModificar);

        cajaPais = findViewById(R.id.cajaModelosPaisModificar);

        spinnerAnio = findViewById(R.id.spinnerModelosAnioModificar);

        spinnerCilindros = findViewById(R.id.spinnerModelosCilindrosModificar);


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

    public void cargarDatos(View v){

        String valorBuscado;
        ArrayAdapter adapter;
        int posicion;

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

    }

    public void actualizarModelo(View v){

        int id = Integer.parseInt(cajaid.getText().toString());

        String nombre = cajaNombre.getText().toString().toUpperCase();

        int anio = Integer.parseInt(spinnerAnio.getSelectedItem().toString());

        String fabricante = cajaFabricante.getText().toString().toUpperCase();

        int cilindros = Integer.parseInt(spinnerCilindros.getSelectedItem().toString());

        int puertas = Integer.parseInt(cajaPuertas.getText().toString());

        double peso =  Double.valueOf(cajaPeso.getText().toString());

        int pasajeros = Integer.parseInt(cajaPasajeros.getText().toString());

        String color = cajaColor.getText().toString();

        String pais = cajaPais.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityModificarModelos.this);
        builder.setTitle("Actualizar");
        builder.setMessage("¿Estás seguro de actualizar este modelo?");

        builder.setPositiveButton("Sí", (dialog, which) -> {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    int agregado = bd.modeloDAO().actualizarModeloPorId(id,

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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (agregado == 1){

                                Toast.makeText(getBaseContext(), "Actualización correcta", Toast.LENGTH_LONG).show();

                                restablecer.restablecer(cajaid, cajaNombre, cajaFabricante, cajaPuertas, cajaPeso, cajaPasajeros, cajaColor, cajaPais, spinnerAnio, spinnerCilindros);


                            }else{

                                Toast.makeText(getBaseContext(), "Actualización Incorrecta", Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }).start();

        });

        builder.setNegativeButton("No", null);

        builder.show();



    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityModificarModelos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de cancelar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityModificarModelos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null);

        builder.show();


    }

}
