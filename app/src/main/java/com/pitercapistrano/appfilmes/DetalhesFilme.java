package com.pitercapistrano.appfilmes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetalhesFilme extends AppCompatActivity {

    private ImageView dtCapaFilme, playVideo;
    private TextView dtTituloFilme, dtDescricaoFilme, dtElenco;
    private Toolbar toolbarDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_filme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IniciarComponenetes();

        String capa = getIntent().getExtras().getString("capa");
        String titulo = getIntent().getExtras().getString("titulo");
        String descricao = getIntent().getExtras().getString("descricao");
        String elenco = getIntent().getExtras().getString("elenco");
        String video = getIntent().getExtras().getString("video");

        String stVideo = video;

        Glide.with(getApplicationContext()).load(capa).into(dtCapaFilme);
        dtTituloFilme.setText(titulo);
        dtDescricaoFilme.setText(descricao);
        dtElenco.setText(elenco);

        // Iniciando a Toolbar
        toolbarDetalhes.setNavigationOnClickListener(v -> {
            finish();
        });

        playVideo.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Video.class);
            intent.putExtra("video", stVideo);
            startActivity(intent);
        });

    }
    public void IniciarComponenetes(){
        dtCapaFilme = findViewById(R.id.dt_capa_filme);
        dtTituloFilme = findViewById(R.id.dt_titulo_filme);
        dtDescricaoFilme = findViewById(R.id.dt_descricao_filme);
        dtElenco = findViewById(R.id.dt_elenco_filme);
        toolbarDetalhes = findViewById(R.id.toobar_detalhes);
        playVideo = findViewById(R.id.play_video);
    }
}