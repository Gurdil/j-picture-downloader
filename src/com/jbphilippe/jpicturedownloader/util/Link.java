package com.jbphilippe.jpicturedownloader.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class Link
{
	public static String getAbsoluteLinkToJar(Class<?> classe)
	{
		String jarName = "/" + classe.getName().replace('.', '/') + ".class";
		URL url = classe.getResource(jarName);
		try
		{
			jarName = URLDecoder.decode(url.toString(), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}

		// suppression de la classe ou du jar du path de l'url
		int index = jarName.indexOf(".jar");
		if (index == -1)
		{
			return null;
		}

		jarName = jarName.substring(0, index + ".jar".length());

		index = jarName.lastIndexOf("/");
		jarName = jarName.substring(index + "/".length());

		return Link.getAbsoluteLinkToFolderJar(classe) + jarName;
	}

	public static String getAbsoluteLinkToFolderJar(Class<?> classe)
	{
		String path = "/" + classe.getName().replace('.', '/') + ".class";
		URL url = classe.getResource(path);
		try
		{
			path = URLDecoder.decode(url.toString(), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}

		// suppression de la classe ou du jar du path de l'url
		int index = path.lastIndexOf("/");
		path = path.substring(0, index);

		if (path.startsWith("jar:file:"))
		{
			// suppression de jar:file: de l'url d'un jar
			// ainsi que du path de la classe dans le jar
			index = path.indexOf("!");
			path = path.substring(9, index);
			index = path.lastIndexOf("/");
			path = path.substring(0,index + "/".length());
		}
		else
		{
			// suppresion du file: de l'url si c'est une classe en dehors d'un
			// jar
			// et suppression du path du package si il est présent.
			path = path.substring(5, path.length());
			Package pack = classe.getPackage();
			if (null != pack)
			{
				String packPath = pack.getName().replace('.', '/');
				if (path.endsWith(packPath))
				{
					path = path.substring(0, (path.length() - packPath.length()));
				}
			}
			
			if (path.endsWith("bin/"))
			{
				path = path.substring(0, path.length() - "bin/".length());
			}
		}
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
		{
			while (path.startsWith("/"))
			{
				path = path.substring(1);
			}

		}
		return path;
	}
}
