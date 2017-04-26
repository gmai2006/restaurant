<jsp:include page="admin-menu.jsp" >
	<jsp:param value="admin" name="active"/>
</jsp:include>		
<br/>

<div class="content clearfix">
<p><strong>File Manager</strong> is a virtual file manager which can be used to upload/rename/delete files.  
<p>All pages are under WEB-INF/jsp/
<p>super: can access the application and modify every files including application ifself.  Please use with caution as you may destroy the web site</p>
<p>admin: can upload photos, files and update the index page
<p>template.jsp is a container that contains header, footer
<p>other pages are self explanatory
</div>