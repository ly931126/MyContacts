package com.liye.mycontacts.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.liye.mycontacts.R;
import com.liye.mycontacts.utils.DBHelper;


public class RecordNote extends Activity {
	private ListView mListview;
	//content记录内容， data 记录的日期 ， days 记录是星期几写的
	private String[] from = {"content", "data", "days"};
	private int[] to = {R.id.txt_view, R.id.txt_year, R.id.txt_days};
	private SimpleCursorAdapter adapter;
	private Cursor mCursor;
	private DBHelper mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_note);
		intview();
		String s = mDbHelper.selectall();
		if (!s.equals("")) {
			mCursor = mDbHelper.query();
			//SimpleCursorAdapter用于将Cursor中的columns（列）与XML文件中定义的TextView或者ImageView进行匹配的Adapter
			//(Context context, int layout, Cursor c, String[] from, int[] to)
//			要构造一个SimpleAdapter，需要以下的参数:
//			1.Context context:上下文，这个是每个组件都需要的，
//          它指明了SimpleAdapter关联的View的运行环境，也就是我们当前的Activity。
//			2.int layout 布局每一个布局中包含的就是所有在from参数中指定的key。
//			3.Cursor c: 通过移动指向每个布局的id
//			4.String[] from:将被添加到布局映射上的key。
//			5.int[] to:将绑定数据的视图的ID跟from参数对应，
//           这些被绑定的视图元素应该全是TextView。
			adapter = new SimpleCursorAdapter(this, R.layout.list, mCursor, from, to);
			mListview.setAdapter(adapter);

			//设置ListView每一栏的间隔线不存在
			//mListview.setDivider(null);
		}
	}
//更新记录
 	@Override
		 protected void onResume() {
		//查询记录的表
			mCursor  = mDbHelper.query();
			adapter = new SimpleCursorAdapter(this, R.layout.list, mCursor, from, to);
			mListview.setAdapter(adapter);
			//mListview.setDivider(null);
		super.onResume();
	}

	private void intview() {
		mDbHelper = new DBHelper(RecordNote.this);
		mListview = (ListView) findViewById(R.id.listview);
		mListview.setOnItemClickListener(new OnItemClickListener() {
			/*
			这个参数是系统自动传入的，我们也不用调用，一般常用第二个和第三个参数。
			然后给你讲AdapterView<?> ，
			这个属于java基础的内容，叫做泛型，就是告诉你传入的参数是哪种类型。
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int n, long m) {
				/*
				各项的意义：v是当前item的view，通过它可以获得该项中的各个组件。
              例如v.textview.settext("asd");
          n是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
          m是当前的item在listView中的相对位置！
				 */
         //n的初始值默认为0
				n = n + 1;
				//通过Bundle传值
				Intent intent = new Intent(RecordNote.this, WaterMemory.class);
				Bundle bundle = new Bundle();
				//将记录的id传过去
				bundle.putString("id", n + "");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}


}
