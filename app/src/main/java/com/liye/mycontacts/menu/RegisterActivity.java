package com.liye.mycontacts.menu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liye.mycontacts.R;

import java.util.Random;

/**
 * 注册界面
 * 
 * */
public class RegisterActivity extends Activity implements OnClickListener {
	Button mBtngettest;//获取验证码
	EditText mEdtTest;//输入验证码
	Button mBtnlogin;//注册
	EditText mEdt_reg_user;//输入注册账户
	EditText mEdt_reg1_password;//设置密码
	EditText mEdt_reg3_password;//再次确认密码
	Button mBtn_return;//返回主界面
	SharedPreferences sp;// 将数据存储到软件中

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载注册布局
		setContentView(R.layout.activity_register);
		sp = getSharedPreferences("user", Activity.MODE_PRIVATE);

	}

	@Override
	public void onContentChanged() {
		// 绑定获取验证码的按钮id并设置他的监听事件
		mBtngettest = (Button) findViewById(R.id.btn_gettest);
		mBtngettest.setOnClickListener(this);
		mEdtTest = (EditText) findViewById(R.id.edt_test);
		// 绑定注册按钮id并设置他的监听事件
		mBtnlogin = (Button) findViewById(R.id.btn_last_login);
		mBtnlogin.setOnClickListener(this);
		// 账号和密码的加载
		mEdt_reg_user = (EditText) findViewById(R.id.edt_reg_user);
		mEdt_reg1_password = (EditText) findViewById(R.id.edt_reg1_password);
		mEdt_reg3_password = (EditText) findViewById(R.id.edt_reg3_password);
		// 返回
		mBtn_return = (Button) findViewById(R.id.btn_return);
		mBtn_return.setOnClickListener(this);
	}

	int testword;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_gettest:
				// 随机一个获取验证码
				Random rd = new Random();
				testword = rd.nextInt(10000);
				// 弹出一个toast窗口  LENGTH_LONG显示时间是稍长
				Toast.makeText(this, "当前验证码为" + testword, Toast.LENGTH_LONG).show();

				break;

			case R.id.btn_last_login:
				//把获取的验证码转化成字符并去掉空格
				String test = mEdtTest.getText().toString().trim();
				// (testword + "")表示把验证码转化成字符串
				// 判断输入的验证码和获取的是否相等
				if (test.equals(testword + "")) {
					// 判断输入的账号密码不为空
					if (mEdt_reg_user.getText().toString().trim() != null
							&& mEdt_reg1_password.getText().toString().trim() != null
							&& mEdt_reg3_password.getText().toString().trim() != null) {
						// 判断两次输入的密码是否一致
						if (mEdt_reg1_password.getText().toString().trim()
								.equals(mEdt_reg3_password.getText().toString()
										.trim())) {
							//将输入的密码和账号重新给一个Editor并转化成字符串去掉空格
							Editor ed = sp.edit();
							ed.putString("username", mEdt_reg_user.getText()
									.toString().trim());
							ed.putString("pwd", mEdt_reg1_password.getText()
									.toString().trim());
							//检索所有的值
							ed.apply();
							//弹出一个窗口显示注册成功
							Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
							//跳转到登录界面
							finish();
//							Intent intent = new Intent(this, NoteFragment.class);
//							 startActivity(intent);
							// 不符合账号验证码以及密码条件  则弹出窗口显示
						} else {
							Toast.makeText(this, "两次密码不同，请重试", Toast.LENGTH_SHORT)
									.show();

						}

					} else {
						Toast.makeText(this, "密码或账号为空", Toast.LENGTH_SHORT).show();

					}

				}
				break;
			case R.id.btn_return:
				//返回主界面
				finish();

				break;

		}

	}
}
