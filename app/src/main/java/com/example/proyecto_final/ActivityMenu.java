package com.example.proyecto_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class ActivityMenu extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void abrirActivity(View v) {


        Intent i = null;

        if (v.getId() == R.id.btnVehiculosAgregar) {

            i = new Intent(this, ActivityAltasVehiculos.class);

        }else if (v.getId() == R.id.btnVehiculosEliminar){

            i = new Intent(this, ActivityEliminarVehiculos.class);

        }else if (v.getId() == R.id.btnVehiculosModificar){

            i = new Intent(this, ActivityModificarVehiculos.class);

        }


        startActivity(i);

    }

}
