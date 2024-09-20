package com.pitercapistrano.appfilmes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pitercapistrano.appfilmes.adapter.AdapterFilmes;
import com.pitercapistrano.appfilmes.model.Filme;
import com.pitercapistrano.appfilmes.model.FilmeApi;
import com.pitercapistrano.appfilmes.onclick.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFilmes;
    private AdapterFilmes adapterFilme;
    private List<Filme> filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IniciarComponentes();

        filmeList = new ArrayList<>();

        //Eventos de Click da Recycler View
        recyclerViewFilmes.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewFilmes,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), DetalhesFilme.class);
                        intent.putExtra("capa",filmeList.get(position).getCapa());
                        intent.putExtra("titulo",filmeList.get(position).getTitulo());
                        intent.putExtra("descricao",filmeList.get(position).getDescricao());
                        intent.putExtra("elenco",filmeList.get(position).getElenco());
                        intent.putExtra("video",filmeList.get(position).getVideo());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

        //Configurar retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/appdelivery-ddd36.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        // Iniciar a retrofit
        FilmeApi filmeApi = retrofit.create(FilmeApi.class);
        Call<List<Filme>> call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (response.code() != 200){
                    return;
                }
                List<Filme> filmes = response.body();

                for (Filme filme : filmes){
                    filmeList.add(filme);
                }
                adapterFilme = new AdapterFilmes(getApplicationContext(), filmeList);
                recyclerViewFilmes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerViewFilmes.setHasFixedSize(true);
                recyclerViewFilmes.setAdapter(adapterFilme);

            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {

            }
        });
    }

    public void IniciarComponentes(){
        recyclerViewFilmes = findViewById(R.id.recycler_view_filmes);

    }
}