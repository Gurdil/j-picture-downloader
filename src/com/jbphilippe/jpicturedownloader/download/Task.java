package com.jbphilippe.jpicturedownloader.download;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import com.jbphilippe.jpicturedownloader.util.Number;

public class Task implements Iterable<URL>
{
	private String begin="";
	private String end="";
	private int endPosition = 0;
	private int beginPosition = 0;
	private String login="";
	private String password="";
	private int max = 0;
	private int beginNumber=0;
	
	public Task(String adress) 
	{
		for(int i=adress.length()-1; i>0; i--)
		{
			if (adress.charAt(i)>='0' && adress.charAt(i)<='9')
			{
				if (max == 0)
				{
					end = adress.substring(i+1, adress.length());
					endPosition = i+1;
					max++;
				}
				else
				{
					max++;
				}
			}
			else
			{
				if (max>0)
				{
					begin=adress.substring(0, i+1);
					beginPosition=i+1;
					break;
				}
			}
		}
		
		
		beginNumber = Integer.parseInt(adress.substring(beginPosition, endPosition));
		
	}

	public Task(String adress, String login, String password) 
	{
		this(adress);
		this.login=login;
		this.password=password;
	}
	
	public String getAuthorization()
	{
		return "Basic " + new sun.misc.BASE64Encoder().encode((login+":"+password).getBytes());
	}

	@Override
	public Iterator<URL> iterator() 
	{
		return new Iterator<URL>() 
		{
			int nbr=beginNumber;
			@Override
			public boolean hasNext() 
			{
				if (nbr < Math.pow(10, max))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			@Override
			public URL next() 
			{
				try 
				{
					String adress = begin+Number.convertInt(nbr, max)+end;
					nbr++;
					return new URL(adress);
				}
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void remove() 
			{
				// TODO Auto-generated method stub
			}
		};		
	}
	
	public String getName()
	{
		String result = begin.substring(0, begin.lastIndexOf('/'));
		result = result.substring(result.lastIndexOf('/')+1);
		return result;
	}
}
