<%--
  Created by IntelliJ IDEA.
  User: roman.pogorelov
  Date: 18.12.2019
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Your Queries</title>
</head>
<body>
<h4>Add new query</h4>
<form action="/queries" method="post">
    Input query<input type="text" name="query"><br>
    Name<input type="text" name="name"><br>
    Description<textarea type="text" name="description"></textarea><br>
    <input type="submit">
</form>
<c:if test="${queries.size()>0}">
    <table class="table table-striped table-dark table-hover">
        <thead>
        <tr>
            <th>Found ${queries.size()} queries.</th>
            <th>Name</th>
            <th>Description</th>
            <th>Found cars</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${queries}" var="query">
            <tr>
                <td>${query.id}</td>
                <td><a href="${pageContext.request.contextPath}/find?query=${query.link}">${query.name}</a></td>
                <td>${query.description}</td>
                <td>${query.carQuantity}</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
