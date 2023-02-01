<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/01/2023
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>HelloWorld</h1>
<h2>${name}</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
        </tr>
    </c:forEach>
</table>
<form action="demo" method="post">
    <label>Id: <input type="text" name="id"></label>
    <label>Name: <input type="text" name="name"></label>
    <label>Price: <input type="text" name="price"></label>
    <label>Quantity: <input type="text" name="quantity"></label>
    <button type="submit">Save</button>
</form>

<a href="hello-servlet">
    <button>Back</button>
</a>
</body>
</html>
