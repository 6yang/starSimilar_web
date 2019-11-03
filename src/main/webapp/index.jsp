<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/file/upload" method="post"  enctype="multipart/form-data">
    <label>用户名：</label><input type="text" name="title"><br>
       <label>上传头像：</label>
    <input type="file" name="imgFile"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>

