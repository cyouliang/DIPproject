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


public class waterplants extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference WaterPump = myRef.child("WaterPump").child("input3");
    final DatabaseReference pumpstatus = myRef.child("WaterPump").child("status");

    private Button wpBtn;
    private TextView wpTextView, wpresultTextView, wpstatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterplants);

        wpBtn = (Button)findViewById(R.id.wpBtn);
        wpTextView = (TextView)findViewById(R.id.wpTextView);
        wpstatusTextView = (TextView)findViewById(R.id.wpstatusTextView);
        wpresultTextView = (TextView)findViewById(R.id.wpresultTextView);

        wpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WaterPump.setValue(1);
            }
        });

        pumpstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String status = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + status);
                wpresultTextView.setText(status);
                if (status.equals("OFF"))
                    WaterPump.setValue(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });


    }
}