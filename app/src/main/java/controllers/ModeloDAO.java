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

    @Query("SELECT * FROM modelo WHERE id_modelo=:idModelo")
    public List<Modelo> mostrarPorId(int idModelo);

    @Query("SELECT * FROM modelo WHERE nombre_modelo LIKE :nombre")
    List<Modelo> buscarPorNombre(String nombre);

    @Query("SELECT * FROM modelo WHERE anio_modelo = :anio")
    List<Modelo> buscarPorAnio(int anio);

    @Query("SELECT * FROM modelo WHERE fabricante LIKE :fab")
    List<Modelo> buscarPorFabricante(String fab);

    @Query("SELECT * FROM modelo WHERE numero_cilindros = :cilindros")
    List<Modelo> buscarPorCilindros(int cilindros);

    @Query("SELECT * FROM modelo WHERE numero_puertas = :puertas")
    List<Modelo> buscarPorPuertas(int puertas);

    @Query("SELECT * FROM modelo WHERE peso = :peso")
    List<Modelo> buscarPorPeso(double peso);

    @Query("SELECT * FROM modelo WHERE capacidad_pasajeros = :pasajeros")
    List<Modelo> buscarPorPasajeros(int pasajeros);

    @Query("SELECT * FROM modelo WHERE color_base LIKE :color")
    List<Modelo> buscarPorColor(String color);

    @Query("SELECT * FROM modelo WHERE pais_fabricacion LIKE :pais")
    List<Modelo> buscarPorPais(String pais);
    
}
