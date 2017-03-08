package com.liye.mycontacts.utils;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/*
 * 联系人信息的封装类
 */
public class ContactInfo implements Parcelable {
	public int contactId;
	public int rawContactId;

	public String name;
	public String phone;
	public String email;
	public String address;
	// 记录排序的首字母
	public String sortFirstWord;
	// 联系人头像
	public Bitmap icon;
	public int getRawContactId() {
		return rawContactId;
	}

	public void setRawContactId(int rawContactId) {
		this.rawContactId = rawContactId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSortFirstWord() {
		return sortFirstWord;
	}

	public void setSortFirstWord(String fw) {
		this.sortFirstWord = fw;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	// 将对象序列化（将对象写入到流当中)
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(contactId);
		dest.writeString(name);
		dest.writeString(phone);
		dest.writeString(email);
		dest.writeString(address);
		// 头像
		// icon.writeToParcel(dest, 0);
		dest.writeValue(icon);

	}

	// 反序列化
	public static final Creator<ContactInfo> CREATOR = new Creator<ContactInfo>() {
		// 将对象进行反序列化
		@Override
		public ContactInfo createFromParcel(Parcel source) {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(source.readInt());
			contact.setName(source.readString());

			contact.setPhone(source.readString());

			contact.setEmail(source.readString());
			contact.setAddress(source.readString());

			// contact.setIcon((Bitmap) source.readParcelable(Bitmap.class
			// .getClassLoader()));
			Bitmap bitmap = (Bitmap) source.readValue(Bitmap.class
					.getClassLoader());
			contact.setIcon(bitmap);

			return contact;

			// if(state==a1){
			// //���
			// }else{
			// //����
			// }
		}

		@Override
		public ContactInfo[] newArray(int size) {

			return new ContactInfo[size];
		}
	};

}
