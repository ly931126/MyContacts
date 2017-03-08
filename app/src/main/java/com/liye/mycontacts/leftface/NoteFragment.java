package com.liye.mycontacts.leftface;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liye.mycontacts.R;
import com.liye.mycontacts.menu.CallbyeTabActivity;
import com.liye.mycontacts.menu.RegisterActivity;
import com.liye.mycontacts.menu.TelephoneActivity;

@SuppressLint("ValidFragment")
public class NoteFragment extends Fragment  implements View.OnClickListener{

	Button mBtnback;// 返回
	Button mBtnzhuce;// 注册按钮
	Button mBtnlogin;// 登录
	EditText mEdtuser;// 输入账号
	EditText mEdtpassword;// 输入密码
	SharedPreferences sp;// 获得储存的数据

	/**
	 *
	 * 加载布局
	 * */
	View view;
	private TelephoneActivity activity;
	@SuppressLint("ValidFragment")
	public NoteFragment(TelephoneActivity activity) {
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.activity_note , container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// 返回按钮
		mBtnback = (Button) view.findViewById(R.id.btn_return);
		mBtnback.setOnClickListener(this);
		// 注册按钮
		mBtnzhuce = (Button)view.findViewById(R.id.btn_zhuce);
		mBtnzhuce.setOnClickListener(this);
		// 账号
		mEdtuser = (EditText) view.findViewById(R.id.edt_user);
		// 密码
		mEdtpassword = (EditText)view. findViewById(R.id.edt_password);
		// 登录
		mBtnlogin = (Button)view. findViewById(R.id.btn_user2);
		mBtnlogin.setOnClickListener(this);
		// 获得注册成功的账号和密码
		sp = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);



	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.btn_zhuce:

				// 跳转到注册界面
				Intent intent = new Intent(getActivity(), RegisterActivity.class);
				this.startActivity(intent);
				break;
			case R.id.btn_return:
				// 按钮按下，将抽屉打开
				activity.mDrawerLayout.openDrawer(Gravity.LEFT);


				break;
			case R.id.btn_user2:
				// 获得注册成功的账号和密码
				String s = sp.getString("username", null);
				String p = sp.getString("pwd", null);
				// Log.e(this+"=-----------",s+"------");
				// Log.e(this+"=-----------",p+"------");
				// 判断输入的账号密码是否匹配注册过的账号和密码
				// getText().toString().trim()获得文本并转化成字符串再去掉空格
				if (mEdtuser.getText().toString().trim().equals(s)
						&& mEdtpassword.getText().toString().trim().equals(p)) {
					// Toast窗口
					Toast.makeText(getActivity(), "成功登陆", Toast.LENGTH_LONG).show();
					Intent  intentDiarly=new Intent(getActivity(),CallbyeTabActivity.class);
					startActivity(intentDiarly);

				} else {
					// Toast窗口"1"表示显示时间稍长“0”表示很快
					Toast.makeText(getActivity(), "账户和密码不正确，请重试", Toast.LENGTH_LONG).show();

				}
				break;
		}
	}



}
