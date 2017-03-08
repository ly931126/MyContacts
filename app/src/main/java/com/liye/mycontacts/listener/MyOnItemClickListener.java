package com.liye.mycontacts.listener;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.leftface.CalendarFragment;
import com.liye.mycontacts.leftface.LookAndListenFragment;
import com.liye.mycontacts.leftface.NoteFragment;
import com.liye.mycontacts.leftface.SimleFragment;
import com.liye.mycontacts.menu.TelephoneActivity;

/**
 * Created by MK on 2016/4/8.
 */
/*
侧滑菜单子条目的监听事件
 */
public class MyOnItemClickListener implements AdapterView.OnItemClickListener {
    private TelephoneActivity activity;
    public MyOnItemClickListener(TelephoneActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // String text = activity.contentItems[position];
       // Toast.makeText(TelephoneActivity.this, text, Toast.LENGTH_SHORT).show();
        FragmentTransaction tran1 = activity.getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                //添加日历界面
                tran1.replace(R.id.content_frame, new CalendarFragment());
                tran1.commit();
                break;
            case 1:
                //添加冷笑话界面
                tran1.replace(R.id.content_frame, new SimleFragment(activity));
                tran1.commit();
                break;
            case 2:
                //添加动物世界界面
                tran1.replace(R.id.content_frame, new LookAndListenFragment(activity));
                tran1.commit();
                break;
            case 3:
                //添加心情记事本界面
                tran1.replace(R.id.content_frame, new NoteFragment(activity));
                tran1.commit();
                break;
        }
    }
    }


