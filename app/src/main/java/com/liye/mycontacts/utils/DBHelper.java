package com.liye.mycontacts.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
// 数据库的缺省路径
// 私人最终的静态字符串db_name =“日。DB”
	private static final String DB_NAME = "dialy.db";
	// 私人最终的静态字符串tbl_name =“日”；
	private static final String TBL_NAME = "Dialy";
	// 私人最终的静态字符串create_tbl =“创建表”+“日（_id整型主键递增，内容文本，数据，文本，天文本，提升文本）”；
	private static final String CREATE_TBL = " create table " + " Dialy(_id integer primary key autoincrement,content text,data text,days text,winder text) ";

	static String temp0 = "";
	private SQLiteDatabase db;
	public DBHelper(Context c) {
		super(c, DB_NAME, null, 2);
	}
//当数据库被首次创建时执行该方法，一般将创建表等初始化操作在该方法中执行。

	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		// TODO 创建数据库后，对数据库的操作
		//创建一个表格，表格名为Dialy，id自动增长，列数为四列，数据类型为text（字符串）

		db.execSQL(CREATE_TBL);

	}
    /*
    Android使用getWritableDatabase()和getReadableDatabase()方法都可以获取一个用于操作数据库的SQLiteDatabase实例。(getReadableDatabase()方法中会调用getWritableDatabase()方法)

其中getWritableDatabase() 方法以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，倘若使用的是getWritableDatabase() 方法就会出错。

getReadableDatabase()方法则是先以读写方式打开数据库，如果数据库的磁盘空间满了，就会打开失败，当打开失败后会继续尝试以只读方式打开数据库。如果该问题成功解决，则只读数据库对象就会关闭，然后返回一个可读写的数据库对象。


     */
	////插入

	public void insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
	}
	//查询
	//Cursor 一般在sql查询中使用比如你在查询一张表格，而cursor会像手指一样，从第一条开始指向最后一条，这在java中就可以写成一个for循环，然后取出每一条的数据，放入list中，表中的数据就全部拿到了
	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		//(表名,   后面的是一些分组排列方式，没用到就全设为空值);
		Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
		return c;
	}
	//修改记录心情的内容
	public void update(String _id,String content) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = "update Dialy  " + "set content='" + content +"'"+"where _id='"+ _id +"'";
		db.execSQL(sql);
		Log.d("==update content==", sql);
		db.close();
	}
	////查询全部数据
	public ArrayList<NoteEntity> fetchValue(String id) {
		ArrayList<NoteEntity> list = new ArrayList<NoteEntity>();
		String sql="select * from Dialy " +
				"where _id='"+id+"'";
		Log.d("==sql==", sql);
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(sql,null);
		if (cursor != null && cursor.getCount() >= 0) {
			if (cursor.moveToFirst()) {//指向第一条数据
				do {
					NoteEntity wt = new NoteEntity();
					wt.setCONTENT(cursor.getString(1));
					wt.setDATA(cursor.getString(2));
					wt.setDAYS(cursor.getString(3));
					wt.setWINDER(cursor.getString(4));
					list.add(wt);
				} while (cursor.moveToNext());//指向下一条数据
			}
		}
		if (cursor != null) {
			cursor.close();
			db.close();
		}
		return list;
	}
	public ArrayList<NoteEntity> fetchValue() {
		ArrayList<NoteEntity> list = new ArrayList<NoteEntity>();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
		if (cursor != null && cursor.getCount() >= 0) {
			if (cursor.moveToFirst()) {
				do {
					NoteEntity wt = new NoteEntity();
					wt.setCONTENT(cursor.getString(1));
					wt.setDATA(cursor.getString(2));
					wt.setDAYS(cursor.getString(3));
					wt.setWINDER(cursor.getString(4));
					list.add(wt);
				} while (cursor.moveToNext());
			}
		}
		if (cursor != null) {
			cursor.close();
			db.close();
		}
		return list;
	}
	//查询所有记录心情的内容
	public String selectall() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
		if (cursor != null) {
			String temp = "";
			int i = 0;
			while (cursor.moveToNext()) {
				temp += cursor.getString(1);
				temp0 = temp;
			}
			cursor.close();
			db.close();
		}
		return temp0;
	}
	//删除表中的若干条数据
	// 一个包含所有要删除数据的"id"字段的数组
	public void delete(int id) {
		db = getWritableDatabase();
		//根据id删除数据
		db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		db.close(); //关闭数据库对象
	}
	public void close() {
		if (db != null)
			db.close();//();//关闭数据库对象
	}
	/**
	 * 清空表中的数据
	 */
	public void clean() {
		this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
		this.onCreate(this.getWritableDatabase());
		this.getWritableDatabase().close();
	}
	/*
    当打开数据库时传入的版本号与当前的版本号不同时会调用该方法。
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 更改数据库版本的操作
	}
}


