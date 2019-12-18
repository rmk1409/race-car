<%@include file="common/header.jspf"%>
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
<%@include file="common/footer.jspf"%>