package com.example.dashboardapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartTimer extends BroadcastReceiver {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    final DatabaseReference ServoMotor = myRef.child("ServoMotor").child("input2");

    @Override
    public void onReceive(Context context, Intent intent) {

        ServoMotor.setValue(1);
    }
}
