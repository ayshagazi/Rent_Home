package com.example.rent_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageButton login, register;
    private ImageView cover;
    private RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.reg);
        login = findViewById(R.id.lgn);
        cover = findViewById(R.id.start);

/*
        TranslateAnimation animation1= new TranslateAnimation(0,0,0,-1000);
        animation1.setDuration(1000);
        animation1.setFillAfter(false);
        animation1.setAnimationListener(new MyAnimation());
        cover.setAnimation(animation1);

*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });
    }
/*
    private class MyAnimation implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            cover.clearAnimation();
            cover.setVisibility(View.INVISIBLE);
            relative.animate().alpha(1f).setDuration(3000);


        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    */
    }
