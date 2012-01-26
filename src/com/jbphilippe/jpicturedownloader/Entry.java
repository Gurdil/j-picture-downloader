package com.jbphilippe.jpicturedownloader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbphilippe.jpicturedownloader.download.Downloader;
import com.jbphilippe.jpicturedownloader.download.DownloaderRun;
import com.jbphilippe.jpicturedownloader.download.Task;

public class Entry
{

	public static void main(String[] args)
	{
		String login = "Test";
		String password = "test";
		
		OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		ExecutorService pool = Executors.newFixedThreadPool(bean.getAvailableProcessors());
		
		InputStream ips;
		try 
		{
			ips = new FileInputStream("liste.txt");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
			return;
		}
		
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		
		try
		{
			String adresse="";
			while((adresse=br.readLine())!=null)	
			{
				Task task = new Task(adresse, login, password);
				
				Downloader downloader = new Downloader(task);
				
				DownloaderRun runnable = new DownloaderRun(downloader);
				
				pool.execute(runnable);
			}
			br.close();
			pool.shutdown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}