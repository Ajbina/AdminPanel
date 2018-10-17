package com.example.asus.adminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button login,signup;
    String semail,spassword;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sg = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(sg);
            }
        });
    }

    private void signin() {

        semail=email.getText().toString();
        spassword=password.getText().toString();

        mAuth.signInWithEmailAndPassword(semail, spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent stock = new Intent(MainActivity.this,MenuActivity.class);
                            stock.putExtra("Email",semail);
                            stock.putExtra("Password",spassword);
                            startActivity(stock);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        //progressDialog.dismiss();
                    }
                });
    }
}
