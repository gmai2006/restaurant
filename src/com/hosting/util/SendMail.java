package com.hosting.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail implements Runnable
{
	final Log logger = LogFactory.getLog(getClass());
	SimpleMailMessage m_msg;
	JavaMailSender m_sender;
	
	public SendMail(JavaMailSender sender, SimpleMailMessage msg)
	{
		 m_sender = sender;
		 m_msg = msg;
	}
	public void run()
	{
		 try
	       {
	           this.m_sender.send(m_msg);
	       }
	       catch(MailException ex) 
	       {
	           logger.error(ex.getMessage(), ex);            
	       }
	}
}
