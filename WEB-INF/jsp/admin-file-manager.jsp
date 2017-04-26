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
String rootdir = request.getParameter("rootdir");//getServletContext().getRealPath("/");
File root = new File(rootdir);
File iconsDir = new File(getServletContext().getRealPath("/"), "images/16px");
String IMGDIR =iconsDir.getAbsolutePath();
String all = FileManagerUtil.allExtension(new File(IMGDIR));
File currentDir = null;

String dir = request.getParameter("dir");
currentDir = (File)session.getAttribute("currentDir");
if (null == dir)
{
	if (null == currentDir) currentDir = root;
}
else
{
	if (currentDir.getAbsolutePath().indexOf(dir) != -1)
	{
		currentDir = currentDir.getParentFile();
	}
	else
	{
		currentDir = new File(currentDir, dir);
	}
}
session.setAttribute("currentDir", currentDir);
session.setAttribute("dir", dir);

String sort = request.getParameter("sort");
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
File[] files = currentDir.listFiles();
%>

Current Directory: <%=currentDir.getName()%> - <a href="#" onclick="showPrompt('NA','mkdir')">[Create new Folder]</a>
<form id="uploadform" action="<%=context%>upload.asp" enctype="multipart/form-data" method="POST">
  <p><a href="javascript:sendFromForm()">Upload</a> <input name="userfile" type="file">&nbsp;<a href="javascript:sendFromForm()">[Upload]</a></p> 
</form>
<div id="pb2" >
    <div class="progress-bar-thumb"></div>
    <div style='position:absolute;top:0;text-align:center;width:100%;
                font:statusbar;padding:2px'>
        loading...
    </div>
</div>
<table id="hor-minimalist-a" summary="File Manager">
 
    <thead>
    	<tr>
        	<% 
        		for (int i  = 0; i < header.length; i++)
        		{
        	%>	
        		<th scope="col"><a href="admin-file-manager.xhtml?sort=<%=header[i]%>"><%=header[i]%></a></th>
        	<%
        		}
        	%>
        	<% 
        		for (int i  = 0; i < actions.length; i++)
        		{
        	%>	
        		<th scope="col"><%=actions[i] %></th>
        	<%
        		}
        	%>
        </tr>
    </thead>

    <tbody>
    <%
    if (currentDir.getAbsolutePath().length() > rootdir.length())
    {
    %>
    <tr>
 			<td><a href="admin-file-manager.xhtml?dir=<%=currentDir.getParentFile().getName()%>">
 			<img src="<%=context%>images/16px/folder.png" alt="page" width="16" height="16"/> ..
 			<img src="<%=context%>images/arrow_turn_left.png" alt="page" width="16" height="16"/></a></td>
 			<td><br/></td>
 			<td><br/></td>
 			<td></td>
  		</tr>
    	
    <%
    }
    %>
    <%
     for (int i = 0; i < files.length; i++)
     {
    	 String fileType = FileManagerUtil.getFileType(files[i].getName());
    	 String img = "page.png";
    	 if (all.indexOf(fileType + ".png") != -1)
    	 {
    		 img = fileType + ".png";
    	 }
    	 else if (files[i].isDirectory())
    	 {
    		 img = "folder.png";
    	 }
	 %>
		 <%
	 	if (files[i].isDirectory())
	 	{
		 %>
  		<tr>
 			<td><a href="admin-file-manager.xhtml?dir=<%=files[i].getName()%>"><img src="<%=context%>images/16px/<%=img%>" alt="page" width="16" height="16"/> <%=files[i].getName()%></a></td>
 			<td><%=ApplicationUtil.displayFormat.format(files[i].lastModified())%></td>
 			<td><%=FileManagerUtil.convertFileSize(files[i].length())%></td>
 			<td><a href="#" onclick="showPrompt('<%=files[i].getName()%>','')">[Rename]</a></td>
  		</tr>
		 <%
	     }
		 else
		 {
	     %>
	     <tr>
 			<td><a href="viewFile.asp?file=<%=files[i].getName()%>" target="_blank"><img src="<%=context%>images/16px/<%=img%>" alt="page" width="16" height="16"/> <%=files[i].getName()%></a></td>
 			<td><%=ApplicationUtil.displayFormat.format(files[i].lastModified())%></td>
 			<td><%=FileManagerUtil.convertFileSize(files[i].length())%></td>
 			<td id="test">
 			
 			<a href="#" onclick="showPrompt('<%=files[i].getName()%>','rn')">[Rename]</a>
 			<a href="#" onclick="showPrompt('<%=files[i].getName()%>','cp')">[Copy]</a>
 			<a href="#"  onclick="showDialog('Are you sure you want to delete ', '<%=files[i].getName()%>', 'manageFile.asp?cmd=delete&fileName=<%=files[i].getName()%>');">[Delete]</a>
 			<%
 			if (FileManagerUtil.editableFile(files[i]))
 			{
 			%> 
 				<a href="admin-editor.xhtml?fileName=<%=files[i].getAbsolutePath()%>">[Edit]</a></td>
 			<%
		 	}
 			%>
  		</tr>
  		<%
		 }
     }
  		%>
    </tbody>
</table>




 	