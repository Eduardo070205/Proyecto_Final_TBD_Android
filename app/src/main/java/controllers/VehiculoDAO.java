package controllers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import entities.Vehiculo;

@Dao
public interface VehiculoDAO {

    // ALTAS
    @Insert
    void agregarVehiculo(Vehiculo vehiculo);

    // BAJAS
    @Query("DELETE FROM vehiculo WHERE id_vehiculo=:id")
    int eliminarVehiculoPorId(int id);

    // MODIFICACIONES
    @Query("UPDATE vehiculo SET numero_serie=:numSerie, id_modelo_fk=:idModelo, fecha_fabricacion=:fechaFab, precio=:precio, kilometraje=:kilometraje, fecha_entrada=:fechaEntrada, tipo=:tipo, estado=:estado WHERE id_vehiculo=:idVehiculo")
    int actualizarVehiculo(String numSerie, int idModelo, String fechaFab, double precio, int kilometraje, String fechaEntrada, String tipo, String estado, int idVehiculo);

    // CONSULTAS
    @Query("SELECT * FROM vehiculo")
    List<Vehiculo> mostrarTodos();
}