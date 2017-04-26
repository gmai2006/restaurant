package com.hosting.spring;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;
import com.hosting.util.StreamCopier;
public class ManageFileController extends ApplicationAbstractController 
{
	
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String fileName = request.getParameter("fileName");
		if (ApplicationUtil.isStringNull(fileName))
		{
			request.setAttribute("error", "Invalid fileName!!! Please try again");
			return new ModelAndView(failurePage);
		}
		File currentDir = (File)request.getSession().getAttribute("currentDir");
        File f = new File(currentDir, fileName);
        
        String cmd = request.getParameter("cmd");
    	if ("delete".equals(cmd))
    	{
    		f.delete();    		
    	}
    	else if ("rn".equals(cmd))
    	{
    		String newName = request.getParameter("newName");
    		f.renameTo(new File(f.getParentFile(), newName));
    	}
    	else if ("mkdir".equals(cmd))
    	{
    		String newName = request.getParameter("newName");
    		File newfolder = new File(currentDir, newName);
    		newfolder.mkdir();
    	}
    	else if ("cp".equals(cmd))
    	{
    		String newName = request.getParameter("newName");
    		File newFile = new File(f.getParentFile(), newName);
    		StreamCopier.copyNonRecursive(f, newFile);
    	}
		return new ModelAndView(new StandardView(successPage));
        
	}
	
	
}