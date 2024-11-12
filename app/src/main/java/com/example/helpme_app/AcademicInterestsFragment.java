package com.example.helpme_app;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class AcademicInterestsFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_academic_interests, container, false);

        TextView interesMatematicas = view.findViewById(R.id.interes_matematicas);
        TextView interesTecnologia = view.findViewById(R.id.interes_tecnologia);
        TextView interesProgramacion = view.findViewById(R.id.interes_programacion);
        TextView interesPsicologia = view.findViewById(R.id.interes_psicologia);
        TextView interesEducacionFinanciera = view.findViewById(R.id.interes_educacion_financiera);
        TextView interesDesarrolloPersonal = view.findViewById(R.id.interes_desarrollo_personal);
        TextView interesHistoria = view.findViewById(R.id.interes_historia);
        TextView interesIdiomas = view.findViewById(R.id.interes_idiomas);
        TextView interesIngles = view.findViewById(R.id.interes_ingles);

        configureInterestToggle(interesMatematicas, 0);
        configureInterestToggle(interesTecnologia, 1);
        configureInterestToggle(interesProgramacion, 2);
        configureInterestToggle(interesPsicologia, 3);
        configureInterestToggle(interesEducacionFinanciera, 4);
        configureInterestToggle(interesDesarrolloPersonal, 5);
        configureInterestToggle(interesHistoria, 6);
        configureInterestToggle(interesIdiomas, 7);
        configureInterestToggle(interesIngles, 8);

        Button btnSiguiente = view.findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(v -> {

            // Aquí podrías enviar los intereses seleccionados a otro fragmento o actividad
            StringBuilder seleccionados = new StringBuilder("Intereses seleccionados:\n");
            for (int i = 0; i < interesesSeleccionadosEstado.length; i++) {
                if (interesesSeleccionadosEstado[i]) {
                    seleccionados.append(getInterestName(i)).append("\n");
                }
            }

            // Puedes hacer una acción con esta cadena, por ejemplo, un Toast o pasar a otro fragmento
        });

        return view;
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
}
