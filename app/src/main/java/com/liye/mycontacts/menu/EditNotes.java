package com.liye.mycontacts.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liye.mycontacts.R;
import com.liye.mycontacts.utils.DBHelper;


public class EditNotes extends Activity implements View.OnClickListener {
	private Button btn_return, btn_queding, btn_quxiao;
	private EditText edit_content;
	private String str_content,str_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_notes);
		intview();
	}
	private void intview() {
		btn_return = (Button) findViewById(R.id.btn_bianjifanhui);
		btn_queding = (Button) findViewById(R.id.btn_bianjiqueding);
		btn_quxiao = (Button) findViewById(R.id.btn_bianjiquxiao);
		edit_content = (EditText) findViewById(R.id.edit_content);
		btn_return.setOnClickListener(this);
		btn_queding.setOnClickListener(this);
		btn_quxiao.setOnClickListener(this);
		Intent intent=getIntent();
		//根据bundle的key得到对应的对象
		Bundle myBundle=intent.getExtras();
		//Bundle bd=intent.getBundleExtra("edit_notes");
		//得到编辑后的内容和id
		str_content=myBundle.getString("content");
		str_id=myBundle.getString("id");
		Log.d("xxxxxxxxx", str_content+str_id);
		edit_content.setText(str_content);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_bianjifanhui :
						EditNotes.this.finish();
				break;
			case R.id.btn_bianjiqueding :
						DBHelper db=new DBHelper(EditNotes.this);
						db.update(str_id, edit_content.getText().toString());
						Toast.makeText(EditNotes.this, "修改成功", Toast.LENGTH_SHORT).show();
						//Conmon.bln_content=true;
						EditNotes.this.finish();
				break;
			case R.id.btn_bianjiquxiao :
						EditNotes.this.finish();
				break;
		}
	}
//使点击系统的返回按钮无效
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}
}
