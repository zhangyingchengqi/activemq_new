<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	
	<script type="text/javascript">
		   function reg(){
			   $.ajax({
				   url: "reg.action",
				   type:"POST",
				   data: $("#regForm").serialize(),      //    {"op":"reg","uname":"zy","email":"xxx.com"}
				   dataType:'text',
				   success:function(data){
					  // alert( data );
				   }
			   });
		   }
	</script>
	
  </head>
  <body>
  
    <center>
    	<h1>注册</h1>
    	<div style="background:#69F;width:300px;padding:10px">
            <form id="regForm" action="reg.action" method="post" >
            <input type="hidden" name="op" value="reg"/>
            <table>
            	<tr>
            		<th ><h2>用户名:</h2></th>
            		<th id="addReceiver"><input type="text" name="uname" value="zy" /></th>
            	</tr>
            	<tr id="receiverTr">
				    <td><label for="receiver">注册邮箱:</label></td>
				    <td><input name="email" type="text" value="1069595532@qq.com"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="注册" onclick="reg()" /></td>
				</tr>
            </table>
            </form>
        </div>
    </center>
  </body>
</html>
