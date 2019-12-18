<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 17.12.2019
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Race or die</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
</head>
<body>
<div class="container">
    Hi, there!
    <form action="/find">
        Enter query <input type="text" name="query"/><br>
        <input type="submit">
    </form>
    <h4>Found ${cars.size()} cars.</h4>
    <table class="table table-striped table-dark table-hover">
        <thead>
        <tr>
            <th></th>
            <th>Price</th>
            <th>Year</th>
            <th></th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cars}" var="car">
            <tr>
                <td><img class="car-img" src="${car.imgLink}"></td>
                <td>${car.dollarPrice}</td>
                <td>${car.year}</td>
                <td><a href="${car.link}">${car.name}</a></td>
                <td>${car.description}</td>
            </tr>
        </c:forEach>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        </tbody>
    </table>
</div>
</body>
</html>
