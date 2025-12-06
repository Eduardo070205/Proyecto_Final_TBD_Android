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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import conexion.Autos_Amistosos_BD;
import custom_adapter.CustomAdapter;
import entities.Modelo;

public class ActivityConsultasModelos extends Activity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Modelo> datos = null;

    Autos_Amistosos_BD bd;

    EditText cajaId, cajaNombre, cajaFabricante, cajaPuertas, cajaPasajeros, cajaPais;

    Spinner spinnerAnio, spinnerCilindros;

    RadioButton radioId, radioNombre, radioAnio, radioFabricante, radioCilindros, radioPuertas, radioPasajeros, radioPais, radioTodos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_modelos);



        bd = Autos_Amistosos_BD.getAppDatabase(this);

        cajaId = findViewById(R.id.cajaModelosIDModeloBuscar);
        cajaNombre = findViewById(R.id.cajaModelosNombreBuscar);
        cajaFabricante = findViewById(R.id.cajaModelosFabricanteBuscar);
        cajaPuertas = findViewById(R.id.cajaModelosPuertasBuscar);
        cajaPasajeros = findViewById(R.id.cajaModelosPasajerosBuscar);
        cajaPais = findViewById(R.id.cajaModelosPaisBuscar);

        spinnerCilindros = findViewById(R.id.spinnerModelosCilindrosBuscar);
        spinnerAnio = findViewById(R.id.spinnerModelosAnioBuscar);


        radioId = findViewById(R.id.radioModelosIDModelo);
        radioNombre = findViewById(R.id.radioModelosNombre);
        radioAnio = findViewById(R.id.radioModelosAnio);
        radioFabricante = findViewById(R.id.radioModelosFabricante);
        radioCilindros = findViewById(R.id.radioModelosCilindros);
        radioPuertas = findViewById(R.id.radioModelosPuertas);
        radioPasajeros = findViewById(R.id.radioModelosPasajeros);
        radioPais = findViewById(R.id.radioModelosPais);
        radioTodos =  findViewById(R.id.radioModelosTodos);




        cajaId.setEnabled(false);
        cajaNombre.setEnabled(false);
        cajaFabricante.setEnabled(false);
        cajaPuertas.setEnabled(false);
        cajaPasajeros.setEnabled(false);
        cajaPais.setEnabled(false);
        spinnerAnio.setEnabled(false);
        spinnerCilindros.setEnabled(false);


        List<String> listaAnios = new ArrayList<>();

        for (int i = 2025; i >= 1900; i--) {
            listaAnios.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaAnios);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnio.setAdapter(adapter);


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

    public void buscarModelo(View v) {

        Intent i = new Intent(this, ActivityListaModelos.class);

        if (radioId.isChecked()) {
            i.putExtra("filtro", "id");
            i.putExtra("valor", cajaId.getText().toString());
        }
        else if (radioNombre.isChecked()) {
            i.putExtra("filtro", "nombre");
            i.putExtra("valor", cajaNombre.getText().toString());
        }
        else if (radioFabricante.isChecked()) {
            i.putExtra("filtro", "fabricante");
            i.putExtra("valor", cajaFabricante.getText().toString());
        }
        else if (radioPuertas.isChecked()) {
            i.putExtra("filtro", "puertas");
            i.putExtra("valor", cajaPuertas.getText().toString());
        }
        else if (radioPasajeros.isChecked()) {
            i.putExtra("filtro", "pasajeros");
            i.putExtra("valor", cajaPasajeros.getText().toString());
        }
        else if (radioPais.isChecked()) {
            i.putExtra("filtro", "pais");
            i.putExtra("valor", cajaPais.getText().toString());
        }
        else if (radioCilindros.isChecked()) {
            i.putExtra("filtro", "cilindros");
            i.putExtra("valor", spinnerCilindros.getSelectedItem().toString());
        }
        else if (radioAnio.isChecked()) {
            i.putExtra("filtro", "anio");
            i.putExtra("valor", spinnerAnio.getSelectedItem().toString());
        }
        else if (radioTodos.isChecked()) {
            i.putExtra("filtro", "todos");
            i.putExtra("valor", "");
        }

        startActivity(i);
    }

}
