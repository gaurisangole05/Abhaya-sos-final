package com.abhaya.sos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.password);
        Button register = findViewById(R.id.registerBtn);

        register.setOnClickListener(v ->
                auth.createUserWithEmailAndPassword(
                        email.getText().toString(),
                        pass.getText().toString()
                ).addOnSuccessListener(r -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
        );
    }
}
