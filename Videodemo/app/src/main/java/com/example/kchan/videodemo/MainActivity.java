package com.example.kchan.videodemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(this, R.raw.surviva);
        audioPlayer=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume= audioPlayer.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.i("Max volume",Integer.toString(maxVolume));
        int curVolume= audioPlayer.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.i("Current Volume", Integer.toString(curVolume));
        SeekBar volume= (SeekBar)findViewById(R.id.seekBar);
        volume.setMax(maxVolume);
        volume.setProgress(curVolume);//  TO KNOW THE CURRENT VALUE FOR THE SEEKBAR.
        volume.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged  (SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar",Integer.toString(progress));
                audioPlayer.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
        });

        final SeekBar posseek=(SeekBar)findViewById(R.id.positionSeek);
        posseek.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            posseek.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,10000);// for the movement.!
        posseek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Position Seek",Integer.toString(progress));
                mediaPlayer.seekTo(progress);// This is to make sure changes are happening when seekbar is changed.

            }
        });

    }

    public void playAudio(View view){

        mediaPlayer.start();
    }


    public void pauseAudio(View view){
        mediaPlayer.pause();
    }
}
