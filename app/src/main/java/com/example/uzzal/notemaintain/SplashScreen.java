package com.example.uzzal.notemaintain;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //private Button bouncebutton;
    private TextView textnotetv;
    private Chronometer chronometer;

      //new
    ImageView imageViewSplash;
    Thread splashThread;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewSplash = (ImageView) findViewById(R.id.imageview_splash_id);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
       // bouncebutton = findViewById(R.id.bounce_button_id);
        textnotetv = (TextView) findViewById(R.id.text_note);




        chronometer = (Chronometer) findViewById(R.id.choronomiter_id);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();


        startAnim();


//       bouncebutton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               switch (v.getId()){
//                   case R.id.bounce_button_id:
//                       notetv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
//                       break;
//               }
//           }
//       });



                // etai best silo kintuooo.....

//        Thread myThread = new Thread(){
//
//            @Override
//            public void run() {
//
//                try{
//                    sleep(5000);
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//
//
//
//
//            }
//        };
//
//        myThread.start();

    }

    private void startAnim(){

        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Animation transelete = AnimationUtils.loadAnimation(this,R.anim.translate);

        rotate.reset();
        transelete.reset();
        relativeLayout.clearAnimation();

        imageViewSplash.startAnimation(rotate);
        textnotetv.startAnimation(transelete);


        splashThread = new Thread(){

            @Override
            public void run() {
                super.run();

                int watied  = 10;
                while (watied<5500){
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    watied+=200;
                }
                SplashScreen.this.finish();

                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };

        splashThread.start();

    }
}
