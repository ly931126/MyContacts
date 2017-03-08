package com.liye.mycontacts.myContacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.menu.TelephoneActivity;
import com.liye.mycontacts.utils.CommonUtil;
import com.liye.mycontacts.utils.ContactInfo;
import com.liye.mycontacts.utils.ContactsUtil;

import java.util.List;

public class XiangxiActivity extends Activity implements OnClickListener {
	TextView mReturn;
	ImageView mIcon;
	TextView mName, mPhone, mEmail, mAddress, mCallPhone;
	ContactsUtil mContactsUtil;
	List<ContactInfo> contacts;
	TextView mEditContact, mDeleteContact, mSendDesk;
	ContactInfo contactInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiangxi);
		Intent intent = getIntent();
		contactInfo = intent.getParcelableExtra("contact");
//		Log.e(this + "",
//				contactInfo + " contactInfo=" + contactInfo.getAddress());
		initData();

	}

	public void initData() {

		mReturn = (TextView) this.findViewById(R.id.txt_return3);
		mReturn.setOnClickListener(this);
		mIcon = (ImageView) this.findViewById(R.id.img_show_photo3);
		Bitmap smallBitmap = Bitmap.createScaledBitmap(contactInfo.getIcon(), 100, 80, true);
		Bitmap circleBitmap = CommonUtil.createCircleImage(smallBitmap);
		mIcon.setImageBitmap(circleBitmap);

		mName = (TextView) this.findViewById(R.id.txt_show_name3);
		mName.setText(contactInfo.getName());
		mPhone = (TextView) this.findViewById(R.id.txt_show_phone3);
		mPhone.setText(contactInfo.getPhone());
		mEmail = (TextView) this.findViewById(R.id.txt_show_email3);
		mEmail.setText(contactInfo.getEmail());
		mAddress = (TextView) this.findViewById(R.id.txt_show_address3);
		mAddress.setText(contactInfo.getAddress());

		mEditContact = (TextView) this.findViewById(R.id.edit_contact3);
		mEditContact.setOnClickListener(this);
		mDeleteContact = (TextView) this.findViewById(R.id.delete_contact3);
		mDeleteContact.setOnClickListener(this);
//		mSendDesk = (TextView) this.findViewById(R.id.send_desk3);
//		mSendDesk.setOnClickListener(this);
		mCallPhone = (TextView) this.findViewById(R.id.callPhone);
		mCallPhone.setOnClickListener(this);
		mContactsUtil = new ContactsUtil(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.txt_return3:
				finish();
				break;
			case R.id.edit_contact3:
				Intent editContact = new Intent(XiangxiActivity.this,
						EditContactActivity.class);
				editContact.putExtra("contact", contactInfo);
				startActivity(editContact);
				break;
			case R.id.delete_contact3:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("删除");
				builder.setMessage("确定要删除联系人吗?");

				builder.setPositiveButton("删除",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

								mContactsUtil.delete(contactInfo.getContactId());
								Intent delete = new Intent(XiangxiActivity.this,
										TelephoneActivity.class);
								startActivity(delete);
								finish();
							}
						}

				).show();

				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

								finish();
								// dialog.dismiss();
							}
						}).show();

				break;
//			case R.id.send_desk3:
//				// 发送到桌面
//				Intent send = new Intent(
//						"com.android.launcher.action.INSTALL_SHORTCUT");
//				// 快捷方式 图标 名字
//				// 图标
//				send.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, R.drawable.t4);
//				// 名字
//				send.putExtra(Intent.EXTRA_SHORTCUT_NAME, "");
//				Intent handle = XiangxiActivity.this.getPackageManager()
//						.getLaunchIntentForPackage(
//								XiangxiActivity.this.getPackageName());
//				// 点击快捷方式---->处理的事情
//				//Intent handle = new Intent(Intent.ACTION_CALL);
//				send.putExtra(Intent.EXTRA_SHORTCUT_INTENT, handle);
//
//				sendBroadcast(send);
//
//				Toast.makeText(getApplicationContext(), "桌面的快捷方式添加完成",
//						Toast.LENGTH_LONG).show();
//
//				break;
			case R.id.callPhone:
				// 打电话的意图
				Intent intent = new Intent();
				// Intent.ACTION_CALL打电话的动作
				intent.setAction(Intent.ACTION_CALL);
				// uri统一资源标示符
				intent.setData(Uri.parse("tel:" + contactInfo.getPhone()));
				// 开启一个新的界面
				startActivity(intent);
				break;

		}

	}
}