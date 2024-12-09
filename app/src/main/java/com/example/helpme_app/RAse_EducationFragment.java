package com.example.helpme_app;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.helpme_app.databinding.FragmentRAseEducationBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RAse_EducationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RAse_EducationFragment extends Fragment {
    private FragmentRAseEducationBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RAse_EducationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RAse_EducationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RAse_EducationFragment newInstance(String param1, String param2) {
        RAse_EducationFragment fragment = new RAse_EducationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRAseEducationBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.edEspecialidad.setOnClickListener(v -> showBottomSheetDialogEspecialidad());
        binding.edAniosEx.setOnClickListener(v -> showBottomSheetDialogAniosExperiencia());
        binding.edtipoEnsenianza.setOnClickListener(v -> showBottomSheetDialogTipoEnseñanza());
        //  binding.edDisponibilidad.setOnClickListener(v -> showBottomSheetDialogDisponibilidad());
        // binding.edTokens.setOnClickListener(v -> showBottomSheetDialogToken());

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RAse_EducationFragmentDirections.actionRAseEducationFragmentToDisponibilidadAsesorFragment();
                NavHostFragment.findNavController(RAse_EducationFragment.this).navigate(action);
            }
        });

    }

    private void showBottomSheetDialogEspecialidad() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout0_ase, null);

        BottomSheetDialog bottomSheetDialogEspecialidad = new BottomSheetDialog(requireContext());
        bottomSheetDialogEspecialidad.setContentView(bottomSheetView);

        String[] especialidades = {
                "Ingeniería de Sistemas (UNMSM)",
                "Arquitectura (PUCP)",
                "Derecho (USMP)",
                "Medicina (UNSA)",
                "Psicología (URP)",
                "Administración de Empresas (ESAN)",
                "Ingeniería Industrial (USS)",
                "Ciencias Sociales (UL)",
                "Ciencias de la Salud (UPCH)",
                "Ingeniería Civil (UNI)",
                "Otro"

        };
        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPickerEspecialidad);
        numberPicker.setMinValue(0); // Índice mínimo
        numberPicker.setMaxValue(especialidades.length - 1); // Índice máximo
        numberPicker.setDisplayedValues(especialidades); // Muestra las instituciones en el picker
        numberPicker.setWrapSelectorWheel(true); // Hacer cíclico (opcional)

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String institucionSeleccionada = especialidades[newVal];
            binding.edEspecialidad.setText(institucionSeleccionada);
            //Toast.makeText(requireContext(), "Seleccionaste: " + institucionSeleccionada, Toast.LENGTH_SHORT).show();
        });
        bottomSheetDialogEspecialidad.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialogEspecialidad.show();
    }

    private void showBottomSheetDialogAniosExperiencia() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout1_ase, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPickerExperiencia);
        numberPicker.setMinValue(1); // Valor mínimo
        numberPicker.setMaxValue(15); // Valor máximo
        numberPicker.setWrapSelectorWheel(true); // Comportamiento cíclico
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String valorElegido = "Experiencia "+ "mas de " + newVal+ " años";
            binding.edAniosEx.setText(valorElegido); // Actualizar l botón con el valor
            // Toast.makeText(requireContext(), valorElegido, Toast.LENGTH_SHORT).show();
        });
        bottomSheetDialog.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialog.show();
    }

    private void showBottomSheetDialogTipoEnseñanza() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout2_ase, null);

        BottomSheetDialog bottomSheetDialogEspecialidad = new BottomSheetDialog(requireContext());
        bottomSheetDialogEspecialidad.setContentView(bottomSheetView);

        String[] tipoEnseñanza  = {"Virtual","Presencial","Híbrida" };

        NumberPicker numberPicker = bottomSheetView.findViewById(R.id.numberPickerEnseñanza);
        numberPicker.setMinValue(0); // Índice mínimo
        numberPicker.setMaxValue(tipoEnseñanza.length - 1); // Índice máximo
        numberPicker.setDisplayedValues(tipoEnseñanza); // Muestra las instituciones en el picker
        numberPicker.setWrapSelectorWheel(true); // Hacer cíclico (opcional)

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            String ensenianzaSeleccionada = tipoEnseñanza[newVal];
            binding.edtipoEnsenianza.setText(ensenianzaSeleccionada);
            //Toast.makeText(requireContext(), "Seleccionaste: " + institucionSeleccionada, Toast.LENGTH_SHORT).show();
        });
        bottomSheetDialogEspecialidad.setOnDismissListener(dialogInterface ->
                Toast.makeText(requireContext(), "Bottom sheet cerrado", Toast.LENGTH_SHORT).show()
        );
        bottomSheetDialogEspecialidad.show();
    }

}