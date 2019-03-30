package com.ksg.easykitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private TextView vw,creator;
    private ImageView iw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        vw= findViewById(R.id.vw);
        iw= findViewById(R.id.iw);
        creator = findViewById(R.id.creator);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);

        vw.startAnimation(anim);
        iw.startAnimation(anim);
        creator.startAnimation(anim);

        final Intent x =new Intent(SplashScreen.this, Home.class);
        Thread t =new Thread(){
            public void run(){
                try{
                    sleep(4000);
                } catch (InterruptedException e){
                    e.printStackTrace();

                }finally{
                    startActivity(x);
                    finish();
                }
            }
        };
        t.start();
    }
}
