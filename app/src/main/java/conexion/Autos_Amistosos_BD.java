package conexion;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import controllers.VehiculoDAO;
import entities.Vehiculo;

@Database(entities = {Vehiculo.class}, version = 1)
public abstract class Autos_Amistosos_BD extends RoomDatabase {

    private static Autos_Amistosos_BD INSTANCE;

    public abstract VehiculoDAO vehiculoDAO();

    public static Autos_Amistosos_BD getAppDatabase(Context context){

        if(INSTANCE==null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Autos_Amistosos_BD.class, "BD_Autos_Amistosos").build();

        }

        return INSTANCE;

    }

    public static void desstroyInstance(){INSTANCE=null;}

}
