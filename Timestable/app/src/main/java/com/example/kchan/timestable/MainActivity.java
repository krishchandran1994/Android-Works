package com.example.kchan.timestable;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
        public void generateTimes(int tt){
            ArrayList<String> progresslist = new ArrayList<String>();
            for(int i=0; i<=10; i++){
                progresslist.add(Integer.toString(i*tt));
            }

            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, progresslist);
            listview.setAdapter(arrayAdapter);

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar= (SeekBar)findViewById(R.id.seekBar);
         listview=(ListView)findViewById(R.id.listview);
        seekBar.setMax(20);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min=1;
                int tt;
                if(progress<min){
                    tt=min;
                }
                else{
                    tt=progress;
                }
                Log.i("Seekbar",Integer.toString(tt));
                generateTimes(tt);

            }
        });
    }
}
