package com.liye.mycontacts.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;

import com.liye.mycontacts.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactsUtil {
	/*
	 
	 */

	/*
	 * 对联系人进行操作
	 */
	Context mContext;
	ContentResolver mContentResolver;

	public ContactsUtil(Context context) {
		this.mContext = context;
		mContentResolver = mContext.getContentResolver();

	}


	/*
	 * 添加联系人
	 */
	public void insert(String name, String phone, String email,String address,
					   Bitmap bitmap) {
		ArrayList<ContentProviderOperation> operation = new ArrayList<ContentProviderOperation>();
		// 往raw_contact中添加
		Builder rawContactBuild = ContentProviderOperation
				.newInsert(RawContacts.CONTENT_URI);
		rawContactBuild.withValue(RawContacts.ACCOUNT_NAME, "");// 往默认的账户中添加
		rawContactBuild.withValue(RawContacts.ACCOUNT_TYPE, "");// 往默认的账户类型中添加
		// 返回内容提供者的操作类
		ContentProviderOperation rawOperation = rawContactBuild.build();
		operation.add(rawOperation);

		// 往data表中添加
		Builder dataBuild = ContentProviderOperation
				.newInsert(Data.CONTENT_URI);
		// 将集合中第0个元素的结果作为键的内容
		dataBuild.withValueBackReference(Data.RAW_CONTACT_ID, 0);
		// 添加姓名
		//CommonDataKinds包含Data表的一些信息
		dataBuild.withValue(Data.MIMETYPE,
				CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
		// 值
		dataBuild.withValue(StructuredName.DISPLAY_NAME, name);
		operation.add(dataBuild.build());

		// 添加电话
		Builder dataBuild2 = ContentProviderOperation
				.newInsert(Data.CONTENT_URI);
		// 将集合中第0个元素的结果作为键的内容
		dataBuild2.withValueBackReference(Data.RAW_CONTACT_ID, 0);
		dataBuild2.withValue(Data.MIMETYPE,
				CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
		// 值
		dataBuild2.withValue(Phone.NUMBER, phone);
		operation.add(dataBuild2.build());

		// 邮箱
		Builder dataBuild3 = ContentProviderOperation
				.newInsert(Data.CONTENT_URI);
		// 将集合中第0个元素的结果作为键的内容
		dataBuild3.withValueBackReference(Data.RAW_CONTACT_ID, 0);
		dataBuild3.withValue(Data.MIMETYPE,
				CommonDataKinds.Email.CONTENT_ITEM_TYPE);
		// 值
		dataBuild3.withValue(Email.ADDRESS, email);
		operation.add(dataBuild3.build());

		// 地址
		Builder dataBuild4 = ContentProviderOperation
				.newInsert(Data.CONTENT_URI);
		// 将集合中第0个元素的结果作为键的内容
		dataBuild4.withValueBackReference(Data.RAW_CONTACT_ID, 0);
		dataBuild4.withValue(Data.MIMETYPE,
				CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE);
		// 值
		dataBuild4.withValue(CommonDataKinds.SipAddress.SIP_ADDRESS, address);
		operation.add(dataBuild4.build());

		// 添加头像
		ContentProviderOperation.Builder photoBuild = ContentProviderOperation
				.newInsert(Data.CONTENT_URI);
		photoBuild.withValueBackReference(Data.RAW_CONTACT_ID, 0);
		// 类型
		photoBuild.withValue(Data.MIMETYPE,
				CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
		// 添加值
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bos);
		// 将字节输出流转成byte数组
		byte bs[] = bos.toByteArray();
		photoBuild.withValue(CommonDataKinds.Photo.PHOTO, bs);
		operation.add(photoBuild.build());

		// 批量处理联系人
		// 参数authority：域名 operations：集合
		try {
			mContentResolver.applyBatch(ContactsContract.AUTHORITY, operation);
		} catch (RemoteException e) {

			e.printStackTrace();
		} catch (OperationApplicationException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 更新联系人
	 */
	//String address,
	public void update(int raw_contact_id, String newName, String phone,
					 String email, String address, Bitmap icon) {
		//Log.e(this + "", raw_contact_id + "------");
		ArrayList<ContentProviderOperation> opts = new ArrayList<ContentProviderOperation>();
		// 姓名
		Builder updateNameBuilder = ContentProviderOperation
				.newUpdate(Data.CONTENT_URI);

		// 添加条件
		updateNameBuilder.withSelection(Data.RAW_CONTACT_ID + "=?  and "
				+ Data.MIMETYPE + "=?", new String[]{raw_contact_id + "",
				StructuredName.CONTENT_ITEM_TYPE});
		// 添加类型
		updateNameBuilder.withValue(Data.MIMETYPE,
				StructuredName.CONTENT_ITEM_TYPE);
		// 添加值
		updateNameBuilder.withValue(StructuredName.DISPLAY_NAME, newName);
		opts.add(updateNameBuilder.build());

		// 邮箱
		Builder updateEmailBuilder = ContentProviderOperation
				.newUpdate(Data.CONTENT_URI);
		updateEmailBuilder.withSelection(Data.RAW_CONTACT_ID + "=?  and "
				+ Data.MIMETYPE + "=?", new String[]{raw_contact_id + "",
				Email.CONTENT_ITEM_TYPE});
		updateEmailBuilder.withValue( ContactsContract.CommonDataKinds.Email.DATA, email);
		opts.add(updateEmailBuilder.build());

		// 住址

		Builder updateAddressBuilder = ContentProviderOperation
				.newUpdate(Data.CONTENT_URI);
		updateAddressBuilder.withSelection( Data.RAW_CONTACT_ID + "=?  and "
				+ Data.MIMETYPE + "=?", new String[]{raw_contact_id + "",
				CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE});
		updateAddressBuilder.withValue(CommonDataKinds.SipAddress.SIP_ADDRESS , address);
		opts.add(updateAddressBuilder.build());

		// 电话
		Builder updatePhoneBuilder = ContentProviderOperation
				.newUpdate(Data.CONTENT_URI);
		updatePhoneBuilder.withSelection(Data.RAW_CONTACT_ID + "=?  and "
				+ Data.MIMETYPE + "=?", new String[]{raw_contact_id + "",
				Phone.CONTENT_ITEM_TYPE});
		updatePhoneBuilder.withValue(Phone.NUMBER, phone);
		opts.add(updatePhoneBuilder.build());

		// 头像
		Builder photoBuilder = ContentProviderOperation
				.newUpdate(ContactsContract.Data.CONTENT_URI);
		photoBuilder.withSelection(Data.RAW_CONTACT_ID + "=? and "
				+ Data.MIMETYPE + "=?", new String[]{raw_contact_id + "",
				Photo.CONTENT_ITEM_TYPE});
		photoBuilder.withValue(Data.MIMETYPE,
				CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
		//Log.e(this + "", "UserUtil-->icon" + icon);
		//添加值
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		icon.compress(CompressFormat.JPEG, 100, baos);
		//将字节输出流转成byte数组
		byte[] bt = baos.toByteArray();
		photoBuilder.withValue(Photo.PHOTO, bt);
		opts.add(photoBuilder.build());
		// 执行
		try {
			mContentResolver.applyBatch(ContactsContract.AUTHORITY, opts);
		} catch (RemoteException e) {

			e.printStackTrace();
		} catch (OperationApplicationException e) {

			e.printStackTrace();
		}
	}

	List<ContactInfo> contacts;

	/*
	 * 删除
	 */
	public void delete(int raw_contact_id) {
		ArrayList<ContentProviderOperation> opts = new ArrayList<ContentProviderOperation>();
		// 删除
		Builder deleteBuilder = ContentProviderOperation
				.newDelete(RawContacts.CONTENT_URI);
		// 添加条件
		deleteBuilder.withSelection(RawContacts._ID + "=?",
				new String[]{raw_contact_id + ""});
		opts.add(deleteBuilder.build());
		try {
			mContentResolver.applyBatch(ContactsContract.AUTHORITY, opts);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 查询联系人
	 */
	public List<ContactInfo> select() {
		contacts = new ArrayList<ContactInfo>();
		//拿到联系人的uri =ContactsContract.Contacts.CONTENT_URI;
		//拿到联系人的全部信息
		Cursor contactsCursor = mContentResolver.query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		for (int i = 0; i < contactsCursor.getCount(); i++) {
			contactsCursor.moveToPosition(i);
			// 联系人的id
			int contactId = contactsCursor.getInt(contactsCursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			// raw_contacts
			Cursor rawCuror = mContentResolver.query(
					ContactsContract.RawContacts.CONTENT_URI, null,
					RawContacts.CONTACT_ID + "=?", new String[]{contactId
							+ ""}, null);

			for (int j = 0; j < rawCuror.getCount(); j++) {
				rawCuror.moveToPosition(j);
				int rawContactId = rawCuror.getInt(rawCuror
						.getColumnIndex(RawContacts._ID));
				ContactInfo contact = new ContactInfo();
				contact.setContactId(contactId);
				contact.setRawContactId(rawContactId);
				// 获取联系人的头像
				getIconByContactId(contactId, contact);
				// 根据id获取联系人的名字
				getContactName(rawContactId, contact);
				// 获取手机号码
				getContactPhone(rawContactId, contact);
				// 获取邮箱
				getContactEamil(rawContactId, contact);
				// 获取地址
				getContactAddress(rawContactId, contact);
			//	Log.e(this + "", "contact=" + contact);
				// 将联系人添加到集合里
				contacts.add(contact);
			}

			rawCuror.close();
		}
		// 关闭游标
		contactsCursor.close();
		return contacts;

	}

	// 根据联系人的id获取联系人的头像
	public void getIconByContactId(int contactId, ContactInfo contact) {
		// 第一种方式
		Uri contactUri = Uri.withAppendedPath(Contacts.CONTENT_URI, contactId
				+ "");
		InputStream in = Contacts.openContactPhotoInputStream(mContentResolver,
				contactUri, false);
		if (null != in) {
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			Bitmap smallBitmap = bitmap
					.createScaledBitmap(bitmap, 80, 80, true);
			contact.setIcon(smallBitmap);
		} else {
			Bitmap bitmap = BitmapFactory.decodeResource(
					mContext.getResources(), R.drawable.t4);
			Bitmap smallBitmap = bitmap
					.createScaledBitmap(bitmap, 80, 80, true);
			contact.setIcon(smallBitmap);
		}

		// 第二种方式获取头像
		// Cursor dataCursor = mContentResolver.query(Data.CONTENT_URI, null,
		// "=? and " + Data.MIMETYPE + "=?", new String[] { "rid",
		// CommonDataKinds.Photo.CONTENT_ITEM_TYPE }, null);
		// for (int i = 0; i < dataCursor.getCount(); i++) {
		// dataCursor.moveToPosition(i);
		// byte[] bs = dataCursor.getBlob(dataCursor
		// .getColumnIndex(CommonDataKinds.Photo.PHOTO));
		// contact.setIcon(BitmapFactory.decodeByteArray(bs, 0, bs.length));
		// }
		// dataCursor.close();

	}

	// 获取联系人的名字
	/*
  *
  */
	public void  getContactName(int rawContactId, ContactInfo contact) {
		// data
		// 从data表查询所有的
		// 条件 rawId=? and mimeType=?
		Cursor dataCuror = mContentResolver.query(Data.CONTENT_URI, null,
				Data.RAW_CONTACT_ID + "=? and  " + Data.MIMETYPE + "=?",
				new String[]{rawContactId + "",
						CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE},
				null);

		for (int k = 0; k < dataCuror.getCount(); k++) {
			dataCuror.moveToPosition(k);
			// 获取姓名
			//根据列名CommonDataKinds.StructuredName.DISPLAY_NAME拿到下标
			String contactName = dataCuror
					.getString(dataCuror
							.getColumnIndex(CommonDataKinds.StructuredName.DISPLAY_NAME));
			// 添加联系人的姓名
			contact.setName(contactName);
			//Log.e(this + "", "contactName=" + contact.getName());

			String fw = getFirstWord(contactName);
			contact.setSortFirstWord(fw);// 排序的首字母

		}
		dataCuror.close();
	}

	// 获取联系人姓名的首字母
	// 根据姓名获取首字母
	public String getFirstWord(String name) {
		// 要将汉字转成拼音
		String pinyin = CharacterParser.getInstance().getSelling(name);
		String firstWord = pinyin.substring(0, 1).toString().toUpperCase();
		// 判断是否在26个字母之间
		// 大写的A~Z
		if (firstWord.matches("[A-Z]")) {
			return firstWord;
		} else {
			return "#";
		}

	}

	// 获取联系人的电话号码
	public void getContactPhone(int rawContactId, ContactInfo contact) {
		Cursor dataCursor = mContentResolver.query(
				ContactsContract.Data.CONTENT_URI, null, Data.RAW_CONTACT_ID
						+ "=? and  " + Data.MIMETYPE + "=?", new String[]{
						rawContactId + "",
						CommonDataKinds.Phone.CONTENT_ITEM_TYPE}, null);
		for (int i = 0; i < dataCursor.getCount(); i++) {
			dataCursor.moveToPosition(i);
			// 手机号码
			String phone = dataCursor.getString(dataCursor
					.getColumnIndex(CommonDataKinds.Phone.NUMBER));
			// String type=dataCursor.getString(dataCursor.getColumnIndex(
			// CommonDataKinds.Phone.TYPE));
			contact.setPhone(phone);
		}
		dataCursor.close();
	}

	// 获取联系人的邮箱
	public void getContactEamil(int rawContactId, ContactInfo contact) {
		Cursor dataCursor = mContentResolver.query(
				ContactsContract.Data.CONTENT_URI, null, Data.RAW_CONTACT_ID
						+ "=? and " + Data.MIMETYPE + "=?", new String[]{
						rawContactId + "",
						CommonDataKinds.Email.CONTENT_ITEM_TYPE}, null);
		for (int i = 0; i < dataCursor.getCount(); i++) {
			dataCursor.moveToPosition(i);
			// 邮箱
			String email = dataCursor.getString(dataCursor
					.getColumnIndex(CommonDataKinds.Email.ADDRESS));
			contact.setEmail(email);
		}
		dataCursor.close();
	}

	// 获取联系人的地址
	public void getContactAddress(int rawContactId, ContactInfo contact) {
		Cursor dataCursor = mContentResolver.query(
				ContactsContract.Data.CONTENT_URI, null, Data.RAW_CONTACT_ID
						+ "=? and " + Data.MIMETYPE + "=?", new String[]{
						rawContactId + "",
						CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE}, null);
		//Log.e(this + "", "dataCursor=" + dataCursor.getCount());
		for (int i = 0; i < dataCursor.getCount(); i++) {
			dataCursor.moveToPosition(i);
			String address = dataCursor.getString(dataCursor
					.getColumnIndex(CommonDataKinds.SipAddress.SIP_ADDRESS));
			//Log.e(this + "", "address=" + address);
			contact.setAddress(address);
		}
		dataCursor.close();
	}
}