package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.R;

import java.util.ArrayList;

import entities.Modelo;

public class ModeloAdapter extends RecyclerView.Adapter<ModeloAdapter.ViewHolder> {

    private ArrayList<Modelo> lista;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtID, txtNombre, txtMarca, txtAnio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.itemModeloID);
            txtNombre = itemView.findViewById(R.id.itemModeloNombre);
            txtMarca = itemView.findViewById(R.id.itemModeloMarca);
            txtAnio = itemView.findViewById(R.id.itemModeloAnio);
        }
    }

    public ModeloAdapter(ArrayList<Modelo> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_modelo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Modelo m = lista.get(position);

        holder.txtID.setText("ID: " + m.getId_modelo());
        holder.txtNombre.setText("Modelo: " + m.getNombre_modelo());
        holder.txtMarca.setText("Marca: " + m.getFabricante());
        holder.txtAnio.setText("Año: " + m.getAnio_modelo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
