package com.rishiraj.chandiguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView appIcon;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        /*Frontend - XML
          Backend - Java
          Database - SQLite
         */


        appIcon = findViewById(R.id.appIcon);

        //Animation on app icon---------------------------------------
        anim = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        appIcon.setAnimation(anim);
        //----------------------------------------------------------------

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent start = new Intent(MainActivity.this,NavigationDrawer.class);
                startActivity(start);
                finish();
            }
        },2000);
    }
}