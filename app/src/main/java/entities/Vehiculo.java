package entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(

        tableName = "vehiculo",
        foreignKeys = @ForeignKey(
                entity = Modelo.class,
                parentColumns = "id_modelo",
                childColumns = "id_modelo_fk",
                onDelete = ForeignKey.CASCADE
        )


)
public class Vehiculo {



    @PrimaryKey
    @NonNull
    public String id_vehiculo;

    @NonNull
    @ColumnInfo(name = "numero_serie")
    public String numero_serie;

    @NonNull
    @ColumnInfo(name = "id_modelo_fk")
    public int id_modelo_fk;

    @NonNull
    @ColumnInfo(name = "fecha_fabricacion")
    public String fecha_fabricacion;

    @NonNull
    @ColumnInfo(name = "precio")
    public double precio;

    @NonNull
    @ColumnInfo(name = "kilometraje")
    public int kilometraje;

    @NonNull
    @ColumnInfo(name = "fecha_entrada")
    public String fehca_entrada;

    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;

    @NonNull
    @ColumnInfo(name = "estado")
    public String estado;


}
