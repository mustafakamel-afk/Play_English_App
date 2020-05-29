package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {

    Activity activity;
    View colorChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        activity = this;
        colorChange = (View) findViewById(R.id.color_change);

        Thread thread = new Thread() {

            int x = 5;

            public void run() {
                while (x >= 0) {

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (x == 2) {
                                colorChange.setVisibility(View.VISIBLE);
                                colorChange.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom));
                            } else if (x == 0) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }
                    });
                    x--;
                    try {
                        Thread.sleep(1000);

                    } catch (Exception e) {

                    }
                }
            }

        };
        thread.start();
    }

}
