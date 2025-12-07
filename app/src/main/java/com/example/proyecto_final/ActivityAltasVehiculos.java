package com.example.proyecto_final;

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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Recursos.Restablecer;
import conexion.Autos_Amistosos_BD;
import entities.Modelo;
import entities.Vehiculo;

public class ActivityAltasVehiculos extends AppCompatActivity {

    TextInputEditText txtFecha;

    EditText cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerIdM, spinnerTipo, spinnerEstado;

    Restablecer restablecer = new Restablecer();

    long numFilas = 0;

    Autos_Amistosos_BD bd;

    String fechaHoy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        Date hoy = new Date();
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        fechaHoy = formato.format(hoy);

        txtFecha = findViewById(R.id.txtVehiculosFechaFabricacion);

        cajaIdV = findViewById(R.id.cajaVehiculosIDVehiculoAgregar);
        cajaNumSerie = findViewById(R.id.cajaVehiculosNumeroSerieAgregar);
        cajaPrecio = findViewById(R.id.cajaVehiculosPrecioAgregar);
        cajaKilometraje = findViewById(R.id.cajaVehiculosKilometrajeAgregar);

        spinnerIdM = findViewById(R.id.spinnerVehiculosIDModeloAgregar);
        spinnerTipo = findViewById(R.id.spinnerVehiculosTipoAgregar);
        spinnerEstado = findViewById(R.id.spinnerVehiculosEstadoAgregar);



        txtFecha.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Selecciona una fecha")
                            .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                txtFecha.setText(datePicker.getHeaderText());
            });

            datePicker.show(getSupportFragmentManager(), "datePicker");
        });



        new Thread(() -> {

            List<Integer> listaIds = bd.modeloDAO().obtenerModelos();

            List<String> idsString = new ArrayList<>();
            for (Integer id : listaIds) {
                idsString.add(String.valueOf(id));
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(ActivityAltasVehiculos.this,
                                android.R.layout.simple_spinner_item,
                                idsString);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerIdM.setAdapter(adapter);
            });

        }).start();


    }

    public void agregarVehiculo(View v){

        String id = cajaIdV.getText().toString();
        String numSerie = cajaNumSerie.getText().toString();
        int idModelo = Integer.parseInt(spinnerIdM.getSelectedItem().toString());
        String fechaFab = txtFecha.getText().toString();
        double precio = Double.parseDouble(cajaPrecio.getText().toString());
        int kilometraje = Integer.parseInt(cajaKilometraje.getText().toString());
        String fechaEntrada = fechaHoy;
        String tipo = spinnerTipo.getSelectedItem().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        Vehiculo vehiculo = new Vehiculo(
                id,
                numSerie,
                idModelo,
                fechaFab,
                precio,
                kilometraje,
                fechaEntrada,
                tipo,
                estado

        );

        new Thread(new Runnable() {
            @Override
            public void run() {

                numFilas = 0;

                numFilas = bd.vehiculoDAO().agregarVehiculo(vehiculo);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(numFilas > 0){

                            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasVehiculos.this);
                            builder.setTitle("Aviso");
                            builder.setMessage("Se agrego el Modelo Correctamente.");
                            builder.setPositiveButton("OK", null);
                            builder.show();

                            restablecer.restablecer(cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, txtFecha, cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje);

                        } else {

                            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasVehiculos.this);
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

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAltasVehiculos.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de cancelar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                restablecer.restablecer(cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, txtFecha, cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje);


                Intent i = new Intent(ActivityAltasVehiculos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null); // Solo cierra el diálogo

        builder.show();


    }

    public void retablecer(View v){

        restablecer.restablecer(cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje, txtFecha, cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje);

    }

}
