package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.Interface.api.MyApi;
import com.example.helpme_app.Model.Usuario;
import com.example.helpme_app.databinding.FragmentFirstBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtEmail.getText().toString().trim(); // Eliminar espacios al inicio y final

                // Verificar si el campo está vacío
                if (email.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor ingresa un email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Realizar la llamada al método POST para verificar el email
                verificarEmail(email, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            try {
                                // Parsear la respuesta JSON
                                JSONObject jsonResponse = new JSONObject(response.body().string());
                                String message = jsonResponse.getString("message");
                                int status = jsonResponse.getInt("status");

                                // Verificar si el correo existe
                                if (status == 1) { // El correo ya está registrado
                                    Toast.makeText(getContext(), "El correo ya está registrado", Toast.LENGTH_SHORT).show();
                                } else { // El correo no existe
                                    Toast.makeText(getContext(), "El correo no existe en la base de datos", Toast.LENGTH_SHORT).show();

                                    // Crear objeto usuario
                                    Usuario usuario = new Usuario();
                                    usuario.setEmail(email);

                                    // Navegar al siguiente fragmento
                                    FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                                            FirstFragmentDirections.actionFirstFragmentToSecondFragment(usuario);
                                    NavHostFragment.findNavController(FirstFragment.this).navigate(action);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error al verificar el correo: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    private void verificarEmail(String email, Callback<ResponseBody> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/") // Cambia esto por la URL base de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);

        // Crear un objeto JSON para enviar el email
        Map<String, String> body = new HashMap<>();
        body.put("email", email);

        // Llamar al método POST
        Call<ResponseBody> call = api.verificarcorreo(body);
        call.enqueue(callback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}