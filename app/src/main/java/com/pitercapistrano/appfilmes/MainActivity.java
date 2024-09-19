package com.pitercapistrano.appfilmes;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pitercapistrano.appfilmes.adapter.AdapterFilmes;
import com.pitercapistrano.appfilmes.model.Filme;

import java.util.ArrayList;
import java.util.List;

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
        adapterFilme = new AdapterFilmes(getApplicationContext(), filmeList);
        recyclerViewFilmes.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewFilmes.setHasFixedSize(true);
        recyclerViewFilmes.setAdapter(adapterFilme);

        Filme filme1 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme1);

        Filme filme2 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme2);

        Filme filme3 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme3);

        Filme filme4 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme4);

        Filme filme5 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme5);

        Filme filme6 = new Filme(R.drawable.ic_launcher_background, "Titanic");
        filmeList.add(filme6);
    }

    public void IniciarComponentes(){
        recyclerViewFilmes = findViewById(R.id.recycler_view_filmes);
    }
}