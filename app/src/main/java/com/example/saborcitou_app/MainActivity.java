package com.example.saborcitou_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask timerSplash = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MenuPrincipal.class);
                startActivity(intent);
                finish();
            }

        };

        Timer tiempoSplash = new Timer();
        tiempoSplash.schedule(timerSplash,4000);

    }
}