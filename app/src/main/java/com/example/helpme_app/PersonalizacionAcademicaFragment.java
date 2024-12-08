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

        // Configurar el botón "Siguiente"
        Button nextButton = bottomSheetView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            int selectedValue = numberPicker.getValue();
            Toast.makeText(requireContext(), "Seleccionaste: " + selectedValue, Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        // Acción al cerrar el BottomSheet
        bottomSheetDialog.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );

        // Mostrar el BottomSheet
        bottomSheetDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitar memory leaks
    }
}
