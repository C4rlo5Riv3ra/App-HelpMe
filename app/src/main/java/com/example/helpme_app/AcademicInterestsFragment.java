package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.helpme_app.databinding.FragmentAcademicInterestsBinding;

import com.example.helpme_app.databinding.FragmentAcademicInterestsBinding;
import com.example.helpme_app.databinding.FragmentLoginBinding;

public class AcademicInterestsFragment extends Fragment {
    private FragmentAcademicInterestsBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private boolean[] interesesSeleccionadosEstado = new boolean[9];

    public AcademicInterestsFragment() {
    }

    public static AcademicInterestsFragment newInstance(String param1, String param2) {
        AcademicInterestsFragment fragment = new AcademicInterestsFragment();
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
        // Inflamos el binding
        binding = FragmentAcademicInterestsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configureInterestToggle(binding.interesMatematicas, 0);
        configureInterestToggle(binding.interesTecnologia, 1);
        configureInterestToggle(binding.interesProgramacion, 2);
        configureInterestToggle(binding.interesPsicologia, 3);
        configureInterestToggle(binding.interesEducacionFinanciera, 4);
        configureInterestToggle(binding.interesDesarrolloPersonal, 5);
        configureInterestToggle(binding.interesHistoria, 6);
        configureInterestToggle(binding.interesIdiomas, 7);
        configureInterestToggle(binding.interesIngles, 8);

        binding.btnSiguiente.setOnClickListener(v -> {
            StringBuilder seleccionados = new StringBuilder("Intereses seleccionados:\n");
            for (int i = 0; i < interesesSeleccionadosEstado.length; i++) {
                if (interesesSeleccionadosEstado[i]) {
                    seleccionados.append(getInterestName(i)).append("\n");
                }
            }
            // Puedes hacer algo con la cadena de intereses seleccionados, como un Toast o navegar a otro fragmento
        });
    }

    private void configureInterestToggle(TextView interes, int index) {
        interes.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_outline_blank_24, 0);
        interes.setOnClickListener(view -> {
            if (interesesSeleccionadosEstado[index]) {
                interes.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_outline_blank_24, 0);
                interesesSeleccionadosEstado[index] = false;
            } else {
                interes.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_24, 0);
                interesesSeleccionadosEstado[index] = true;
            }
        });
    }

    private String getInterestName(int index) {
        switch (index) {
            case 0: return "Matemáticas";
            case 1: return "Tecnología";
            case 2: return "Programación";
            case 3: return "Psicología";
            case 4: return "Educación Financiera";
            case 5: return "Desarrollo Personal";
            case 6: return "Historia";
            case 7: return "Idiomas";
            case 8: return "Inglés";
            default: return "Desconocido";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpiamos el binding para evitar fugas de memoria
        binding = null;
    }
}
