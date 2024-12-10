package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Interface.api.MyApi;
import com.example.helpme_app.Model.Estudiantes.EstudianteRequest;
import com.example.helpme_app.databinding.FragmentFinishEstudianteRegistroBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishEstudianteRegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishEstudianteRegistroFragment extends Fragment {
    private FragmentFinishEstudianteRegistroBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FinishEstudianteRegistroFragment() {
        // Required empty public constructor
    }

    public static FinishEstudianteRegistroFragment newInstance(String param1, String param2) {
        FinishEstudianteRegistroFragment fragment = new FinishEstudianteRegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflamos el binding correctamente
        binding = FragmentFinishEstudianteRegistroBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Asegúrate de retornar la raíz del binding
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EstudianteRequest estudianteRequest = new EstudianteRequest();

        estudianteRequest.setEstudiante(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgEstudiante());
        estudianteRequest.setPersona(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgPersona());
        estudianteRequest.setUsuario(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgUsuario());

        estudianteRequest.setIntereses(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getSelectedInterests());
        binding.btnFinRegistro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarestudiante(estudianteRequest);

            }
        });

    }

    private void registrarestudiante(EstudianteRequest estudianteRequest) {
        Retrofit retrofit = getRetrofit();
        MyApi api = retrofit.create(MyApi.class);

        Call<JsonArray> call = api.guardarEstudiante(estudianteRequest);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    // Asegúrate de que la respuesta sea un JsonArray válido
                    if (response.body() != null) {
                        // Obtener el primer objeto del JsonArray (suponiendo que siempre hay un solo objeto)
                        JsonObject jsonResponse = response.body().get(0).getAsJsonObject();

                        // Obtener el valor del campo "status"
                        int status = jsonResponse.get("status").getAsInt();

                        // Comprobar si el registro fue exitoso o hubo un error
                        if (status == 1) {
                            // Registro exitoso
                            Toast.makeText(getContext(), "Estudiante registrado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error en el registro
                            String mensajeError = jsonResponse.get("message").getAsString();
                            Toast.makeText(getContext(), "Error: " + mensajeError, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Si la respuesta no fue exitosa (por ejemplo, código 400 o 500)
                    Toast.makeText(getContext(), "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                // Error de red o algo falló al hacer la solicitud
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())

                .build();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpiamos el binding para evitar fugas de memoria
        binding = null;
    }
}
