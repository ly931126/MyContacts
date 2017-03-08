package com.liye.mycontacts.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liye.mycontacts.R;
import com.liye.mycontacts.utils.DBHelper;
import com.liye.mycontacts.utils.NoteEntity;

import java.util.ArrayList;
/*
似水流年的界面
 */

public class WaterMemory extends Activity {
    private TextView tv_year, tv_days, tv_winder;
    private DBHelper DB;
    private Button btn_return;
    private TextView tv_content;
    private Button btn_bianji, btn_delete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_watermemory);
        intview();
    }

    private void intview() {
        String content = null, data = null, days = null, winder = null;
        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_days = (TextView) findViewById(R.id.tv_days);
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_bianji = (Button) findViewById(R.id.btn_bianji);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        tv_winder = (TextView) findViewById(R.id.tv_winder);
        btn_return = (Button) findViewById(R.id.btn_fanhui);
        DB = new DBHelper(WaterMemory.this);
        //通过Bundle取值
        Intent intent = getIntent();
        Bundle bundle1 = intent.getExtras();
        id = bundle1.getString("id");
        ArrayList<NoteEntity> list = DB.fetchValue(id);
        for (NoteEntity m : list) {
            content = m.getCONTENT();
            //拿到日期
            data = m.getDATA();
            //拿到星期几
            days = m.getDAYS();
            //拿到天气
            winder = m.getWINDER();
        }
        tv_year.setText(data);
        tv_days.setText(days);
        tv_content.setText(content);
        tv_winder.setText("天气：" + winder);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterMemory.this.finish();
            }
        });

        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaterMemory.this, EditNotes.class);
                Bundle bundle2 = new Bundle();
                bundle2 .putString("content", tv_content.getText().toString());
                bundle2 .putString("id", id);
               // intent.putExtra("edit_notes", bundle2 );
                intent.putExtras(bundle2);
                startActivity(intent);
                //返回前一页
                WaterMemory.this.finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog();
            }
        });
    }

    public void mydialog() {
        //对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("真的要删除吗？");
        builder.setPositiveButton("是的",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //将String 类型的id转成int类型的
                        DB.delete(Integer.valueOf(id));
                        ArrayList<NoteEntity> list = DB.fetchValue();
                        DB.clean();
                        //ContentValues它也是负责存储一些名值对，但是它存储的名值对当中的名是一个String类型，
                        // 而值都是基本类型
                        // DB.insert(values);语句负责插入一条新的纪录，如果插入成功则会返回这条记录的id，如果插入失败会返回-1。

                        ContentValues values = new ContentValues();
                        for (NoteEntity mode : list) {
                            values.put("content", mode.getCONTENT());
                            values.put("data", mode.getDATA());
                            values.put("days", mode.getDAYS());
                            values.put("winder", mode.getWINDER());
                            DB.insert(values);
                        }
                        Toast.makeText(WaterMemory.this, "删除成功", Toast.LENGTH_SHORT).show();
                        WaterMemory.this.finish();

                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //不做任何操作，还停留在那一页
                    }
                });
        AlertDialog mydialog = builder.create();
        mydialog.show();

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
