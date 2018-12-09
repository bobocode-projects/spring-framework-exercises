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
    <h1>Product</h1>
    <div>
    <p>ID: ${product.id}</p>
    <p>Name: ${product.name}</p>
    <p>Price: ${product.price}</p>
    </div>
    <div>
    <a class = "prettyButton" href="/products/add" role="button">Add new product</a>
    <a class = "prettyButton" href="/products/list" role="button">List all products</a>
    </div>
</div>
</body>
</html>
