package com.example.recetas.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recetas.Model.Comida;
import com.example.recetas.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Comidaadapter extends FirestoreRecyclerAdapter<Comida,Comidaadapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Comidaadapter(@NonNull FirestoreRecyclerOptions<Comida> options) {
        super(options);
  }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Comida model) {
        holder.Nombre.setText(model.getNombre());
        holder.Imagen.setText(model.getImagen());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View C = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comida_single, parent, false);
        return new ViewHolder(C);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.nombre);
            Imagen = itemView.findViewById(R.id.imagen);
        }
    }
}
