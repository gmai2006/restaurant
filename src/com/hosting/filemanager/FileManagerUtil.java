package com.hosting.filemanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManagerUtil 
{
	static final Pattern pattern = 
		Pattern.compile("^([drwx-]*) ([0-9]*) ([a-zA-Z]*) ([a-zA-Z]*) ([0-9]*) ([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}) ([_a-zA-Z0-9+ \\-\\.]*)$");
	static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	static final String IMAGE = "gif,jpg,jpeg,png,ico,bmp";
//	public static FileWrapper[] getFiles(String output) throws Exception
//	{
//		Matcher matcher = null;
//		List<FileWrapper> files = new ArrayList<FileWrapper>();
//		String[] tokens = new String[0];   
//		if ("".equals(output)) return files.toArray(new FileWrapper[0]);
//		
//		tokens = output.split("\n");
//		for (int i = 0; i < tokens.length; i++)
//	     {
//	    	if (tokens[i].startsWith("total")) continue;
//		    	tokens[i] = tokens[i].replaceAll("\\s+", " ");	
//		    
//	    	matcher = pattern.matcher(tokens[i].subSequence(0, tokens[i].length()));
//	    	if (false == matcher.matches()) continue;
//    		FileWrapper file = new FileWrapper();
//    		file.setName(matcher.group(matcher.groupCount()));
//    		Date d = FORMAT.parse(matcher.group(matcher.groupCount()-1));
//    		float size = Float.valueOf(matcher.group(matcher.groupCount()-2))/1000f;
//    		file.setDate(d);
//    		file.setSize(size);
//    		
//    		String permission = matcher.group(1);
//    		if (permission.startsWith("d"))
//    		{
//    			file.setDir(true);
//    		}
//    		file.setFileType(getFileType(file.getName()));
//    		files.add(file);
//    		
//    	}
//		return files.toArray(new FileWrapper[0]);
//	}
	
	public static String getFileType(String name)
	{
		String extension = "unknown";
		int index = name.lastIndexOf(".");
		if (index >=0)
		{
			extension = name.substring(index+1);
		}
		return extension;
	}
	
	public static boolean isImage(File file)
	{
		return IMAGE.indexOf(getFileType(file.getName())) != -1;
	}
	
	public static String allExtension(File location)
	{
		StringBuilder builder = new StringBuilder();
		File[] files = location.listFiles();
		for (int i  =0; i < files.length; i++)
		{
			builder.append(files[i].getName().toLowerCase()).append(",");
		}
		return builder.toString();
	}
	
	public static void main(String[] s)
	{
		String test = allExtension(new File("/home/tomcat/apps/hosting/images/16px"));
		System.out.println(test.indexOf("pdf.png"));
	}
	
	public static String convertFileSize (long size)
	{
		int divisor = 1;
		String unit = "bytes";
		if (size>= 1024*1024){
			divisor = 1024*1024;
			unit = "MB";
		}
		else if (size>= 1024){
			divisor = 1024;
			unit = "KB";
		}
		if (divisor ==1) return size /divisor + " "+unit;
		String aftercomma = ""+100*(size % divisor)/divisor;
		if (aftercomma.length() == 1) aftercomma="0"+aftercomma;
		return size /divisor + "."+aftercomma+" "+unit;
	}
	
	public static boolean editableFile (File file)
	{
		return ("jsp".equals(getFileType(file.getName())) || "txt".equals(getFileType(file.getName()))
				|| "xml".equals(getFileType(file.getName()))
				|| "sh".equals(getFileType(file.getName())));
	}

}
