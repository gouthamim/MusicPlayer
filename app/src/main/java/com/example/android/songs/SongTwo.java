package com.example.android.songs;

import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;


/**
 * Created by gouthami.m on 17/05/16.
 */
public class SongTwo extends AppCompatActivity {

    Handler handler = new Handler();
    int progressBarValue = 0;
    SeekBar SeekBar;
    Boolean isStart;
    MediaPlayer mPlayer;
    int startTime = 0;
    int finalTime;
    int forwardTime = 3000;
    int backwardTime = 3000;
    int currentTime;
    File audioFile = new File(R.raw.beautiful_girls + "/raw/beautiful_girls.mp3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_two);

        final ImageButton button = (ImageButton) findViewById(R.id.play);
        SeekBar = (SeekBar) findViewById(R.id.seekBar);
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beautiful_girls);
        mPlayer.start();
        button.setImageResource(android.R.drawable.ic_media_pause);
        isStart = true;
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (mPlayer.isPlaying() && isStart) {
                    mPlayer.pause();
                    button.setImageResource(android.R.drawable.ic_media_play);
                    isStart = false;
                } else {
                    mPlayer.start();
                    button.setImageResource(android.R.drawable.ic_media_pause);
                    isStart = true;
                }
            }
        });
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (isStart) {
                    progressBarValue++;
                }
                SeekBar.setProgress(progressBarValue);
                handler.sendEmptyMessageDelayed(0, 1000);
                if(progressBarValue == finalTime) {
                  //  mPlayer.start();
                    button.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        };

        handler.sendEmptyMessage(0);


        ImageButton next = (ImageButton) findViewById(R.id.fastForward);
        finalTime = mPlayer.getDuration();
        SeekBar.setMax(finalTime);
        if (next != null)
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = (int) startTime;

                    if ((temp + forwardTime) <= finalTime) {
                        startTime = startTime + forwardTime;
                        currentTime = mPlayer.getCurrentPosition();
                        currentTime = currentTime + forwardTime;
                        mPlayer.seekTo(currentTime);
                       // Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                    } else {
                       // Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        ImageButton rewind = (ImageButton) findViewById(R.id.rewind);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    currentTime = mPlayer.getCurrentPosition();
                    currentTime = currentTime - backwardTime;
                    mPlayer.seekTo(currentTime);
                    //  Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });


        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                //  mPlayer.seekTo(progress);
                // SeekBar.setProgress(progress);

                int mediaPos = mPlayer.getCurrentPosition();
                int mediaMax = mPlayer.getDuration();

                SeekBar.setMax(mediaMax); // Set the Maximum range of the
                SeekBar.setProgress(mediaPos);// set current progress to song's

                handler.removeCallbacks(moveSeekBarThread);
                handler.postDelayed(moveSeekBarThread, 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progressBarValue = progressChanged;
                currentTime = mPlayer.getCurrentPosition();
                currentTime = currentTime + progressBarValue;
                mPlayer.seekTo(currentTime);
            }
        });


        Button repeat = (Button) findViewById(R.id.repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.setLooping(true);

                mPlayer.start();
            }
        });


    }

  /*  @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        View decorView = getWindow().getDecorView();
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.stop();
        }
    }

    /**
     * The Move seek bar. Thread to move seekbar based on the current position
     * of the song
     */
    private Runnable moveSeekBarThread = new Runnable() {
        public void run() {
            if(mPlayer.isPlaying()){

                int mediaPos_new = mPlayer.getCurrentPosition();
                int mediaMax_new = mPlayer.getDuration();
                SeekBar.setMax(mediaMax_new);
                SeekBar.setProgress(mediaPos_new);

                handler.postDelayed(this, 100); //Looping the thread after 0.1 second
            }
        }
    };
}
