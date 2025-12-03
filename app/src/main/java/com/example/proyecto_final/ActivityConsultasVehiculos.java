package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ActivityConsultasVehiculos extends Activity {

    EditText cajaVehiculo, cajaSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerModelo, spinnerTipo, spinnerEstado;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_vehiculos);

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


}
