<%-- 
    Document   : response
    Created on : 11.04.2014, 18:55:20
    Author     : Ryner
--%>

<%@page language="java" import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String uadr = String.valueOf(request.getAttribute("urladdres")); %> 
<!DOCTYPE html>
<html>
	<meta charset="windows-1251">
    <head>
        <script src="HTML5Player/player.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="Style/style.css">
        <title>audio</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="player" id="audioplayer" style="width:400px;height:50px"></div>
        <p><script type="text/javascript">this.player = new Uppod({m:"audio",uid:"audioplayer",file:"<%=uadr %>"});</script></p>
        <p><%= uadr%></p>
    </body>
</html>
