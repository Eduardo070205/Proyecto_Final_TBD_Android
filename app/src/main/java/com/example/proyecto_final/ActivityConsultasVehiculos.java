package com.example.proyecto_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import conexion.Autos_Amistosos_BD;

public class ActivityConsultasVehiculos extends Activity {

    EditText cajaVehiculo, cajaSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerModelo, spinnerTipo, spinnerEstado;

    Autos_Amistosos_BD bd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_vehiculos);

        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaVehiculo = findViewById(R.id.cajaVehiculosIDVehiculosBuscar);

        cajaSerie = findViewById(R.id.cajaVehiculosNumSerieBuscar);

        cajaPrecio = findViewById(R.id.cajaVehiculosPrecioBuscar);

        cajaKilometraje = findViewById(R.id.cajaVehiculosKilometrajeBuscar);

        spinnerModelo = findViewById(R.id.spinnerVehiculosIDModeloBuscar);

        spinnerTipo = findViewById(R.id.spinnerVehiculosTipoBuscar);

        spinnerEstado = findViewById(R.id.spinnerVehiculosEstadoBuscar);

        cajaVehiculo.setEnabled(false);
        cajaSerie.setEnabled(false);
        cajaPrecio.setEnabled(false);
        cajaKilometraje.setEnabled(false);

        spinnerModelo.setEnabled(false);
        spinnerTipo.setEnabled(false);
        spinnerEstado.setEnabled(false);

        new Thread(() -> {

            List<Integer> listaIds = bd.modeloDAO().obtenerModelos();

            List<String> idsString = new ArrayList<>();
            for (Integer id : listaIds) {
                idsString.add(String.valueOf(id));
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(ActivityConsultasVehiculos.this,
                                android.R.layout.simple_spinner_item,
                                idsString);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModelo.setAdapter(adapter);
            });

        }).start();

    }

    public void desactivarRadio(View v){

        cajaVehiculo.setEnabled(false);
        cajaSerie.setEnabled(false);
        cajaPrecio.setEnabled(false);
        cajaKilometraje.setEnabled(false);

        spinnerModelo.setEnabled(false);
        spinnerTipo.setEnabled(false);
        spinnerEstado.setEnabled(false);

        if(v.getId() == R.id.radioVehiculosIDVehiculo){

            cajaVehiculo.setEnabled(true);

        } else if (v.getId() == R.id.radioVehiculoNumSerie) {

            cajaSerie.setEnabled(true);

        }else if (v.getId() == R.id.radioVehiculosIDModelo) {

            spinnerModelo.setEnabled(true);

        }else if (v.getId() == R.id.radioVehiculosPrecio) {

            cajaPrecio.setEnabled(true);

        }else if (v.getId() == R.id.radioVehiculosKilometraje) {

            cajaKilometraje.setEnabled(true);

        }else if (v.getId() == R.id.radioVehiculosTipo) {

            spinnerTipo.setEnabled(true);

        }else if (v.getId() == R.id.radioVehiculosEstado) {

            spinnerEstado.setEnabled(true);

        }

    }

    public void buscarVehiculos(View v) {

        Intent intent = new Intent(this, ActivityListaVehiculos.class);

        if (((RadioButton)findViewById(R.id.radioVehiculosIDVehiculo)).isChecked()) {

            intent.putExtra("tipo", "idVehiculo");
            intent.putExtra("valor", cajaVehiculo.getText().toString());

        } else if (((RadioButton)findViewById(R.id.radioVehiculoNumSerie)).isChecked()) {

            intent.putExtra("tipo", "numeroSerie");
            intent.putExtra("valor", cajaSerie.getText().toString());
        } else if (((RadioButton)findViewById(R.id.radioVehiculosIDModelo)).isChecked()) {

            intent.putExtra("tipo", "idModelo");
            intent.putExtra("valor", spinnerModelo.getSelectedItem().toString());
        } else if (((RadioButton)findViewById(R.id.radioVehiculosPrecio)).isChecked()) {

            intent.putExtra("tipo", "precio");
            intent.putExtra("valor", cajaPrecio.getText().toString());
        } else if (((RadioButton)findViewById(R.id.radioVehiculosKilometraje)).isChecked()) {

            intent.putExtra("tipo", "kilometraje");
            intent.putExtra("valor", cajaKilometraje.getText().toString());
        } else if (((RadioButton)findViewById(R.id.radioVehiculosTipo)).isChecked()) {

            intent.putExtra("tipo", "tipo");
            intent.putExtra("valor", spinnerTipo.getSelectedItem().toString());
        } else if (((RadioButton)findViewById(R.id.radioVehiculosEstado)).isChecked()) {

            intent.putExtra("tipo", "estado");
            intent.putExtra("valor", spinnerEstado.getSelectedItem().toString());

        }else if (((RadioButton)findViewById(R.id.radioVehiculosTodos)).isChecked()){

            intent.putExtra("tipo", "todos");
            intent.putExtra("valor", "");

        }

        startActivity(intent);
    }


}
