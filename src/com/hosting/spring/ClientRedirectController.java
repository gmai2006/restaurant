package com.hosting.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;

public class ClientRedirectController extends ApplicationAbstractController
{
	private String securePage;
	private String pages;
	private String successPage2;
	private String fullPage;
	
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String page = "index";
		String name = request.getRequestURI();
		String context = request.getContextPath();
		String currentPath = name.substring(context.length()).trim();
		
		int index = currentPath.lastIndexOf("/");
		if (index >= 0 && currentPath.length() > 1)
		{
			page = currentPath.substring(index+1);
		}
		index = page.indexOf(".");
		if (index > 0)
		{
			page = page.substring(0, index);
		}
		name = page;
		addData(request, "current-path", currentPath);
		addData(request, "messageSource", messageSource);
		logger.info("URI " + name + ", current-path " + currentPath);
		page = page + "_body.jsp";
		String query = ApplicationUtil.buildQueryString(request);
		if (query.length() >0)
		{
			page = page + "?" + query;
		}
		logger.info("go to page name " + page);
		if (name.startsWith("admin") || fullPage.indexOf(name) != -1)
		{
			return new ModelAndView(successPage2 + page);
		}
		else
		{
			return new ModelAndView(successPage + page);
		}
	}
	
	
	
	public String getPages()
	{
		return pages;
	}

	public void setPages(String pages)
	{
		this.pages = pages;
	}

	public String getSecurePage() {
		return securePage;
	}

	public void setSecurePage(String securePage) {
		this.securePage = securePage;
	}



	public String getSuccessPage2() {
		return successPage2;
	}



	public void setSuccessPage2(String successPage2) {
		this.successPage2 = successPage2;
	}



	public String getFullPage() {
		return fullPage;
	}



	public void setFullPage(String fullPage) {
		this.fullPage = fullPage;
	}
}
