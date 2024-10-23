package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helpme_app.databinding.FragmentRegistroAsesorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroAsesorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroAsesorFragment extends Fragment {
    private FragmentRegistroAsesorBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistroAsesorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroAsesorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroAsesorFragment newInstance(String param1, String param2) {
        RegistroAsesorFragment fragment = new RegistroAsesorFragment();
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_registro_asesor, container, false);
        binding = FragmentRegistroAsesorBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String email = RegistroAsesorFragmentArgs.fromBundle(getArguments()).getArgEmail();
        String emailFormat = getString(R.string.welconCode, email);
        binding.tvSubTitle.setText(emailFormat);

    }


}