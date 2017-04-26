<%@page import="com.hosting.filemanager.FileManagerUtil"%>
<%@page import="com.hosting.util.ApplicationUtil"%>
<%@page import="java.io.FileFilter"%>
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
String content = request.getParameter("contents");
User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
Collection<GrantedAuthority> authorities = user.getAuthorities();
Iterator<GrantedAuthority> iter = authorities.iterator();
GrantedAuthority authority = iter.next();

String cmd = request.getParameter("cmd");
if ("delete".equals(cmd))
{
	String fileName = request.getParameter("fileName");
	File file = new File(fileName);
	file.delete();
}
String[] header = 
{
		"Name",
		"Date",
		"Size",
};
String[] actions = 
{
		"Action",
};


String resourcePath = "/home/files/pho/pages/";
File resourceDir = new File(resourcePath);
final String[] pages = {"index","menu","party", "services", "review"};
File[][] resources = new File[pages.length][];
int[] newSectionIds = new int[pages.length];
File[] newSections = new File[pages.length];
for (int i = 0; i < resources.length; i++ )
{
	final int counter = i;
	resources[i] = resourceDir.listFiles(new FileFilter()
		{
			public boolean accept(File f)
			{
				return f.isFile() && f.getName().startsWith(pages[counter]) && f.getName().endsWith(".txt");
			}
		}
	);
	
	newSectionIds[i] = (null != resources[i]) ? resources[i].length : 0;
	newSections[i] = new File(resourcePath, pages[i] + "-section" + newSectionIds[i] + ".txt");
}
	



%>
<jsp:include page="admin-menu.jsp" >
	<jsp:param value="utilities" name="active"/>
</jsp:include>		
<br/>
<% for (int i = 0; i < resources.length; i++ ){  %>
<% if (null != resources[i] && resources[i].length > 0) { %>
	<h4><%=pages[i] %> Page</h4>
	<a href="admin-editor.html?file=<%=newSections[i].getAbsolutePath()%>">Add new section</a>
	<table id="hor-minimalist-a" >
	 
	    <thead>
	    	<tr>
	        	<% 
	        		for (int j = 0; j < header.length; j++)
	        		{
	        	%>	
	        		<th scope="col"><%=header[j]%></th>
	        	<%
	        		}
	        	%>
	        	<% 
	        		for (int j = 0; j < actions.length; j++)
	        		{
	        	%>	
	        		<th scope="col"><%=actions[j] %></th>
	        	<%
	        		}
	        	%>
	        </tr>
	    </thead>
	
	    <tbody>
	    <% for (int j = 0; j < resources[i].length; j++) { %>
			
		     <tr>
	 			<td><img src="<%=context%>images/16px/html.png" alt="page" width="16" height="16"/> <%=resources[i][j].getName()%></a></td>
	 			<td><%=ApplicationUtil.displayFormat.format(resources[i][j].lastModified())%></td>
	 			<td><%=FileManagerUtil.convertFileSize(resources[i][j].length())%></td>
	 			<td id="test">
	 			<a href="admin-editor.xhtml?fileName=<%=resources[i][j].getAbsolutePath()%>">[Edit]</a>
	 			<a href="#"  onclick="showDialog('Are you sure you want to delete ', '<%=resources[i][j].getName()%>', 'admin-misc.html?cmd=delete&fileName=<%=resources[i][j].getAbsolutePath()%>');">[Remove]</a></td>
	 			
	  		</tr>
	 	<%
		 }
	 	%>
	    </tbody>
	</table>
	<br/>
<%	} %>
<% } %>
 	