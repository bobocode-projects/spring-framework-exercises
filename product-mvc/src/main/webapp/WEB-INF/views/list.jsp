<%--
  Created by IntelliJ IDEA.
  User: serhii
  Date: 08.12.18
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of products</title>
    <style>

        h1 {
            font-family: "Helvetica", helvetica;
            color: darkslategrey;
        }

        body {
            background-color: gainsboro;
            font-family: 'DejaVu Serif', Georgia, "Times New Roman", Times, serif;
        }

        table, tr {
            background-color: darkseagreen;
            border: 2px solid grey;
            border-collapse: collapse;
            text-align: left;
            vertical-align: center;
        }

        td, th {
            text-align: left;
            vertical-align: center;
            padding: 15px;
            font-family: Helvetica;
        }

        tr:hover {
            cursor: crosshair;
            background-color: lightsteelblue;
        }

        a, button {
            font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>List of products</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tbody>
            <tr>
                <td><a href="/products/${product.id}">${product.id}</a></td>
                <td>${product.name}</td>
                <td>${product.price}</td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<div align="center">
    <br>
    <a href="/products/add" role=button>Add new product</a>
    <br>
</div>
</body>
</html>
