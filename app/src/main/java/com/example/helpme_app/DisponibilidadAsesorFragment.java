package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.helpme_app.databinding.FragmentDisponibilidadAsesorBinding;
import com.example.helpme_app.databinding.FragmentPersonalizacionAcademicaBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
/*
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisponibilidadAsesorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DisponibilidadAsesorFragment extends Fragment {

    private FragmentDisponibilidadAsesorBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDisponibilidadAsesorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.horaInicioButton.setOnClickListener(v -> showBottomSheetDialogHoraInicio());
        binding.horaFinButton.setOnClickListener(v -> showBottomSheetDialogHoraFin());
    }

    private void showBottomSheetDialogHoraInicio() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.boottom_shet_layout_ase_hi, null);
        BottomSheetDialog bottomSheetDialogHoraInicio = new BottomSheetDialog(requireContext());
        bottomSheetDialogHoraInicio.setContentView(bottomSheetView);

        NumberPicker numberPickerHora1 = bottomSheetView.findViewById(R.id.numberPickerHoraInicio);
        NumberPicker numberPickerMinuto1 = bottomSheetView.findViewById(R.id.numberPickerMinutoInicio);
        NumberPicker numberPickerAMPM1 = bottomSheetView.findViewById(R.id.numberPickerAMPM);

        numberPickerHora1.setMinValue(1);
        numberPickerHora1.setMaxValue(12);
        numberPickerHora1.setWrapSelectorWheel(true);

        numberPickerMinuto1.setMinValue(0);
        numberPickerMinuto1.setMaxValue(59);
        numberPickerMinuto1.setWrapSelectorWheel(true);

        String[] ampmValues = {"AM", "PM"};
        numberPickerAMPM1.setMinValue(0);
        numberPickerAMPM1.setMaxValue(1);
        numberPickerAMPM1.setDisplayedValues(ampmValues);
        numberPickerAMPM1.setWrapSelectorWheel(true);

        NumberPicker.OnValueChangeListener onValueChangeListener = (picker, oldVal, newVal) -> {
            int horaSeleccionada = numberPickerHora1.getValue();
            int minutoSeleccionado = numberPickerMinuto1.getValue();
            String ampmSeleccionado = ampmValues[numberPickerAMPM1.getValue()];
            // Formatear la hora seleccionada incluyendo AM/PM
            String horaFormateada = String.format("%02d:%02d %s", horaSeleccionada, minutoSeleccionado, ampmSeleccionado);
            binding.horaInicioButton.setText(horaFormateada);
        };
        numberPickerHora1.setOnValueChangedListener(onValueChangeListener);
        numberPickerMinuto1.setOnValueChangedListener(onValueChangeListener);
        numberPickerAMPM1.setOnValueChangedListener(onValueChangeListener);
        bottomSheetDialogHoraInicio.show();
    }

    private void showBottomSheetDialogHoraFin() {
        View bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.boottom_shet_layout_ase_hf, null);
        BottomSheetDialog bottomSheetDialogHoraFin = new BottomSheetDialog(requireContext());
        bottomSheetDialogHoraFin.setContentView(bottomSheetView);


        NumberPicker numberPickerHora = bottomSheetView.findViewById(R.id.numberPickerHorafin);
        NumberPicker numberPickerMinuto = bottomSheetView.findViewById(R.id.numberPickerMinutoFin);
        NumberPicker numberPickerAMPM = bottomSheetView.findViewById(R.id.numberPicker1AMPM);  // Nuevo NumberPicker para AM/PM

        numberPickerHora.setMinValue(1);
        numberPickerHora.setMaxValue(12);
        numberPickerHora.setWrapSelectorWheel(true);

        numberPickerMinuto.setMinValue(0);
        numberPickerMinuto.setMaxValue(59);
        numberPickerMinuto.setWrapSelectorWheel(true);

        String[] ampmValues = {"AM", "PM"};
        numberPickerAMPM.setMinValue(0);
        numberPickerAMPM.setMaxValue(1);
        numberPickerAMPM.setDisplayedValues(ampmValues);
        numberPickerAMPM.setWrapSelectorWheel(true);

        // Listener para actualizar automáticamente la hora seleccionada
        NumberPicker.OnValueChangeListener onValueChangeListener = (picker, oldVal, newVal) -> {
            int horaSeleccionada = numberPickerHora.getValue();
            int minutoSeleccionado = numberPickerMinuto.getValue();
            String ampmSeleccionado = ampmValues[numberPickerAMPM.getValue()];

            // Formatear la hora seleccionada incluyendo AM/PM
            String horaFormateada = String.format("%02d:%02d %s", horaSeleccionada, minutoSeleccionado, ampmSeleccionado);
            binding.horaFinButton.setText(horaFormateada);
        };
        // Establecer los listeners
        numberPickerHora.setOnValueChangedListener(onValueChangeListener);
        numberPickerMinuto.setOnValueChangedListener(onValueChangeListener);
        numberPickerAMPM.setOnValueChangedListener(onValueChangeListener);
        // Mostrar el BottomSheetDialog
        bottomSheetDialogHoraFin.show();
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisponibilidadAsesorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisponibilidadAsesorFragment.
     */
    // TODO: Rename and change types and number of parameters

    /*
    public static DisponibilidadAsesorFragment newInstance(String param1, String param2) {
        DisponibilidadAsesorFragment fragment = new DisponibilidadAsesorFragment();
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
    }*/

}