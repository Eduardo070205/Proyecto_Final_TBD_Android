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

import java.util.ArrayList;
import java.util.List;

import conexion.Autos_Amistosos_BD;
import entities.Vehiculo;

public class ActivityModificarVehiculos extends AppCompatActivity {

    TextInputEditText txtFecha1, txtFecha2;

    EditText cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerIdM, spinnerTipo, spinnerEstado;

    ArrayList<Vehiculo> lista = null;

    Autos_Amistosos_BD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modificaciones_vehiculos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        txtFecha1 = findViewById(R.id.txtVehiculosFechaFabriModificar);

        txtFecha1.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Selecciona una fecha")
                            .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                txtFecha1.setText(datePicker.getHeaderText());
            });

            datePicker.show(getSupportFragmentManager(), "datePicker");
        });

        txtFecha2 = findViewById(R.id.txtVehiculosFechaEntradaModificar);

        txtFecha2.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Selecciona una fecha")
                            .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                txtFecha2.setText(datePicker.getHeaderText());
            });

            datePicker.show(getSupportFragmentManager(), "datePicker");
        });

        cajaIdV = findViewById(R.id.cajaVehiculosIDVehiculoModificar);
        cajaNumSerie = findViewById(R.id.cajaVehiculosNumSerieModificar);
        cajaPrecio = findViewById(R.id.cajaVehiculosPrecioModificar);
        cajaKilometraje = findViewById(R.id.cajaVehiculosKilometrajeModificar);



        spinnerIdM = findViewById(R.id.spinnerVehiculosIDModeloModificar);
        spinnerTipo = findViewById(R.id.spinnerVehiculosTipoModificar);
        spinnerEstado = findViewById(R.id.spinnerVehiculosEstadoModificar);

        new Thread(() -> {

            List<Integer> listaIds = bd.modeloDAO().obtenerModelos();

            List<String> idsString = new ArrayList<>();
            for (Integer id : listaIds) {
                idsString.add(String.valueOf(id));
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(ActivityModificarVehiculos.this,
                                android.R.layout.simple_spinner_item,
                                idsString);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerIdM.setAdapter(adapter);
            });

        }).start();


    }


    public void modificarVehiculo(View v){

        String id = cajaIdV.getText().toString();
        String numSerie = cajaNumSerie.getText().toString();
        int idModelo = Integer.parseInt(spinnerIdM.getSelectedItem().toString());
        String fechaFab = txtFecha1.getText().toString();
        double precio = Double.parseDouble(cajaPrecio.getText().toString());
        int kilometraje = Integer.parseInt(cajaKilometraje.getText().toString());
        String fechaEntrada = txtFecha2.getText().toString();
        String tipo = spinnerTipo.getSelectedItem().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                int agregado = bd.vehiculoDAO().actualizarVehiculo(

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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (agregado == 1){

                            Toast.makeText(getBaseContext(), "Actualización correcta", Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(getBaseContext(), "Actualización Incorrecta", Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        }).start();

    }

    public void buscarVehiculo(View v) {

        String valorBuscado;
        ArrayAdapter adapter;
        int posicion;

        new Thread(new Runnable() {
            @Override
            public void run() {

                lista = (ArrayList<Vehiculo>) bd.vehiculoDAO().buscarPorIdVehiculo(cajaIdV.getText().toString());

                runOnUiThread(() -> {

                    if (lista == null || lista.isEmpty()) {
                        Toast.makeText(getBaseContext(), "No se encontró el modelo", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Vehiculo vehiculo = lista.get(0);

                    cajaNumSerie.setText(vehiculo.getNumero_serie());
                    txtFecha1.setText(vehiculo.getFecha_fabricacion());
                    cajaPrecio.setText(String.valueOf(vehiculo.getPrecio()));
                    cajaKilometraje.setText(String.valueOf(vehiculo.getKilometraje()));
                    txtFecha2.setText(vehiculo.getFehca_entrada());


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

    }
}
