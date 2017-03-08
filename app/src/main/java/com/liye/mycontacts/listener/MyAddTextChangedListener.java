package com.liye.mycontacts.listener;

import android.text.Editable;
import android.text.TextWatcher;

import com.liye.mycontacts.menu.TelephoneActivity;

/**
 * Created by MK on 2016/4/8.
 */
/*
文本改变的监听事件
 */
    //TextWatcher编辑框监听器，会实现下面三个方法
public class MyAddTextChangedListener  implements TextWatcher {
    private TelephoneActivity activity;
    public MyAddTextChangedListener(TelephoneActivity activity) {
        this.activity = activity;
    }
    //文本改变之前
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // s:之前的文字内容
        // start:添加文字的位置(从0开始)
        // count:不知道 一直是0
        // after:添加的文字总数

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // s:之后的文字内容
        // start:添加文字的位置(从0开始)
        // before:不知道 一直是0
        // before:添加的文字总数

        //filterContact（）过滤联系人信息的方法
        activity.filterContact(s.toString());
    }
    //文本改变之后
    @Override
    public void afterTextChanged(Editable s) {
// s:之后的文字内容
    }
}
