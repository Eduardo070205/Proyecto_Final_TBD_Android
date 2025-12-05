package entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Modelo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id_modelo;

    @NonNull
    @ColumnInfo(name = "nombre_modelo")
    public String nombre_modelo;

    @NonNull
    @ColumnInfo(name = "anio_modelo")
    public int anio_modelo;

    @NonNull
    @ColumnInfo(name = "fabricante")
    public String fabricante;


    @NonNull
    @ColumnInfo(name = "numero_cilindros")
    public int numero_cilindros;

    @NonNull
    @ColumnInfo(name = "numero_puertas")
    public int numero_puertas;

    @NonNull
    @ColumnInfo(name = "peso")
    public double peso;

    @NonNull
    @ColumnInfo(name = "capacidad_pasajeros")
    public int capacidad_pasajeros;

    @NonNull
    @ColumnInfo(name = "color_base")
    public String color_base;

    @NonNull
    @ColumnInfo(name = "pais_fabricacion")
    public String pais_fabricacion;

    public Modelo(@NonNull int id_modelo, @NonNull String nombre_modelo, int anio_modelo, int numero_cilindros, int numero_puertas, double peso, int capacidad_pasajeros, @NonNull String color_base, @NonNull String pais_fabricacion) {
        this.id_modelo = id_modelo;
        this.nombre_modelo = nombre_modelo;
        this.anio_modelo = anio_modelo;
        this.numero_cilindros = numero_cilindros;
        this.numero_puertas = numero_puertas;
        this.peso = peso;
        this.capacidad_pasajeros = capacidad_pasajeros;
        this.color_base = color_base;
        this.pais_fabricacion = pais_fabricacion;
    }

    @NonNull
    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(@NonNull int id_modelo) {
        this.id_modelo = id_modelo;
    }

    @NonNull
    public String getNombre_modelo() {
        return nombre_modelo;
    }

    public void setNombre_modelo(@NonNull String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
    }

    public int getAnio_modelo() {
        return anio_modelo;
    }

    public void setAnio_modelo(int anio_modelo) {
        this.anio_modelo = anio_modelo;
    }

    public int getNumero_cilindros() {
        return numero_cilindros;
    }

    public void setNumero_cilindros(int numero_cilindros) {
        this.numero_cilindros = numero_cilindros;
    }

    public int getNumero_puertas() {
        return numero_puertas;
    }

    public void setNumero_puertas(int numero_puertas) {
        this.numero_puertas = numero_puertas;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCapacidad_pasajeros() {
        return capacidad_pasajeros;
    }

    public void setCapacidad_pasajeros(int capacidad_pasajeros) {
        this.capacidad_pasajeros = capacidad_pasajeros;
    }

    @NonNull
    public String getColor_base() {
        return color_base;
    }

    public void setColor_base(@NonNull String color_base) {
        this.color_base = color_base;
    }

    @NonNull
    public String getPais_fabricacion() {
        return pais_fabricacion;
    }

    public void setPais_fabricacion(@NonNull String pais_fabricacion) {
        this.pais_fabricacion = pais_fabricacion;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id_modelo='" + id_modelo + '\'' +
                ", nombre_modelo='" + nombre_modelo + '\'' +
                ", anio_modelo=" + anio_modelo +
                ", numero_cilindros=" + numero_cilindros +
                ", numero_puertas=" + numero_puertas +
                ", peso=" + peso +
                ", capacidad_pasajeros=" + capacidad_pasajeros +
                ", color_base='" + color_base + '\'' +
                ", pais_fabricacion='" + pais_fabricacion + '\'' +
                '}';
    }
}
