package com.examples;
import android.provider.BaseColumns;

public class BusinessContract {
	private BusinessContract(){}
	
	public static abstract class Appointments implements BaseColumns {
		public static final String TABLE_NAME = "Appointments";
	    public static final String COLUMN_NAME_FIRST = "first";
	    public static final String COLUMN_NAME_LAST = "last";
	    public static final String COLUMN_NAME_EMPLOYEE = "employee";
	    public static final String COLUMN_NAME_SERVICES = "services";
	    public static final String COLUMN_NAME_CHARGE = "charge";
	    public static final String COLUMN_NAME_TIME = "time";
	}
}
