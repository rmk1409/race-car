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
    <link rel="icon" href="img/racing-car.png"
          type="image/x-icon">
    <title>Race or die</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>
</head>
<body class="bg-dark text-white">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/">Race or die</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/cars">All cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/queries">Query</a>
                </li>
            </ul>
            <form action="/find" method="get" class="form-inline my-2 my-lg-0">
                <input name="query" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Find cars</button>
            </form>
        </div>
    </nav>
</header>
<main class="container">
    <h1 class="mt-5">We'll find your race car.</h1>
    <p>
        Put your query in search field.
    </p>
    <c:if test="${cars.size()>0}">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                <th>Found ${cars.size()} cars.</th>
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
            </tbody>
        </table>
    </c:if>
</main>
<footer class="page-footer font-small fixed-bottom bg-dark text-white <c:choose><c:when test='${cars.size()>3}'>footer-with-result</c:when><c:otherwise>footer-for-empty</c:otherwise></c:choose>">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <a href="https://axamit.com"> Axamit.com</a>
    </div>
</footer>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
</body>
</html>
