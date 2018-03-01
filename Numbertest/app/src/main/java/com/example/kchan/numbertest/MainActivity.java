package com.example.kchan.numbertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    class Number {
        int n;

        public boolean isSquare() {
            double sq = Math.sqrt(n);
            if (sq == Math.floor(sq)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isTriangular() {
            int x = 1;
            int tn = 1;
            while (tn < n) {
                x++;
                tn = tn + x;
            }
            if (tn == n) {
                return true;
            } else {
                return false;
            }
        }
    }


    public void testBut(View view) {
        String message = "";
        EditText n = (EditText) findViewById(R.id.numberfield);
        Log.i("Number is", n.getText().toString());

        if (n.getText().toString().isEmpty()) {
            message = " You have to enter a number to get it checked";
        } else {
            Number nn = new Number();
            nn.n = Integer.parseInt(n.getText().toString());
            if (nn.isSquare()) {
                if (nn.isTriangular()) {
                    message = nn.n + " is both square and triangular";
                } else {
                    message = nn.n + " is square and NOT triangular";
                }
            } else if (nn.isTriangular()) {
                message = nn.n + " is Triangular and NOT square";
            } else {
                message = nn.n + " is NOT both square and triangular";
            }
        }
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
