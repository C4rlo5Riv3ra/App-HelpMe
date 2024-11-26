package com.example.helpme_app;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helpme_app.databinding.FragmentRAseEducationBinding;
import com.example.helpme_app.databinding.FragmentRAseTokensBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RAse_TokensFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RAse_TokensFragment extends Fragment {
    private FragmentRAseTokensBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RAse_TokensFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RAse_TokensFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RAse_TokensFragment newInstance(String param1, String param2) {
        RAse_TokensFragment fragment = new RAse_TokensFragment();
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
        binding = FragmentRAseTokensBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.edTokens.setOnClickListener(v -> showTokensForSesion() );
        binding.edDurationSesion.setOnClickListener(c -> showDurationForSesion());

    }

    private void showTokensForSesion() {
        // Opciones para el diálogo
        String[] tokensOptions = {"10 tokens", "20 tokens", "30 tokens",
                "40 tokens", "50 tokens", "60 tokens",
                "70 tokens", "80 tokens", "90 tokens",
                "100 tokens", "150 tokens", "200 tokens"};

        // Crear y mostrar el diálogo
        new AlertDialog.Builder(requireContext())
                .setTitle("Tokens")
                .setItems(tokensOptions, (dialog, which) -> {
                    // Establecer la opción seleccionada en el EditText
                    binding.edTokens.setText(tokensOptions[which]);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showDurationForSesion() {
        // Opciones para el diálogo
        String[] hoursOptions = {"30 min", "60 min", "90 min :",
                "120 min", "3 horas", " 4 horas", "5 horas"};

        // Crear y mostrar el diálogo
        new AlertDialog.Builder(requireContext())
                .setTitle("Tokens")
                .setItems(hoursOptions, (dialog, which) -> {
                    // Establecer la opción seleccionada en el EditText
                    binding.edDurationSesion.setText(hoursOptions[which]);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

}