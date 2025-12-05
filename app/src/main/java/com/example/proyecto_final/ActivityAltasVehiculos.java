package com.example.proyecto_final;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityAltasVehiculos extends AppCompatActivity {

    TextInputEditText txtFecha;

    EditText cajaIdV, cajaNumSerie, cajaPrecio, cajaKilometraje;

    Spinner spinnerIdM, spinnerTipo, spinnerEstado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculos);

        txtFecha = findViewById(R.id.txtVehiculosFechaFabricacion);

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
    }

    public void agregarVehiculo(View v){

        if(v.getId() == R.id.btnVehiculosAgregarAgregar){

            Toast.makeText(this, txtFecha.getText(), Toast.LENGTH_SHORT).show();

        }

    }

}
