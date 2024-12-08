package com.example.helpme_app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helpme_app.Model.Persona;
import com.example.helpme_app.Model.Usuario;
import com.example.helpme_app.databinding.FragmentRegistroEstudianteBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroEstudianteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroEstudianteFragment extends Fragment {
    private FragmentRegistroEstudianteBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistroEstudianteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroEstudianteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroEstudianteFragment newInstance(String param1, String param2) {
        RegistroEstudianteFragment fragment = new RegistroEstudianteFragment();
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
        binding = FragmentRegistroEstudianteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        binding.btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = RegistroEstudianteFragmentArgs.fromBundle(getArguments()).getArgUsuario();
                Persona persona = new Persona();

                if (binding.etNombres.getText().toString().trim().isEmpty() ||
                        binding.etApellidos.getText().toString().trim().isEmpty() ||
                        binding.etDocumento.getText().toString().trim().isEmpty() ||
                        binding.etFechaNacimiento.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                persona.setNombre(binding.etNombres.getText().toString().trim());
                persona.setApellidos(binding.etApellidos.getText().toString().trim());
                persona.setDni(binding.etDocumento.getText().toString().trim());
                Date fecha = obtenerFechaNacimiento(binding.etFechaNacimiento);
                if (fecha == null) return; // Validación de la fecha
                persona.setFechanacimiento(fecha);

                if (!binding.cbAceptarTerminos.isChecked()) {
                    Toast.makeText(getContext(), "Debes aceptar los términos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailFormat = getString(R.string.welconCode, usuario.getEmail());
                binding.tvSubTitle.setText(emailFormat);

                RegistroEstudianteFragmentDirections.ActionRegistroEstudianteFragmentToPersonalizacionacademicaFragment action =
                        RegistroEstudianteFragmentDirections.actionRegistroEstudianteFragmentToPersonalizacionacademicaFragment(usuario, persona);
                NavHostFragment.findNavController(RegistroEstudianteFragment.this).navigate(action);
            }
        });
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechaSeleccionada = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        binding.etFechaNacimiento.setText(fechaSeleccionada);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    public Date obtenerFechaNacimiento(EditText etFechaNacimiento) {
        String fechaStr = etFechaNacimiento.getText().toString();

        if (fechaStr.isEmpty()) {
            Toast.makeText(getContext(), "Por favor ingresa una fecha", Toast.LENGTH_SHORT).show();
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date fechaNacimiento = null;

        try {
            fechaNacimiento = sdf.parse(fechaStr);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Fecha inválida, usa el formato correcto (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        int edad = calendar.get(Calendar.YEAR) - (fechaNacimiento.getYear() + 1900);

        if (edad < 18) {
            Toast.makeText(getContext(), "Debe tener más de 18 años", Toast.LENGTH_SHORT).show();
            return null;
        }

        return fechaNacimiento;
    }

}