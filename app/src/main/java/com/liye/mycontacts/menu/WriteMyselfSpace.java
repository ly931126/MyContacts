package com.liye.mycontacts.menu;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liye.mycontacts.R;
import com.liye.mycontacts.utils.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WriteMyselfSpace extends Activity implements View.OnClickListener {
    private EditText mEdtWrite_note, mEdtyear, mEdtDay_WEEK, mEdtWeather;
    private Button btn_join, btn_clean;
    private DBHelper db;
    private String mWeekDay,mWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.writedialy);
        IntView();
    }
    private void IntView() {
        db = new DBHelper(WriteMyselfSpace.this);
        mEdtWrite_note = (EditText) findViewById(R.id.edit_writedialy);
        mEdtyear = (EditText) findViewById(R.id.edit_year);
        mEdtDay_WEEK = (EditText) findViewById(R.id.edit_days);
        mEdtWeather = (EditText) findViewById(R.id.edit_winder);
        btn_join = (Button) findViewById(R.id.btn_join);
        btn_clean = (Button) findViewById(R.id.btn_clean);
        btn_join.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
        mEdtyear.setOnClickListener(this);
        mEdtDay_WEEK.setOnClickListener(this);
        mEdtWeather.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_join:

                String s = mEdtWrite_note.getText().toString();
                String data = mEdtyear.getText().toString();
                String days = mEdtDay_WEEK.getText().toString();
                String winder = mEdtWeather.getText().toString();

                if (data.equals("")) {
                    mEdtyear.requestFocus();
                } else {
                    if (days.equals("")) {
                        mEdtDay_WEEK.requestFocus();
                    } else {
                        if (winder.equals("")) {
                            mEdtWeather.requestFocus();
                        } else

                        {
                            if (!s.equals("")) {
                                ContentValues values = new ContentValues();
                                values.put("content", s);
                                values.put("data", data);
                                values.put("days", days);
                                values.put("winder", winder);
                                db.insert(values);
                                //Conmon.bln_content = true;
                                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                                mEdtWrite_note.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "honey,你什么也没有写哟！", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }

                break;
            case R.id.btn_clean:
                mEdtWrite_note.setText("");

                break;
            case R.id.edit_year:

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
                Date date=new Date();
                String nowDate = df.format(date);
                mEdtyear.setText(nowDate);
                break;
            case R.id.edit_days:

                Calendar calendar = Calendar.getInstance();
                mWeekDay= String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
                //1
                if ((Calendar.SUNDAY+"").equals(mWeekDay)) {
                    mWeekDay = "天";
                    //2
                } else if ((Calendar.MONDAY+"").equals(mWeekDay)) {
                    mWeekDay= "一";
                    //3
                } else if ((Calendar.TUESDAY+"").equals(mWeekDay)) {
                    mWeekDay = "二";
                    //4
                } else if ((Calendar.WEDNESDAY+"").equals(mWeekDay)) {
                    mWeekDay = "三";
                    //5
                } else if ((Calendar.THURSDAY+"").equals(mWeekDay)) {
                    mWeekDay = "四";
                    //6
                } else if ((Calendar.FRIDAY+"").equals(mWeekDay)) {
                    mWeekDay = "五";
                    //7
                } else if ((Calendar.SATURDAY+"").equals(mWeekDay)) {
                    mWeekDay = "六";
                }
                String dayOfweek = "星期" +mWeekDay;
                mEdtDay_WEEK.setText(dayOfweek);
                break;
            case R.id.edit_winder:
                    mWeather="晴";
                    mEdtWeather.setText(mWeather);
                break;
            default:
                break;
        }

    }


}