<%@include file="common/header.jspf" %>
<main class="container">
    <!--${queries}-->
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
        <button id="add-new-query-button" type="submit" class="btn btn-success">Add query</button>
        <button id="save-query-button" type="button" class="btn btn-success">Save</button>
        <button id="cancel-save-query-button" type="reset" class="btn btn-success">Cancel</button>
    </form>
    <c:if test="${queries.size()>0}">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Description</th>
                <th>Created</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${queries}" var="query">
                <tr>
                    <td>${query.id}</td>
                    <td>${query.name}</td>
                    <td>${query.description}</td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${query.createdDate}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/find?query=${query.href}"
                           class="btn btn-success btn-sm">Look up</a>
                            <%-- /update_query/${query.id}--%>
                        | <button class="edit-button btn btn-primary btn-sm">Edit</button>
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
        let form = $("#add-new-query");

        $(".edit-button").click(function () {
            $("html, body").animate({scrollTop: 0}, "slow");
            add.attr("disabled", true);
            add.slideUp(function () {
                save.slideDown();
                cancel.slideDown();
                save.removeAttr("disabled");
                cancel.removeAttr("disabled");
                form.addClass("highlight");
            });
        });

        cancel.click(function () {
            form.trigger("reset");
            changeUI();
        });

        save.click(function () {
            let row = $(this).parent().parent();
            let firstTd = row.children("td").text();
            alert(firstTd);
            changeUI();
        });

        function changeUI() {
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