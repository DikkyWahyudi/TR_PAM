package com.example.retailnft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView title1,slogan;
    private ImageView cart;
    private FrameLayout frame;
    private final int REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,permissions, REQ_CODE);
                }
            }
        }
        title1 = findViewById(R.id.title);
        slogan = findViewById(R.id.title2);
        cart = findViewById(R.id.logo);
        frame = findViewById(R.id.frame);

        Animation animasi = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.app_name_animation);
        Animation animasi2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.base_slide_right_in);
        Animation animasi3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
        title1.startAnimation(animasi);
        slogan.startAnimation(animasi2);
        cart.startAnimation(animasi3);
        Intent intent = new Intent(this,OpeningActivity.class);

        Handler my_handler = new Handler();
        my_handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(intent);
            }
        }, 4000);

    }
    
}