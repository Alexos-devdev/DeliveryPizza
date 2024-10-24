package com.example.deliverypizza.service;

import com.example.deliverypizza.entity.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUser {

    @POST("/usuarios/login")
    Call<Usuario> login(@Body Usuario usuario);

    @POST("/usuarios/registrar")
    Call<Usuario> registerUser(@Body Usuario usuario);

}
