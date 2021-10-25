<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%--静态包含头部信息base标签,css标签,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%--静态提取管理模块菜单 --%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <form action="manager/book" method="post">
        <%--设置表单隐藏域用来区分同种请求的不同表单--%>
        <%--通过判断请求参数中是否有id值来进行add操作还是update操作--%>
        <%--通过_method属性通过HiddenHttpMethodFilter设置请求方式--%>
        <input type="hidden" name="_method" value="${empty param.id ? "post":"put"}"/>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>
        <input type="hidden" name="pageNo" value="${param.pageNo}"/>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>

</div>

<%--静态包含底部页脚信息 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>