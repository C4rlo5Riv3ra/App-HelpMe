package com.example.helpme_app.Interface;

import com.example.helpme_app.Model.AuthRequest;
import com.example.helpme_app.Model.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Grupo06PyAnyApi {

    @POST("login")
    Call<AuthResponse> obtenerToken(@Body AuthRequest authRequest);
}
