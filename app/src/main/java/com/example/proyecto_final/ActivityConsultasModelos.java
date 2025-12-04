package com.example.proyecto_final;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class ActivityConsultasModelos extends Activity {

    EditText cajaId, cajaNombre, cajaFabricante, cajaPuertas, cajaPasajeros, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_modelos);

        cajaId = findViewById(R.id.cajaModelosIDModeloBuscar);
        cajaNombre = findViewById(R.id.cajaModelosNombreBuscar);
        cajaFabricante = findViewById(R.id.cajaModelosFabricanteBuscar);
        cajaPuertas = findViewById(R.id.cajaModelosPuertasBuscar);
        cajaPasajeros = findViewById(R.id.cajaModelosPasajerosBuscar);
        cajaPais = findViewById(R.id.cajaModelosPaisBuscar);

        spinnerCilindros = findViewById(R.id.spinnerModelosCilindrosBuscar);
        spinnerAnio = findViewById(R.id.spinnerModelosAnioBuscar);


        cajaId.setEnabled(false);
        cajaNombre.setEnabled(false);
        cajaFabricante.setEnabled(false);
        cajaPuertas.setEnabled(false);
        cajaPasajeros.setEnabled(false);
        cajaPais.setEnabled(false);
        spinnerAnio.setEnabled(false);
        spinnerCilindros.setEnabled(false);


    }

    public void deshabilitarRadios(View v){

        cajaId.setEnabled(false);
        cajaNombre.setEnabled(false);
        cajaFabricante.setEnabled(false);
        cajaPuertas.setEnabled(false);
        cajaPasajeros.setEnabled(false);
        cajaPais.setEnabled(false);
        spinnerAnio.setEnabled(false);
        spinnerCilindros.setEnabled(false);

        if(v.getId() == R.id.radioModelosIDModelo){

            cajaId.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosNombre){

            cajaNombre.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosFabricante){

            cajaFabricante.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosPuertas){

            cajaPuertas.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosPasajeros){

            cajaPasajeros.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosPais){

            cajaPais.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosCilindros){

            spinnerCilindros.setEnabled(true);

        }else if(v.getId() == R.id.radioModelosAnio){

            spinnerAnio.setEnabled(true);

        }


    }

}
