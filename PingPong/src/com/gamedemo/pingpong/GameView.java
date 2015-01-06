package com.gamedemo.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class GameView extends View implements SensorEventListener {

	int scrW;
	int scrH;
	Rect topRect;
	Rect downRect;
	Paint mRectPaint;
	protected SensorManager senManager;
	protected Sensor senAccelometer;
	protected Context cntxt;
	protected float lastvX = 0;
	// sensor events dealy in microsec
	protected static final int senDelay = 600000;
	static final float NS2S = 1.0f / 1000000000.0f;
	long last_timestamp =0;
	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		cntxt = context;
		init();
	}
    public GameView(final Context context) {
        super(context);
        cntxt = context;
        init();
    }

    public GameView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        cntxt = context;
        init();
    }

	
	public void init() {
		
		//sensor initialiazing
		senManager =(SensorManager) cntxt.getSystemService(cntxt.SENSOR_SERVICE);
		senAccelometer =senManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		//4th param can used for batch reporting sensor events saves battery consumption added in api 19
		senManager.registerListener(this, senAccelometer,senDelay );
		//
		this.setBackgroundColor(Color.BLACK);
		DisplayMetrics display = this.getResources().getDisplayMetrics();
		scrW = display.widthPixels;
		scrH = display.heightPixels;
		int rectWidth = (int)(scrW*0.35);
		int rectHeight = 40;
		int left = (scrW - rectWidth)/2;
		
		topRect = new Rect(left,0,left +rectWidth,rectHeight);
		int top = scrH-rectHeight;
		downRect = new Rect(left,scrH-rectHeight,left + rectWidth,top +rectHeight);
		mRectPaint = new Paint();
		mRectPaint.setColor(Color.WHITE);
		mRectPaint.setStyle(Paint.Style.FILL);
		invalidate();
	}
	
	@Override
	public void onDraw(final Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(topRect, mRectPaint);
		canvas.drawRect(downRect, mRectPaint);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		Sensor mysensor = event.sensor;
		if(mysensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			
			if(lastvX != 0) {
				 float dt = (event.timestamp - last_timestamp) * NS2S;
				 float velocity = (event.values[0]+lastvX)/2 *dt;
				 float position = velocity * dt;
				if(velocity > 0.3) {
					topRect.left = topRect.left - 20;
					topRect.right = topRect.right -20;
					downRect.left = downRect.left - 20;
					downRect.right = downRect.right -20;
					
					invalidate();
				}else if(velocity < -0.3) {
					topRect.left = topRect.left + 20 ;
					topRect.right = topRect.right +20;
					downRect.left = downRect.left + 20 ;
					downRect.right = downRect.right +20;
					invalidate();
				}
				 
			}else { lastvX = event.values[0];}
			last_timestamp = event.timestamp;
		}
		
	}
	
}
