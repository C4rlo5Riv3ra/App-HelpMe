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
        binding = FragmentPersonalizacionAcademicaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.carreraButtom.setOnClickListener(v -> showBottomSheetDialogCarrera());
        binding.universityButton.setOnClickListener(v -> showBottomSheetDialogInstitucion());
        binding.cycleButton.setOnClickListener(v -> showBottomSheetDialog());
    }

    private void showBottomSheetDialogInstitucion() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout_institucion, null);

        BottomSheetDialog bottomSheetDialogInstitucion = new BottomSheetDialog(requireContext());
        bottomSheetDialogInstitucion.setContentView(bottomSheetView);

        String[] instituciones = {
                "Universidad Nacional Mayor de San Marcos (UNMSM)",
                "Pontificia Universidad Católica del Perú (PUCP)",
                "Universidad de San Martín de Porres (USMP)",
                "Universidad Nacional de San Agustín de Arequipa (UNSA)",
                "Universidad Ricardo Palma (URP)",
                "Universidad ESAN",
                "Universidad Señor de Sipan (USS)",
                "Universidad de Lima (UL)",
                "Universidad Peruana Cayetano Heredia (UPCH)",
                "Universidad Nacional de Ingeniería (UNI)",
                "Otro"
        };
        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPicker3);
        numberPicker.setMinValue(0); // Índice mínimo
        numberPicker.setMaxValue(instituciones.length - 1); // Índice máximo
        numberPicker.setDisplayedValues(instituciones); // Muestra las instituciones en el picker
        numberPicker.setWrapSelectorWheel(true); // Hacer cíclico (opcional)

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String institucionSeleccionada = instituciones[newVal];
            binding.universityButton.setText(institucionSeleccionada);
            //Toast.makeText(requireContext(), "Seleccionaste: " + institucionSeleccionada, Toast.LENGTH_SHORT).show();
        });
        bottomSheetDialogInstitucion.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialogInstitucion.show();
    }

    private void showBottomSheetDialogCarrera() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout_carreras, null);
        BottomSheetDialog bottomSheetDialogcarrera = new BottomSheetDialog(requireContext());
        bottomSheetDialogcarrera.setContentView(bottomSheetView);
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
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String carreraSeleccionada = carrerasProfesionales[newVal];
            binding.carreraButtom.setText(carreraSeleccionada);
            //Toast.makeText(requireContext(), "Seleccionaste: " + carreraSeleccionada, Toast.LENGTH_SHORT).show();
        });
        bottomSheetDialogcarrera.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialogcarrera.show();
    }

    private void showBottomSheetDialog() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1); // Valor mínimo
        numberPicker.setMaxValue(10); // Valor máximo
        numberPicker.setWrapSelectorWheel(true); // Comportamiento cíclico
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String valorSeleccionado = "Ciclo " + newVal;
            binding.cycleButton.setText(valorSeleccionado); // Actualizar l botón con el valor
           // Toast.makeText(requireContext(), valorSeleccionado, Toast.LENGTH_SHORT).show();
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
