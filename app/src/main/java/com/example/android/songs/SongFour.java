package com.example.android.songs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by gouthami.m on 17/05/16.
 */
public class SongFour extends AppCompatActivity {

    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_four);
        final ImageButton button = (ImageButton)findViewById(R.id.play);
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dheere_dheere);
        mPlayer.start();
        button.setImageResource(android.R.drawable.ic_media_pause);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                    button.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mPlayer.start();
                    button.setImageResource(android.R.drawable.ic_media_pause);
                }
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
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mPlayer != null && mPlayer.isPlaying()){
            mPlayer.pause();
            mPlayer.stop();
        }
    }
}
