package com.example.dashboardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Data extends AppCompatActivity {

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference SoilMoistureStatus = myRef.child("WaterPump").child("SoilMoistureStatus");
    final DatabaseReference WaterLevelStatus = myRef.child("WaterPump").child("WaterLevelStatus");

    private TextView soilmoisturestatus, waterlevelstatus, pHdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        soilmoisturestatus = (TextView)findViewById(R.id.soilmoisturestatus);
        waterlevelstatus = (TextView)findViewById(R.id.waterlevelstatus);
        pHdata = (TextView)findViewById(R.id.pHdata);

        SoilMoistureStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String SoilMoistureStatus = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + SoilMoistureStatus);
                soilmoisturestatus.setText(SoilMoistureStatus);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });

        WaterLevelStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                    String status = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + status);
                waterlevelstatus.setText(" " + status);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });



    }
}