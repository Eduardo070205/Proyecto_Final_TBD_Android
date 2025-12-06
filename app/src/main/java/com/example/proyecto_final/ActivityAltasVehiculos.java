package com.example.proyecto_final;

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

import conexion.Autos_Amistosos_BD;
import entities.Modelo;
import entities.Vehiculo;

public class ActivityAltasVehiculos extends AppCompatActivity {

    TextInputEditText txtFecha;

    EditText cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerIdM, spinnerTipo, spinnerEstado;



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

                bd.vehiculoDAO().agregarVehiculo(vehiculo);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getBaseContext(), "Insercción correcta", Toast.LENGTH_LONG).show();

                    }
                });

            }
        }).start();


    }

}
