package com.example.dashboardapp;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LED extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference led3 = myRef.child("led3").child("input1");
    final DatabaseReference ledstatus = myRef.child("led3").child("status");

    private TextView ledtextview, ledstatustextview, ledresulttextview;
    private Switch ledswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_e_d);

        ledtextview = (TextView)findViewById(R.id.ledtextview);
        ledstatustextview = (TextView)findViewById(R.id.ledstatustextview);
        ledresulttextview = (TextView)findViewById(R.id.ledresulttextview);
        ledswitch = (Switch)findViewById(R.id.ledswitch);

        ledswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    led3.setValue(1);
                }
                else{
                    led3.setValue(0);
                }
            }
        });

        ledstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String status = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + status);
                ledresulttextview.setText(status);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });
    }
}