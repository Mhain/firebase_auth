package com.example.mhainulhoque.firebsa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase datebase;
    DatabaseReference myRef;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private EditText emailbox;
    private EditText passbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    emailbox=(EditText)findViewById(R.id.userEmail);
    passbox=(EditText)findViewById(R.id.userPass);
        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(MainActivity.this, Newactivity.class);
                    startActivity(intent);
                }
            }
        };



    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);

    }

    public void loginButtonCLicked(View view) {

        String email =emailbox.getText().toString();
        String pass =passbox.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Email Address and Password", Toast.LENGTH_SHORT);

        } else if (TextUtils.isEmpty(email)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Email Address ", Toast.LENGTH_SHORT);

        } else if (TextUtils.isEmpty(pass)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter Password ", Toast.LENGTH_SHORT);

        } else {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

       }
}
