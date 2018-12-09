<%--
  Created by IntelliJ IDEA.
  User: serhii
  Date: 07.12.18
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Single product page</title>
    <style>
        .beauty {
            color: green;
            margin-left: 50px;
            border-left: 10px;
        }
        .display-4 {
            color: brown;
        }
        .prettyButton {
            color: lawngreen;
        }
    </style>
</head>
<body>
<div class="beauty">
    <h1 class="display-4" align="center">Product</h1>
    <div>
    <p>ID: ${product.id}</p>
    <p>Name: ${product.name}</p>
    <p>Price: ${product.price}</p>
    </div>
    <a class = "prettyButton" href="/products/add" role="button">Add new product</a>
    <a class = "prettyButton" href="/products/list" role="button">List all products</a>

</div>
</body>
</html>
