<%@page import="com.hosting.filemanager.FileManagerUtil"%>
<%@page import="com.hosting.util.ApplicationUtil"%>
<%@page import="java.io.FileFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String context = request.getContextPath();
if ("".equals(context)) context = "/";
final String pageName = request.getParameter("pageName");
String dirName = "/home/files/pho/pages/";
StringWriter writer = null;

File dir = new File(dirName);
File[] sections = dir.listFiles(new FileFilter() {
	public boolean accept(File f)
	{
		return f.isFile() && f.getName().startsWith(pageName) && f.getName().endsWith(".txt");
	}
});
String[] sectionText = new String[0];
if (dir.exists() && null != sections && sections.length > 0)
{
	sectionText = new String[sections.length];
	for (int i = 0; i < sections.length; i++)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(
		new FileInputStream(sections[i]), "UTF8"));
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
			sectionText[i] = (null == writer) ? "" : writer.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

%>


<% for (int i = 0; i < sectionText.length; i++) { %>
	<% if (sectionText[i].length() > 0) { %>
		<%=sectionText[i] %>
		<br/>
	<% } %>
<% } %>



 	