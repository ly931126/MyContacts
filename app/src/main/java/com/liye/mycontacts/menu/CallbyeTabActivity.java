package com.liye.mycontacts.menu;


import com.liye.mycontacts.R;

import java.util.ArrayList;
import java.util.List;

/*
底部按钮跳转选择
TabHost是整个Tab的容器，包括两部分，TabWidget和FrameLayout。
TabWidget就是每个tab的标签，FrameLayout则是tab内容。
 */
public class CallbyeTabActivity extends TopTabActivity {
	public CallbyeTabActivity() {
		// R.drawable.zise
		super(R.layout.buttom_center);

	}

	@Override
	public List<MyTab> getMyTabList() {
		//集合
		List<MyTab> myTabList = new ArrayList<MyTab>();
		//"记录心情",
		myTabList.add(new MyTab(R.drawable.pink_design_16,
				RecordNote.class));
		//"吐槽吧",
		myTabList.add(new MyTab(R.drawable.pink_design_15,
				WriteMyselfSpace.class));
		return myTabList;
	}
	
}