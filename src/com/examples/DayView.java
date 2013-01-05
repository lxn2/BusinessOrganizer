package com.examples;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.examples.SimpleCalendarViewActivity.GridCellAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.view.Gravity;
//import android.widget.TextView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DayView extends FragmentActivity implements OnClickListener{
	private static final String tag = "DayView";
	
	public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.day_appointments);
		}
	
	public class Appointment
	{
		private String firstName;
		private String lastName;
		private String techName;
		private List<String> services;
		private String phoneNumber;
	}

	@Override
	public void onClick(View v) {
		Log.d(tag, "dayclick");
	}
}
