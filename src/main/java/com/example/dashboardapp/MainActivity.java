package com.example.dashboardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView LEDCard, feedfishCard, phvaluecard, waterplantscard, fancard, cameracard, datacard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining cards
        LEDCard = (CardView)findViewById(R.id.LED);
        feedfishCard = (CardView)findViewById(R.id.feedfish);
        phvaluecard = (CardView)findViewById(R.id.phvalue);
        waterplantscard = (CardView)findViewById(R.id.waterplants);
        fancard= (CardView)findViewById(R.id.fan);
        cameracard = (CardView)findViewById(R.id.camera);
        datacard = (CardView)findViewById(R.id.data);
        //Add Click listener to the cards
        LEDCard.setOnClickListener(this);
        feedfishCard.setOnClickListener(this);
        phvaluecard.setOnClickListener(this);
        waterplantscard.setOnClickListener(this);
        fancard.setOnClickListener(this);
        cameracard.setOnClickListener(this);
        datacard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            //remember to create new activity for each option first
            case R.id.LED:
                i = new Intent(this, LED.class);
                startActivity(i);
                break;
            case R.id.feedfish:
                i = new Intent(this, feedfish.class);
                startActivity(i);
                break;
            case R.id.phvalue:
                i = new Intent(this, phvalue.class);
                startActivity(i);
                break;
            case R.id.waterplants:
                i = new Intent(this, waterplants.class);
                startActivity(i);
                break;
            case R.id.fan:
                i = new Intent(this, fan.class);
                startActivity(i);
                break;
            case R.id.camera:
                i = new Intent(this, camera.class);
                startActivity(i);
                break;
            case R.id.data:
                i = new Intent(this, Data.class);
                startActivity(i);
                break;



        }

    }

}