package com.jbphilippe.jpicturedownloader;

import java.io.IOException;
import com.jbphilippe.jpicturedownloader.binary.OctetException;
import com.jbphilippe.jpicturedownloader.binary.Octets;

public class Entry
{
	static final int BUFFER = 2048;

	public static void main(String[] args) throws IOException, OctetException
	{
		System.err.println(Math.ceil(8.9));
		String arr = "test:try";
		Octets lol = new Octets(arr.getBytes());
		System.out.println(lol.toString());
		
		return;
	}

}
