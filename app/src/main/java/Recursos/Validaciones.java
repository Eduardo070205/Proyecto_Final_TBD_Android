package Recursos;

import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Validaciones {


    public static boolean validarCamposVacios(EditText... campos) {
        boolean error = false;

        for (EditText c : campos) {
            String texto = c.getText().toString().trim();
            if (texto.isEmpty()) {
                c.setError("Este campo es obligatorio");
                error = true;
            }
        }

        return error;
    }

    public static boolean validarSoloLetrasYNumeros(EditText... campos) {
        boolean error = false;

        for (EditText c : campos) {
            String texto = c.getText().toString().trim();
            if (!texto.matches("[a-zA-Z0-9 ]+")) {
                c.setError("Solo se permiten letras y números");
                error = true;
            }
        }

        return error;
    }

    public static boolean validarSoloLetras(EditText... campos) {
        boolean error = false;

        for (EditText c : campos) {
            String texto = c.getText().toString().trim();
            if (!texto.matches("[a-zA-Z ]+")) {
                c.setError("Solo se permiten letras");
                error = true;
            }
        }

        return error;
    }

    public static boolean validarFechaNoMayorQueHoy(EditText campoFecha) {

        String textoFecha = campoFecha.getText().toString().trim();

        if (textoFecha.isEmpty()) {
            //campoFecha.setError("Selecciona una fecha");
            return false;
        }

        try {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            LocalDate fechaSeleccionada = LocalDate.parse(textoFecha, formatter);

            LocalDate hoy = LocalDate.now();

            if (fechaSeleccionada.isAfter(hoy)) {
                //campoFecha.setError("La fecha no puede ser mayor a hoy");
                return false;
            }

        } catch (Exception e) {
            //campoFecha.setError("Formato de fecha inválido");
            return false;
        }

        return true;
    }


}