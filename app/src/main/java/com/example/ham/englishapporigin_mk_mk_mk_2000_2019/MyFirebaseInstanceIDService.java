package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {

        String recent_token = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("FCM_TOKEN", MODE_PRIVATE);

        sharedPreferences.edit().putString("FCM_TOKEN", recent_token).apply();

        Log.d(REG_TOKEN, recent_token);

    }
}
