package com.hosting.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;


public class LogoutController extends ApplicationAbstractController 
{
	public ModelAndView process(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(false);
		SecurityContextHolder.getContext().setAuthentication(null);

		if (null != session) session.invalidate();
		if (successPage.indexOf(".jsp") == -1)
		{
			return new ModelAndView(new StandardView(successPage));
		}
		else
		{
			return new ModelAndView(successPage);
		}
	}
}
