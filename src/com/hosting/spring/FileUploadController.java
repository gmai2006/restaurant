package com.hosting.spring;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;
import com.hosting.util.StreamCopier;

public class FileUploadController  extends ApplicationAbstractController   
{
	static final long serialVersionUID = 11111;
	static final int SIZE = 10*1024;
	
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		File currentDir = (File)request.getSession().getAttribute("currentDir");
		try
		{
				
			logger.info("destinate directory: " + currentDir);
		    
//			 Create a factory for disk-based file items
	    	DiskFileItemFactory factory = new DiskFileItemFactory();

//	    	 Set factory constraints
	    	factory.setSizeThreshold(SIZE);
	    	factory.setRepository(currentDir);
	    	
//	    	 Create a new file upload handler
	    	ServletFileUpload upload = new ServletFileUpload(factory);

//	    	 Parse the request
	    	List<FileItem> /* FileItem */ items = upload.parseRequest(request);
	    	logger.info("uploaded " + items.size());
	    	
	    	for (FileItem item : items)
	    	{
	    		if (!item.isFormField())
	    		{
	    			System.out.println(item.getFieldName() + ": " + item.getName());
	    		}
	    	}

			FileItem textFilefield = findFileField(items, "userfile");
			if (null != textFilefield && !(ApplicationUtil.isStringNull(textFilefield.getName())))
			{		
				processUploadedFile(currentDir, textFilefield);	
			}
			
			File uploadFile = new File(currentDir, textFilefield.getName());
			if (!uploadFile.exists())
			{
				logger.error("Application err");
		        notifyAdmin("Application ERROR: " + this.getClass().getName() + " Unable to load data file for " + " from " + request.getRemoteAddr() 
		        		+ " due to file not uploaded successfully");  
		        request.setAttribute("error", "not uploading " + textFilefield.getName());
		        logger.info("returned page for exception " + errorPage);
				return new ModelAndView(failurePage);
			}
			
			
		    request.setAttribute("error", "Successfully uploaded the file: " + textFilefield.getName());
			return process(request, response);
		}
		catch (Exception e)
		{
	        logger.error("Application err", e);
	        notifyAdmin("Application ERROR: " + this.getClass().getName() + " Unable to load data file for " + " from " + request.getRemoteAddr() 
	        		+ " due to "+ e.getMessage());  
	        
    		
	        request.setAttribute("error", e.getMessage());
	        logger.info("returned page for exception " + errorPage);
			return new ModelAndView(errorPage);
		}
		finally {
			try {
			}catch (Exception ignored){}
		}
	}
	
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response)throws Exception 
	  {
	    return new ModelAndView(new StandardView(successPage));
	  }

		
	FileItem findField(List<FileItem> items, String keyword)
	{
		for (FileItem item : items)
		{
			if (item.isFormField())
			{
//				logger.info(item.getFieldName() + ": " + item.getString());
				if (item.getFieldName().equals(keyword)) return item;
			}
			else continue;
		}
		return null;
	}
	
	FileItem findFileField(List<FileItem> items, String keyword)
	{
		for (FileItem item : items)
		{
			if (!item.isFormField())
			{
//				logger.info(item.getFieldName() + ": " + item.getString());
				if (item.getFieldName().equals(keyword) 
						&& !(ApplicationUtil.isStringNull(item.getName()))) return item;
			}
			else continue;
		}
		return null;
	}
	
	private int processUploadedFile(File dir, FileItem item)
	  {
		  BufferedInputStream input = null;
		  BufferedOutputStream output = null;

		  try
		  {
		    String fileName = item.getName();
		    logger.info("uploaded file name " + fileName + " type " + fileName.lastIndexOf("."));
		    logger.info("upload file " + fileName + " to " + dir.getAbsolutePath());
		    File file = new File(dir, fileName);
			input = new BufferedInputStream(item.getInputStream());
			output = new BufferedOutputStream(new FileOutputStream(file));
			StreamCopier.copy(input, output);	
			
		  }
		  catch(Exception e)
		  {
			  logger.error("ERROR: process upload file due to\n", e);
			  return -1;
		  }
		  finally {
			  try {
				
				  if (null != input)
				  {
					  input.close();
				  }
				  if (null != output)
				  {
					  output.flush();
					  output.close();
				  }
			  } catch (Exception ignored) {}
		  }
		  return 0;
	  }
	
}
