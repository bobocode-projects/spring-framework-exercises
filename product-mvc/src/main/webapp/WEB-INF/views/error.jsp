<%--
  Created by IntelliJ IDEA.
  User: serhii
  Date: 09.12.18
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <style>
        h1, h2, h3, h4 {
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
<h1>Oh, crap... Something went wrong...</h1></body>
<h2>Maybe information that you want to GET doesn't exist.</h2>
<div align="center">
<a href="/products/add" role="button">Add new product</a>
<br>
<a href="/products/list" role = "button">List all products</a>
</div>
    </body>
</html>
