package com.example.dashboardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    //Variables for animation
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    private static int SPLASH_TIME_OUT = 5000;
    private TextView splashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
