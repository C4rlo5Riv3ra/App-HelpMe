package com.example.helpme_app.Interface.api;

import com.example.helpme_app.Model.Estudiantes.EstudianteRequest;

import com.example.helpme_app.Model.Estudiantes.EstudianteResponse;
import com.example.helpme_app.Model.InteresesAcademic.ResponseInteresesAcademic;
import com.google.gson.JsonArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;
public interface MyApi {
        @GET("/interesesacademicos")
        Call<ResponseInteresesAcademic> getIntereses();
        @POST("/verificar_correo")
        Call<ResponseBody> verificarcorreo(@Body Object datos);
        @POST("/crearestudiante")
        Call<EstudianteResponse> guardarEstudiante(@Body EstudianteRequest estudianteRequest);
}
