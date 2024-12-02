package com.example.helpme_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helpme_app.Interface.Grupo06PyAnyApi;
import com.example.helpme_app.Model.AuthRequest;
import com.example.helpme_app.Model.AuthResponse;
import com.example.helpme_app.databinding.FragmentLoginBinding;
import com.example.helpme_app.databinding.FragmentRegistroAsesorBinding;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private HashMap<String, String> userCredentials;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userCredentials = new HashMap<>();
        userCredentials.put("Estudiante1", "Estudiante1");
        userCredentials.put("Asesor1", "Asesor1");

        binding.btnNewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NewUser = binding.btnNewLogin.getText().toString();
                LoginFragmentDirections.ActionLoginFragmentToFirstFragment action =
                        LoginFragmentDirections.actionLoginFragmentToFirstFragment(NewUser);
                NavHostFragment.findNavController(LoginFragment.this).navigate(action);
            }
        });

        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUserName.getText().toString();
                String password = binding.edtPassword.getText().toString();
/*
                if (authenticate(username, password)) {
                    Toast.makeText(getContext(), "¡Bienvenido, " + username + "!", Toast.LENGTH_SHORT).show();
                    LoginFragmentDirections.ActionLoginFragmentToInicioFragment action =
                            LoginFragmentDirections.actionLoginFragmentToInicioFragment(username);
                    NavHostFragment.findNavController(LoginFragment.this).navigate(action);
                } else {
                    Toast.makeText(getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }

 */
                accederLogin(username, password);
            }
        });
    }
    // Método para verificar las credenciales
    private boolean authenticate(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }


    private void accederLogin(String p_username, String p_passsword){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Grupo06PyAnyApi grupo06PyAnyApi = retrofit.create(Grupo06PyAnyApi.class);
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(p_username);
        authRequest.setPassword(p_passsword);
        Call<AuthResponse> call = grupo06PyAnyApi.obtenerToken(authRequest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    return;
                }
                AuthResponse authResponse = response.body();
                Toast.makeText(getContext(), authResponse.getToken(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });


    }


}