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

    function fillEditForm(button) {
        let row = $(button).parent().parent();
        queryId.val(row.children("td:first").text());
        inputName.val(row.children("td:nth-child(3)").text());
        inputQuery.val(decodeURIComponent(row.children("td:nth-child(2)").text()));
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