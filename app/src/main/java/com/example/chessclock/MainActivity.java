package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long TIME_MILLIS = 600000;
    private TextView text1,text2;


    private Button start1,start2;
    private ImageButton pause,reset;
    private CountDownTimer count1,count2;
    private boolean mTimerRunning;
    private long LeftMillis = TIME_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        start1 = (Button) findViewById(R.id.start1);
        start2 = (Button) findViewById(R.id.start2);
        reset = (ImageButton)  findViewById(R.id.reset);
        pause = (ImageButton)  findViewById(R.id.pause);

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  if (mTimerRunning) {
                pauseTimer(count1);
                //   } else {
                //       startTimer();
                // }
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 //   if (mTimerRunning) {
                   //   pauseTimer();
               // } else {

                startTimer();
                  }
           // }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer(count1);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

            updateCountDownText1();
        updateCountDownText2();

    }

    private void startTimer(){
        count1= new CountDownTimer(LeftMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                LeftMillis= millisUntilFinished;
                updateCountDownText1();
                updateCountDownText2();

            }


            @Override
            public void onFinish() {
                mTimerRunning=false;
              reset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning=true;


    }
    private void pauseTimer(CountDownTimer count1){
        this.count1.cancel();
        mTimerRunning=false;
       reset.setVisibility(View.VISIBLE);

    }

    private void resetTimer(){
        LeftMillis = TIME_MILLIS;
        updateCountDownText1();
        updateCountDownText2();

        start1.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText1(){
        int minutes = (int)(LeftMillis/1000)  / 60;
        int seconds = (int) (LeftMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

            text1.setText(timeLeftFormatted);

    }


    private void updateCountDownText2(){
        int minutes = (int)(LeftMillis/1000)  / 60;
        int seconds = (int) (LeftMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);


        text2.setText(timeLeftFormatted);
    }
}

