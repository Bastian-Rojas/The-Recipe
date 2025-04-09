package com.example.recetas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recetas.Model.Comida;
import com.example.recetas.adapter.Comidaadapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ListComida extends AppCompatActivity {

    Button btn_add;
    RecyclerView mReclycer;
    Comidaadapter mAdapter;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comida);

        mFirestore = FirebaseFirestore.getInstance();
        mReclycer = findViewById(R.id.recycler_food);
        mReclycer.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("Receta");

        FirestoreRecyclerOptions<Comida> FirestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Comida>().setQuery(query, Comida.class).build();

        mAdapter = new Comidaadapter(FirestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mReclycer.setAdapter(mAdapter);
        btn_add = findViewById(R.id.AgregarR);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent add = new Intent(ListComida.this,AgregarRecetas.class);
                startActivity(add);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.stopListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}