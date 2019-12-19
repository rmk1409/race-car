<%@include file="common/header.jspf" %>
<main class="container">
    <h4>Add new query</h4>
    <form id="add-new-query" class="bg-dark text-white" action="/queries" method="post">
        <div class="row">
            <fieldset class="form-group col">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" name="name"
                       placeholder="Input short name for query"/>
            </fieldset>
            <fieldset class="form-group col">
                <label for="inputQuery">Query</label>
                <input type="text" class="form-control" id="inputQuery" name="href"
                       placeholder="Copy and past your search query">
            </fieldset>
        </div>
        <fieldset class="form-group">
            <label for="inputDescription">Description</label>
            <textarea class="form-control" id="inputDescription" name="description"
                      placeholder="Write a few words about this query"></textarea>
        </fieldset>
        <button type="submit" class="btn btn-success">Add query</button>
    </form>
    <c:if test="${queries.size()>0}">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Description</th>
                <th>Cars</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${queries}" var="query">
                <tr>
                    <td>${query.id}</td>
                    <td>${query.name}</td>
                    <td>${query.description}</td>
                    <td><a href="${pageContext.request.contextPath}/find?query=${query.href}"
                           class="btn btn-success btn-sm">Look up</a></td>
                    <td>
                            <%-- /update_query/${query.id}--%>
                        <button class="edit-button btn btn-primary btn-sm">Edit</button>
                        | <a href="/delete_query/${query.id}" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</main>
<%@include file="common/footer.jspf" %>