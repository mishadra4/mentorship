<%--
  Created by IntelliJ IDEA.
  User: Yoga
  Date: 18.11.2018
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/editBook" method="POST" >
        ID: <input type="number" name="id"/> &nbsp; <input type="submit" value="Get"/><br/>
    </form>
    <form action="/removeBook" method="post">
        ID: <input type="number" name="id"/> &nbsp; <input type="submit" value="Delete"/>
    </form>
    Title: <h5>${book.title}</h5>
    <br/><br/>
    Pages: <h5>${book.pages}</h5>
    <br/><br/>
</body>
</html>
