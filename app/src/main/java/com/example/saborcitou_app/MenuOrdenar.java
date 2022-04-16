package com.example.saborcitou_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import kotlin.contracts.Returns;


public class MenuOrdenar extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView ListaOrdenes;
    private FirestoreRecyclerAdapter adaptador;

    public MenuOrdenar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_menu_ordenar, container, false);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ListaOrdenes=view.findViewById(R.id.listar_ordenes);
        Button btn_pedido = view.findViewById(R.id.btn_pedido);

        //Consulta
        Query consulta = firebaseFirestore.collection("menu");

        //Opciones del RecycleView
        FirestoreRecyclerOptions<Modulo_ListarOrdenes> opciones = new FirestoreRecyclerOptions.Builder<Modulo_ListarOrdenes>()
                .setQuery(consulta,Modulo_ListarOrdenes.class)
                .build();

        adaptador = new FirestoreRecyclerAdapter<Modulo_ListarOrdenes, ListarOrdenesHolder>(opciones) {
            @NonNull
            @Override
            public ListarOrdenesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View vistaRecycle = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_ordenes_layout,parent,false);
                return new ListarOrdenesHolder(vistaRecycle);
            }

            @Override
            protected void onBindViewHolder(@NonNull ListarOrdenesHolder holder, int position, @NonNull Modulo_ListarOrdenes model) {
                holder.nombreplato.setText(model.getNombre_plato());
                holder.descripcionPlato.setText(model.getDescripcion());
                holder.precioPlato.setText("$"+(model.getPrecio()));
            }
        };
        ListaOrdenes.setHasFixedSize(true);
        ListaOrdenes.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ListaOrdenes.setAdapter(adaptador);

        btn_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController navegador = Navigation.findNavController(view);
                navegador.navigate(R.id.completarOrden);

            }
        });

    }

    // View Holder

    private class ListarOrdenesHolder extends RecyclerView.ViewHolder{

        private TextView nombreplato;
        private TextView descripcionPlato;
        private TextView precioPlato;
        private EditText cantidadPlato;


        public ListarOrdenesHolder(@NonNull View itemView) {
            super(itemView);

            nombreplato=itemView.findViewById(R.id.txt_nombrePlato);
            descripcionPlato=itemView.findViewById(R.id.txt_descripcionPlato);
            precioPlato=itemView.findViewById(R.id.txt_precioPlato);
            cantidadPlato=itemView.findViewById(R.id.edit_cantidad);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adaptador.startListening();
    }
}