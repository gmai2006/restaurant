package com.hosting.spring;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;
import com.hosting.util.StreamCopier;
public class ViewFileController extends ApplicationAbstractController 
{
	
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String file = request.getParameter("file");
		if (ApplicationUtil.isStringNull(file))
		{
			request.setAttribute("error", "Invalid fileName!!! Please try again");
			return new ModelAndView(failurePage);
		}
		File currentDir = (File)request.getSession().getAttribute("currentDir");
        File f = new File(currentDir, file);
        
        String mimeType = getMimeType(f.getName());
		response.setContentType(mimeType);
		if (mimeType.equals("text/plain"))
			response.setHeader("Content-Disposition", "inline;filename=\"temp.txt\"");
		else
			response.setHeader ("Content-Disposition", "inline;filename=\""+f.getName()+"\"");	
		
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        StreamCopier.copy(new BufferedInputStream( new FileInputStream( f)), b);
        b.writeTo(response.getOutputStream());
	    return null;
	}
	
	/**
	*	Returns true if the given filename tends towards a packed file
	*/
	static boolean isPacked(String name, boolean gz){
		return (name.toLowerCase().endsWith(".zip")||name.toLowerCase().endsWith(".jar")||
				(gz&&name.toLowerCase().endsWith(".gz"))||name.toLowerCase().endsWith(".war"));
	}

	/**
	 * Returns the Mime Type of the file, depending on the extension of the filename
	 */
	public static String getMimeType(String fName)
	{
		fName = fName.toLowerCase();
		if (fName.endsWith(".jpg")||fName.endsWith(".jpeg")||fName.endsWith(".jpe")) return "image/jpeg";
		else if (fName.endsWith(".gif")) return "image/gif";
		else if (fName.endsWith(".pdf")) return "application/pdf";
		else if (fName.endsWith(".htm")||fName.endsWith(".html")||fName.endsWith(".shtml")) return "text/html";
		else if (fName.endsWith(".avi")) return "video/x-msvideo";
		else if (fName.endsWith(".mov")||fName.endsWith(".qt")) return "video/quicktime";
		else if (fName.endsWith(".mpg")||fName.endsWith(".mpeg")||fName.endsWith(".mpe")) return "video/mpeg";
		else if (fName.endsWith(".zip")) return "application/zip";
		else if (fName.endsWith(".tiff")||fName.endsWith(".tif")) return "image/tiff";
		else if (fName.endsWith(".rtf")) return "application/rtf";
		else if (fName.endsWith(".mid")||fName.endsWith(".midi")) return "audio/x-midi";
		else if (fName.endsWith(".xl")||fName.endsWith(".xls")||fName.endsWith(".xlv")||fName.endsWith(".xla")
				||fName.endsWith(".xlb")||fName.endsWith(".xlt")||fName.endsWith(".xlm")||fName.endsWith(".xlk"))
			return "application/excel";
		else if (fName.endsWith(".doc")||fName.endsWith(".dot")) return "application/msword";
		else if (fName.endsWith(".png")) return "image/png";
		else if (fName.endsWith(".xml")) return "text/xml";
		else if (fName.endsWith(".svg")) return "image/svg+xml";
		else if (fName.endsWith(".mp3")) return "audio/mp3";
		else if (fName.endsWith(".ogg")) return "audio/ogg";
		else return "text/plain";
	}
}