<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>

    <%--静态包含头部信息base标签,css标签,jQuery文件 --%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        //页面加载完成之后执行
        $(function () {

            //给用户名输入框绑定失去焦点事件，Ajax请求验证存在
            $("#username").blur(function () {
                //1.获取用户名
                var usernameText = this.value;
                $.getJSON("${basePath}user/ajaxExistsUsername", "username="+usernameText, function (data) {
                    if(data.existsUsername){
                        $("span.errorMsg").text("用户名已存在！");
                    }else {

                    }
                });
            });


            //给注册按钮绑定单击事件
            $("#sub_btn").click(function () {
                //验证用户名：必须由字母数字和下划线组成，长度5到12位
                //1.获取输入框内容
                var usernameText = $("#username").val();
                //2.创建正则表达式
                var usernamePatt = /^\w{5,12}$/;
                //3.test方法验证是否符合正则表达式
                if (!usernamePatt.test(usernameText)) {
                    //4.不符合提示用户
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }

                //验证密码：必须由字母数字和下划线组成，长度5到12位
                //1.获取输入框内容
                var passwordText = $("#password").val();
                //2.创建正则表达式
                var passwordPatt = /^\w{5,12}$/;
                //3.test方法验证是否符合正则表达式
                if (!passwordPatt.test(passwordText)) {
                    //4.不符合提示用户
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }

                //确认密码：和密码相同
                //1.获取输入框内容
                var repwdText = $("#repwd").val();
                //2.和密码比较
                if (passwordText != repwdText) {
                    //3.不同提示用户
                    $("span.errorMsg").text("两次密码输入不一致！");
                    return false;
                }

                //邮箱验证:格式是否符合
                //1.获取输入框内容
                var emailText = $("#email").val();
                //2.创建正则表达式
                var emailPatt = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/;
                //3.test方法验证是否符合正则表达式
                if (!emailPatt.test(emailText)) {
                    //4.不符合提示用户
                    $("span.errorMsg").text("邮箱不合法！");
                    return false;
                }

                //验证码:
                //1.获取输入框内容
                var codeText = $("#code").val();

                //2.检查是否输入
                if (codeText == null || codeText == "") {
                    //4.不符合提示用户
                    $("span.errorMsg").text("请输入验证码！");
                    return false;
                }
                //清除提示信息
                $("span.errorMsg").text("");

            });
        });


    </script>

    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="user/register" method="post">
                        <%--设置表单隐藏域用来区分同种请求的不同表单--%>
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 120px;" name="code" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px;width:100px;height:40px">
                        <script type="text/javascript">
                            <%--点击验证码图片更换,在请求地址后加时间戳参数是为了使每次请求的址参数
                            不一致避开浏览器因为请求地址与之前地址一致时使用上次缓存的资源--%>
                            $(function () {
                                $("#code_img").click(function () {
                                    this.src = "${basePath}kaptcha.jpg?d=" + new Date();
                                });
                            });
                        </script>
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含底部页脚信息 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>