<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/01/2023
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--thêm JSTL vào JSP để tiện sử dụng--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Product</h1>
<a href="/products?action=create">
    <button>Create new product</button>
</a>
<a href="/categories">
    <button>List category</button>
</a>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Category</th>
        <th colspan="2">Action</th>
    </tr>
    <%--    dùng c:forEach để render dữ liệu của list--%>
    <c:forEach var="p" items="${products}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td>${p.category.name}</td>
            <td><a href="/products?action=update&id=${p.id}">
                <button>Update</button>
            </a></td>
            <td><a href="/products?action=delete&id=${p.id}">
                <button>Delete</button>
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
