package com.example.helpme_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.helpme_app.Interface.Grupo06PyAnyApi;
import com.example.helpme_app.Model.AuthRequest;
import com.example.helpme_app.Model.AuthResponse;
import com.example.helpme_app.databinding.FragmentLoginBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private String token = "";  // Para almacenar el token de forma global

    public LoginFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnNewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NewUser = binding.btnNewLogin.getText().toString();
                LoginFragmentDirections.ActionLoginFragmentToFirstFragment action =
                        LoginFragmentDirections.actionLoginFragmentToFirstFragment(NewUser);
                NavHostFragment.findNavController(LoginFragment.this).navigate(action);
            }
        });


        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUserName.getText().toString();
                String password = binding.edtPassword.getText().toString();
                accederLogin(username, password);
            }
        });
    }

    private void accederLogin(String p_username, String p_password) {
        // Usar Retrofit para hacer login con la API y obtener el token
        Retrofit retrofit = getRetrofit();
        Grupo06PyAnyApi api = retrofit.create(Grupo06PyAnyApi.class);

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(p_username);
        authRequest.setPassword(p_password);

        Call<AuthResponse> call = api.login(authRequest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse != null) {
                        token = authResponse.getToken();  // Guardar el token
                        saveToken(token);  // Guardarlo en SharedPreferences
                        Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(LoginFragment.this)
                                .navigate(R.id.action_loginFragment_to_inicioFragment);
                    }
                } else {
                    Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth_token", token);
        editor.apply();
    }

    private Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(request);
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
