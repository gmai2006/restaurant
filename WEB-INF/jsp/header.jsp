<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String context = request.getContextPath();
if ("".equals(context)) context = "/";
String pageName = request.getParameter("name");

String menuName = pageName.substring(0, pageName.lastIndexOf("_"));
String index,menu,about,contact, review, party,hour;
index=menu=about=contact=review = party = hour = "";
if ("index".equals(menuName)) index = "class=\"act\"";
else if ("menu".equals(menuName)) menu = "class=\"act\"";
else if ("about".equals(menuName)) about = "class=\"act\"";
else if ("contact".equals(menuName)) contact = "class=\"act\"";
else if ("review".equals(menuName)) review = "class=\"act\"";
else if ("party".equals(menuName)) party = "class=\"act\"";
else if ("hour".equals(menuName)) hour = "class=\"act\"";
%>
<div class="header">
<!-- top links -->
  <div class="top-links">
  425-750-5883 &nbsp;|&nbsp;  (425) 744-0990 &nbsp;|&nbsp; <a href="http://www.facebook.com/profile.php?id=100003714812775" target="_blank">facebook</a>&nbsp;|&nbsp;<a href="admin.xhtml">Admin</a>
  </div>
<!-- /top links -->
<!-- main menu -->
  <ul class="main-menu">
    <li><a href="index.xhtml" <%=index%>>Home</a></li>
    <li><a href="services.xhtml">Services</a></li>
    <li><a href="menu.xhtml" <%=menu%>>menu</a></li>
    <li><a href="hour.xhtml" <%=hour%>>hour</a></li>
    <li><a href="party.xhtml" <%=party%>>Party Tray</a></li>
    <li><a href="review.xhtml" <%=review%>>reviews</a></li>
    <li><a href="contact.xhtml" <%=contact%>>Contacts</a></li>
  </ul>
<!-- /main menu -->
  <a href="index.xhtml" class="logo"><img src="images/logo2.jpg" width="183"/></a>
</div>