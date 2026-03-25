package com.quadcore.impact;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // Splash screen duration in milliseconds (2 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Set the splash screen layout

        // Handler to delay the transition to the LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the duration, transition to LoginActivity
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Close SplashActivity to prevent the user from returning to it
            }
        }, SPLASH_DURATION); // Delay for SPLASH_DURATION (2 seconds)
    }
}
