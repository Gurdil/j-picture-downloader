package com.jbphilippe.jpicturedownloader.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip
{
	private final static Logger logger = Logger.getLogger(Unzip.class.getName());
	
	final static int BUFFER = 8192;

	public static void unzipByName(String zip, String out, String target)
	{
		try
		{
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(zip);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null)
			{
				if (entry.isDirectory())
				{
					continue;
				}

				String entry_name = entry.getName();

				if (!entry_name.contains(target))
				{
					continue;
				}
				logger.info("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(out + target);
				dest = new BufferedOutputStream(fos, BUFFER);

				while ((count = zis.read(data, 0, BUFFER)) != -1)
				{
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		}
		catch (Exception e)
		{
			logger.warning("Failed to extract :" + target);
		}
	}

	public static void unzipByLocationName(String zip, String out, String target)
	{
		try
		{
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(zip);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null)
			{
				if (entry.isDirectory())
				{
					continue;
				}

				String entry_name = entry.getName();

				if (!entry_name.contentEquals(target))
				{
					continue;
				}
				logger.info("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(out + target.substring(target.lastIndexOf("/")+1));
				dest = new BufferedOutputStream(fos, BUFFER);

				while ((count = zis.read(data, 0, BUFFER)) != -1)
				{
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		}
		catch (Exception e)
		{
			logger.warning("Failed to extract :" + target);
		}

	}
}