package com.thereza.radiosouthern;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    boolean isPlaying = true;
    private ProgressBar progressBar;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        startMediaPlayer();
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    
    private void startMediaPlayer() {
        String url = "http://50.22.218.73:16507/"; // your URL here
        player = new MediaPlayer();
        
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        
        try {
        	player.setDataSource(url);
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if(isPlaying){
            try {
            	
            	player.prepareAsync();
            	
            } catch (IllegalStateException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            player.setOnPreparedListener(new OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                	MainActivity.getProgressBar().setVisibility(View.GONE);
                	player.start();
                	
                }
            });
        }
        player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
            }
        });
    }


    
    public void onStart(Intent intent, int startId) {
        // TO DO
    	
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
    	

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
  
}