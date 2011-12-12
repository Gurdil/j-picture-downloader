package com.jbphilippe.jpicturedownloader.binary;

public class Octet
{
	public static final int LENGHT_BYTE = 8;
	private byte value;
	
	
	private final static byte POSITION_ZERO		= (byte) 0b1111_1110;
	private final static byte POSITION_UN		= (byte) 0b1111_1101;
	private final static byte POSITION_DEUX		= (byte) 0b1111_1011;
	private final static byte POSITION_TROIS	= (byte) 0b1111_0111;
	private final static byte POSITION_QUATRE	= (byte) 0b1110_1111;
	private final static byte POSITION_CINQ		= (byte) 0b1101_1111;
	private final static byte POSITION_SIX		= (byte) 0b1011_1111;
	private final static byte POSITION_SEPT		= (byte) 0b0111_1111;
	
	
	public Octet()
	{
		value = 0;
	}
	
	public Octet(byte value)
	{
		this.value = value;
	}
	
	public Boolean getBite(int position) throws OctetException
	{
		if (position<0 || position>7 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		byte test = 0b0000_0001;
		if ((value & (test<<position)) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setBite(int position) throws OctetException
	{
		if (position<0 || position>7 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		byte test = 0b0000_0001;
		value = (byte) (value | ((byte) (test<<position)));
	}
	
	public void unsetBite(int position) throws OctetException
	{
		if (position<0 || position>7 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		
		switch (position)
		{
			case 0:
			{
				value = (byte) (value & POSITION_ZERO);
				break;
			}
			case 1:
			{
				value = (byte) (value & POSITION_UN);
				break;
			}
			case 2:
			{
				value = (byte) (value & POSITION_DEUX);
				break;
			}
			case 3:
			{
				value = (byte) (value & POSITION_TROIS);
				break;
			}
			case 4:
			{
				value = (byte) (value & POSITION_QUATRE);
				break;
			}
			case 5:
			{
				value = (byte) (value & POSITION_CINQ);
				break;
			}
			case 6:
			{
				value = (byte) (value & POSITION_SIX);
				break;
			}
			case 7:
			{
				value = (byte) (value & POSITION_SEPT);
				break;
			}
		}
		
	}
	
	public byte getchar()
	{
		return value;
	}

	public Octet cut(int begin, int stop)
	{
		Octet result = new Octet((byte) (value >>> begin));
		
		for(int i = LENGHT_BYTE; i>(stop-begin); i--)
		{
			try
			{
				result.unsetBite(i);
			}
			catch (OctetException e)
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public void pushRightZero(int offset) throws OctetException
	{
		if (offset<1 || offset>8 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		value >>>= offset;
	}
	
	public void pushRightOne(int offset) throws OctetException
	{
		if (offset<1 || offset>8 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		value >>>= offset;
		for(int i=0; i<offset; i++)
		{
			this.setBite((Octet.LENGHT_BYTE - 1) - i);
		}
	}
	
	public void pushLeftZero(int offset) throws OctetException
	{
		if (offset<1 || offset>8 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		value <<= offset;
	}
	
	public void pushLeftOne(int offset) throws OctetException
	{
		if (offset<1 || offset>8 )
		{
			throw new OctetException("The lenght of a Byte is only 8 bits.");
		}
		value <<= offset;
		for(int i=0; i<offset; i++)
		{
			this.setBite(i);
		}
	}
	
	@Override
	public String toString()
	{
		String result = "";
		for(int i = 0; i<LENGHT_BYTE;i++)
		{
			try
			{
				if (this.getBite(i))
				{
					result = "1" + result;
				}
				else
				{
					result = "0" + result;
				}
			}
			catch (OctetException e)
			{
				e.printStackTrace();
			}
			if(i == 3)
			{
				result = "_" + result;
			}
		}
		return result;
	}
}
