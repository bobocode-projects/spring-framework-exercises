<%--
  Created by IntelliJ IDEA.
  User: serhii
  Date: 09.12.18
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to us...</title>
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
    <h1>Add your first product:</h1>
</div>
<div align="center">
    <a href="/products/add" role="button">Add product</a>
</div>
</body>
</html>
