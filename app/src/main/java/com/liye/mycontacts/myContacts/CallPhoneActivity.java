package com.liye.mycontacts.myContacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liye.mycontacts.R;

public class CallPhoneActivity extends Activity {
    Button mBtn,mReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        mBtn = (Button) this.findViewById(R.id.btn);
        mReturn=(Button) this.findViewById(R.id.btn_return_call);
        mReturn.setOnClickListener(new MyListener() );
        // 设置一个点击事件
        mBtn.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {
        /**
         * 当按钮被点击的时候调用onClick()
         *
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                EditText number = (EditText) CallPhoneActivity.this
                        .findViewById(R.id.edt_number);
                // 得到电话号码
                String callNumber = number.getText().toString();
                // 打电话的意图
                Intent intent = new Intent();
                // Intent.ACTION_CALL打电话的动作
                intent.setAction(Intent.ACTION_CALL);
                // uri统一资源标示符
                intent.setData(Uri.parse("tel:" + callNumber));
                // 开启一个新的界面
                startActivity(intent);
                    break;
                case R.id.btn_return_call:
//                    Intent intent1=new Intent(CallPhoneActivity.this, TelephoneActivity.class);
//                    startActivity(intent1);
                    finish();
                    break;
            }
        }

    }
}
