package com.example.silvanadorantes.listadepeliculas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvana on 07/08/16.
 */
public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<Pelicula> mItem = new ArrayList<Pelicula>();
    private Context mContext;
    private Pelicula mPelicula;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        public void onClick(PeliculaAdapter.ViewHolder viewHolder, int idPelicula);
    }

    public PeliculaAdapter(Context context, List<Pelicula> item, OnItemClickListener listener){
        this.mContext = context;
        this.mItem = item;
        this.listener = listener;


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivItem;
        private TextView tvPelicula;
        private ImageView ivEliminar;

        public ViewHolder(View view){
            super(view);
            ivItem = (ImageView) view.findViewById(R.id.ivItem);
            tvPelicula = (TextView) view.findViewById(R.id.tvItem);
            ivEliminar = (ImageView)view.findViewById(R.id.ivEliminar);
            ivEliminar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(this, getIdPelicula(getAdapterPosition()));
        }

        public void setIvItem(int imagenItem) {
            ivEliminar.setTag(imagenItem);
            ivEliminar.setImageResource(imagenItem);

        }

        public void setTvPelicula(String text) {
            tvPelicula.setText(text);
        }

        public void setIvEliminar(int imageEliminar) {
           ivEliminar.setTag(imageEliminar);
            ivEliminar.setImageResource(imageEliminar);
        }
    }

    public void add(Pelicula pelicula){
        mItem.add(pelicula);
        notifyItemInserted(mItem.indexOf(pelicula));
    }

    public List<Pelicula> getItem() {

        return mItem;
    }

    public void remove(Pelicula pelicula){
        int position = mItem.indexOf(pelicula);
        if (position != -1){
            mItem.remove(position);
            notifyItemRemoved(position);
        }


    }

     /*
    Permite limpiar todos los elementos del RecyclerView
     */

    public void clear(){
        mItem.clear();
        notifyDataSetChanged();

    }

    private int getIdPelicula(int position){
        if (position != RecyclerView.NO_POSITION){
            return mItem.get(position).getIdPelicula();
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.ivItem.setTag(position);
        holder.setTvPelicula(mItem.get(position).getNombrePelicula());
        holder.ivEliminar.setTag(position);

    }
}
