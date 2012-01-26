package com.jbphilippe.jpicturedownloader.util;

import java.util.logging.Logger;

public class Load
{
	private final static Logger logger = Logger.getLogger(Load.class.getName());
	
	public static void library(String name)
	{
		try
		{
			System.loadLibrary(name);
			return;
		}
		catch (UnsatisfiedLinkError e)
		{
			logger.warning("Can't acess to " + System.mapLibraryName(name) + " in system library folders.");
		}

		try
		{
			System.load(Link.getAbsoluteLinkToFolderJar(Load.class) + System.mapLibraryName(name));
			return;
		}
		catch (UnsatisfiedLinkError e)
		{
			logger.warning("Can't acess to " + System.mapLibraryName(name) + " in current library folders.");
		}

		String arch;

		if (System.getProperty("sun.arch.data.model") == "64")
		{
			arch = "x64";
		}
		else
		{
			arch = "x86";
		}

		try
		{
			logger.warning("Try to find matching library in jar archive...");
			Unzip.unzipByLocationName(Link.getAbsoluteLinkToJar(Load.class), Link.getAbsoluteLinkToFolderJar(Load.class), arch + "/" + System.mapLibraryName(name));
		}
		catch (Exception e)
		{
			logger.warning("Failed to find proper library./n Program exit");
			System.exit(1);
		}

		try
		{
			System.load(Link.getAbsoluteLinkToFolderJar(Load.class) + System.mapLibraryName(name));
			return;
		}
		catch (UnsatisfiedLinkError e)
		{
			logger.severe(e.getMessage());
			System.exit(1);
		}
	}
}
