package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Interface.Grupo06PyAnyApi;
import com.example.helpme_app.Model.Asesores.AsesorRequest;
import com.example.helpme_app.Model.Asesores.ResponseAsesor;
import com.example.helpme_app.databinding.FragmentRegistroAsesorBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroAsesorFragment extends Fragment {
    private FragmentRegistroAsesorBinding binding;

    // Constructor vacío requerido
    public RegistroAsesorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistroAsesorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recupera el correo desde los argumentos
        String email = RegistroAsesorFragmentArgs.fromBundle(getArguments()).getArgUsuario().getEmail();
        String emailFormat = getString(R.string.welconCode, email);
        binding.tvSubTitle.setText(emailFormat);

        binding.btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén los datos ingresados por el usuario
                String nombres = binding.etNombres.getText().toString().trim();
                String apellidos = binding.etApellidos.getText().toString().trim();
                String dni = binding.etDocumento.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                // Valida los campos antes de proceder
                if (validarFormulario(nombres, apellidos, dni, password)) {
                    // Si todo está bien, crea el nuevo usuario
                    nuevoUsuario(nombres, apellidos, dni, password);
                }
            }
        });
    }

    /**
     * Método para validar los datos ingresados por el usuario
     */
    private boolean validarFormulario(String nombres, String apellidos, String dni, String password) {
        if (nombres.isEmpty()) {
            binding.etNombres.setError("El nombre es obligatorio");
            return false;
        }
        if (apellidos.isEmpty()) {
            binding.etApellidos.setError("El apellido es obligatorio");
            return false;
        }
        if (dni.isEmpty() || dni.length() != 8 || !dni.matches("\\d+")) {
            binding.etDocumento.setError("El DNI debe tener 8 dígitos numéricos");
            return false;
        }
        if (password.isEmpty() || password.length() < 8) {
            binding.etPassword.setError("La contraseña debe tener al menos 8 caracteres");
            return false;
        }
        return true;
    }

    /**
     * Método para registrar un nuevo usuario en el servidor
     */
    private void nuevoUsuario(String p_nombres, String p_apellidos, String p_dni, String p_password) {
        // Configuración de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Grupo06PyAnyApi grupo06PyAnyApi = retrofit.create(Grupo06PyAnyApi.class);

        // Crea el objeto AsesorRequest
        AsesorRequest asesorRequest = new AsesorRequest();
        asesorRequest.setNombres(p_nombres);
        asesorRequest.setApellido(p_apellidos);
        asesorRequest.setDni(p_dni);
        asesorRequest.setPassword(p_password);

        // Llamada al servicio
        Call<ResponseAsesor> call = grupo06PyAnyApi.nuevoAsesor(asesorRequest);

        // Manejo de la respuesta asíncrona
        call.enqueue(new Callback<ResponseAsesor>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAsesor> call, Response<ResponseAsesor> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "¡Ocurrió un error al registrar el usuario!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Usuario registrado exitosamente
                Toast.makeText(getActivity(), "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();

                // Navegar al siguiente fragmento
                NavDirections action = RegistroAsesorFragmentDirections.actionRegistroAsesorFragmentToRAseAsesoriaFragment();
                NavHostFragment.findNavController(RegistroAsesorFragment.this).navigate(action);
            }

            @Override
            public void onFailure(Call<ResponseAsesor> call, Throwable t) {
                // Error en la conexión o el servidor
                Toast.makeText(getActivity(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
