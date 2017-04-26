
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String context = request.getContextPath();
if ("".equals(context)) context = "/";
String pageName = request.getParameter("name");

String menuName = pageName.substring(0, pageName.lastIndexOf("_"));
String index,menu,about,contact, review, party;
index=menu=about=contact=review = party = "";
if ("index".equals(menuName)) index = "class=\"act\"";
else if ("menu".equals(menuName)) menu = "class=\"act\"";
else if ("about".equals(menuName)) about = "class=\"act\"";
else if ("contact".equals(menuName)) contact = "class=\"act\"";
else if ("review".equals(menuName)) review = "class=\"act\"";
else if ("party".equals(menuName)) party = "class=\"act\"";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Menu | Phở Alderwood (425) 744-0990</title>
<meta name="copyright" content="(C) 2003 - 2009 Pho Alderwood" />
<meta name="DC.Title" content="Phở Alderwood (425) 744-0990 | Authentic Vietnamese Food Lovingly Prepared by Our Family for Your Family" />
<meta name="keywords" content="Pho,Pho Alderwood,Beef Noodle,Vietnamese Pho,Lynnwood Restaurant,Vietnamese Restaurant,Chinese Restaurant,Asia Restaurant,Restaurant,Restaurants,Washington Restaurants" />
<meta name="robots" content="index,follow" />
<meta name="geo.position" content="47.60621;-122.332071" />
<meta name="ICBM" content="47.60621,-122.332071" />
<link rel="shortcut icon" href="images/favicon.jpg" type="image/x-icon" />

<link href="style.css" type="text/css" rel="stylesheet" />
<link href="styles/table.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/featured.js"></script>
<!--[if IE 6]>
    <script type="text/javascript" src="js/ie_png.js"></script>
     <script type="text/javascript">
  	   ie_png.fix('.png');
  	   ie_png.fix('.foot-contform textarea');
     </script>
<![endif]-->
</head>
<body>
<div class="main">
<!-- header -->
<jsp:include page="header.jsp"></jsp:include>
<!-- header block -->
<div class="header-block"><div class="shadow"><img src="images/info-block-shad.gif" /></div>
    <ul id="featured-content">
        <li class="act" id="feat0">
            <img src="images/DSC_4548.JPG" width="980" height="348"/>
            <div class="info-block">
                <h1>Noodles Soup</h1>
                <p>Pho is pleasing medley of Beef/Chicken and flat rice noodles served in a healthy broth.  Noodles are served with bean sprouts, basils, lime wedges and jalapeno slices.  For extra spices, you may add hot sauce and hoisin sauce at the table to your soup.</p>
                
            </div>        
        </li>
        <li id="feat1" style="display: none">
            <img src="images/DSC_5725A.JPG" />
            <div class="info-block">
                <h1>Vermicelli Noodles</h1>
                <p>Bún dishes are served with your choice of meat and/ or seafood combinations, blend of herds and lettuce, and a light lime- anchovy’s sauce.</p>
                
            </div>
        </li>
        <li id="feat2" style="display: none">
            <img src="images/DSC_4523.JPG" />
            <div class="info-block">
                <h1>Steamed Rice</h1>
                <p>Entrees are serving on a bed of rice/ or fried rice (Added extra $1.50 for fried rice) with anchovies sauce and our specialty salads.</p>
                
            </div>
        </li>
        <!-- 
        <li id="feat3" style="display: none">
            <img src="images/header-img004.jpg" />
            <div class="info-block">
                <h1>Vegetarian Dishes</h1>
                <p>We will gladly substitute any meat items in our menu to Tofu as you request.</p>
                <a href="#" class="rmore">Read More</a>
            </div>
        </li>
        <li id="feat4" style="display: none">
            <img src="images/header-img005.jpg" />
            <div class="info-block">
                <h1>Stir Fried</h1>
                <p>Our Specialty sauces for stir-fried with clean healthy canola oil and medley vegetable.</p>
                <a href="#" class="rmore">Read More</a>
            </div>
        </li>
         -->
    </ul>
    <ul id="featured-tabs" class="tabs">
      <li class="act"><a href="#" rel="0"></a></li>
      <li><a href="#" rel="1"></a></li>
      <li><a href="#" rel="2"></a></li>
      <!-- 
      <li><a href="#" rel="3"></a></li>
      <li><a href="#" rel="4"></a></li>
       -->
    </ul>
</div>
<!-- /header block -->
<div class="box">
<!-- three colls block -->
<jsp:include page="<%=pageName%>"></jsp:include>
<!-- /content -->
</div>
<!-- footer -->    
<div class="footer">
    Pho © Copyright 2011. 
</div>
<!-- /footer -->
</div>
</body>
</html>
