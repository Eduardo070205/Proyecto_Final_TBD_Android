package com.example.proyecto_final;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Recursos.Restablecer;
import conexion.Autos_Amistosos_BD;
import entities.Modelo;

public class ActivityConsultasModelos extends Activity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    Restablecer restablecer = new Restablecer();

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

        restablecer.restablecer(cajaId, cajaNombre, cajaFabricante, cajaPuertas, cajaPasajeros, cajaPais, spinnerAnio, spinnerCilindros);


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

        if (radioId.isChecked() && !cajaId.getText().isEmpty()) {
            i.putExtra("filtro", "id");
            i.putExtra("valor", cajaId.getText().toString());
            startActivity(i);

        }
        else if (radioNombre.isChecked() && !cajaNombre.getText().isEmpty()) {
            i.putExtra("filtro", "nombre");
            i.putExtra("valor", cajaNombre.getText().toString());
            startActivity(i);
        }
        else if (radioFabricante.isChecked() && !cajaFabricante.getText().isEmpty()) {
            i.putExtra("filtro", "fabricante");
            i.putExtra("valor", cajaFabricante.getText().toString());
            startActivity(i);
        }
        else if (radioPuertas.isChecked() && !cajaPuertas.getText().isEmpty()) {
            i.putExtra("filtro", "puertas");
            i.putExtra("valor", cajaPuertas.getText().toString());
            startActivity(i);
        }
        else if (radioPasajeros.isChecked() && !cajaPasajeros.getText().isEmpty()) {
            i.putExtra("filtro", "pasajeros");
            i.putExtra("valor", cajaPasajeros.getText().toString());
            startActivity(i);
        }
        else if (radioPais.isChecked() && !cajaPais.getText().isEmpty()) {
            i.putExtra("filtro", "pais");
            i.putExtra("valor", cajaPais.getText().toString());
            startActivity(i);
        }
        else if (radioCilindros.isChecked()) {
            i.putExtra("filtro", "cilindros");
            i.putExtra("valor", spinnerCilindros.getSelectedItem().toString());
            startActivity(i);
        }
        else if (radioAnio.isChecked()) {
            i.putExtra("filtro", "anio");
            i.putExtra("valor", spinnerAnio.getSelectedItem().toString());
            startActivity(i);
        }
        else if (radioTodos.isChecked()) {
            i.putExtra("filtro", "todos");
            i.putExtra("valor", "");
            startActivity(i);
        }else{

            Toast.makeText(getBaseContext(), "No se encontró el modelo", Toast.LENGTH_LONG).show();


        }


    }

    public void regresarMenu(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityConsultasModelos.this);
        builder.setTitle("Salir");
        builder.setMessage("¿Estás seguro de Cancelar?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(ActivityConsultasModelos.this, ActivityMenu.class);

                startActivity(i);

            }
        });

        builder.setNegativeButton("No", null);

        builder.show();


    }

}
