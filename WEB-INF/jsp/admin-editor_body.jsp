<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%
	FCKeditor fckEditor = new FCKeditor(request, "EditorDefault");
	String fileName = request.getParameter("fileName");
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

<br/>
<jsp:include page="admin-menu.jsp" >
	<jsp:param value="utilities" name="active"/>
</jsp:include>	
<br/>
<p>Editing file: <%=fileName %>
<br/>
<form action="saveContent.asp" method="post">
<input type="hidden" name="fileName" value="<%=fileName%>" />
<%
	fckEditor.setValue(existing);
	out.println(fckEditor);
%>
<br />
<input type="submit" value="Save" />
</form>