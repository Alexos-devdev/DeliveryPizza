package com.example.deliverypizza.service;

import com.example.deliverypizza.entity.RecetaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPizza {
    @GET("recipes") // Asegúrate que sea la ruta correcta
    Call<RecetaResponse> getPizza();  // Ajustar para obtener RecetaResponse
}
