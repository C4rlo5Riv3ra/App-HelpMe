package com.example.helpme_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helpme_app.Interface.api.MyApi;
import com.example.helpme_app.Model.Estudiante;
import com.example.helpme_app.Model.InteresesAcademic.ResponseInteresesAcademic;
import com.example.helpme_app.Model.Persona;
import com.example.helpme_app.Model.Usuario;
import com.example.helpme_app.databinding.FragmentAcademicInterestsBinding;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AcademicInterestsFragment extends Fragment {
    private FragmentAcademicInterestsBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://grupo6tdam2024.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi apiService = retrofit.create(MyApi.class);

        apiService.getIntereses().enqueue(new Callback<ResponseInteresesAcademic>() {
            @Override
            public void onResponse(Call<ResponseInteresesAcademic> call, Response<ResponseInteresesAcademic> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResponseInteresesAcademic.Interes> intereses = response.body().getData(); // Obtén solo el campo `data`
                    populateInterests(intereses); // Pasa la lista de intereses a tu función
                } else {
                    Toast.makeText(getContext(), "Error al cargar intereses. Intente nuevamente.", Toast.LENGTH_LONG).show();
                    // Imprimir en la consola el detalle del error
                    Log.e("API Error", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseInteresesAcademic> call, Throwable t) {
                Toast.makeText(getContext(), "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
                // Imprimir en la consola el error de conexión
                Log.e("API Failure", "Error de conexión: ", t);
            }
        });

        binding.btnSiguiente.setOnClickListener(v -> {
            Usuario usuario = AcademicInterestsFragmentArgs.fromBundle(getArguments()).getArgUsuario();
            Persona persona = AcademicInterestsFragmentArgs.fromBundle(getArguments()).getArgPersona();
            Estudiante estudiante =AcademicInterestsFragmentArgs.fromBundle(getArguments()).getArgEstudiante();

            List<String> selectedInterests = getSelectedInterests();
            String[] interestsArray = selectedInterests.toArray(new String[0]);

            // Usar Safe Args para crear la acción con los argumentos
            AcademicInterestsFragmentDirections.ActionAcademicInterestsFragmentToFinishEstudianteRegistroFragment action =
                    AcademicInterestsFragmentDirections.actionAcademicInterestsFragmentToFinishEstudianteRegistroFragment(usuario, persona, interestsArray, estudiante);
            NavHostFragment.findNavController(AcademicInterestsFragment.this).navigate(action);
            Toast.makeText(getContext(), "Seleccionados: " + selectedInterests.toString(), Toast.LENGTH_LONG).show();
            // O enviar como argumento
        });
    }
    private List<String> getSelectedInterests() {
        LinearLayout container = binding.interesesContainer;
        List<String> selectedInterests = new ArrayList<>();

        for (int i = 0; i < container.getChildCount(); i++) {
            TextView textView = (TextView) container.getChildAt(i);
            if (textView.getTag() != null && (boolean) textView.getTag()) {
                selectedInterests.add(textView.getText().toString());
            }
        }

        return selectedInterests;
    }


    private void populateInterests(List<ResponseInteresesAcademic.Interes> intereses) {
        LinearLayout container = binding.interesesContainer;
        for (ResponseInteresesAcademic.Interes interes : intereses) {
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setText(interes.getNombre());
            textView.setPadding(16, 16, 16, 16);
            textView.setTextSize(16);
            textView.setBackgroundResource(android.R.drawable.list_selector_background);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_outline_blank_24, 0);
            textView.setOnClickListener(v -> toggleSelection(textView, interes));
            container.addView(textView);
        }
    }

    private void toggleSelection(TextView textView, ResponseInteresesAcademic.Interes interes) {
        boolean isSelected = textView.getTag() != null && (boolean) textView.getTag();
        if (isSelected) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_outline_blank_24, 0);
            textView.setTag(false);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_check_box_24, 0);
            textView.setTag(true);
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpiamos el binding para evitar fugas de memoria
        binding = null;
    }


}
