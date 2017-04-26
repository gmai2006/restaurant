
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
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
<link rel="stylesheet" href="<%=context%>/styles/ph_core.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="<%=context%>/styles/admin-table.css"
	type="text/css" media="screen">

		
<script src="js/closure/goog/base.js"></script>
<script>
    goog.require('goog.events');
    goog.require('goog.events.EventType');
    goog.require('goog.ui.Dialog');
    goog.require('goog.ui.Prompt');
    goog.require('goog.net.IframeIo');
    goog.require('goog.ui.Component');
    goog.require('goog.ui.ProgressBar');
    goog.require('goog.dom');
    goog.require('goog.Timer');
    goog.require('goog.net.XhrIo');
  </script>
<link rel="stylesheet" href="styles/closure/dialog.css">
<style>
.modal-dialog {
	width: 430px;
	top: 100px;
	position: fixed;
}
</style>
<style>
    .progress-bar-horizontal {
      position: relative;
      border: 1px solid #949dad;
      background: white;
      padding: 1px;
      overflow: hidden;
      margin: 2px;
    }

    .progress-bar-horizontal {
      width: 80%;
      height: 1em;
    }

    .progress-bar-thumb {
      position: relative;
      background: #d4e4ff;
      overflow: hidden;
      width: 100%;
      height: 100%;
    }

    #pb2 {
      height: 1em;
      display: none;
    }
  </style>


</head>
<body>
	<div class="main">
		<!-- header -->
		<jsp:include page="header.jsp"></jsp:include>


		<div class="box">
			<!-- three colls block -->
			<jsp:include page="<%=pageName%>"></jsp:include>
			<!-- /content -->
		</div>

		<script>
var dialog1 = new goog.ui.Dialog();
dialog1.setContent('Please wait......');
dialog1.setTitle('');
dialog1.setButtonSet(null);

function showPrompt(oldName, command)
{
	var promptHandler = function(response) {
    if (response != null && '' != response) {
    	dialog1.setVisible(true);
      window.parent.location.href='admin-file-manager.xhtml?cmd=' + command + '&fileName=' + oldName + '&newName=' + response
    } 
  }

  var prompt = new goog.ui.Prompt(
      'Information Required',
      'Please enter new name:',
      promptHandler);
   prompt.setVisible(true);
}

function showDialog(message, content, url) {
	var dialog2 = new goog.ui.Dialog(null, true);
	dialog2.setContent(message + content);
    dialog2.setTitle('Confirm Window');
    dialog2.setButtonSet(goog.ui.Dialog.ButtonSet.YES_NO);

    goog.events.listen(dialog2, goog.ui.Dialog.EventType.SELECT, function(e) {
      if ("yes" == e.key)
       {
    	  dialog1.setVisible(true);
    	  window.parent.location.href=url
       }
    });

    goog.events.listen(window, 'unload', function() {
      goog.events.removeAll();
    });  

  dialog2.setVisible(true);
}

function showDialog2(message) {
	var dialog2 = new goog.ui.Dialog(null, true);
	dialog2.setContent(message);
    dialog2.setTitle('Confirm Window');
    dialog2.setButtonSet(goog.ui.Dialog.ButtonSet.OK);

    goog.events.listen(dialog2, goog.ui.Dialog.EventType.SELECT, function(e) {
   	  window.opener.doSubmit();
    });

    goog.events.listen(window, 'unload', function() {
      goog.events.removeAll();
    });  

  dialog2.setVisible(true);
}

goog.debug.LogManager.getRoot().setLevel(goog.debug.Logger.Level.INFO);

var testLogger = goog.debug.Logger.getLogger('test');

/** Creates an iframeIo instance and sets up the test environment */
function getTestIframeIo() {
    
  var io = new goog.net.IframeIo();
  //io.setErrorChecker(checkForError);
  
  goog.events.listen(io, 'success', onSuccess); 
  goog.events.listen(io, 'error', onError);  
  goog.events.listen(io, 'ready', onReady);
  
  return io;
}

/** Logs the status of an iframeIo object */
function logStatus(i) {
  testLogger.fine('Is complete/success/active: ' +
      [i.isComplete(), i.isSuccess(), i.isActive()].join('/'));
}

function onSuccess(e) {
  testLogger.warning('Request Succeeded'); 
}

function onError(e) {
  testLogger.warning('Request Errored: ' + e.target.getLastError()); 
}

function onReady(e) {
  testLogger.info('Test finished and iframe ready, disposing test object');
  e.target.dispose();
}

function onUploadSuccess(e) {
	  testLogger.shout('Upload Succeeded');
	  testLogger.info('ResponseText: ' + e.target.getResponseText());
	  document.location.reload()
	  document.getElementById('uploadform').reset()
	}

	function onUploadError(e) {
	  testLogger.shout('Upload Errored');
	  testLogger.info('ResponseText: ' + e.target.getResponseText());
	}
	
function sendFromForm() {
  var io = getTestIframeIo()
  goog.events.listen(io, 'success', onUploadSuccess);
  goog.events.listen(io, 'error', onUploadError);
  io.sendFromForm(document.getElementById('uploadform'));
  document.getElementById("pb2").style.display = 'block';
  
  var $ = goog.dom.getElement;
  var pb2 = new goog.ui.ProgressBar;
  pb2.decorate($('pb2'));
  var last = 0;
  var delta = 1;
  var t = new goog.Timer(20);
  t.addEventListener('tick', function(e) {
    last += delta;
    pb2.setValue(last);
  });
  t.start();
}

function onPostSuccess(e) {
	  
	}

	function onUploadError(e) {
	  testLogger.shout('Upload Errored');
	  testLogger.info('ResponseText: ' + e.target.getResponseText());
	}
	
function sendPost(url) {
	var request = new goog.net.XhrIo;
	// listen to complete event
    goog.events.listen(request, "complete", function(){
        if (request.isSuccess()) {
                       
        	dialog1.setVisible(false);
            // print confirm to the console
            //console.log("Satus code: ", request.getStatus(), " - ", request.getResponseText());
                       
        } else {
            console.log(
                "Something went wrong in the ajax call. Error code: ", request.getLastErrorCode(),
                " - message: ", request.getLastError()
            );
                       
        }
                   
    });
    dialog1.setVisible(true);
    request.send(url, "POST");
}

function sendPost(url, targetUrl) {
	var request = new goog.net.XhrIo;
	// listen to complete event
    goog.events.listen(request, "complete", function(){
        if (request.isSuccess()) {
                       
        	dialog1.setVisible(false);
            // print confirm to the console
            //console.log("Satus code: ", request.getStatus());
            window.location.href=targetUrl;
                       
        } else {
            console.log(
                "Something went wrong in the ajax call. Error code: ", request.getLastErrorCode(),
                " - message: ", request.getLastError()
            );
                       
        }
                   
    });
    dialog1.setVisible(true);
    request.send(url, "POST");
}

  </script>

		<!-- footer -->
		<div class="footer">
			Pho © Copyright 2011. 
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
