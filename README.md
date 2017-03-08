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
- dependencies {
-  compile fileTree(include: ['*.jar'], dir: 'libs')
-    testCompile 'junit:junit:4.12'
-   compile 'com.android.support:appcompat-v7:21+'
-   compile project(':PullToRefreshLibrary')
- }

#####  注：PullToRefreshLibrary为第三方框架，用于刷新联系人，这里要添加依赖
- DrawerLayout布局用于侧滑菜单
-  DrawerLayout 用作侧滑效果，使用简单
-  在布局文件中放入主界面和左侧菜单界面，注意：左侧菜单界面要设置属性
-   android:layout_gravity="start",
-  DrawerLayout才有效果
-  在主界面中绑定DrawerLayout的id，点击右边布局的控件点击显示左侧界面
-  用
- mDrawerLayout.openDrawer(Gravity.LEFT);打开左边布局

#####  数据库作用
-  数据库主要用于联系人的增删改查和记事本的增删改查



