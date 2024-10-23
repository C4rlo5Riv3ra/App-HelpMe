package com.example.helpme_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String txtemail = SecondFragmentArgs.fromBundle(getArguments()).getArgEmail();


        binding.bntSTUDENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragmentDirections.ActionSecondFragmentToRegistroEstudianteFragment action =
                        SecondFragmentDirections.actionSecondFragmentToRegistroEstudianteFragment(txtemail);
                NavHostFragment.findNavController(SecondFragment.this).navigate(action);

            }
        });

        binding.bntADVISER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragmentDirections.ActionSecondFragmentToRegistroAsesorFragment action =
                        SecondFragmentDirections.actionSecondFragmentToRegistroAsesorFragment(txtemail);
                NavHostFragment.findNavController(SecondFragment.this).navigate(action);
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}