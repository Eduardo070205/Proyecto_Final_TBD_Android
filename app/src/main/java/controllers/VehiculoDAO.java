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
    int eliminarVehiculoPorId(String id);

    // MODIFICACIONES
    @Query("UPDATE vehiculo SET numero_serie=:numSerie, id_modelo_fk=:idModelo, fecha_fabricacion=:fechaFab, precio=:precio, kilometraje=:kilometraje, fecha_entrada=:fechaEntrada, tipo=:tipo, estado=:estado WHERE id_vehiculo=:idVehiculo")
    int actualizarVehiculo(String idVehiculo, String numSerie, int idModelo, String fechaFab, double precio, int kilometraje, String fechaEntrada, String tipo, String estado);

    // CONSULTAS
    @Query("SELECT * FROM vehiculo")
    List<Vehiculo> mostrarTodos();

    @Query("SELECT * FROM vehiculo WHERE id_vehiculo = :idVehiculo")
    List<Vehiculo> buscarPorIdVehiculo(String idVehiculo);

    // 2. Buscar por número de serie
    @Query("SELECT * FROM vehiculo WHERE numero_serie LIKE '%' || :numeroSerie || '%'")
    List<Vehiculo> buscarPorNumeroSerie(String numeroSerie);

    // 3. Buscar por id_modelo_fk
    @Query("SELECT * FROM vehiculo WHERE id_modelo_fk = :idModelo")
    List<Vehiculo> buscarPorIdModelo(int idModelo);

    // 5. Buscar por precio exacto
    @Query("SELECT * FROM vehiculo WHERE precio = :precio")
    List<Vehiculo> buscarPorPrecio(double precio);


    // 7. Buscar por kilometraje
    @Query("SELECT * FROM vehiculo WHERE kilometraje = :km")
    List<Vehiculo> buscarPorKilometraje(int km);


    // 10. Buscar por tipo
    @Query("SELECT * FROM vehiculo WHERE tipo LIKE '%' || :tipo || '%'")
    List<Vehiculo> buscarPorTipo(String tipo);

    // 11. Buscar por estado
    @Query("SELECT * FROM vehiculo WHERE estado LIKE '%' || :estado || '%'")
    List<Vehiculo> buscarPorEstado(String estado);


}