package com.jbphilippe.jpicturedownloader.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Downloader 
{
	private static final int IN_BUFFER_SIZE = 512;
	
	private Task task;
	ZipOutputStream out;
	
	public Downloader(Task task) throws FileNotFoundException 
	{
		this.task = task;
		FileOutputStream dest = new FileOutputStream("result/"+this.task.getName() + ".zip");
		BufferedOutputStream buff = new BufferedOutputStream(dest);
		out = new ZipOutputStream(buff);
		out.setMethod(ZipOutputStream.DEFLATED);
		out.setLevel(9);
	}
	
	public Worker getWorker()
	{
		System.out.println(task.getName());
		return new Worker() 
		{
			private String name="";
			private boolean workLeft=true;
			private Iterator<URL> it = task.iterator();
			private int error=0;
			
			private static final int TOLERANCE = 10;
			
			@Override
			public int workprogress() 
			{
				return 0;
			}
			
			@Override
			public String workName() 
			{
				return name;
			}
			
			@Override
			public boolean workLeft() 
			{
				return workLeft;
			}
			
			@Override
			public void work() 
			{
				URL url;
				if(it.hasNext())
				{
					url = it.next();
				}
				else
				{
					workLeft=false;
					try 
					{
						out.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					return;
				}
				
				
				try 
				{
					URLConnection con = url.openConnection();
					con.setRequestProperty ("Authorization", task.getAuthorization());
					BufferedInputStream inBuffered = new BufferedInputStream(con.getInputStream(), IN_BUFFER_SIZE);
				
				
					String fileName = url.getFile().substring(url.getFile().lastIndexOf('/')+1);
					name=fileName;
					ZipEntry entry = new ZipEntry(fileName);
					out.putNextEntry(entry);
					
					byte[] buffer = new byte[IN_BUFFER_SIZE];
					
					int nbrByte;
					while ((nbrByte = inBuffered.read(buffer)) != -1) 
					{
						out.write(buffer, 0, nbrByte);
					}
					
					inBuffered.close();
					out.closeEntry();
					error=0;
				} 
				catch (IOException e) 
				{
					error++;
					if(error < TOLERANCE)
					{
						return;
					}
					workLeft=false;
					try 
					{
						out.close();
					} 
					catch (IOException ex)
					{
						ex.printStackTrace();
					}
					return;
				}
				
			}
		};
	}
	
	public void download() throws IOException
	{
		try
		{
			for (URL url : task) 
			{
				URLConnection con = url.openConnection();
				con.setRequestProperty ("Authorization", task.getAuthorization());
				BufferedInputStream inBuffered = new BufferedInputStream(con.getInputStream(), IN_BUFFER_SIZE);
			
				String fileName = url.getFile().substring(url.getFile().lastIndexOf('/')+1);
				System.out.println("Download : " + fileName);
				ZipEntry entry = new ZipEntry(fileName);
				out.putNextEntry(entry);
				
				byte[] buffer = new byte[IN_BUFFER_SIZE];
				
				int nbrByte;
				while ((nbrByte = inBuffered.read(buffer)) != -1) 
				{
					out.write(buffer, 0, nbrByte);
				}
				
				inBuffered.close();
				out.closeEntry();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		out.close();
	}
	
	
}
