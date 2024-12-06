package com.example.helpme_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.Interface.Grupo06PyAnyApi;
import com.example.helpme_app.Model.Asesoria;
import com.example.helpme_app.databinding.FragmentRAseRegistroaseBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RAse_AsesoriaFragment extends Fragment {
    private FragmentRAseRegistroaseBinding binding;

    public RAse_AsesoriaFragment() {
        // Constructor vacío requerido.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRAseRegistroaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupEducationSection();
    }

    private void setupEducationSection() {
        binding.edtipoEnsenianza.setOnClickListener(v -> showTeachingTypeDialog());

        binding.btnContinueTokens.setOnClickListener(v -> {
            if (validateFields()) {
                String nombreAsesoria = binding.ednameAsesoria.getText().toString();
                String tokens = binding.edTokens.getText().toString();
                String duracion = binding.edDurationSesion.getText().toString();

                Asesoria asesoria = new Asesoria(nombreAsesoria, tokens, duracion);
                enviarDatos(asesoria);

                // Navegar a loginFragment
                NavDirections action = RAse_AsesoriaFragmentDirections.actionRAseAsesoriaFragmentToLoginFragment();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });
    }

    private boolean validateFields() {
        String nombreAsesoria = binding.ednameAsesoria.getText().toString();
        String tokens = binding.edTokens.getText().toString();
        String duracion = binding.edDurationSesion.getText().toString();

        if (nombreAsesoria.isEmpty() || tokens.isEmpty() || duracion.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showTeachingTypeDialog() {
        String[] teachingOptions = {"Presencial", "Virtual", "Híbrido"};
        new AlertDialog.Builder(requireContext())
                .setTitle("Selecciona el tipo de enseñanza")
                .setItems(teachingOptions, (dialog, which) -> binding.edtipoEnsenianza.setText(teachingOptions[which]))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void enviarDatos(Asesoria asesoria) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Grupo06PyAnyApi apiService = retrofit.create(Grupo06PyAnyApi.class);
        Call<Void> call = apiService.guardarAsesoria(asesoria);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
