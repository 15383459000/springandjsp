<%@ page import="java.util.List" %>
<%@ page import="com.xitianfo.entity.Image" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>
<form action="http://localhost:8080/testCode" method="get" enctype="multipart/form-data">
    手机号<input type="text" name="number">
    验证码<input type="text" name="code">
    <input type="submit" value="test">
</form>
</body>