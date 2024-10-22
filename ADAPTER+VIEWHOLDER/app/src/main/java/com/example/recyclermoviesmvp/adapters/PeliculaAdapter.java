package com.example.recyclermoviesmvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclermoviesmvp.R;
import com.example.recyclermoviesmvp.beans.Pelicula;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {
    private List<Pelicula> peliculas;
    private Context context; // me da el control de la disposicion del movil

    public PeliculaAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Viewgroup: espacio del diseño visual para el diseño por fila
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, //LayoutInflater: permite modificar el diseño visual desde java
                parent, //te doy el diseño por fila y me guardo en un new de la clase estatica el viewholder
                false);
        return new ViewHolder(view); // este objeto solo se le va a llamar un vez, la primera vez que cree la lista
        //Asi conseguimos no estar continuamente repintando los elementos de la lista, los tenemos disponibles con esto--> optimizacion de codigo
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { // coge el viewholder de x posicion y asigna el contenido para rellenarlo (vendra de un API, arrayList o de donde sea)
        Pelicula pelicula = peliculas.get(position);
        holder.tvTitulo.setText(pelicula.getTitulo());
        holder.bind(pelicula);
        //este metodo si que se va a llamar con cada una de las peliculas, por cada posicion necesita ser llamado
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitulo;
        public ImageView ivPeliculaImagen;
        private Pelicula currentPelicula; //objeto pelicula del que se alimenta, con su contenido

        public ViewHolder(View itemView) { //recibe el espacio visual
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);

            ivPeliculaImagen = itemView.findViewById(R.id.ivPeliculaImagen);

        }

        public void bind(Pelicula pelicula) { //guarda el objeto a nivel de contenido con el que pinta el titulo y la imagen
            currentPelicula = pelicula;
        }
    }
}