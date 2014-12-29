package com.gamedemo.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class GameView extends View {

	int scrW ;
	int scrH;
	Rect topRect;
	Rect downRect;
	Paint mRectPaint ;
	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}
    public GameView(final Context context) {
        super(context);
        init();
    }

    public GameView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

	
	public void init() {
		
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
	
}
