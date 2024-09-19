package com.pitercapistrano.appfilmes.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmeApi {

    @GET("filmes.json?alt=media&token=a923f6d0-ecb1-41d8-bafa-0b07ecdcbb3f")
    Call<List<Filme>> getFilmes();

}
