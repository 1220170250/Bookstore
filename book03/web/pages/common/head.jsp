<%--
  Created by IntelliJ IDEA.
  User: qinkai
  Date: 2020/12/17
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 动态获取IP地址--%>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            +"/";
    //把basePath保存到pageContext域中
    pageContext.setAttribute("basePath",basePath);
%>

<base href="<%=basePath%>>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<!--引入jQuery文件-->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>