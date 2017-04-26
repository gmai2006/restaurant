package com.hosting.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hosting.util.ApplicationUtil;


public abstract class ApplicationAbstractController extends AbstractController
{
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected String error = "";
	protected int authorizationcode = 1;
	protected SimpleMailMessage templateMessage;
	protected JavaMailSenderImpl mailSender;
	
	protected String referer;
	
	protected String m_env;
	
	protected String successPage;
	protected String failurePage;
	protected String errorPage;
	
	protected ResourceBundleMessageSource messageSource;
	
	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	} 
	
	public void settemplateMessage(SimpleMailMessage msg)
	{
		this.templateMessage = msg;
	}
	public SimpleMailMessage gettemplateMessage()
	{
		return templateMessage;
	}
	
	public void setmailSender(JavaMailSenderImpl msg)
	{
		this.mailSender = msg;
	}
	public JavaMailSenderImpl getmailSender()
	{
		return mailSender;
	}
	public String getEnv()
	{
		return m_env;
	}
	
	public String getReturnPage(String page)
	{
		if (page.indexOf(".") != -1)
		{
			page = page.substring(0, page.indexOf("."));
		}
		
		if (page.indexOf("_body") != -1)
		{
			return "template.jsp?name=" + page + ".jsp";
		}
		else
		{
			return "template.jsp?name=" + page + "_body.jsp";
		}
	}
	
    
    public void setAuthorizationCode(int code) { this.authorizationcode = code; }
    
	public void setReferer(HttpServletRequest request, String url)
	{
		addData(request, "referer", url);
	}
	
	public String getReferer(HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		return (String)session.getAttribute("referer");
	}
	
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			return process(request, response);
		}
		
		catch (Exception e)
		{
	        logger.error("Application err", e);
//	        notifyAdmin("Application ERROR", "Error has occurs please fix the problem ASAP: "
//	        		+ ApplicationUtil.getDate(System.currentTimeMillis()) + " from " + request.getRemoteAddr() 
//	        		+ " due to "+ e.getMessage());  
	        request.setAttribute("error", e.getMessage());
	        String page = getReturnPage("error");
	        logger.info("returned page for exception " + page);
			return new ModelAndView(page);
		}
	}

	 
	 protected void notifyAdmin(String message)
	 {
		 SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	        msg.setText(message);    
	        try
	        {
	            new Thread(new com.hosting.util.SendMail(mailSender, msg)).start();
	        }
	        catch(MailException ex) {
	            logger.error(ex);            
	        }
	 }
	 
	 protected void notifyAdmin(String subject, String message)
	 {
		 SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		 msg.setSubject(subject);
	        msg.setText(message);    
	        try
	        {
	            new Thread(new com.hosting.util.SendMail(mailSender, msg)).start();
	        }
	        catch(MailException ex) {
	        	ex.printStackTrace();
	            logger.error(ex);            
	        }
	 }
	 
	
	protected abstract ModelAndView process(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	
	protected void createSite(HttpServletRequest request, String site)
	{
		addData(request, "site", site);
	}
	
	protected void addData(HttpServletRequest request, String id, Object o)
	{
		HttpSession session = request.getSession(true);
		session.setAttribute(id, o);
	}
	
	
	public String getSuccessPage() {
		return successPage;
	}

	public void setSuccessPage(String successPage) {
		this.successPage = successPage;
	}

	public String getFailurePage() {
		return failurePage;
	}

	public void setFailurePage(String failurePage) {
		this.failurePage = failurePage;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
