package com.abhaya.sos.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.abhaya.sos.model.TrustedContact;

public class ContactsActivity extends AppCompatActivity {

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_contacts);

        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);
        Button save = findViewById(R.id.saveBtn);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getUid();

        save.setOnClickListener(v -> {
            TrustedContact c =
                    new TrustedContact(name.getText().toString(),
                            phone.getText().toString());

            db.collection("users")
                    .document(uid)
                    .collection("contacts")
                    .add(c);
        });
    }
}
