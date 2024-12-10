package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Interface.api.MyApi;
import com.example.helpme_app.Model.Estudiantes.EstudianteRequest;
import com.example.helpme_app.Model.Estudiantes.EstudianteResponse;
import com.example.helpme_app.databinding.FragmentFinishEstudianteRegistroBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

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

        Call<EstudianteResponse> call = api.guardarEstudiante(estudianteRequest);
        call.enqueue(new Callback<EstudianteResponse>() {
            @Override
            public void onResponse(Call<EstudianteResponse> call, Response<EstudianteResponse> response) {
                Log.d("API_CALL", "onResponse: Llamada completada con código: " + response.code());

                if (response.isSuccessful()) {
                    EstudianteResponse respuesta = response.body();
                    if (respuesta != null) {
                        Log.d("API_CALL", "Respuesta recibida: " + respuesta.toString());
                        if (respuesta.getStatus() == 1) {
                            Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Log.d("API_CALL", "Registro exitoso: Navegando a siguiente pantalla");
                            NavHostFragment.findNavController(FinishEstudianteRegistroFragment.this)
                                    .navigate(R.id.action_FinishEstudianteRegistroFragment_to_loading_Fragment);
                        } else {
                            Log.e("API_CALL", "Error del servidor: " + respuesta.getMenssage());
                            Toast.makeText(getContext(), "Error del servidor: " + respuesta.getMenssage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("API_CALL", "Respuesta nula desde el servidor.");
                        Toast.makeText(getContext(), "Respuesta nula desde el servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_CALL", "Error no exitoso con código: " + response.code() + ", cuerpo del error: " + errorBody);
                        Toast.makeText(getContext(), "Error: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Log.e("API_CALL", "Error al leer el cuerpo del error: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<EstudianteResponse> call, Throwable t) {
                Log.e("API_CALL", "Fallo en la llamada: " + t.getMessage());
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
