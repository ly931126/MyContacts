package com.liye.mycontacts.myContacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.liye.mycontacts.R;
import com.liye.mycontacts.adapter.IconPagerAdapter;
import com.liye.mycontacts.menu.TelephoneActivity;
import com.liye.mycontacts.utils.CommonUtil;
import com.liye.mycontacts.utils.ContactsUtil;

import java.util.ArrayList;
import java.util.List;

public class AddPeopleActivity extends Activity implements OnClickListener {
	Button mBcancel, mSave;
	ContactsUtil mContactsUtil;
	EditText mEdtName;
	EditText mEdtPhone;
	EditText mEdtEmail;
	EditText mEdtAddress;
	ImageView mIgvIcon;
	//ViewPager mVpgIcon;
	List<ImageView> image = new ArrayList<ImageView>();
	int[] iconId = { R.drawable.t1, R.drawable.t2, R.drawable.t3,
			R.drawable.lianxiren, R.drawable.t4, R.drawable.t5, R.drawable.t6,
			R.drawable.t7, R.drawable.t8, R.drawable.t10, R.drawable.t11,
			R.drawable.touxiang };
	//ImageView[] mIcon = new ImageView[iconId.length];
	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_people);
		initData();
	}

	public void initData() {
		mBcancel = (Button) this.findViewById(R.id.btn_cancel);
		mBcancel.setOnClickListener(this);
		mSave = (Button) this.findViewById(R.id.btn_save);
		mSave.setOnClickListener(this);
		mEdtName = (EditText) findViewById(R.id.edt_show_name4);
		mEdtPhone = (EditText) findViewById(R.id.edt_show_phone4);
		mEdtEmail = (EditText) findViewById(R.id.edt_show_email4);
		mEdtAddress = (EditText) findViewById(R.id.edt_show_address4);
		mIgvIcon = (ImageView) findViewById(R.id.img_show_photo4);
		mIgvIcon.setOnClickListener(this);
		mContactsUtil = new ContactsUtil(this);
		// mVpgIcon = (ViewPager) findViewById(R.id.vpg_add_contact);

		for (int i = 0; i < iconId.length; i++) {

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					iconId[i]);
			Bitmap smallBitmap = Bitmap.createScaledBitmap(bitmap, 100, 80,
					true);

			Bitmap circleBitmap = CommonUtil.createCircleImage(smallBitmap);

			ImageView igv = new ImageView(this);
			igv.setImageBitmap(circleBitmap);
			// igv.setImageResource(iconId[i]);
			image.add(igv);
			// mIcon[i] = igv;

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.img_show_photo4:

				Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("请选择头像");
				View view = LayoutInflater.from(this).inflate(
						R.layout.item_viewpager, null);
				ViewPager mVpgIcon = (ViewPager) view
						.findViewById(R.id.vpg_add_viewpager);
				IconPagerAdapter adapter = new IconPagerAdapter(image, this);
				mVpgIcon.setAdapter(adapter);
				builder.setView(view);
				mVpgIcon.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						position = arg0;
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								Bitmap bitmap = BitmapFactory.decodeResource(
										getResources(), iconId[position]);
								Bitmap smallBitmap = Bitmap.createScaledBitmap(
										bitmap, 100, 80, true);

								Bitmap circleBitmap = CommonUtil
										.createCircleImage(smallBitmap);
								mIgvIcon.setImageBitmap(circleBitmap);
							}
						});
				builder.show();

				break;
			case R.id.btn_cancel:
				finish();

				break;

			case R.id.btn_save:
				// 保存
				if (null == mEdtName) {
					Toast.makeText(this, "名字不能为空", Toast.LENGTH_LONG).show();
				} else {
					addNewContact();
					Intent intent = new Intent(AddPeopleActivity.this,
							TelephoneActivity.class);
					startActivity(intent);
					finish();
				}
				break;
		}
	}

	private void addNewContact() {
		String name = mEdtName.getText().toString();
		String phone = mEdtPhone.getText().toString();
		String email = mEdtEmail.getText().toString();
		String address = mEdtAddress.getText().toString();
		Drawable drawable = mIgvIcon.getDrawable();
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bitmap = bd.getBitmap();
		mContactsUtil.insert(name, phone, email, address, bitmap);

	}
}
