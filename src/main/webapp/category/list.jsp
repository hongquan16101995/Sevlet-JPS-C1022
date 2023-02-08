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
<h1>List Category</h1>
<a href="/categories?action=create"><button>Create new category</button></a>
<a href="/products"><button>List product</button></a>
<table>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th colspan="2">Action</th>
    </tr>
<%--    dùng c:forEach để render dữ liệu của list--%>
    <c:forEach var="c" items="${categories}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${c.name}</td>
            <td><a href="/categories?action=update&id=${c.id}">
                <button>Update</button>
            </a></td>
            <td><a href="/categories?action=delete&id=${c.id}">
                <button>Delete</button>
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
