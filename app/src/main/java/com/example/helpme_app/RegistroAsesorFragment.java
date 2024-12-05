package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Interface.Grupo06PyAnyApi;
import com.example.helpme_app.Model.Asesores.AsesorRequest;
import com.example.helpme_app.Model.Asesores.ResponseAsesor;
import com.example.helpme_app.databinding.FragmentRegistroAsesorBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        binding = FragmentRegistroAsesorBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String nombres = binding.etNombres.getText().toString();
        String apellidos = binding.etApellidos.getText().toString();
        String dni = binding.etDocumento.getText().toString();
        //String correo =
        String password = binding.etPassword.getText().toString();
        //String fechaNacimiento = binding.etFechaNacimiento.getText().toString() // validar tipo date


        String email = RegistroAsesorFragmentArgs.fromBundle(getArguments()).getArgEmail();
        String emailFormat = getString(R.string.welconCode, email);
        binding.tvSubTitle.setText(emailFormat);

        binding.btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavDirections action = RegistroAsesorFragmentDirections.actionRegistroAsesorFragmentToRAseEducationFragment();
                //NavHostFragment.findNavController(RegistroAsesorFragment.this).navigate(action);
                nuevoUsuario(nombres, apellidos, dni, password);
            }
        });

    }

    private void nuevoUsuario(String p_nombres, String p_apellidos, String p_dni, String p_password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Grupo06PyAnyApi grupo06PyAnyApi = retrofit.create(Grupo06PyAnyApi.class);
        AsesorRequest asesorRequest = new AsesorRequest();
        asesorRequest.setNombres(p_nombres);
        asesorRequest.setApellido(p_apellidos);
        asesorRequest.setDni(p_dni);
        asesorRequest.setPassword(p_password);
        Call<ResponseAsesor> call = grupo06PyAnyApi.nuevoAsesor(asesorRequest);
        // Llamado asíncrono a nuestro servicio
        call.enqueue(new Callback<ResponseAsesor>() {
            @Override
            public void onResponse(Call<ResponseAsesor> call, Response<ResponseAsesor> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponseAsesor responseAsesor = response.body();
                Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                NavDirections action = RegistroAsesorFragmentDirections.actionRegistroAsesorFragmentToRAseEducationFragment();
                NavHostFragment.findNavController(RegistroAsesorFragment.this).navigate(action);

            }

            @Override
            public void onFailure(Call<ResponseAsesor> call, Throwable t) {

            }
        });


    }


}