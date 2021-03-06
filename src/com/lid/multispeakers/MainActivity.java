package com.lid.multispeakers;

import android.app.Activity;
import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
	
	private Button b2,b3;
	private TextView iv;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private TextView tx1,tx2,tx3;

	public static int oneTimeOnly = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	      b2 = (Button) findViewById(R.id.button2);
	      b3 =(Button)findViewById(R.id.button3);
	      
	      tx3=(TextView)findViewById(R.id.textview2);
	      tx3.setText("Song.mp3");
		 

	      mediaPlayer = MediaPlayer.create(this, R.raw.slow_whoop_bubble_pop);
	      seekbar=(SeekBar)findViewById(R.id.seekBar);
	      seekbar.setClickable(false);
	      b2.setEnabled(false);
	      
	      b3.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	             Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
	             mediaPlayer.start();
	             
	             finalTime = mediaPlayer.getDuration();
	             startTime = mediaPlayer.getCurrentPosition();
	             
	             if (oneTimeOnly == 0) {
	                seekbar.setMax((int) finalTime);
	                oneTimeOnly = 1;
	             }
	       
	      
	             b2.setEnabled(true);
	             b3.setEnabled(false);
	          }
	       });
	      
	      
	      b2.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	             Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
	             mediaPlayer.pause();
	             b2.setEnabled(false);
	             b3.setEnabled(true);
	          }
	       });
	       

	 
	    }
	
	private Runnable UpdateSongTime = new Runnable() {
	      public void run() {
	         startTime = mediaPlayer.getCurrentPosition();
	
	         seekbar.setProgress((int)startTime);
	         myHandler.postDelayed(this, 100);
	      }
	   };
	      
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
	
	// O Duarte é feio 
}
