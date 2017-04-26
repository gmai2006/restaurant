package com.hosting.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.ModelAndView;

import com.hosting.util.ApplicationUtil;
import com.hosting.util.SendMail;

public class ContactController extends ApplicationAbstractController 
{
	protected ModelAndView process(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String topic = request.getParameter("topic");
		String question = request.getParameter("question");
		String email = request.getParameter("user");
		
		if (ApplicationUtil.isStringNull(email)
				|| ApplicationUtil.isStringNull(question)
				|| ApplicationUtil.isStringNull(topic))
		{
			error = messageSource.getMessage("contact.faulure", null, "Missing required information. Please try again", null);
	        request.setAttribute("error", error);
			return new ModelAndView(failurePage);
		}
		
		logger.info("send email to admin from "  + request.getRemoteAddr());
        try
        {
        	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        	if (!ApplicationUtil.isStringNull(email))
        	{
        		question = email + ":" + question;
        	}
        	msg.setSubject(topic);
            msg.setText(question);
            
            new Thread(new SendMail(mailSender, msg)).start();
        }
        catch(MailException ex) {
            logger.error(ex.getMessage());            
        }
        error = messageSource.getMessage("contact.success", null, "Thank you for your inquiries.  We will review your request and respond back to your request as soon as we can</p><p>Meanwhile please continue using our services<br/>", null);
        request.setAttribute("error", error);
		return new ModelAndView(successPage);
	}
}
