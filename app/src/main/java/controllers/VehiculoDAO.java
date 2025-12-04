package controllers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import entities.Vehiculo;

@Dao
public interface VehiculoDAO {

    // -------------------------- ALTAS -----------------------------

    @Insert
    public void agregarVehiculo(Vehiculo vehiculo);

    // ------------------------- BAJAS ------------------------------


    @Query("DELETE FROM vehiculo WHERE id_vehiculo=:id")
    public int eliminarAlumnoPorNumControl(String id);

    // ------------------------ MODIFICACIONES -----------------------

    @Query("UPDATE vehiculo SET numero_serie =:numserie, id_modelo =:idModelo, fecha_fabricacion =:fechaFab, precio =:precio, kilometraje =:kilometraje, fecha_entrada =:fechaEntrada, tipo =:tipo, estado =:estado WHERE id_vehiculo=:idVehiculo")
    public int actualizarAlumnoPorNumControl(String numserie, int idModelo, Date fechaFab, double precio, int kilometraje, Date fechaEntrada, String tipo, String estado,String idVehiculo);

    // -------------------------- CONSULTAS --------------------------

    @Query("SELECT * FROM vehiculo")
    public List<Vehiculo> mostrarTodos();



}
