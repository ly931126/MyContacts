package com.liye.mycontacts.leftface;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.menu.TelephoneActivity;

@SuppressLint("ValidFragment")
public class LookAndListenFragment extends Fragment {
	
	ImageView					mImgReturn;
	// 声名变量
	private Button				start	= null;
	private Button				pause	= null;
	private Button				stop	= null;
	private TextView			state	= null;
	private MediaPlayer			mp3;
	private Boolean				flag	= false;	// 设置标记，false表示正在播放
	// int datas[] = { R.raw.simple, R.raw.puzzle,R.raw.sing };
	// int index;
	View						view;
	
	private TelephoneActivity	activity;
	@SuppressLint("ValidFragment")
	public LookAndListenFragment(TelephoneActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 防止锁屏
		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		view = inflater.inflate(R.layout.fragment_lookandlisten, container, false);
		initView();
		return view;
		
	}
	private void initView() {
		
		mImgReturn = (ImageView) view.findViewById(R.id.menu1);
		mImgReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 按钮按下，将抽屉打开
				activity.mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		start = (Button) view.findViewById(R.id.start);
		pause = (Button) view.findViewById(R.id.pause);
		stop = (Button) view.findViewById(R.id.stop);
		state = (TextView) view.findViewById(R.id.state); // 为每个按钮设置单击事件
		start.setOnClickListener(new OnClickListenerStart());
		pause.setOnClickListener(new OnClickListenerPause());
		stop.setOnClickListener(new OnClickListenerStop());
		mp3 = new MediaPlayer(); // 创建一个MediaPlayer对象
		// 在res下新建一个raw文件夹把一首歌放到此文件夹中并用英文命名
		mp3 = MediaPlayer.create(getActivity(), R.raw.puzzle);
		
	}
	
	// 各按钮单击事件的实现如下 //开始播放
	
	//
	private class OnClickListenerStart implements View.OnClickListener { //
		// implementsOnClickListener为实现OnClickListener接口
		@Override // 重写onClic事件
		public void onClick(View v) {
			// 执行的代码，其中可能有异常。一旦发现异常，则立即跳到catch执行。否则不会执行catch里面的内容
			try {
				if (mp3 != null) {
					mp3.stop();
				}
				mp3.prepare();
				// 进入到准备状态
				mp3.start();
				mp3.setLooping(true);
				// 开始播放
				state.setText("开始播放");
				// 改变输出信息为“Playing”，下同
			} catch (Exception e) {
				// state.setText(e.toString());
				// 以字符串的形式输出异常
				e.printStackTrace();
				// 在控制台（control）上打印出异常
				
			}
		}
	} // 暂停播放
	
	private class OnClickListenerPause implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			try {
				if (flag == false)
				// 若flag为false，则表示此时播放器的状态为正在播放
				{
					mp3.pause();
					flag = true;
					state.setText("暂停");
				} else if (flag == true) {
					mp3.start();
					// 开始播放
					flag = false;
					// 重新设置flag为false
					state.setText("开始播放");
				}
			} catch (Exception e) {
				// state.setText(e.toString());
				e.printStackTrace();
				
			}
		}
	}
	
	// 停止播放
	private class OnClickListenerStop implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			try {
				if (mp3 != null) {
					mp3.stop();
					state.setText("停止");
				}
			} catch (Exception e) {
				// state.setText(e.toString());
				e.printStackTrace();
				
			}
		}
	}
	
	// 重写暂停状态事件
	public void onPause() {
		try {
			mp3.release(); // 释放音乐资源
		} catch (Exception e) {
			// state.setText(e.toString());
			e.printStackTrace();
		}
		super.onPause();
	}
	
}
