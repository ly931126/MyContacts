package com.liye.mycontacts.utils;

import java.util.Comparator;

public class PinyinComparator implements Comparator<ContactInfo> {

	@Override
	public int compare(ContactInfo lhs, ContactInfo rhs) {
		// 返回3个值
		// 1表示前面的大于后面的
		// 0表示相等
		// -1表示前面的小雨后面的
		// return lhs.getName().compareToIgnoreCase(rhs.getName());
		return lhs.getSortFirstWord().compareTo(rhs.getSortFirstWord());

	}

}
