package com.example.dashboardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class feedfish extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference ServoMotor = myRef.child("ServoMotor").child("input2");
    final DatabaseReference motorstatus = myRef.child("ServoMotor").child("status");
    final DatabaseReference motortimer = myRef.child("ServoMotor").child("Timer");

    private Button smBtn, settimeBtn;
    private TextView smTextView, smstatusTextView, smresultTextView;
    private TimePicker timePicker1;
    private TextView hrTextView, minTextView;
    String timehour;
    String timemin;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedfish);

        smBtn = (Button) findViewById(R.id.smBtn);
        smTextView = (TextView) findViewById(R.id.smTextView);
        smstatusTextView = (TextView) findViewById(R.id.smstatusTextView);
        smresultTextView = (TextView) findViewById(R.id.smresultTextView);
        settimeBtn = (Button) findViewById(R.id.settimeBtn);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        hrTextView = (TextView) findViewById(R.id.hrTextView);
        minTextView = (TextView) findViewById(R.id.minTextView);

        smBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServoMotor.setValue(1);
                smBtn.setEnabled(false);
                settimeBtn.setEnabled(false);
            }
        });

        motorstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String status = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + status);
                smresultTextView.setText(status);
                if (status.equals("OFF")) {
                    ServoMotor.setValue(0);
                    smBtn.setEnabled(true);
                    settimeBtn.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });

        settimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar rightNow = Calendar.getInstance();
                smBtn.setEnabled(false);
                timehour = timePicker1.getCurrentHour().toString();
                timemin = timePicker1.getCurrentMinute().toString();
                //currentTime = timehour + timemin;
                hrTextView.setText(timehour + timemin + "H");
                motortimer.setValue(timehour + timemin + "H");
                rightNow.set(rightNow.get(Calendar.YEAR),
                        rightNow.get(Calendar.MONTH),
                        rightNow.get(Calendar.DAY_OF_MONTH),
                        timePicker1.getCurrentHour(),
                        timePicker1.getCurrentMinute(),
                        0);
                setAlarm(rightNow.getTimeInMillis());
            }
        });

    }
    private void setAlarm(long TimeInMillis) {
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, StartTimer.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent,0);

        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis, pendingIntent);

        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }
}
