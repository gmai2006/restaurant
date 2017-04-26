package com.hosting.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class GeneralUtil 
{
	static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	public static String getFileNameWithoutExtension(String file)
	{
		return file.substring(0, file.lastIndexOf("."));
	}

	public static Date getCurrentDateFormat(SimpleDateFormat formater, int year, int month) throws Exception
	{
		String time = year + "/" + month + "/01";
		Date d = formater.parse(time);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
//		System.out.println(sdf.format(c.getTime()));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public static Date getFirstDayofMonth(int year, int month) throws Exception
	{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String time = year + "/" + month + "/01";
		Date d = formater.parse(time);
		return d;
	}
	
	public static Date getLastDayofMonth(int year, int month) throws Exception
	{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String time = year + "/" + month + "/01";
		Date d = formater.parse(time);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	/**
	 * return YR2006
	 */
	public static String getYearString(int year) throws Exception
	{
		Calendar c = Calendar.getInstance();
		c.roll(Calendar.YEAR, year);
		String result = YEAR.format(c.getTime());
		return result;
	}
	
	public static String getNullString(String s, String replace)
	{
		if (isStringNull(s)) return replace;
		return s.trim();
	}
	
	public static boolean isStringNull(String s)
	{
		if (null == s) return true;
		s = s.trim();
		if ("".equals(s) || "null".equals(s)) return true;
		else return false;
	}
	
	public static String trimQuote(String s)
	{
		String result = s.trim();
		if (result.startsWith("\"")) {
			result = result.substring(1);
		}
		if (result.endsWith("\"")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	public static String getStackTrace(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}//--
	
	public static String truncate(String value, int len)
	{
		if (null == value) return null;
		value = value.trim();
		if (value.length() < len) return value;
		System.err.println("input is longer than allowable limit " + value);
		return value.substring(0, len-1);
	}
	

	public static String toUpperCase(String s)
	{
		if (null == s) return null;
		s = s.trim();
		if ("".equals(s)) return null;
		return s.trim().toUpperCase();
	}

	/**
	 * month starts at 0
	 * @param sdf
	 * @param month
	 * @return
	 */
	public static String getLastDay(SimpleDateFormat sdf, int month)
	{
		Calendar c = Calendar.getInstance();
		int mon = c.get(Calendar.MONTH);
//		System.out.println("current month " + mon);
		if ((month + mon) <= -1)
		{
			c.roll(Calendar.YEAR, -1);
		}
		c.roll(Calendar.MONTH, month);
//		 Output last day of Month
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(c.getTime()).toUpperCase();
	}
	
	public static String getFirstDay(SimpleDateFormat sdf, int month)
	{
		Calendar c = Calendar.getInstance();
		int mon = c.get(Calendar.MONTH);
//		System.out.println("current month " + mon);
		if ((month + mon) <= -1)
		{
			c.roll(Calendar.YEAR, -1);
		}
		
		c.roll(Calendar.MONTH, month);
//		 Output last day of Month
		c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
		return sdf.format(c.getTime()).toUpperCase();
	}
}//--
