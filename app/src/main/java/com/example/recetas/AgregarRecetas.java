package com.example.recetas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarRecetas extends AppCompatActivity {

    EditText Nreceta,ImagenUrl,Tiempoa,Ingred,Elabo;
    Button AgreRe;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recetas);

        mfirestore = FirebaseFirestore.getInstance();

        Nreceta = (EditText)findViewById(R.id.Nreceta);
        ImagenUrl = (EditText)findViewById(R.id.UrlI);
        Tiempoa = (EditText)findViewById(R.id.Tiempo);
        Ingred = (EditText)findViewById(R.id.Ingre);
        Elabo = (EditText)findViewById(R.id.Elab);

        AgreRe = (Button)findViewById(R.id.AgrR);



        AgreRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nomrec = Nreceta.getText().toString().trim();
                String ImUrl = ImagenUrl.getText().toString().trim();
                String Tiemp = Tiempoa.getText().toString().trim();
                String Ingr = Ingred.getText().toString().trim();
                String Ela = Elabo.getText().toString().trim();

                if (Nomrec.isEmpty() && ImUrl.isEmpty() && Tiemp.isEmpty() && Ingr.isEmpty() && Ela.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los datos",Toast.LENGTH_SHORT);
                }
                else {
                    postReceta(Nomrec,ImUrl,Tiemp,Ingr,Ela);
                }

            }
        });
    }

    private void postReceta(String nomrec, String imUrl, String tiemp, String ingr, String ela) {
        Map<String,Object> map = new HashMap<>();
        map.put("Nombre",nomrec);
        map.put("Imagen",imUrl);
        map.put("Tiempo",tiemp);
        map.put("Ingredientes",ingr);
        map.put("Elaboracion",ela);
            mfirestore.collection("Recetas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(),"Creado con Exito",Toast.LENGTH_SHORT);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Error al ingresar",Toast.LENGTH_SHORT);
                }
            });
    }

}