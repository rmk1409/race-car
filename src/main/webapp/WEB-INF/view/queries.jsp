<%@include file="common/header.jspf" %>
<%@ page import="java.net.URLDecoder" %>
<h4>Add new query</h4>
<form:form id="add-new-query-form" class="bg-dark text-white" action="/queries" method="post" modelAttribute="query">
    <div class="row">
        <input type="hidden" id="query-id" name="id" value="0"/>
        <fieldset class="form-group col">
            <label for="inputName">Name</label>
            <form:input type="text" class="form-control" id="inputName" name="name"
                        placeholder="Input short name for query" path="name"/>
            <small><form:errors path="name" cssClass="error-msg"/></small>
        </fieldset>
        <fieldset class="form-group col">
            <label for="inputQuery">Query</label>
            <form:input type="text" class="form-control" id="inputQuery" name="href"
                        placeholder="Copy and past your search query" path="href"/>
            <small><form:errors path="href" cssClass="error-msg"/></small>
        </fieldset>
    </div>
    <fieldset class="form-group">
        <label for="inputDescription">Description</label>
        <form:textarea class="form-control" id="inputDescription" name="description"
                       placeholder="Write a few words about this query" path="description"/>
        <small><form:errors path="description" cssClass="error-msg"/></small>
    </fieldset>
    <button id="add-new-query-button" type="submit" class="btn btn-success">Add query</button>
    <button id="save-query-button" type="submit" class="btn btn-success">Save</button>
    <button id="cancel-save-query-button" type="reset" class="btn btn-success">Cancel</button>
</form:form>
<c:if test="${queries.size()>0}">
    <table class="table table-striped table-dark table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Created</th>
            <th>Updated</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${queries}" var="query">
            <tr>
                <td hidden>${query.id}</td>
                <td hidden>${query.href}</td>
                <td>${query.name}</td>
                <td>${query.description}</td>
                <td><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${query.createdDate}"/></td>
                <td><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${query.updatedDate}"/></td>
                <td>
                    <form action="/find" class="look-up-form">
                        <input name="query" type="hidden" value="${URLDecoder.decode(query.href, 'UTF-8')}">
                        <button type="submit" class="btn btn-success btn-sm">Look up</button>
                    </form>
                    |
                    <button class="edit-button btn btn-primary btn-sm">Edit</button>
                    |
                    <a href="/delete_query/${query.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<%@include file="common/footer.jspf" %>
<script src="js/query-handler.js"></script>