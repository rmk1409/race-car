<%@include file="common/header.jspf" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<main class="container">
    <!--${queries}-->
    <h4>Add new query</h4>
    <form id="add-new-query-form" class="bg-dark text-white" action="/queries" method="post">
        <div class="row">
            <input type="hidden" id="query-id" name="id" value="0"/>
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
        <button id="add-new-query-button" type="submit" class="btn btn-success">Add query</button>
        <button id="save-query-button" type="submit" class="btn btn-success">Save</button>
        <button id="cancel-save-query-button" type="reset" class="btn btn-success">Cancel</button>
    </form>
    <c:if test="${queries.size()>0}">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                    <%--                <th hidden></th>--%>
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
                            <%--                        <a href="${pageContext.request.contextPath}/find?query=${query.href}"--%>
                            <%--                           class="btn btn-success btn-sm">Look up</a>--%>
                            <%--                        <c:url var="lookUrl" value="/find?query=${query.href}"/>--%>

                        <form action="/find" style="display: inline">
                            <input name="query" type="hidden" value="${URLDecoder.decode(query.href, 'UTF-8')}">
                            <button type="submit" class="btn btn-success btn-sm">Look up</button>
                        </form>
                            <%--                        <a href="${pageContext.request.contextPath}/find?query=${URLDecoder.decode(query.href, 'UTF-8')}"--%>
                            <%--                           class="btn btn-success btn-sm">Look up</a>--%>
                        |
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
<script>
    $(document).ready(function () {
        let add = $("#add-new-query-button");
        let save = $("#save-query-button");
        let cancel = $("#cancel-save-query-button");
        let form = $("#add-new-query-form");
        let queryId = $("#query-id");
        let inputName = $("#inputName");
        let inputQuery = $("#inputQuery");
        let inputDescription = $("#inputDescription");

        $(".edit-button").click(function () {
            changeUIToEditForm();
            fillEditForm(this);
        });

        cancel.click(function () {
            changeUIToAddForm();
        });

        // save.click(function () {
        //     // Todo add logic
        //     changeUIToAddForm();
        //     $.put('/queries', {
        //         id: queryId.text(),
        //         name: inputName.text(),
        //         query: inputQuery.text(),
        //         description: inputDescription.text()
        //     }, function (response) {
        //         alert("successful");
        //     });
        // });

        function fillEditForm(button) {
            let row = $(button).parent().parent();
            queryId.val(row.children("td:first").text());
            inputName.val(row.children("td:nth-child(3)").text());
            inputQuery.val(decodeURIComponent(row.children("td:nth-child(2)").text()));
            // inputQuery.trigger("reset");
            inputDescription.val(row.children("td:nth-child(4)").text());
        }

        function changeUIToEditForm() {
            $("html, body").animate({scrollTop: 0}, "slow");
            add.attr("disabled", true);
            add.slideUp(function () {
                save.slideDown();
                cancel.slideDown();
                save.removeAttr("disabled");
                cancel.removeAttr("disabled");
                form.addClass("highlight");
            });
        }

        function changeUIToAddForm() {
            form.trigger("reset");
            save.attr("disabled", true);
            cancel.attr("disabled", true);
            add.slideDown(function () {
                save.slideUp();
                cancel.slideUp();
                form.removeClass("highlight");
                add.removeAttr("disabled");
            });
        }
    });
</script>