package com.jbphilippe.jpicturedownloader.util;

public class Number
{
	public static String convertInt(int value, int stringLenght)
	{
		String result = "";

		result = String.valueOf(value);

		int lenghtValue = result.length();
		if (lenghtValue < stringLenght)
		{
			for (int i = 0; i < stringLenght - lenghtValue; i++)
			{
				result = "0" + result;
			}
		}

		return result;
	}
	
	public static int round(int value, int step)
	{
		return value - value%step;
	}
	
	public static int max(int[] values)
	{
		if (values.length <= 0)
		{
			throw new IllegalArgumentException("The array is empty.");
		}
		int max = values[0];
		for(int i=0; i<values.length; i++)
		{
			if (values[i]>max)
			{
				max = values[i];
			}
		}
		
		return max;
	}
	
	public static int min(int[] values)
	{
		if (values.length <= 0)
		{
			throw new IllegalArgumentException("The array is empty.");
		}
		int min = values[0];
		for(int i=0; i<values.length; i++)
		{
			if (values[i]<min)
			{
				min = values[i];
			}
		}
		
		return min;
	}
	
	public static double perCent(double percentage, double value)
	{
		return (value/100)*percentage;
	}
}
