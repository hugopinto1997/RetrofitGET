package com.hugopinto.pruebaretrofit.Interfaz;

import com.hugopinto.pruebaretrofit.Models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getPosts();
}
