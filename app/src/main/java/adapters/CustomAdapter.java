package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(View view){

            super(view);

            textView = view.findViewById(R.id.recyclerPersonalizadoVehiculos);

        }

        public TextView getTextView(){return textView;}

    }

    public CustomAdapter(ArrayList dataset){

        localDataSet = dataset;

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recicler_personalizado_vehiculos, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.getTextView().setText(localDataSet.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }




}
