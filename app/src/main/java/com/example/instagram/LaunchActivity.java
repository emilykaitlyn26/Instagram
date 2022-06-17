package com.example.instagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    public ImageView ivLaunch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launchscreen);

        Thread splashScreenStarter = new Thread(() ->{
            try {
                int delay = 0;
                while (delay < 1000) {
                    Thread.sleep(150);
                    delay = delay + 100;
                }
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        });
        splashScreenStarter.start();

    }
}
