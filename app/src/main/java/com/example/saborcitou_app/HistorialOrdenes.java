package com.example.saborcitou_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HistorialOrdenes extends Fragment {

    private FirebaseFirestore firestore;
    private RecyclerView listarHistorial;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    public HistorialOrdenes() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_historial_ordenes, container, false);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        listarHistorial=view.findViewById(R.id.listarhistorial);

        Query consulta = firestore.collection("ordenes");

        //Opciones del RecycleView
        FirestoreRecyclerOptions<Modulo_ListarHistorial> opciones = new FirestoreRecyclerOptions.Builder<Modulo_ListarHistorial>()
                .setQuery(consulta,Modulo_ListarHistorial.class)
                .build();

        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<Modulo_ListarHistorial,ListarHistorialHolder>(opciones) {

            @NonNull
            @Override
            public ListarHistorialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View vistaRecycle = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_historial_layout,parent,false);
                return new ListarHistorialHolder(vistaRecycle);
            }

            @Override
            protected void onBindViewHolder(@NonNull ListarHistorialHolder holder, int position, @NonNull Modulo_ListarHistorial model) {
                holder.cantidadOrden.setText("N° de platos ordenados: "+model.getCantidad_items());
                holder.descripcionOrden.setText(model.getDescripcion_orden());
                holder.totalOrden.setText("Total: $"+(model.getMonto_total()));
            }
        };
        listarHistorial.setHasFixedSize(true);
        listarHistorial.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        listarHistorial.setAdapter(firestoreRecyclerAdapter);

    }

    private class ListarHistorialHolder extends RecyclerView.ViewHolder {

        private TextView cantidadOrden;
        private TextView descripcionOrden;
        private TextView totalOrden;

        public ListarHistorialHolder(@NonNull View itemView) {
            super(itemView);
            cantidadOrden=itemView.findViewById(R.id.txt_cantidadTotal);
            descripcionOrden=itemView.findViewById(R.id.txt_descripcion_orden);
            totalOrden=itemView.findViewById(R.id.txt_total);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }
}