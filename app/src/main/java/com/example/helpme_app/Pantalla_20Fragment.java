package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.helpme_app.databinding.FragmentPantalla20Binding;

public class Pantalla_20Fragment extends Fragment {

    // Variables para almacenar los datos ingresados
    private String tipoAsesoria = "";
    private int cantidadTokens = 0;
    private int duracionHoras = 0;

    // Binding para acceder a los componentes del layout
    private FragmentPantalla20Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = FragmentPantalla20Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Paso 1: Manejo de los eventos en el formulario
        binding.btnElegirTokens.setOnClickListener(v -> showTokenSelection());
        binding.btnElegirDuracion.setOnClickListener(v -> showDurationSelection());
        binding.btnFinalizarFormulario.setOnClickListener(v -> showSummary());

        // Paso 2: Confirmación de tokens seleccionados
        binding.btnConfirmarTokens.setOnClickListener(v -> confirmTokens());

        // Paso 3: Confirmación de duración seleccionada
        binding.btnConfirmarDuracion.setOnClickListener(v -> confirmDuration());

        return root;
    }

    // Mostrar la interfaz de selección de Tokens
    private void showTokenSelection() {
        binding.layoutFormulario.setVisibility(View.GONE);
        binding.layoutElegirTokens.setVisibility(View.VISIBLE);
    }

    // Confirmar selección de Tokens
    private void confirmTokens() {
        cantidadTokens = binding.seekBarTokens.getProgress();
        binding.layoutElegirTokens.setVisibility(View.GONE);
        binding.layoutFormulario.setVisibility(View.VISIBLE);
    }

    // Mostrar la interfaz de selección de Duración
    private void showDurationSelection() {
        binding.layoutFormulario.setVisibility(View.GONE);
        binding.layoutElegirDuracion.setVisibility(View.VISIBLE);
    }

    // Confirmar selección de Duración
    private void confirmDuration() {
        duracionHoras = binding.seekBarDuracion.getProgress();
        binding.layoutElegirDuracion.setVisibility(View.GONE);
        binding.layoutFormulario.setVisibility(View.VISIBLE);
    }

    // Mostrar el resumen de la asesoría
    private void showSummary() {
        tipoAsesoria = binding.etTipoAsesoria.getText().toString();

        // Verificar si se ha ingresado un tipo de asesoría
        if (tipoAsesoria.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, ingresa el tipo de asesoría", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar el resumen con los datos seleccionados
        binding.layoutFormulario.setVisibility(View.GONE);
        binding.layoutResumen.setVisibility(View.VISIBLE);
        binding.tvDetalleResumen.setText(
                "Tipo de Asesoría: " + tipoAsesoria + "\n" + "Tokens: " + cantidadTokens + "\n" +"Duración: " + duracionHoras + " horas"
        );
    }
}
