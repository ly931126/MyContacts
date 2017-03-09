# 1.电话簿演示图
- ![联系人主界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E8%81%94%E7%B3%BB%E4%BA%BA%E4%B8%BB%E7%95%8C%E9%9D%A2.png)
 -              图1   联系人主界面
- ![添加联系人界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E6%B7%BB%E5%8A%A0%E8%81%94%E7%B3%BB%E4%BA%BA%E7%95%8C%E9%9D%A2.png)
 -              图2   添加联系人界面
- ![查看联系人详情界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E6%9F%A5%E7%9C%8B%E8%81%94%E7%B3%BB%E4%BA%BA%E8%AF%A6%E7%BB%86%E4%BF%A1%E6%81%AF.png)
 -              图3   查看联系人详情界面
- ![编辑联系人界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E7%BC%96%E8%BE%91%E8%81%94%E7%B3%BB%E4%BA%BA%E7%95%8C%E9%9D%A2.png)
 -              图4   编辑联系人界面
- ![侧滑菜单界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E4%BE%A7%E6%BB%91%E8%8F%9C%E5%8D%95%E7%95%8C%E9%9D%A2.png)
 -              图5   侧滑菜单界面
- ![日历界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E6%97%A5%E5%8E%86%E7%95%8C%E9%9D%A2.png)
 -              图6   日历界面
- ![冷笑话界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E6%97%A5%E5%8E%86%E7%95%8C%E9%9D%A2.png)
 -              图7   冷笑话界面
- ![音乐界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E9%9F%B3%E4%B9%90%E7%95%8C%E9%9D%A2.png)
 -              图8   音乐界面
- ![记事本注册界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E8%AE%B0%E4%BA%8B%E6%9C%AC%E6%B3%A8%E5%86%8C%E7%95%8C%E9%9D%A2.png)
 -              图9   记事本注册界面
- ![记事本登陆界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E8%AE%B0%E4%BA%8B%E6%9C%AC%E6%B3%A8%E5%86%8C%E7%95%8C%E9%9D%A2.png)
 -              图10  记事本登陆界面
- ![记事本查看记录界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E8%AE%B0%E4%BA%8B%E6%9C%AC%E8%AE%B0%E5%BD%95%E7%95%8C%E9%9D%A2.png)
 -              图11  记事本查看记录界面
- ![记事本书写界面](https://github.com/ly931126/MyContacts/blob/master/picture/%E8%AE%B0%E4%BA%8B%E6%9C%AC%E4%B9%A6%E5%86%99%E7%95%8C%E9%9D%A2.png)
 -              图12  记事本书写界面
 
 # 2.用法
```
 dependencies {
  compile fileTree(include: ['*.jar'], dir: 'libs')
   testCompile 'junit:junit:4.12'
   compile 'com.android.support:appcompat-v7:21+'
   compile project(':PullToRefreshLibrary')
 }
 
 ```
#####  注：PullToRefreshLibrary为第三方框架，用于刷新联系人，这里要添加依赖
```
 DrawerLayout布局用于侧滑菜单
 DrawerLayout 用作侧滑效果，使用简单
   在布局文件中放入主界面和左侧菜单界面，注意：左侧菜单界面要设置属性
   android:layout_gravity="start",
  DrawerLayout才有效果
   在主界面中绑定DrawerLayout的id，点击右边布局的控件点击显示左侧界面
  用
 mDrawerLayout.openDrawer(Gravity.LEFT);打开左边布局
```
#####  数据库的使用
-  数据库主要用于联系人的增删改查和记事本的增删改查
-  数据库的帮助类继承SQLiteOpenHelper,实现onCreate()和 onUpGrade（）方法
（1）onCreate()当数据库首次被创建时执行该方法，一般将创建表等初始化操作在该方法中执行，
```
// 私人最终的静态字符串create_tbl =“创建表”+“日（_id整型主键递增，内容文本，数据，文本，天文本，提升文本）”；
	private static final String	CREATE_TBL	= " create table " + " Dialy(_id integer primary key autoincrement,content text,data text,days text,winder text) ";
	// 当数据库被首次创建时执行该方法，一般将创建表等初始化操作在该方法中执行。
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		// TODO 创建数据库后，对数据库的操作
		// 创建一个表格，表格名为Dialy，id自动增长，列数为四列，数据类型为text（字符串）
		db.execSQL(CREATE_TBL);
	}
```
   (2)onUpGrade()方法，当打开数据库时传入的版本号与当前的版本号不同时会调用该方法，如
```
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 更改数据库版本的操作
	}
```
 
###### 1.增加的方法
```
  @Override
	public void add(Person p) {
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(_ID, p.get_id());
		values.put(NAME, p.getName());
		db.insert(STUDENT_TABLE, null, values);
  }
  ```
 
######  2.删除的方法
```
 @Override
	public void delete(int id) {
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		db.delete(STUDENT_TABLE, _ID + "=?", new String[]{String.valueOf(id)});
	}
```
 
###### 3.修改的方法
``` 
/**
	 * 修改指定id的数据
	 * 
	 * @param p
	 */
 @Override
 	public void updata(Person p) {
 		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
 		values.put(_ID, p.get_id());
 		values.put(NAME, p.getName());
		db.update(STUDENT_TABLE, values, _ID + "=?", new String[]{String.valueOf(p.get_id())});
		
	}
```
 
######  4.查询的方法
   (1)查询表中所有的数据
 ```
 /**
	 * 查询表中所有的数据
	 * 
	 * @return
	 */
```
```
  	@Override
  	public List<Person> find() {
  		List<Person> persons = null;
  		SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
  		Cursor cursor = db.query(STUDENT_TABLE, null, null, null, null, null, null);
 		if (cursor != null) {
  			persons = new ArrayList<>();
  			while (cursor.moveToNext()) {
 				Person person = new Person();
  				int id = cursor.getInt(cursor.getColumnIndex(_ID));
  				String name = cursor.getString(cursor.getColumnIndex(NAME));
  				person.set_id(id);
  				person.setName(name);
  				persons.add(person);
  			}
  		}
 		return persons;
	}
 ```
 (2)查询指定id的数据
 ```  
 // 查询指定id的数据
 	@Override
 	public Person findById(int id) {
 		SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
 		Cursor cursor = db.query(STUDENT_TABLE, null, _ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
 		Person person = null;
 		if (cursor != null && cursor.moveToFirst()) {
 			person = new Person();
 			int id1 = cursor.getInt(cursor.getColumnIndex(_ID));
 			String name1 = cursor.getString(cursor.getColumnIndex(NAME));
 			person.set_id(id1);
 			person.setName(name1);
			
 		}
 		return person;
 	}
```
 
 
