<%
String context = request.getContextPath();
if (null == context || "".equals(context)) context = "/";
if (!context.endsWith("/")) context += "/";
%>
<div class="content clearfix">
        <h1>Quick Contact us</h1>    
        <form id="form" name='f' action='<%=context%>j_spring_security_check' method='POST'>
            <div class="hldr clearfix">
            <div class="inparea left">
                <div class="text-field">UserId:</div>                    
                <div class="input-field"><input type="text" name="j_username" id="name"/></div>
            </div>
            </div>
           <div class="hldr clearfix">
            <div class="inparea left">        
                <div class="text-field">Password:</div>                    
                <div class="input-field"><input type="password" name="j_password" id="email" /></div>
            </div>
            </div>
            
           <input type="button" value="Clear" class="clear-but" />
            <input type="submit" value="Log In" class="submit-but" />
			
            
        </form>
 </div>