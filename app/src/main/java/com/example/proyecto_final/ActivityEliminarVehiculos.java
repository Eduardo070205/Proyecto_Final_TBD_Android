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
import entities.Vehiculo;

public class ActivityEliminarVehiculos extends Activity {

    EditText cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, cajaFechaFab, cajaFechaEntrada;

    Spinner spinnerIdM, spinnerTipo, spinnerEstado;

    ArrayList<Vehiculo> lista = null;

    Restablecer restablecer = new Restablecer();

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_vehiculos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaIdV = findViewById(R.id.cajaVehiculosIDVehiculoEliminar);
        cajaNumSerie = findViewById(R.id.cajaVehiculosNumSerieEliminar);
        cajaPrecio = findViewById(R.id.cajaVehiculosPrecioEliminar);
        cajaKilometraje = findViewById(R.id.cajaVehiculosKilometrajeEliminar);
        cajaFechaFab = findViewById(R.id.cajaVehiculosFechaFabriEliminar);
        cajaFechaEntrada = findViewById(R.id.cajaVehiculosFechaEntraEliminar);


        spinnerIdM = findViewById(R.id.spinnerVehiculosIDModeloEliminar);
        spinnerTipo = findViewById(R.id.spinnerVehiculosTipoEliminar);
        spinnerEstado = findViewById(R.id.spinnerVehiculosEstadoEliminar);

        new Thread(() -> {

            List<Integer> listaIds = bd.modeloDAO().obtenerModelos();

            List<String> idsString = new ArrayList<>();
            for (Integer id : listaIds) {
                idsString.add(String.valueOf(id));
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(ActivityEliminarVehiculos.this,
                                android.R.layout.simple_spinner_item,
                                idsString);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerIdM.setAdapter(adapter);
            });

        }).start();

        restablecer.restablecer(cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, cajaFechaFab, cajaFechaEntrada, spinnerIdM, spinnerTipo, spinnerEstado);

    }


    public void eliminarVehiculo(View v){

        if(!cajaIdV.getText().isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEliminarVehiculos.this);
            builder.setTitle("Eliminar");
            builder.setMessage("¿Estás seguro de eliminar este modelo?");

            builder.setPositiveButton("Sí", (dialog, which) -> {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int numFilas = bd.vehiculoDAO().eliminarVehiculoPorId(cajaIdV.getText().toString().toUpperCase());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (numFilas == 1){

                                    Toast.makeText(getBaseContext(), "Eliminación correcta", Toast.LENGTH_LONG).show();

                                    restablecer.restablecer(cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, cajaFechaFab, cajaFechaEntrada, spinnerIdM, spinnerTipo, spinnerEstado);


                                }else{

                                    Toast.makeText(getBaseContext(), "Eliminación Incorrecta", Toast.LENGTH_LONG).show();

                                }


                            }
                        });

                    }
                }).start();

            });

            builder.setNegativeButton("No", null);

            builder.show();

        }else{

            Toast.makeText(getBaseContext(), "No se encontró el vehículo, el campo esta vacío", Toast.LENGTH_LONG).show();

        }




    }


    public void buscarVehiculo(View v) {

        String valorBuscado;
        ArrayAdapter adapter;
        int posicion;

        if(!cajaIdV.getText().isEmpty()){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    lista = (ArrayList<Vehiculo>) bd.vehiculoDAO().buscarPorIdVehiculo(cajaIdV.getText().toString().toUpperCase());

                    runOnUiThread(() -> {

                        if (lista == null || lista.isEmpty()) {
                            Toast.makeText(getBaseContext(), "No se encontró el modelo", Toast.LENGTH_LONG).show();
                            return;
                        }

                        Vehiculo vehiculo = lista.get(0);

                        cajaNumSerie.setText(vehiculo.getNumero_serie());
                        cajaFechaFab.setText(vehiculo.getFecha_fabricacion());
                        cajaPrecio.setText(String.valueOf(vehiculo.getPrecio()));
                        cajaKilometraje.setText(String.valueOf(vehiculo.getKilometraje()));
                        cajaFechaEntrada.setText(vehiculo.getFecha_entrada());


                        String valorModelo = String.valueOf(vehiculo.getId_modelo_fk());
                        ArrayAdapter adapterModelo = (ArrayAdapter) spinnerIdM.getAdapter();
                        int posModelo = adapterModelo.getPosition(valorModelo);
                        spinnerIdM.setSelection(posModelo);

                        String valorTipo = String.valueOf(vehiculo.getTipo());
                        ArrayAdapter adapterTipo = (ArrayAdapter) spinnerTipo.getAdapter();
                        int posTipo = adapterTipo.getPosition(valorTipo);
                        spinnerTipo.setSelection(posTipo);

                        String valorEstado = String.valueOf(vehiculo.getEstado());
                        ArrayAdapter adapterEstado = (ArrayAdapter) spinnerEstado.getAdapter();
                        int posEstado = adapterEstado.getPosition(valorEstado);
                        spinnerEstado.setSelection(posEstado);



                    });

                }
            }).start();

        }else{

            Toast.makeText(getBaseContext(), "No se encontró el vehículo", Toast.LENGTH_LONG).show();


        }



    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEliminarVehiculos.this);
        builder.setTitle("Salir");
        builder.setMessage("¿Estás seguro de Cancelar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityEliminarVehiculos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null);

        builder.show();


    }

}
