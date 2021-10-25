<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--静态包含头部信息base标签,css标签,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给删除的a标签绑定单击事件
            $("a.deleteClass").click(function () {
                /**
                 * confirm是确认提示框函数
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?")
            });

            //给清空购物车的a标签绑定单击事件
            $("#clearCart").click(function () {
                /**
                 * confirm是确认提示框函数
                 */
                return confirm("你确定要清空购物车?")
            });


            //给数量输入框绑定内容发生变化事件
            $(".updateClass").change(function () {
                //获取名称
                var name = $(this).parent().parent().find("td:first").text();
                //获取输入框值
                var count = this.value;

                //读取自定义的属性bookId
                var bookId = $(this).attr("bookId");

                if (confirm("你确定要将【" + name + "】数量修改为" + count + "?")) {
                    location.href = "${basePath}cartServlet?action=updateCount&id=" + bookId + "&count=" + count;
                } else {
                    //恢复为defaultValue值：数量输入框发生变化前值
                    this.value = this.defaultValue;
                }
            });

        });

    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%-- 静态包含登陆成功后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td><input bookId="${entry.value.id}" class="updateClass" style="width:50px" type="text"
                           value="${entry.value.count}"></td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td><a class="deleteClass" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <%--购物车为空时隐藏--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%--静态包含底部页脚信息 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>