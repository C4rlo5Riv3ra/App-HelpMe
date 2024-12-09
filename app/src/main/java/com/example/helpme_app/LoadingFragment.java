package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helpme_app.databinding.FragmentLoadingBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoadingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadingFragment extends Fragment {

    private FragmentLoadingBinding binding;

    public LoadingFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoadingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hacer que el fragmento se muestre durante 10 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navegar al siguiente fragmento después de 10 segundos
                NavHostFragment.findNavController(LoadingFragment.this)
                        .navigate(R.id.loadingfragment_Fragment_to_login);
            }
        }, 10000); // 10 segundos en milisegundos
    }
}