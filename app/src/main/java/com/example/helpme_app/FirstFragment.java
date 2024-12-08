package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.Model.Usuario;
import com.example.helpme_app.databinding.FragmentFirstBinding;

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
                String email = binding.edtEmail.getText().toString().trim(); // Usamos trim() para eliminar espacios al inicio y al final

                // Verificar si el campo está vacío
                if (email.isEmpty()) {
                    // Si el campo está vacío, mostramos un mensaje de error
                    Toast.makeText(getContext(), "Por favor ingresa un email", Toast.LENGTH_SHORT).show();
                    return; // Detener la ejecución si el campo está vacío
                }
                Usuario usuario = new Usuario();
                usuario.setEmail(email);

                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(usuario);
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}