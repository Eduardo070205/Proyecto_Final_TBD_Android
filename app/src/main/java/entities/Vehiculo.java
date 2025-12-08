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
    public String fecha_entrada;

    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;

    @NonNull
    @ColumnInfo(name = "estado")
    public String estado;


    public Vehiculo(@NonNull String id_vehiculo, @NonNull String numero_serie, int id_modelo_fk, @NonNull String fecha_fabricacion, double precio, int kilometraje, @NonNull String fecha_entrada, @NonNull String tipo, @NonNull String estado) {
        this.id_vehiculo = id_vehiculo;
        this.numero_serie = numero_serie;
        this.id_modelo_fk = id_modelo_fk;
        this.fecha_fabricacion = fecha_fabricacion;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.fecha_entrada = fecha_entrada;
        this.tipo = tipo;
        this.estado = estado;
    }

    @NonNull
    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(@NonNull String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    @NonNull
    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(@NonNull String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public int getId_modelo_fk() {
        return id_modelo_fk;
    }

    public void setId_modelo_fk(int id_modelo_fk) {
        this.id_modelo_fk = id_modelo_fk;
    }

    @NonNull
    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(@NonNull String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    @NonNull
    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(@NonNull String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    public String getEstado() {
        return estado;
    }

    public void setEstado(@NonNull String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id_vehiculo='" + id_vehiculo + '\'' +
                ", numero_serie='" + numero_serie + '\'' +
                ", id_modelo_fk=" + id_modelo_fk +
                ", fecha_fabricacion='" + fecha_fabricacion + '\'' +
                ", precio=" + precio +
                ", kilometraje=" + kilometraje +
                ", fecha_entrada='" + fecha_entrada + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
