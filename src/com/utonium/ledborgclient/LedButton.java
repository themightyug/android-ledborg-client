package com.utonium.ledborgclient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.Button;

public class LedButton extends Button {

	private Paint paint = new Paint();
	private Rect clip = new Rect();


	private Colour _colour = new Colour("000");



	public Colour getColour() {
		return _colour;
	}
	public void setColour(Colour colour) {
		this._colour = colour;
	}



	public LedButton(Context context) {
		super(context);
	}

	public LedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		processAttrs(context, attrs);
	}

	public LedButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		processAttrs(context, attrs);
	}



	private void processAttrs(Context context, AttributeSet attrs){
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LedButton);

		final int N = a.getIndexCount();
		for (int i = 0; i < N; ++i)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.LedButton_colour:
				String myText = a.getString(attr);
				this._colour = new Colour(myText);
				break;
			}
		}
		a.recycle();
	}


	@Override
	public void onDraw (Canvas canvas) {
		//super.draw(canvas);

		this.setBackgroundDrawable(null);
		this.setBackgroundColor(Color.parseColor(_colour.getHtmlColour()));

		canvas.getClipBounds(clip);

		int height = clip.height();
		int width = clip.width();

		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(5);
		canvas.drawRect(0, 0, width, height, paint);

		paint.setColor(Color.parseColor(_colour.getHtmlColour()));
		canvas.drawRect(10, 10, (width-10), (height-10), paint);

		//super.onDraw(canvas);
	}

}
