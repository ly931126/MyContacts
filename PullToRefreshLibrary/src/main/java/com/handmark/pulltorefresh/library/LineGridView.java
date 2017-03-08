package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Chaos on 2016/10/24.
 */

public class LineGridView extends GridView
{

	private int mLineColor = 0;
	private int mLineWidth = 0;
	private int mLineMarginTop = 0;
	private int mLineMarginRight = 0;

	public LineGridView(Context context)
	{
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public LineGridView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineGridView);
		mLineColor = typedArray.getColor(R.styleable.LineGridView_item_line_color, mLineColor);
		mLineWidth = typedArray.getDimensionPixelOffset(R.styleable.LineGridView_line_width, mLineWidth);
		mLineMarginTop = typedArray.getDimensionPixelOffset(R.styleable.LineGridView_line_margin_top, mLineMarginTop);
		mLineMarginRight = typedArray.getDimensionPixelOffset(R.styleable.LineGridView_line_margin_right, mLineMarginRight);
		typedArray.recycle();
	}

	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		if (getCount() == 0 || getChildAt(0) == null)
			return;
		int column = getWidth() / getChildAt(0).getWidth();
		int childCount = getChildCount();
		Paint localPaint;
		localPaint = new Paint();
		localPaint.setStyle(Paint.Style.STROKE);
		localPaint.setColor(mLineColor);
		localPaint.setStrokeWidth(mLineWidth);
		for (int i = 0; i < childCount; i++)
		{
			View cellView = getChildAt(i);
			int xRight = cellView.getRight();
			int xLeft = cellView.getLeft();
			int yTop = cellView.getTop();
			int yBottom = cellView.getBottom();
			if (i == 0)
			{
				// first view
				canvas.drawLine(xRight, yTop + mLineMarginTop, xRight, yBottom, localPaint);
				canvas.drawLine(xLeft + mLineMarginRight, yBottom, xRight, yBottom, localPaint);
			}
			else if (i % column == 0)
			{
				// first column view
				canvas.drawLine(xRight, yTop, xRight, yBottom, localPaint);
				canvas.drawLine(xLeft + mLineMarginRight, yBottom, xRight, yBottom, localPaint);
			}
			else
			{
				canvas.drawLine(xLeft, yBottom, xRight - mLineMarginRight, yBottom, localPaint);
			}
		}
	}

	public void setLineColor(int lineColor)
	{
		mLineColor = lineColor;
	}

	public void setLineWidth(int lineWidth)
	{
		mLineWidth = lineWidth;
	}

	public void setLineMarginTop(int lineMarginTop)
	{
		mLineMarginTop = lineMarginTop;
	}

	public void setLineMarginRight(int lineMarginRight)
	{
		mLineMarginRight = lineMarginRight;
	}
}
