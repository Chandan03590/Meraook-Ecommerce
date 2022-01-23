package com.meraook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.meraook.Authentication.LoginActivity;
import com.meraook.Authentication.MobileNumberActivity;
import com.meraook.Authentication.SessionManager;
import com.meraook.MainActivity;
import com.meraook.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        SessionManager sessionManager=new SessionManager(SplashScreenActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sessionManager.getMobile_number() !=""){
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.putExtra("mobile_number",sessionManager.getMobile_number());
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN);

    }
}