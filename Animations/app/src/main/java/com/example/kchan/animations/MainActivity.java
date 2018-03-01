package com.example.kchan.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void magic(View view){
        ImageView b=(ImageView) findViewById(R.id.bart);

        Log.i("Info","Ready to fade");
        b.animate().translationX(1000f).rotationY(1000f).rotation(1800f).scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        Log.i("Info","Faded");
        ImageView b2=(ImageView) findViewById(R.id.imageView2);
        b2.animate().rotationX(1800f).alpha(1f).setDuration(3000);
        Log.i("create","done");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
