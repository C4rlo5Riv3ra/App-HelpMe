package com.example.helpme_app.Interface.api;

import com.example.helpme_app.Model.Estudiantes.EstudianteRequest;

import com.example.helpme_app.Model.InteresesAcademic.ResponseInteresesAcademic;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;
public interface MyApi {
        @GET("/interesesacademicos")
        Call<ResponseInteresesAcademic> getIntereses();

        @POST("/crearestudiante")
        Call<JsonArray> guardarEstudiante(@Body EstudianteRequest asesoriaRequest);
}
