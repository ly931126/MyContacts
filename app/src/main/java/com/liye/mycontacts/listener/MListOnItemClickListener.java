package com.liye.mycontacts.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.liye.mycontacts.menu.TelephoneActivity;
import com.liye.mycontacts.myContacts.XiangxiActivity;
import com.liye.mycontacts.utils.ContactInfo;

/**
 * Created by MK on 2016/4/8.
 */
/*
电话薄列表的监听事件
 */
public class MListOnItemClickListener  implements AdapterView.OnItemClickListener{
    private TelephoneActivity activity;
    public MListOnItemClickListener(TelephoneActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContactInfo contact = activity.adapter.getItem(position);
        //  Log.e(this + "", contact.getAddress() + " contact=" + contact);
        Intent intent = new Intent( activity,
                XiangxiActivity.class);
        intent.putExtra("contact", contact);
       activity.startActivity(intent);
    }
}
