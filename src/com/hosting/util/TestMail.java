package com.hosting.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class TestMail extends TestCase
{
	 protected final Log logger = LogFactory.getLog(getClass());
	 private ApplicationContext ctx;
	 public static void main(String[] s)
	 {
		 junit.textui.TestRunner.run(suite());
	 }
	 
	 public static Test suite() {
	        TestSuite suite = new TestSuite();
	        suite.addTestSuite(TestMail.class);
	        return suite;
	    }
	    public void setUp() throws Exception {
	        ctx = new FileSystemXmlApplicationContext(
	           "/WEB-INF/hosting-servlet.xml");
	    }
	    
	   
	    public void testSendMailWithAuthentication() throws Exception
	    {
	    	JavaMailSender mailSender = (JavaMailSender)ctx.getBean("mailSender");
	    	SimpleMailMessage msg = (SimpleMailMessage)ctx.getBean("templateMessage");

        	msg.setSubject("test send mail with authentication");
            msg.setText("test send mail with authentication");
            msg.setTo(new String[] {"support@tomcathostingservice.com", msg.getFrom() });
            
            new Thread(new SendMail(mailSender, msg)).start();
	    }
	    
	    
}
