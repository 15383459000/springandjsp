<%@ page import="java.util.List" %>
<%@ page import="com.xitianfo.entity.Image" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>
<form action="http://106.14.212.90:8080/xitianfo-2.1.6.RELEASE/getMessage" method="get" enctype="multipart/form-data">
    <div id="box" style="height:300px; width:400px; margin:auto; text-align:center">
        <h1>用户登录</h1>
        手机号：<input type="text"  name="number"/><br><br>
        <input type="submit" value="登录"/>
        <input type="reset" value="取消"/>
    </div>
</form>
</body>