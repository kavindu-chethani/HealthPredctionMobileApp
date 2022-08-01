package com.example.healthpredctionnewapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView retrieveTV;
    private TextView retrieve;
    private TextView retriev;
    private TextView retrievet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("test");
        retrieveTV = findViewById(R.id.txttemp);
        retrieve = findViewById(R.id.txtair);
        retriev = findViewById(R.id.txtheartrate);
        retrievet = findViewById(R.id.txtspo);

        getdata();
    }

    private void getdata() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                retrieveTV.setText(snapshot.child("Body_Temperature").getValue().toString());
                retrieve.setText(snapshot.child("Air_Quality").getValue().toString());
                retriev.setText(snapshot.child("Heart_Rate").getValue().toString());
                retrievet.setText(snapshot.child("SPO2").getValue().toString());
//                System.out.println(snapshot.child("Body_Temperature").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(home.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}