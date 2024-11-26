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
import com.example.helpme_app.databinding.FragmentRAseEducationBinding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRAseEducationBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.edtipoEnsenianza.setOnClickListener(v -> showTeachingTypeDialog());

        binding.edTokens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RAse_EducationFragmentDirections.actionRAseEducationFragmentToRAseTokensFragment();
                NavHostFragment.findNavController(RAse_EducationFragment.this).navigate(action);
            }
        });

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RAse_EducationFragmentDirections.actionRAseEducationFragmentToLoginFragment();
                NavHostFragment.findNavController(RAse_EducationFragment.this).navigate(action);
            }
        });


    }

    private void showTeachingTypeDialog() {
        // Opciones para el diálogo
        String[] teachingOptions = {"Presencial", "Virtual", "Híbrido"};

        // Crear y mostrar el diálogo
        new AlertDialog.Builder(requireContext())
                .setTitle("Selecciona el tipo de enseñanza")
                .setItems(teachingOptions, (dialog, which) -> {
                    // Establecer la opción seleccionada en el EditText
                    binding.edtipoEnsenianza.setText(teachingOptions[which]);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }



}