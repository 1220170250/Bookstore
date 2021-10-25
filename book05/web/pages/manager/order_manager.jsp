<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--静态包含头部信息base标签,css标签,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%--静态提取管理模块菜单 --%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>状态</td>

        </tr>

        <c:forEach items="${requestScope.orders}" var="order">

            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td><a href="#">查看详情</a></td>

                <c:if test="${order.status eq 0}">
                    <c:set var="status" value="未发货"/>
                </c:if>
                <c:if test="${order.status eq 1}">
                    <c:set var="status" value="已发货"/>
                </c:if>
                <c:if test="${order.status eq 2}">
                    <c:set var="status" value="已收货"/>
                </c:if>
                <td><a href="#">${status}</a></td>

            </tr>

        </c:forEach>


    </table>
</div>

<%--静态包含底部页脚信息 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>