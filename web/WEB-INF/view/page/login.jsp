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
    <title>Login page</title>
</head>
<body>
    <form action="/login" method="post">
        Username: <input type="text" name="username"/>
        <br/><br/>
        Password: <input type="text" name="password"/>
        <br/><br/>
        <input type="submit" value="Login"/>
    </form>
    <form action="/register" method="get">
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
