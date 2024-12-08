package com.example.helpme_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helpme_app.databinding.FragmentPersonalizacionAcademicaBinding;
import com.example.helpme_app.databinding.BottomSheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PersonalizacionAcademicaFragment extends Fragment {

    private FragmentPersonalizacionAcademicaBinding binding; // View Binding para el fragmento

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout del fragmento usando View Binding
        binding = FragmentPersonalizacionAcademicaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configurar el botón para mostrar el BottomSheet
        binding.cycleButton.setOnClickListener(v -> showBottomSheetDialog());
        binding.carreraButtom.setOnClickListener(v -> showBottomSheetDialogCarrera());
    }

    private void showBottomSheetDialogCarrera() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout_carreras, null);
        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialogcarrera = new BottomSheetDialog(requireContext());
        bottomSheetDialogcarrera.setContentView(bottomSheetView);

        // Lista de carreras profesionales
        String[] carrerasProfesionales = {
                "Ingeniería de Sistemas", "Ingeniería Civil", "Medicina", "Derecho", "Arquitectura",
                "Administración de Empresas", "Contabilidad", "Psicología", "Ingeniería Industrial",
                "Diseño Gráfico"
        };

        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPickercarrera);
        numberPicker.setMinValue(0); // Índice mínimo
        numberPicker.setMaxValue(carrerasProfesionales.length - 1);
        numberPicker.setDisplayedValues(carrerasProfesionales);
        numberPicker.setWrapSelectorWheel(true);
        // Listener para actualizar el botón cuando se seleccione una carrera
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String carreraSeleccionada = carrerasProfesionales[newVal];
            binding.carreraButtom.setText(carreraSeleccionada);
            //Toast.makeText(requireContext(), "Seleccionaste: " + carreraSeleccionada, Toast.LENGTH_SHORT).show();
        });
        // Acción al cerrar el BottomSheet
        bottomSheetDialogcarrera.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialogcarrera.show();
    }


    private void showBottomSheetDialog() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout, null);

        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        // Encontrar el NumberPicker
        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1); // Valor mínimo
        numberPicker.setMaxValue(10); // Valor máximo
        numberPicker.setWrapSelectorWheel(true); // Comportamiento cíclico

        // Establecer un listener para el cambio de valor en el NumberPicker
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            // Cambiar el estado del botón (puedes cambiar el texto aquí)
            String valorSeleccionado = "Ciclo " + newVal;
            binding.cycleButton.setText(valorSeleccionado); // Actualizar l botón con el valor
            Toast.makeText(requireContext(), valorSeleccionado, Toast.LENGTH_SHORT).show();
        });

        bottomSheetDialog.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitar memory leaks
    }
}
