<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 02/01/2023
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--form tạo mới sản phẩm, action tương ứng không kèm id--%>
<h1>Create form</h1>
<form action="/categories?action=create" method="post">
    <table>
        <tr>
            <td><label for="name">Name:</label></td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Create</button>
                <a href="/categories" style="text-decoration: none">
                    <button type="button">Back to home</button>
                </a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
