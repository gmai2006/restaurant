<%@page import="java.io.FileFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>

<%
String context = request.getContextPath();
if ("".equals(context) || !context.endsWith("/")) context += "/";
String admin, filemanager, utilities, photo;
admin=filemanager=utilities = photo= "tab";
String active = request.getParameter("active");
if ("admin".equals(active)) admin="tab active";
else if ("filemanager".equals(active)) filemanager="tab active";
else if ("utilities".equals(active)) utilities="tab active";
else if ("photo".equals(active)) photo="tab active";

%>
<br/>
<div id="mt" class="gtb"> 
		<a href="admin.xhtml" class="<%=admin%>">[Admin]</a>
		<a href="admin-file-manager.xhtml" class="<%=filemanager%>">[File Manager]</a>
		<a href="admin-misc.xhtml" class="<%=utilities%>">[Utilities]</a>
		<!-- 
		<a href="admin-upload.xhtml" class="<%=photo%>">[Upload Bulk Photos]</a>
		 -->
<div class="gtbc"></div>
</div>