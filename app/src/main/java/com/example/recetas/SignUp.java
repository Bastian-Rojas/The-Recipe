package com.example.recetas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recetas.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtphone,edtname,edtpassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtname = (EditText)findViewById(R.id.edtName);
        edtphone = (EditText)findViewById(R.id.edtPhone);
        edtpassword = (EditText)findViewById(R.id.edtpassword);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Porfavor Espere");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child(edtphone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Número de teléfono ya registrado",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();
                            User user = new User(edtname.getText().toString(),edtpassword.getText().toString());
                            table_user.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Registro de usuario realizado",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }
}