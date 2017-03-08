package com.liye.mycontacts.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class CommonUtil {
	public static Bitmap createCircleImage(Bitmap source) {
		int min = source.getWidth();
		int height = source.getHeight();

		if (min > height) {
			min = height;
		}
		final Paint paint = new Paint();
		// 去掉锯齿
		paint.setAntiAlias(true);
		// 正方形
		Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
		/**
		 * 产生一个同样大小的画布
		 */
		Canvas canvas = new Canvas(target);
		/**
		 *  首先绘制圆形
		 */
		canvas.drawCircle(min / 2, min / 2, min / 2, paint);
		/**
		 * ʹ 使用SRC_IN，参考上面的说明 当图片有交集时，图片如何取 转换的模式：PorterDuffXfermodeerDuffXfermode
		 */
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		/**
		 * 绘制图片
		 * 
		 */
		canvas.drawBitmap(source, 0, 0, paint);
		return target;
	}
}
