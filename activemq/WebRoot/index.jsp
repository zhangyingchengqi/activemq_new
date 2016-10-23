<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>发送邮件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="css/email.css"/>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/email.js"></script>
  </head>
  <body>
  	<div id="emailTip">
  		<span id="emailTip_163" onmouseover="selectEmail(this)"></span>
  		<span id="emailTip_qq"  onmouseover="selectEmail(this)"></span>
  	</div>
    <center>
    	<div style="background:#69F;width:300px;padding:10px">
            <form id="emailInfo">
            <input type="hidden" name="op" value="sendEamil"/>
            <table>
            	<tr>
            		<th colspan="2"><h2>发送邮件</h2></th>
            		<th id="addReceiver"><h4 onclick="addReceiver()">+人</h4></th>
            	</tr>
            	<tr id="receiverTr">
				    <td><label for="receiver">收信人:</label></td>
				    <td><input onfocus="emailFlag(this)" onkeyup="emailTip(this)" onblur="hideEmailTip(this)" class="receiver" name="receiver" type="text"></td>
				</tr>
				<tr>
				    <td><label for="title">标&nbsp;&nbsp;题:</label></td>
				    <td><input id="title" name="title" type="text"></td>
				</tr>
				<tr>
				    <td><label for="contents" style="text-align">内&nbsp;&nbsp;容:</label></td>
				    <td rowspan="2"><textarea id="contents" name="contents"></textarea></td>
				</tr>
				<tr>
					<td><label>　</label></td>
				</tr>
				<tr>
					<td colspan="3"><input type="button" value="发送" onClick="sendInfo()"/></td>
				</tr>
            </table>
            </form>
        </div>
    </center>
  </body>
</html>
