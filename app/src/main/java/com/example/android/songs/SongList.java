package com.example.android.songs;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by gouthami.m on 17/05/16.
 */
public class SongList extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        tv1 = (TextView) findViewById(R.id.song1);
        tv2 = (TextView) findViewById(R.id.song2);
        tv3 = (TextView) findViewById(R.id.song3);
        tv4 = (TextView) findViewById(R.id.song4);
        tv5 = (TextView) findViewById(R.id.song5);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSong1();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSong2();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSong3();
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSong4();
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSong5();
            }
        });
    }

    public void onClickSong1(){
        Intent intent = new Intent(getApplicationContext(),SongOne.class);
        startActivity(intent);
    }
    public void onClickSong2(){
        Intent intent = new Intent(getApplicationContext(),SongTwo.class);
        startActivity(intent);
    }
    public void onClickSong3(){
        Intent intent = new Intent(getApplicationContext(),SongThree.class);
        startActivity(intent);
    }
    public void onClickSong4(){
        Intent intent = new Intent(getApplicationContext(),SongFour.class);
        startActivity(intent);
    }
    public void onClickSong5(){
        Intent intent = new Intent(getApplicationContext(),SongFive.class);
        startActivity(intent);
    }

//    public void getRawFiles(){
//        Field[] fields = R.raw.class.getFields();
//        // loop for every file in raw folder
//        for(int count=0; count < fields.length; count++){
//
//            int rid = fields[count].getInt(fields[count]);
//
//            // Use that if you just need the file name
//            String filename = fields[count].getName();
//
//            // Use this to load the file
//            try {
//                Resources res = getResources();
//                InputStream in = res.openRawResource(rid);
//
//                byte[] b = new byte[in.available()];
//                in.read(b);
//                // do whatever you need with the in stream
//            } catch (Exception e) {
//                // log error
//            }
//        }
//    }
}
