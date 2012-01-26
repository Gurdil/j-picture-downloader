package com.jbphilippe.jpicturedownloader.download;

public interface Worker
{
	public boolean workLeft();
	
	public void work();
	
	public int workprogress();
	
	public String workName();
}
