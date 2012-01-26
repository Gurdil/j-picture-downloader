package com.jbphilippe.jpicturedownloader.util;

public class Random
{
	private static java.util.Random random = new java.util.Random();
	
	public void setSeed(long seed)
	{
		random.setSeed(seed);
	}
	
	public int intRandom(int min, int max)
	{
		return (int) Math.floor(random.nextDouble()*(max-min) + min);
	}

	public double getDouble()
	{
		return random.nextDouble();
	}

	public double doubleRandom(double min, double max)
	{
		return random.nextDouble()*(max-min) + min;
	}
}
