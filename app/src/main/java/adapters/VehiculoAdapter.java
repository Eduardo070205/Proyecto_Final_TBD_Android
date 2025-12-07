package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.R;

import java.util.ArrayList;

import entities.Vehiculo;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    private ArrayList<Vehiculo> lista;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtID, txtSerie, txtPrecio, txtTipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.itemVehiculoID);
            txtSerie = itemView.findViewById(R.id.itemVehiculoSerie);
            txtPrecio = itemView.findViewById(R.id.itemVehiculoPrecio);
            txtTipo = itemView.findViewById(R.id.itemVehiculoTipo);
        }
    }

    public VehiculoAdapter(ArrayList<Vehiculo> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehiculo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehiculo v = lista.get(position);

        holder.txtID.setText("ID: " + v.id_vehiculo);
        holder.txtSerie.setText("Serie: " + v.numero_serie);
        holder.txtPrecio.setText("Precio: $" + v.precio);
        holder.txtTipo.setText("Tipo: " + v.tipo);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
