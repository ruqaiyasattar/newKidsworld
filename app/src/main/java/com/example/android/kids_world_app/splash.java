package com.example.android.kids_world_app;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splash extends Activity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer=new Thread()
        {
            @Override            public void run() {
                try                {
                    music=MediaPlayer.create(splash.this,R.raw.bgmusic);
                    music.start();
                    sleep(5000);

                }
                catch(InterruptedException e)
                {

                }
                finally {
                    Intent i=new Intent(splash.this,MainActivity.class);
                    startActivity(i);
                }
            }
        };

        timer.start();


    }

    @Override    protected void onPause() {
        super.onPause();
        music.release();
    }
}