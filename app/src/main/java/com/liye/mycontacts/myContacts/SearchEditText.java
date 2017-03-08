package com.liye.mycontacts.myContacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.liye.mycontacts.R;

//自定义的搜索框
@SuppressLint("NewApi")
public class SearchEditText extends EditText {

	Drawable drawableLeft;
	Drawable drawableRight;

	public SearchEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 在代码中设置左边和右边的图
		init();

	}

	@SuppressLint("NewApi")
	private void init() {
		drawableLeft = this.getCompoundDrawablesRelative()[0];
		drawableRight = this.getCompoundDrawablesRelative()[2];
		if (null == drawableLeft) {
			drawableLeft = this.getResources().getDrawable(
					R.drawable.search_bar_icon_normal);
		}
		// 编辑框后有内容才显示右边的图案（x);
		if (null == drawableRight) {
			drawableRight = this.getResources().getDrawable(
					R.drawable.emotionstore_progresscancelbtn);
		}
		setIsVisiblePicRight(false);
		// 文本改变时的监听事件
		this.addTextChangedListener(new TextWatcher() {

			// 文本改变之前调用
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {

			}

			// 文本改变时调用
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				// 显示
				// if(s.length()>0){
				// setIsVisiblePicRight(true);
				//
				// }
				// 或者
				setIsVisiblePicRight(s.length() > 0);
			}

			// 文本改变之后调用
			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});

	}

	// 设置编辑框内右边的图何时可见
	private void setIsVisiblePicRight(boolean isVisible) {
		// 这个方法也可以
		// this.setCompoundDrawables(left, top, right, bottom)
		// 给四个方向设置图标
		// 顶部和底部设为空
		this.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableLeft,
				null, isVisible ? drawableRight : null, null);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 按下
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();
			if (x > this.getWidth() - drawableRight.getIntrinsicWidth()
					- this.getPaddingRight()
					&& x < this.getWidth() - this.getPaddingRight()) {
				setText("");
			}
		}

		return super.onTouchEvent(event);

	}
}


