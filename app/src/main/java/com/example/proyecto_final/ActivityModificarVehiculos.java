package com.example.proyecto_final;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityModificarVehiculos extends AppCompatActivity {

    TextInputEditText txtFecha1, txtFecha2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modificaciones_vehiculos);

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

    }
}
