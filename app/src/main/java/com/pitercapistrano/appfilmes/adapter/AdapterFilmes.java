package com.pitercapistrano.appfilmes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pitercapistrano.appfilmes.R;
import com.pitercapistrano.appfilmes.model.Filme;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterFilmes extends RecyclerView.Adapter<AdapterFilmes.FilmeViewHolder> {

    private Context context;
    private List<Filme> filmeList;

    public AdapterFilmes(Context context, List<Filme> filmeList) {
        this.context = context;
        this.filmeList = filmeList;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        itemLista = layoutInflater.inflate(R.layout.filme_item, parent, false);
        return new FilmeViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Glide.with(context).load(filmeList.get(position).getCapa()).into(holder.capa);
        holder.titulo.setText(filmeList.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return filmeList.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder{

        private ImageView capa;
        private TextView titulo, descricao, elenco;

        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            capa = itemView.findViewById(R.id.capa_filme);
            titulo = itemView.findViewById(R.id.titulo_filme);
            descricao = itemView.findViewById(R.id.dt_descricao_filme);
            elenco = itemView.findViewById(R.id.dt_elenco_filme);
        }
    }
}
