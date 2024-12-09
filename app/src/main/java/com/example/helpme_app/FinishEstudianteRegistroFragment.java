package com.example.helpme_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Model.Estudiantes.EstudianteRequest;
import com.example.helpme_app.databinding.FragmentFinishEstudianteRegistroBinding;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishEstudianteRegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishEstudianteRegistroFragment extends Fragment {
    private FragmentFinishEstudianteRegistroBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FinishEstudianteRegistroFragment() {
        // Required empty public constructor
    }

    public static FinishEstudianteRegistroFragment newInstance(String param1, String param2) {
        FinishEstudianteRegistroFragment fragment = new FinishEstudianteRegistroFragment();
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
        // Inflamos el binding correctamente
        binding = FragmentFinishEstudianteRegistroBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Asegúrate de retornar la raíz del binding
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EstudianteRequest estudianteRequest = new EstudianteRequest();

        estudianteRequest.setEstudiante(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgEstudiante());
        estudianteRequest.setPersona(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgPersona());
        estudianteRequest.setUsuario(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getArgUsuario());

        estudianteRequest.setIntereses(FinishEstudianteRegistroFragmentArgs.fromBundle(getArguments()).getSelectedInterests());
        binding.btnFinRegistro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FinishEstudianteRegistroFragment.this)
                        .navigate(R.id.action_FinishEstudianteRegistroFragment_to_loading_Fragment);
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpiamos el binding para evitar fugas de memoria
        binding = null;
    }
}
