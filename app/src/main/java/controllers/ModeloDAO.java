package controllers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import entities.Modelo;

@Dao
public interface ModeloDAO {

    // ============================== ALTAS =====================

    @Insert
    public void agregarModelo(Modelo modelo);

    // ============================== BAJAS ======================

    @Query("DELETE FROM modelo WHERE id_modelo=:idModelo")
    public int eliminarModeloPirId(int idModelo);

    // ============================= MODIFICAR ===================

    @Query("UPDATE modelo SET nombre_modelo =:nombreModelo, anio_modelo =:anioModelo, fabricante =:fabricante, numero_cilindros =:numCilindros, numero_puertas =:numPuertas, peso =:peso, capacidad_pasajeros =:pasajeros, color_base =:colorBase, pais_fabricacion =:pais WHERE id_modelo =:idModelo")
    public int actualizarModeloPorId(int idModelo, String nombreModelo, int anioModelo, String fabricante, int numCilindros, int numPuertas, double peso, int pasajeros, String colorBase, String pais);

    // ============================= CONSULTAS =======================

    @Query("SELECT * FROM modelo")
    public List<Modelo> mostrarTodos();

}
