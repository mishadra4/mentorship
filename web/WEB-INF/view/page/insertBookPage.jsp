<%--
  Created by IntelliJ IDEA.
  User: Yoga
  Date: 17.11.2018
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/insertBook" method="post">
        Title: <input type="text" name="title"/>
        <br/><br/>
        Pages: <input type="number" name="pages"/>
        <br/><br/>
        <input type="submit" value="Insert"/>
    </form>
    <a href="/editBook"><button type="button">Edit books</button></a>
</body>

</html>
