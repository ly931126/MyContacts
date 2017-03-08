package com.liye.mycontacts.menu;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import java.util.List;

public abstract class TopTabActivity extends TabActivity {

	private TabHost tabHost;
	private int tabLayout;
	 public abstract List<MyTab> getMyTabList();
	public TopTabActivity(int tabLayout) {
		this.tabLayout = tabLayout;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(tabLayout);
//		首先是实例化TabHost，
				tabHost = getTabHost(); // Get TabHost after setContentView()
		        initTabHost();
	}

	private void initTabHost() {
		int index = 0;
		List<MyTab> myTabList = getMyTabList();
		for (MyTab myTab : myTabList) {
			index++;
			String tag = Integer.toString(index);
			TabView view = new TabView(this, myTab.icon//下方的两个图标
					);
			TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag) //获得TabHost.TabSpec对象实例
					.setIndicator(view)// //为TabSpec对象设置指示器
					.setContent(new Intent(this, myTab.activity));  //为选项卡设置内容，这里需要创建一个intent对象
//			然后向TabHost中添加tab页
//			最后一步，把 选项卡TabSpec添加到选项卡控件TabHost中
			tabHost.addTab(tabSpec);
		}
	}

	/**
	 * Layout for each TabSpec
	 */
	private class TabView extends LinearLayout {
		private ImageView imageView;
		public TabView(Context c, int icon) {
			super(c);
			setOrientation(VERTICAL);
			setGravity(Gravity.CENTER);
			imageView = new ImageView(c);
			imageView.setImageDrawable(this.getResources().getDrawable(icon));
			imageView.setBackgroundColor(Color.TRANSPARENT);
			addView(imageView);

		}
	}

	/**
	 * Options for each TabSpec. Icon + Text + Activity
	 */
	public class MyTab {
		private int icon;
		private Class<? extends Activity> activity;
		public MyTab(int icon,  Class<? extends Activity> activity) {
			this.icon = icon;
			this.activity = activity;
		}
	}
}