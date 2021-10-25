<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出错了</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>
很抱歉，您输入的信息有误！！！！ <br>
错误信息：<br>${errorInfo.name}${errorInfo.author}${errorInfo.price}${errorInfo.sales}${errorInfo.stock}
<br>
<a href="index.jsp">返回首页</a>

</body>
</html>