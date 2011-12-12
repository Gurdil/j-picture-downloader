package com.jbphilippe.jpicturedownloader.binary;

import java.util.ArrayList;
import java.util.Iterator;

public class Octets
{
	private ArrayList<Octet> octets = new ArrayList<Octet>();

	public Octets()
	{

	}

	public Octets(int[] input)
	{
		int nbr_octet = (Integer.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (input[i] >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octets(char[] input)
	{
		int nbr_octet = (Character.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (input[i] >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octets(byte[] input)
	{
		int nbr_octet = (Byte.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (input[i] >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octets(short[] input)
	{
		int nbr_octet = (Short.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (input[i] >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octets(long[] input)
	{
		int nbr_octet = (Long.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (input[i] >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}

	public Octets(float[] input)
	{
		int nbr_octet = (Float.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (Float.floatToIntBits(input[i]) >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octets(double[] input)
	{
		int nbr_octet = (Double.SIZE / Octet.LENGHT_BYTE);
		octets.ensureCapacity(nbr_octet * input.length);

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < nbr_octet; j++)
			{
				octets.add(new Octet((byte) (0b1111_1111 & (Double.doubleToLongBits(input[i]) >>> 8 * (nbr_octet - 1 - j)))));
			}
		}
	}
	
	public Octet[] getbits(int begin, int end)
	{
		Octet[] result = new Octet[(int) Math.ceil((end - begin) / Octet.LENGHT_BYTE)];
		
		
		
		return result;
	}
	@Override
	public String toString()
	{
		String result = "";
		Iterator<Octet> it = octets.iterator();
		while (it.hasNext())
		{
			result += it.next().toString() + " ";
		}
		return result;
	}

	public String toString(int size)
	{
		String result = "";
		Iterator<Octet> it = octets.iterator();
		int count = 0;
		while (it.hasNext())
		{
			result += it.next().toString() + " ";
			count++;
			if (count == size)
			{
				result += "\n";
				count = 0;
			}
		}
		return result;
	}
}
