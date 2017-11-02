package com.thereza.radiosouthern;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener{
	
	private ImageButton btnSound, btnStart, btnStop,about,message,program,faq,developer;
	private static ProgressBar progressBar;
	private MediaPlayer player;
	TextView tv;
	Layout about1;
	private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null; 
    boolean b=false;
    
    Integer i=0,j=0;
    int currentVolume;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#113e64")));
		bar.setTitle(Html.fromHtml("<b>Radio Southern</b>"));
		tv = (TextView) this.findViewById(R.id.marquee);  
        tv.setSelected(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        developer=(ImageButton) findViewById(R.id.developer);
        about=(ImageButton) findViewById(R.id.about);
        message=(ImageButton) findViewById(R.id.message);
        program=(ImageButton) findViewById(R.id.program);
        faq=(ImageButton) findViewById(R.id.faq);
        btnSound=(ImageButton) findViewById(R.id.btnSound);
        
        btnSound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//mute audio
				mute();
				unmute();
			}

		});
        
       
        
        
        about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(MainActivity.this,AboutActivity.class);
			    startActivity(i);
			    overridePendingTransition(R.anim.slide_left_in,0);
				
			}
		});
        
        message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent m=new Intent(MainActivity.this,MessageActivity.class);
			    startActivity(m);
			    overridePendingTransition(R.anim.slide_left_in,0);
				
			}
		});
        
        program.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent p=new Intent(MainActivity.this,ContactActivity.class);
			    startActivity(p);
			    overridePendingTransition(R.anim.slide_left_in,0);
				
			}
		});
        faq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent p=new Intent(MainActivity.this,FAQActivity.class);
			    startActivity(p);
			    overridePendingTransition(R.anim.slide_left_in,0);
				
			}
		});
       
        developer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent p=new Intent(MainActivity.this,DeveloperActivity.class);
			    startActivity(p);
			    overridePendingTransition(R.anim.slide_left_in,0);
				
			}
		});
        
        
      btnSound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//mute audio
				if(i%2==0){
				mute();
				}else{
				unmute();
				}
				i++;
			}

		});
		    init();
			initControls();
			
		
	}
	public void mute() {
		AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        btnSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
		
	}
	public void unmute() {
		
       	     AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
			 amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
             amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
             amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
             amanager.setStreamMute(AudioManager.STREAM_RING, false);
             amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
             btnSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
		
	}
	
	private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.bar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));   


            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) 
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
	

	private void init() {
		
		setProgressBar((ProgressBar) findViewById(R.id.progressBar));
		
		btnStart = (ImageButton) findViewById(R.id.btnStart);
		btnStop = (ImageButton) findViewById(R.id.btnStop);
		
       
		
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		getProgressBar().setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//internet connection cheack and show dialog if no data connection
				if (!DetectConnection.checkInternetConnection(this)) {

					AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
					dialog.setMessage("Please Cheack Your Internet Connection and try again."
			        		   );
			           dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
			        	   @Override
			        	   public void onClick(DialogInterface dialog, int which) {
			        		   startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
			        		   dialog.cancel();
							
			        	   }
			           });
			           dialog.show();	           
					} 
				else {
		int id = v.getId();
		switch(id){
		case R.id.btnStart:
			btnStart.setImageResource(android.R.drawable.ic_media_pause);
			btnStop.setImageResource(R.drawable.stoprr);
			getProgressBar().setVisibility(View.VISIBLE);
			Intent svc=new Intent(MainActivity.this, BackgroundSoundService.class);
			startService(svc);
			
			btnStop.setEnabled(true);
			Notify("Player Start");
			
			
			break;
		case R.id.btnStop:
			
			btnStart.setImageResource(android.R.drawable.ic_media_play);
			btnStop.setImageResource(R.drawable.s);
        	getProgressBar().setVisibility(View.GONE);
        	stopPlaying();
        	
			break;
		default:
			break;
			
		}
	   }
			
	}
	
    private void stopPlaying() {
        
    	if(isMyServiceRunning(BackgroundSoundService.class)){
    	Intent name = new Intent(MainActivity.this, BackgroundSoundService.class);
        stopService(name);
        btnStart.setImageResource(android.R.drawable.ic_media_play);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        
        
    	}
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
       
    }
    
    
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    
    private void Notify(String notificationTitle){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")
        
        Notification notification = new Notification(R.drawable.logo,"Player start..", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.setLatestEventInfo(MainActivity.this, notificationTitle,"Click here to go", pendingIntent);
        notificationManager.notify(9999, notification);
     }
	public static ProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
    


}

