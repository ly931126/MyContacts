package com.liye.mycontacts.menu;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liye.mycontacts.R;
import com.liye.mycontacts.adapter.ContactAdapter;
import com.liye.mycontacts.adapter.ListViewAdapter;
import com.liye.mycontacts.listener.MListOnItemClickListener;
import com.liye.mycontacts.listener.MyAddTextChangedListener;
import com.liye.mycontacts.listener.MyOnItemClickListener;
import com.liye.mycontacts.listener.MyOnclickListener;
import com.liye.mycontacts.myContacts.SearchEditText;
import com.liye.mycontacts.utils.CommonUtil;
import com.liye.mycontacts.utils.ContactInfo;
import com.liye.mycontacts.utils.ContactsUtil;
import com.liye.mycontacts.utils.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TelephoneActivity  extends FragmentActivity {
     //侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏。
    private ImageView ImageView_menu;
    ProgressDialog mProgressDialog;// 圆形进度条的对话框
    public static final int LOAD_FINISH = 0X01;
    private List<Map<String, Object>> listitem;
    ListView mList;
    //侧滑中展示其它功能的
    ListView mListShow;
    List<ContactInfo> filterContacts;
    ImageView mHead;
    ImageView mExit;
    TextView mAddText;
    TextView mCallphone;
    ContactInfo mContactInfo;
    List<ContactInfo> contacts;
    ContactsUtil mContactsUtil;
    public DrawerLayout mDrawerLayout;
    public ContactAdapter adapter;
    SearchEditText mSearchEditText;
    public String[] contentItems = {"Content Item a1", "Content Item 2", "Content Item 3",
            "Content Item 4"};
    String[] title = {"日历", "午后小憩", "听音乐", "心情记事本"};
    int imageId[] = {R.drawable.calendar2, R.drawable.image30, R.drawable.tocas, R.drawable.pink_design};
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == LOAD_FINISH) {
                // 按首字母排序
                Collections.sort(contacts, new PinyinComparator());
                adapter = new ContactAdapter(TelephoneActivity.this, contacts);
                mList.setAdapter(adapter);
                // 取消进度条
                mProgressDialog.dismiss();
            }
        }

        ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);

        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, "请稍等", "数据正在加载......");
        listitem = new ArrayList<Map<String, Object>>();
        mContactInfo = new ContactInfo();
        // 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
        new Thread() {
            public void run() {
                mContactsUtil = new ContactsUtil(TelephoneActivity.this);
                contacts = mContactsUtil.select();
                handler.sendEmptyMessage(LOAD_FINISH);
            }
        }.start();
        initView();
    }

    public void initView() {
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mHead = (ImageView) findViewById(R.id.imagebtn);
        getCircleHead();
        mExit = (ImageView) findViewById(R.id.imagebtn1);
        mExit.setOnClickListener(new MyOnclickListener(this));
        mListShow = (ListView) findViewById(R.id.contentList);
        ListViewAdapter adapter1 = new ListViewAdapter(this, listitem);
        mListShow.setAdapter(adapter1);
        mListShow.setOnItemClickListener(new MyOnItemClickListener(this));

        ImageView_menu = (ImageView) findViewById(R.id.menu1);
        ImageView_menu.setOnClickListener(new MyOnclickListener(this));
        mList = (ListView) this.findViewById(R.id.lst_show_contact);
        mList.setOnItemClickListener(new MListOnItemClickListener(this));
        mAddText = (TextView) this.findViewById(R.id.txt_add_contact);
        mAddText.setOnClickListener(new MyOnclickListener(this));
        mCallphone = (TextView) this.findViewById(R.id.txt_call_phone);
        mCallphone.setOnClickListener(new MyOnclickListener(this));
        mSearchEditText = (SearchEditText) this.findViewById(R.id.edt_search);
        // 添加一个文本改变的监听事件
        mSearchEditText.addTextChangedListener(new MyAddTextChangedListener(this));
    }


    // 过滤联系人的信息
    public void filterContact(String input) {
        filterContacts = new ArrayList<ContactInfo>();
        // 如果输入是空的
        if (TextUtils.isEmpty(input)) {
            filterContacts = contacts;
        } else {
            // 过滤
            for (int i = 0; i < contacts.size(); i++) {
                // 姓名
                String name = contacts.get(i).getName();
                if (name.contains(input) || name.startsWith(input)) {
                    filterContacts.add(contacts.get(i));
                }
            }
            // 拿到过滤联系人的信息filterContacts
            // 刷新适配器
            adapter.reflash(filterContacts);
        }
    }

    //图片圆形化
    public void getCircleHead() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t1);
        Bitmap smallBitmap = Bitmap.createScaledBitmap(bitmap, 100, 80, true);
        Bitmap circleBitmap = CommonUtil.createCircleImage(smallBitmap);
        mHead.setImageBitmap(circleBitmap);

    }


}
