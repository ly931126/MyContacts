package com.liye.mycontacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.utils.CommonUtil;
import com.liye.mycontacts.utils.ContactInfo;

import java.util.List;
/*
主页面获取联系人信息的适配器（头像，姓名，电话地址）
 */
public class ContactAdapter extends BaseAdapter {
    Context mContext;
    List<ContactInfo> contacts;

    public ContactAdapter(Context context, List<ContactInfo> contacts) {
        this.mContext = context;
        this.contacts = contacts;

    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public ContactInfo getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_phone,
                null);
        TextView sort = (TextView) view.findViewById(R.id.txt_show_sort1);
        ImageView photo = (ImageView) view.findViewById(R.id.img_show_photo1);

        TextView name = (TextView) view.findViewById(R.id.txt_show_name1);
        TextView phone = (TextView) view.findViewById(R.id.txt_contact_phone1);
        // ContactInfo contact = contacts.get(position);
        // Log.e("contacts", contacts + "");
        // name.setText(contact.getName());
        // phone.setText(contact.getPhone());

        // Log.e(this + "", "-------------" + contact.getName());
//		 TextView email = (TextView) view.findViewById(R.id.txt_show_email);
//		 TextView address = (TextView)
//		 view.findViewById(R.id.txt_show_address);
        // sort.setText(contacts.get(position).getSortFirstWord());

        // 判断position==0-->A
        int ascii = getFWAsciiPosition(position);
        // 遍历所有
        int firstPosition = getFirstPosition(ascii);
        if (position == firstPosition) {
            // 让第一个带A显示
            sort.setText(contacts.get(position).getSortFirstWord());
        } else {
            // ,其它隐藏
            sort.setVisibility(View.GONE);
        }

        photo.setImageBitmap(CommonUtil.createCircleImage(contacts
                .get(position).getIcon()));
        name.setText(contacts.get(position).getName());
        // Log.e(this + "", "####################");
        phone.setText(contacts.get(position).getPhone() + "\t   address:"
                + contacts.get(position).getAddress());

//		 email.setText(contacts.get(position).getEmail());
//		 address.setText(contacts.get(position).getAddress());
        return view;
    }

    // 找到position所对应的字母的ascii吗值
    public int getFWAsciiPosition(int position) {
        int wordAscii = contacts.get(position).getSortFirstWord().charAt(0);
        return wordAscii;
    }

    // 找到当前字母第一次出现的位置
    public int getFirstPosition(int ascii) {
        for (int i = 0; i < getCount(); i++) {
            int fwAscii = contacts.get(i).getSortFirstWord().charAt(0);
            if (ascii == fwAscii) {
                return i;
            }
        }

        return 0;
    }

    public void reflash(List<ContactInfo> filter) {
        contacts = filter;
        notifyDataSetChanged();
    }
}
