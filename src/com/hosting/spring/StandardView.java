package com.hosting.spring;

import org.springframework.web.servlet.view.InternalResourceView;

public class StandardView extends InternalResourceView
{
	public StandardView(String url)
	{
		super(url);
	}
}
