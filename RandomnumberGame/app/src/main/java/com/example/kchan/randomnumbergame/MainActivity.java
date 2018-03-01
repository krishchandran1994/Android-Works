package com.example.kchan.randomnumbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

        int n;
        public void guessBut(View V){
            EditText ev= (EditText) findViewById(R.id.editText2);
            int dv= Integer.parseInt(ev.getText().toString());


            if(dv > 20)
             {
                Toast.makeText(MainActivity.this, "Enter number less than 20.!", Toast.LENGTH_SHORT).show();
                Random rand = new Random();
                n = rand.nextInt(20) + 1;
            }else if(dv > n){
                Log.i("number",Integer.toString(n));
                Toast.makeText(MainActivity.this, "Lower value", Toast.LENGTH_SHORT).show();
            } else if(dv < n){
                Log.i("number",Integer.toString(n));
                Toast.makeText(MainActivity.this, "Higher Value", Toast.LENGTH_SHORT).show();
            } else if(dv == n) {
                Log.i("number",Integer.toString(n));
                Toast.makeText(MainActivity.this, "You Got it..!", Toast.LENGTH_SHORT).show();
                Random rand = new Random();
                 n = rand.nextInt(20) + 1;
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
       n = rand.nextInt(20) + 1;
    }
}
