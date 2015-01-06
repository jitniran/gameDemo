package com.gamedemo.pingpong;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private GameView gamer;
	protected int senDelay = 600000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//remove status bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		gamer = (GameView) findViewById(R.id.mainArena);
	
	}
	
	@Override
	protected void onPause() {
		//direct accessing discouraged but meh....
		super.onPause();
		SensorManager senManager = gamer.senManager;
		senManager.unregisterListener(gamer);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SensorManager senManager = gamer.senManager;
		Sensor senAccelometer = gamer.senAccelometer;
		senManager.registerListener(gamer, senAccelometer,senDelay );
	}

}
