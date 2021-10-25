<%--
  Created by IntelliJ IDEA.
  User: qinkai
  Date: 2020/12/24
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 分页条开始--%>
<div id="page_nav">
    <%--当前页为首页隐藏首页和上一页标签--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出开始,要求最多5个当前页在中间--%>
    <c:choose>
        <%--当总页数小于5时，遍历输出1到总页数--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--当总页数大于5时，又分为3种情况--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--当前页为前三页时，页码都是12345--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--当前页为后三页时，页码都是 总页码-4——总页码 678910--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--当前页为中间时，页码都是 当前页码-2——当前页码+2 --%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${requestScope.page.pageNo==i}">
            【${i}】
        </c:if>
        <c:if test="${requestScope.page.pageNo!=i}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出结束--%>

    <%--当前页为尾页隐藏末页和下一页标签--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageButton" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            //页码搜索按钮绑定单击事件
            $("#searchPageButton").click(function () {
                var pageNo = $("#pn_input").val();
                //通过javascript中location对象href属性获取浏览器地址栏中地址
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>

</div>
<%-- 分页条结束--%>