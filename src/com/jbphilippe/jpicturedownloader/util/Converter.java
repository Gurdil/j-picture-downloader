package com.jbphilippe.jpicturedownloader.util;

import java.util.Calendar;

public class Converter
{

	private static final int NUMBER_SECONDE_MINUTE = 60;
	private static final int NUMBER_SECONDE_HOUR = NUMBER_SECONDE_MINUTE*60;
	private static final double ONE_METER_IN_MILES = 0.000621371192;
	private static final double ONE_MILES_IN_METER = 1_609.344;
	private static final double ONE_FOOT_IN_METER = 0.3048;
	private static final double ONE_FL_IN_METER = ONE_FOOT_IN_METER*100;
	private static final double ONE_METER_IN_FOOT = 3.2808399;
	private static final double ONE_METER_IN_FL = ONE_METER_IN_FOOT/100;
	
	
	public static String dateHH_MM_SS(Calendar date)
	{
		return Number.convertInt(date.get(Calendar.HOUR_OF_DAY),2) + ":" + Number.convertInt(date.get(Calendar.MINUTE),2) + ":" + Number.convertInt(date.get(Calendar.SECOND),2);
	}
	
	public static int dateNumberSecondeSinceBeginDay(Calendar date)
	{
		return date.get(Calendar.HOUR_OF_DAY)*NUMBER_SECONDE_HOUR + date.get(Calendar.MINUTE)*NUMBER_SECONDE_MINUTE + date.get(Calendar.SECOND);
	}

	public static double meterToMiles(double meter)
	{
		return meter*ONE_METER_IN_MILES;
	}

	public static double milesToMeter(double miles)
	{
		return miles*ONE_MILES_IN_METER;
	}
	
	public static double flToMeter(double fl)
	{
		return fl*ONE_FL_IN_METER;
	}
	
	public static double meterToFl(double meter)
	{
		return meter*ONE_METER_IN_FL;
	}
	
	public static double degToRad(double deg)
	{
		return (deg*Math.PI)/180;
	}
	
	public static double radToDeg(double rad)
	{
		return (rad*180)/Math.PI;
	}
}
