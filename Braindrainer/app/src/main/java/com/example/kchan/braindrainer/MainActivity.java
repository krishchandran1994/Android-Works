package com.example.kchan.braindrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {
    Button startbutton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationanswer;
    TextView result;
    TextView points;
    TextView textview;
    TextView seconds;
    Button playagain;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    int score=0;
    int questionnumber=0;
    RelativeLayout mainlayout;

    public void playagain(View view){
        score=0;
        questionnumber=0;
        seconds.setText("30s");
        points.setText("0 / 0");
        result.setText("");
        playagain.setVisibility(view.INVISIBLE);
        generatenew();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                seconds.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                seconds.setText("0s");
                result.setText("Your Score " + Integer.toString(score)+ " / " + Integer.toString(questionnumber));

            }
        }.start();

    }
    public void generatenew(){
        Random rand= new Random();
        int a =rand.nextInt(21);
        int b= rand.nextInt(21);
        textview.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationanswer=rand.nextInt(4);
        answers.clear();
        int wrong;
        for ( int i=0;i< 4; i++){
            if(i==locationanswer){
                answers.add(a+b);
            }else{
                wrong=rand.nextInt(41);
                while(wrong==a+b){
                    wrong=rand.nextInt(41);
                }
                answers.add(wrong);
            }
        }
        b0.setText(Integer.toString(answers.get(0)));
        b1.setText(Integer.toString(answers.get(1)));
        b2.setText(Integer.toString(answers.get(2)));
        b3.setText(Integer.toString(answers.get(3)));



    }
    public void selection(View view){
        if(view.getTag().toString().equals(Integer.toString(locationanswer))){
            score++;
            questionnumber++;
            points.setText(Integer.toString(score)+ " /" + Integer.toString(questionnumber));
            result.setText("You got it.!");
            Log.i("Correct tag","Yes");
        }else {
            questionnumber++;
            result.setText("Wrong.!");
            points.setText(Integer.toString(score)+ " /" + Integer.toString(questionnumber));
        }
        Log.i("Tag",(String)view.getTag());
        generatenew();
    }
    public void startB(View view){
        startbutton.setVisibility(View.INVISIBLE);
        mainlayout.setVisibility(View.VISIBLE);
        playagain(findViewById(R.id.playagain));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton =(Button)findViewById(R.id.startbutton);
         textview=(TextView)findViewById(R.id.question);
        points=(TextView)findViewById(R.id.score);
        result=(TextView)findViewById(R.id.result);
        playagain=(Button)findViewById(R.id.playagain);
        seconds=(TextView)findViewById(R.id.secleft);
        b0=(Button)findViewById(R.id.button0);
         b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
         b3=(Button)findViewById(R.id.button3);
        mainlayout=(RelativeLayout)findViewById(R.id.mainlayout);
        generatenew();


    }
}
