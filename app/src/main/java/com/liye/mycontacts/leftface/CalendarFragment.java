package com.liye.mycontacts.leftface;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.liye.mycontacts.R;
import com.liye.mycontacts.listener.MyOnDateSetListener;
import java.util.Calendar;
public class CalendarFragment extends Fragment  {
	Calendar mCalendar;
	DatePickerDialog dateDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_calendar, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//mCalendar = new GregorianCalendar();
		mCalendar = Calendar.getInstance();
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);
		//获取日历的对话框
		  dateDialog = new DatePickerDialog(getActivity(), R.style.date_dialog, new
				MyOnDateSetListener(), year, month, day);
		dateDialog.show();
	}




}
