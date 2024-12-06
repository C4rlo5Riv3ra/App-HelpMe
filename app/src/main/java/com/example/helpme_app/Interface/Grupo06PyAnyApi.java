package com.example.helpme_app.Interface;

import com.example.helpme_app.Model.Asesores.AsesorRequest;
import com.example.helpme_app.Model.Asesores.ResponseAsesor;
import com.example.helpme_app.Model.Asesoria;
import com.example.helpme_app.Model.AuthRequest;
import com.example.helpme_app.Model.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Grupo06PyAnyApi {
    @POST("/login")
    Call<AuthResponse> login(@Body AuthRequest authRequest);

    @POST("/crearasesor")
    Call<ResponseAsesor> nuevoAsesor(@Body AsesorRequest asesorRequest);

    @POST("/guardar_asesoria")
    Call<Void> guardarAsesoria(@Body Asesoria asesoria);
}

