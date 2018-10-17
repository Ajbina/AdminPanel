package com.example.asus.adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText name,email,pass;
    Button sign;
    String sName,sEmail,sPass;

    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        sign=findViewById(R.id.signup);

        mAuth=FirebaseAuth.getInstance();
        //reference= FirebaseDatabase.getInstance().getReference();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

    }

    private void createAccount() {

        sEmail=email.getText().toString();
        sPass=pass.getText().toString();

        mAuth.createUserWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(SignupActivity.this, ""+sEmail+sPass,
                                    Toast.LENGTH_SHORT).show();

                        }
                        // ...
                    }
                });

    }
}
