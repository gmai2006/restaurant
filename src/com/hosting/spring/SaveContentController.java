package com.hosting.spring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;

public class SaveContentController extends ApplicationAbstractController
{
	private String location;
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		String content = request.getParameter("EditorDefault");
		String fileName = request.getParameter("fileName");
		if (!ApplicationUtil.isStringNull(content))
		{
			File file = new File(fileName);
			if (file.exists())
			{
				BufferedWriter writer = 
					new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(file), "UTF8"));
			  
				try
				{
					String value = new String(content.getBytes("ISO-8859-1"),"utf-8");
					writer.write(value);
					writer.flush();
					writer.close();
					request.setAttribute("error", "Successfully save the content");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					logger.error(this.getClass().getName() + ":" + e.getMessage());
				}
				finally {
					try {
							if (null != writer) writer.flush(); writer.close();
					} catch (Exception ignored) {}
				}
			}
			else
			{
				logger.error("not exist " + file.getAbsolutePath());
			}
		}
		if (successPage.indexOf(".xhtml") != -1)
		{
			return new ModelAndView(new StandardView(successPage + "?fileName=" + fileName));
		}
		else
		{
			return new ModelAndView(successPage);
		}
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
