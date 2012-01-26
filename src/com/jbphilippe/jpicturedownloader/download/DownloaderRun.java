package com.jbphilippe.jpicturedownloader.download;

public class DownloaderRun implements Runnable
{
	private Downloader downloader;

	public DownloaderRun(Downloader downloader) 
	{
		this.downloader=downloader;
	}
	
	public void run() 
	{
		Worker worker = downloader.getWorker();
		
		while(worker.workLeft())
		{
			worker.work();
		}
	}
}
