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

        input {
            font-family: Helvetica;
            background-color: darkseagreen;
        }

        a, button {
            font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>Add new product:</h1></div>
<form method="POST" name="product" action="/products">
    <div align="center">
        <input id="productName" name="name" type="text" placeholder="Product name"/>
        <br>
        <input id="productPrice" name="price" type="number" placeholder="Product price"/>
        <br>
        <input type=submit value="Save"/>
    </div>
    <div align="center">
        <a href="/products/list" role="button">See all products</a>
    </div>
</form>
</body>
</html>