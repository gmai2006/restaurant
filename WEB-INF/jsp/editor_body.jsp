<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

	<%
		FCKeditor fckEditor = new FCKeditor(request, "EditorDefault");
	String fileName = request.getParameter("file");
	StringWriter writer = null;
	if (null != fileName)
	{
		File file = new File(fileName);
		
		if (null != fileName && file.exists() && file.isFile())
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(
			new FileInputStream(file), "UTF8"));
			String line = null;
			try
			{
				writer = new StringWriter();
				while (null != (line = in.readLine()))
				{
					writer.write(line);
				}
				writer.flush();
				writer.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally {
				try {
	//					if (null != writer) writer.flush(); writer.close();
				} catch (Exception ignored) {}
			}
		}
	}
	String existing = (null == writer) ? "" : writer.toString();
	%>

		<h1>FCKeditor - JSP - Sample 1</h1>
		
		<hr />
		<form action="sampleposteddata.jsp" method="post" target="_blank">
		<%
			fckEditor.setValue(existing);
			out.println(fckEditor);
		%>
		<br />
		<input type="submit" value="Submit" /></form>
