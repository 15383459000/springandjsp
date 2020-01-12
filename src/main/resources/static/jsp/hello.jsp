<%@ page import="java.util.List" %>
<%@ page import="com.xitianfo.entity.Image" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>
<form action="http://localhost:8080/getMessage" method="post" enctype="multipart/form-data">
    手机号<input type="text" name="number">
    <input type="submit" value="test">
</form>
</body>