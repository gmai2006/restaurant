package com.hosting.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ApplicationUtil
{
	/** Logger for this class and subclasses */
	static Log logger = LogFactory.getLog(ApplicationUtil.class); 
	public static final SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat anotherformater = new SimpleDateFormat("yyyy-MM-dd");
	static final SimpleDateFormat timeformater = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	public static final SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy");
	public static final long ONE_DAY = 3600*24;
	public static final long ONE_WEEK = 3600*24*7;
	public static final long TWO_WEEK = 3600*24*14;
	public static final long ONE_MONTH = 3600*24*30;
	static final String SALT = "*%&$^#($)%^|FFTRE$#$%++_";
	static final SimpleDateFormat displayformater = new SimpleDateFormat("MMM dd, yyyy");
	static final DecimalFormat PRICE = new DecimalFormat("$0.00");
	public final static Properties execute(PostMethod post) throws Exception
	{
		try
		{
			Properties props = new Properties();
			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(post);
			if (statusCode < 200 || statusCode >= 300) {
				throw new Exception("HTTP request failed: response status code '" + statusCode + "' received where 2xx expected");
			}
			props.load(post.getResponseBodyAsStream());
			return props;
		} 
		finally {
			post.releaseConnection();
		}
	}
	
	 public final static String getPath(HttpServletRequest request, String path) 
		{
			StringBuffer url = new StringBuffer();
			String s = request.getProtocol();
			url.append(s.substring(0, s.indexOf('/')).toLowerCase());
			url.append("://");
			url.append(request.getServerName());
			url.append(":");
			url.append(request.getServerPort());
			url.append(request.getContextPath());
			if (path.charAt(0) != '/') {url.append("/");}
			url.append(path); 
			return url.toString();
		}
	 
	public static String ellipsis(String value, int len)
    {
    	if (isStringNull(value)) return "";
    	else if (value.length() < len) return value;
    	return value.substring(0, len -3).concat("...");
    }
    
	public static boolean isTag(String s)
	{
		return s.startsWith("<") && s.endsWith(">");
	}
	
	public static void main(String[] s) throws Exception
	{
		System.out.println(encrypt("test123$$"));
//		System.out.println(encrypt("b2fcd038-14dc-43d8-8cb1-eb361b7d63ad"));
	}
	
	public static String displayCurrentDate(SimpleDateFormat FORMATER) 
	{
		return FORMATER.format(System.currentTimeMillis());
	}
	
	public static long convertIpStringtoLong(String IPAddress)
    {
        StringBuffer buffer = new StringBuffer(IPAddress);
        int index = buffer.indexOf(",");
        if(index > 0)
        {
            int length = buffer.length();
            buffer = buffer.delete(index, length);
        }
        String tokens[] = buffer.toString().split("\\.");
        long answer = 0L;
        for(int i = 0; i < tokens.length; i++)
        {
            long read = (new Long(tokens[i])).longValue();
            read <<= 8 * (tokens.length - 1 - i);
            answer |= read;
        }

        Long IPValue = new Long(answer);
        System.out.println((new StringBuilder()).append("The IP Address value is: ").append(IPValue.toString()).toString());
        return IPValue.longValue();
    }
	
	public static String displayDate(java.util.Date date)
    {
        if(null == date)
            return "";
        else
            return displayformater.format(date);
    }

	public static int countOccurrences(String arg1, String arg2) {
        int count = 0;
        int index = 0;
        while ((index = arg1.indexOf(arg2, index)) != -1) {
             ++index;
             ++count;
        }
        return count;
   }
	public static String encrypt(String x)   throws Exception
	  {
	     java.security.MessageDigest d =null;
	     d = java.security.MessageDigest.getInstance("SHA-1");
	     d.reset();
	     d.update(x.getBytes());
	     byte[] bytes =  d.digest();
	     return byteArrayToHexString(bytes);
	  }

	public static String byteArrayToHexString(byte[] b){
	    StringBuffer sb = new StringBuffer(b.length * 2);
	    for (int i = 0; i < b.length; i++){
	      int v = b[i] & 0xff;
	      if (v < 16) {
	        sb.append('0');
	      }
	      sb.append(Integer.toHexString(v));
	    }
	    return sb.toString().toUpperCase();
	}

	public static byte[] hexStringToByteArray(String s) {
	    byte[] b = new byte[s.length() / 2];
	    for (int i = 0; i < b.length; i++){
	      int index = i * 2;
	      int v = Integer.parseInt(s.substring(index, index + 2), 16);
	      b[i] = (byte)v;
	    }
	    return b;
	  }

	
	public static String encode(String value)
	{
		StringBuilder encode = new StringBuilder(value + ":" + SALT);

		if (encode.length()%4 != 0)
		{
			int remainder = encode.length()%4;
			for (int i = 0; i < 4-remainder; i++)
			{
				encode.append("^");
			}
		}
		
		return Base64Coder.encodeString(encode.toString());
	}
	
	public static String decode(String value)
	{
		String temp = Base64Coder.decodeString(value);
		String[] tokens = temp.split(":");
		return tokens[0];
	}
	
	public static String getUserIdWithoutEmail(String email)
	{
		if (email.indexOf("@") == -1) return email;
		return email.substring(0, email.indexOf("@"));
	}
	
	public static String getNameWithoutExtension(String value)
	{
		return value.substring(0, value.lastIndexOf("."));
	}
	
	public static java.sql.Date getPreviousDate(int interval)
	{
		Calendar c = java.util.GregorianCalendar.getInstance () ; 
		c.add(Calendar.DAY_OF_YEAR, -interval);
		return new java.sql.Date(c.getTimeInMillis());
	}
	
	public static java.sql.Date getFuture(int type, int interval)
	{
		Calendar c = java.util.GregorianCalendar.getInstance () ; 
		c.add(type, interval);
		return new java.sql.Date(c.getTimeInMillis());
	}
	
	public static java.sql.Date getFutureDate(int interval)
	{
		return getPreviousDate(-interval);
	}
	
	public static boolean isStringNull(String s)
	{
		return null == s || s.length() == 0;
	}
	
	public static String getNullString(String s)
	{
		return (null == s || "null".equals(s)) ? "" : s;
	}
	
	public static String getDate(long value)
	{
		return formater.format(value);
	}
	
	public static String displayPrice(float value)
    {
        return PRICE.format(value);
    }

    public static String padding(String value, String padding)
    {
    	return value.concat(padding);
    }
    
	public static String formatDate(java.util.Date date)
	{
		return formater.format(date);
	}
	
	/**
	 * 2007-01-01
	 * @param date
	 * @return
	 */
	public static String formatFormDate(java.util.Date date)
	{
		return anotherformater.format(date);
	}
	
	/**
	 * 2007-01-01
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertStringToDate(String value)
	{
		try
		{
			return new java.sql.Date(anotherformater.parse(value).getTime());
		}
		catch (Exception e)
		{
			logger.error(e);
			return new java.sql.Date(System.currentTimeMillis());
		}
	}
	
	public static String formatTime(java.sql.Timestamp time)
	{
		return timeformater.format(time);
	}
	
	public static String formatTime(SimpleDateFormat formater, java.sql.Timestamp time)
	{
		return formater.format(time);
	}
	
	public static String redirectPage(HttpServletRequest request)
	{
		String page = request.getRequestURI();
		logger.info("coming page: " + page);
		int start = page.lastIndexOf("/");
		page = page.substring(start+1);
		int index = page.indexOf(".");
		if (index >=0)
		{
			page = page.substring(0, index);
		}
		else
		{
			page = "index";
		}
		page = page + "_body.jsp";
		String query = buildQueryString(request);
		if (query.length() >0)
		{
			page = page + "?" + query;
		}
		
		return page;
	}
	
	public static String redirectAdminPage(HttpServletRequest request)
	{
		String page = request.getRequestURI();
		logger.info("coming page: " + page);
		int start = page.lastIndexOf("/");
		int index = page.indexOf(".");
		page = page.substring(start+1, index);
		page = page + ".jsp";
		String query = buildQueryString(request);
		if (query.length() >0)
		{
			page = page + "?" + query;
		}
		
		return page;
	}
	
	public static String directPage(HttpServletRequest request)
	{
		String page = request.getRequestURI();
		int start = page.lastIndexOf("/");
		page = page.substring(start+1);
		int index = page.indexOf(".");
		if (index >=0)
		{
			page = page.substring(0, index);
		}
		else
		{
			page = "index";
		}
		page = page + ".jsp";
		String query = buildQueryString(request);
		if (query.length() >0)
		{
			page = page + "?" + query;
		}
		
		return page;
	}
	
		
	public static String buildQueryString(HttpServletRequest request)
	{
		Map<String, String[]> map = request.getParameterMap();
		StringBuilder builder = new StringBuilder();
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();)
		{
			String key = iter.next();
			String value = map.get(key)[0];
			if (builder.length() > 0)
			{
				builder.append("&");
			}
			builder.append(key+"="+URLEncoder.encode(value));
		}
		return builder.toString();
	}
	public static java.sql.Date convertDateStringToSQLDate(String s)
	{
		if (ApplicationUtil.isStringNull(s)) return null;
		try
		{
			java.util.Date date = formater.parse(s);
			return new java.sql.Date(date.getTime());
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static java.sql.Date convertFormDateStringToSQLDate(String s)
	{
		if (ApplicationUtil.isStringNull(s)) return null;
		try
		{
			java.util.Date date = anotherformater.parse(s);
			return new java.sql.Date(date.getTime());
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static boolean isDigit(String s)
	{
		if (isStringNull(s)) return false;
		try
		{
			new Integer(s);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static boolean isFloat(String s)
	{
		if (isStringNull(s)) return false;
		try
		{
			new Float(s);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	
	
	public static String getProtocol(HttpServletRequest request)
	{
		if (request.isSecure()) return "https://";
		return "http://";
	}

	public static String generateBooleanValue(boolean s)
	{
		if (s) return "checked";
		else return "";
	}
	
	
	public static String truncate(String value, int limit)
	{
		if (value.length() >= limit)
		{
			return value.substring(0, limit-4) + "...";
		}
		else
		{
			return value;
		}
	}
	
	public static String truncateWithoutDot(String value, int limit)
	{
		if (value.length() >= limit)
		{
			return value.substring(0, limit);
		}
		else
		{
			return value;
		}
	}
	
	
	public static String getSystemInfo()
	{
		String os = System.getProperty("os.name");
		String temp = os.toLowerCase();
		if (temp.indexOf("windows") == -1)
		{
			return os + " CPU:" + getCPUInfo() + " Memory:" + getMemInfo();
		}
		return os + ": No hardware information available";
	}
	
	public static String getCPUInfo()
	{
		return getDeviceInfo("/proc/cpuinfo", "model name", "CPU Info Not Available");
	}
	
	public static String getMemInfo()
	{
		return getDeviceInfo("/proc/meminfo", "MemTotal", "Memory Info Not Available");
	}
	
	
	private static String getDeviceInfo(String devicename, String key, String errormsg)
	{
		try
        {            
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(new String[] {"/bin/cat", devicename});// | /bin/grep 'model name'"});
            InputStream stderr = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null) 
        	{
        		if (line.startsWith(key)) 
        		{
        			String[] tokens = line.split(":");
        			if (tokens.length > 1)
        			{
        				return tokens[1].trim();
        			}
        			else return errormsg;
        		}
        				
        	}
           
            int exitVal = proc.waitFor();
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
        return errormsg;
	}
}
