<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: serhii
  Date: 08.12.18
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>
<form method="POST" name="product" action="/products">
    <table>
        <tr>
            <td>
                <input id="productName" name="name" type="text" placeholder="Product name"/>
            </td>
        </tr>
        <tr>
            <td>
                <input id="productPrice" name="price" type="number" placeholder="Product price"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type = submit value="Save"/>
            </td>
        </tr>
    </table>
    <div>
        <a href="/products/list" role="button">See all products</a>
    </div>
</form>
</body>
</html>
