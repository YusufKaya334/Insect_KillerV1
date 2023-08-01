package com.example.insect_killer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score;
    TextView textView,textView2;

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    ImageView []  imgarrays;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.my_score);
        textView2=findViewById(R.id.remaining_time);
        img1=findViewById(R.id.imageView);
        img2=findViewById(R.id.imageView2);
        img3=findViewById(R.id.imageView3);
        img4=findViewById(R.id.imageView4);
        img5=findViewById(R.id.imageView5);
        img6=findViewById(R.id.imageView6);
        img7=findViewById(R.id.imageView7);
        img8=findViewById(R.id.imageView8);
        img9=findViewById(R.id.imageView9);

        imgarrays=new ImageView[]{img1,img2,img3,img4,img5,img6,img7,img8,img9};
        hideimg();

        score=0;
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView2.setText("TİME: "+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                textView.setText("GAME OVER !");
                handler.removeCallbacks(runnable);

                for(ImageView images: imgarrays){
                    images.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert1=new AlertDialog.Builder(MainActivity.this);
                alert1.setTitle("Restart?");
                alert1.setMessage("Your score is :  " + score +"  Are you sure to restart game?");
                alert1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "GAME OVER ! ", Toast.LENGTH_SHORT).show();
                    }
                });

                alert1.show();
            }
        }.start();
    }

    public void increase_score(View view){
        score++;
        textView.setText("SCORE:   "+ score);
    }

    public  void  hideimg(){
        handler =new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView images: imgarrays){
                    images.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imgarrays[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);

            }
        };

        handler.post(runnable);

    }
}