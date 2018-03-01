package com.example.kchan.currencyconverter;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        public void imageBut(View V){
            EditText textfield = (EditText) findViewById(R.id.editText);
            Log.i("$", textfield.getText().toString());
            Double amount= Double.parseDouble(textfield.getText().toString());
            Double inr= amount * 64.60;
            Toast.makeText(MainActivity.this, " It is Rs "+ inr.toString(), Toast.LENGTH_SHORT).show();
            Log.i("Rs", inr.toString());
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
