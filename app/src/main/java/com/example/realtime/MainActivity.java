package com.example.realtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editTextText);
        e2=findViewById(R.id.editTextText2);
        btn=findViewById(R.id.button);
        reference= FirebaseDatabase.getInstance().getReference().child("students");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String email = e2.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                } else {
                    student student1 = new student(name, email);
                    String uniqeID = reference.push().getKey();
                    reference.child(Objects.requireNonNull(uniqeID)).setValue(student1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Data insert successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Data insert failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }
}