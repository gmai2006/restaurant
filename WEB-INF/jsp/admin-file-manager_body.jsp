<%@page import="com.hosting.util.ApplicationUtil"%>
<%@page import="com.hosting.filemanager.FileWrapper"%>
<%@page import="com.hosting.filemanager.FileManagerUtil"%>
<%@page import="java.io.File"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Collection"%>
<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.springframework.security.*"%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String context = request.getContextPath();
if ("".equals(context)) context = "/";
if (!context.endsWith("/")) context += "/";
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
String appRoot = getServletContext().getRealPath("/");
String rootdir = request.getParameter("rootdir");
if (null == rootdir)
{
	rootdir = (String)session.getAttribute("rootdir");
	if (null == rootdir)
	{
		rootdir = appRoot;
	}
}
else
{
	session.setAttribute("currentDir", null);
}

session.setAttribute("rootdir", rootdir);


User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
Collection<GrantedAuthority> authorities = user.getAuthorities();
Iterator<GrantedAuthority> iter = authorities.iterator();
GrantedAuthority authority = iter.next();
%>
<jsp:include page="admin-menu.jsp" >
	<jsp:param value="filemanager" name="active"/>
</jsp:include>	
<br/>
<form name="frm" action="admin-file-manager.xhtml" method="post" >
	<select name="rootdir" onchange='frm.submit();'> 
		<option value="">Select Location</option> 	
		<% if ("super".equals(authority.getAuthority()) ) { %>
			<option value='<%=getServletContext().getRealPath("/")%>'>Web application</option>
		<% } %>
		<option value="/home/files/pho/menu">Menu</option>
		<option value="/home/files/pho/togo">To Go Menu</option>
		
	</select>
</form>
<br/>
<br/>

<jsp:include page="admin-file-manager.jsp" >
	<jsp:param value="<%=rootdir%>" name="rootdir" />
</jsp:include>

 	