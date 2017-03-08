package com.liye.mycontacts.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
/*
侧滑菜单字条目图标的适配器
 */
public class IconPagerAdapter extends PagerAdapter {
	// ImageView[] mIgcIcon;
	Context mContext;
	List<ImageView> mimage;

	public IconPagerAdapter(List<ImageView> image, Context context) {
		super();
		mimage = image;
		this.mContext = context;
	}

	@Override
	public int getCount() {

		return mimage.size();
	}

	@Override
	public ImageView instantiateItem(ViewGroup container, int position) {
		container.addView(mimage.get(position));
		return mimage.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((ImageView) object);
	}
}
