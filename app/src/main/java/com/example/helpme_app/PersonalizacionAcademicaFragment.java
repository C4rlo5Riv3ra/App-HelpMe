package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.Model.Estudiante;
import com.example.helpme_app.Model.Persona;
import com.example.helpme_app.Model.Usuario;
import com.example.helpme_app.databinding.FragmentPersonalizacionAcademicaBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PersonalizacionAcademicaFragment extends Fragment {

    private FragmentPersonalizacionAcademicaBinding binding;

    // Variables para almacenar los valores seleccionados
    private String institucionSeleccionada;
    private String carreraSeleccionada;
    private String cicloSeleccionado;

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

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = PersonalizacionAcademicaFragmentArgs.fromBundle(getArguments()).getArgUsuario();
                Persona persona = PersonalizacionAcademicaFragmentArgs.fromBundle(getArguments()).getArgPersona();
                Estudiante estudiante = new Estudiante();
                Toast.makeText(requireContext(), "Si pasa go next", Toast.LENGTH_SHORT).show();
                if (institucionSeleccionada != null && carreraSeleccionada != null && cicloSeleccionado != null) {
                    // Acciones al presionar "Siguiente"
                    int cicloInt = Integer.parseInt(cicloSeleccionado.replace("Ciclo ", ""));

                    estudiante.setUniversidad(institucionSeleccionada);
                    estudiante.setCarrera(carreraSeleccionada);
                    estudiante.setNivelstudios(cicloInt);
                    String mensaje = "ciclo: " + cicloSeleccionado;
                    Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show();
                    PersonalizacionAcademicaFragmentDirections.ActionAcademicInterestsFragmentToAcademicInterestsFragment action =
                            PersonalizacionAcademicaFragmentDirections.actionAcademicInterestsFragmentToAcademicInterestsFragment(usuario, persona, estudiante);
                    NavHostFragment.findNavController(PersonalizacionAcademicaFragment.this).navigate(action);
                } else {
                    Toast.makeText(requireContext(), "Por favor selecciona todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }

        });
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
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(instituciones.length - 1);
        numberPicker.setDisplayedValues(instituciones);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            institucionSeleccionada = instituciones[newVal];
            binding.universityButton.setText(institucionSeleccionada);
        });

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
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(carrerasProfesionales.length - 1);
        numberPicker.setDisplayedValues(carrerasProfesionales);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            carreraSeleccionada = carrerasProfesionales[newVal];
            binding.carreraButtom.setText(carreraSeleccionada);
        });

        bottomSheetDialogcarrera.show();
    }

    private void showBottomSheetDialog() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            cicloSeleccionado = "Ciclo " + newVal;
            binding.cycleButton.setText(cicloSeleccionado);
        });

        bottomSheetDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
