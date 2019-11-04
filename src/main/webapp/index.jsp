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
    <script type="application/javascript" src="js/jquery.js"></script>

</head>
<body style="padding: 20%">
<form  id="uploadForm" enctype="multipart/form-data">
    <label>
        上传图片：<input  type="file" name="imgFile"/>
    </label><br>
    <br>
    <label>
        人物姓名：<input type="text" name="starName" >
    </label><br>
    <br>
    <label>
        人物分组：
        <select name="starGroup">
            <option value="star_woman_asia">亚洲女明星</option>
            <option value="star_man_asia">亚洲男明星</option>
            <option value="start_woman_white" >白人女明星</option>
            <option value="start_man_white" >白人男明星</option>
            <option value="star_woman_black">黑人女明星</option>
            <option value="star_man_black">黑人男明星</option>
        </select>
    </label><br>
    <br>
    <button id="upload" type="button">上传</button>
</form>
</body>
<script>
    $(function () {
        $("#upload").click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/star/addStar',
                type: 'POST',
                cache: false,
                data: new FormData($('#uploadForm')[0]),
                processData: false,
                contentType: false
            }).done(function (res) {
                alert(res);
            }).fail(function (res) {
            });
        });
    })
</script>
</html>

