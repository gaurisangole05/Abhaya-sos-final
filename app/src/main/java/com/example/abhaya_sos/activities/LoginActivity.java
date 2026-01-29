//package com.example.abhaya_sos;
package com.example.abhaya_sos.activities;



import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.password);
        Button login = findViewById(R.id.loginBtn);

        login.setOnClickListener(v ->
                auth.signInWithEmailAndPassword(
                        email.getText().toString(),
                        pass.getText().toString()
                ).addOnSuccessListener(r -> {
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                })
        );
    }
}
