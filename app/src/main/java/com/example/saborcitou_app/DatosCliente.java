package com.example.saborcitou_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class DatosCliente extends Fragment {


    public DatosCliente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_cliente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnOrdenar = view.findViewById(R.id.btn_ordenar);
        Button btnHistorial = view.findViewById(R.id.btn_historial);
        EditText IDCliente =(EditText) view.findViewById(R.id.edit_idcliente);

        final NavController navegador = Navigation.findNavController(view);

        btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDCliente.setError(null);
                String posibleNumero = IDCliente.getText().toString();
                if ("".equals(posibleNumero)) {
                    IDCliente.setError("Introduce un DUI");
                    IDCliente.requestFocus();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("idCliente",IDCliente.getText().toString());
                getParentFragmentManager().setFragmentResult("DatosCliente",bundle);
                navegador.navigate(R.id.menuOrdenar);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDCliente.setError(null);
                String posibleNumero = IDCliente.getText().toString();
                if ("".equals(posibleNumero)) {
                    IDCliente.setError("Introduce un DUI");
                    IDCliente.requestFocus();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("idCliente",IDCliente.getText().toString());
                getParentFragmentManager().setFragmentResult("DatosCliente",bundle);
                navegador.navigate(R.id.historialOrdenes);
            }
        });

    }
}