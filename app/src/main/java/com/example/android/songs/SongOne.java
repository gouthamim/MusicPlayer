package com.example.android.songs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by gouthami.m on 17/05/16.
 */
public class SongOne extends AppCompatActivity {

    Handler handler = new Handler();
    boolean isStart;
    int progressBarValue = 0;
    SeekBar SeekBar;
    MediaPlayer mPlayer;
    private int startTime = 0;
    private int currentTime;
    private int finalTime;
    private int forwardTime = 3000;
    private int backwardTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_one);

        SeekBar = (SeekBar) findViewById(R.id.seekBar);
        final ImageButton button = (ImageButton) findViewById(R.id.play);
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.why_not_me);
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
               /* int duration = mPlayer.getDuration();
                Toast.makeText(getApplicationContext(),"Duration of song is :" +duration,Toast.LENGTH_SHORT).show();
*/
            }
        });
    //    finalTime = mPlayer.getDuration();
       // SeekBar.setMax(finalTime);
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (isStart) {
                    progressBarValue++;
                }
                SeekBar.setProgress(progressBarValue);
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        };
        handler.sendEmptyMessage(0);

        ImageButton fastForward = (ImageButton) findViewById(R.id.fastForward);
        finalTime = mPlayer.getDuration();

        fastForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = (int) startTime;

                    if ((temp + forwardTime) <= finalTime) {
                        startTime = startTime + forwardTime;
                        currentTime = mPlayer.getCurrentPosition();
                        currentTime = currentTime + forwardTime;
                        mPlayer.seekTo((int) currentTime);
                      //  mPlayer.seekTo(currentTime);
                        Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
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
                    mPlayer.seekTo((int) currentTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });


        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              //  int progressChanged = 0;
              //  progressChanged = progress;
             //   mPlayer.seekTo(seekBar.getProgress());
            //    SeekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
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
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.stop();
        }
    }
}
