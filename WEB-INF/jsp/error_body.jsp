<%
String error = (String)request.getAttribute("error");
%>
<% if (null != error) { %>
<strong><%=error %></strong>
<% } %>
