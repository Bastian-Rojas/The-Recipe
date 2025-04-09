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

public class SignInActivity extends AppCompatActivity {

    EditText edtphone,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtphone = (EditText) findViewById(R.id.edtphone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Porfavor espere");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {
                        if (snapshot.child(edtphone.getText().toString()).exists()) {


                        mDialog.dismiss();

                        User user = snapshot.child(edtphone.getText().toString()).getValue(User.class);
                        if (user.getPassword().equals(edtPassword.getText().toString())) {
                            Toast.makeText(SignInActivity.this, "Sesion Exitosa", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent menu = new Intent(SignInActivity.this,ListComida.class);
                            startActivity(menu);
                        } else {
                            Toast.makeText(SignInActivity.this, "Sesion Fallida", Toast.LENGTH_SHORT).show();
                        }
                        }else{
                            Toast.makeText(SignInActivity.this,"Usuario no Existe",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                    }
                });

            }
        });
    }
}