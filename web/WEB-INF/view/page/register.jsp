<%--
  Created by IntelliJ IDEA.
  User: Yoga
  Date: 01.12.2018
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
    <br/><br/>
    <form action="/register" method="post">
        Username: <input type="text" name="username"/>
        <br/><br/>
        Password: <input type="text" name="password"/>
        <br/><br/>
        <input type="submit" value="Register"/>
    </form>
    <form action="/login" method="get">
        <input type="submit" value="Login"/>
    </form>
</body>
</html>