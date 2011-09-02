$(function() {
    var checkbox = $("<input type='checkbox' id='checkTask'>");

    $(".task .title").live('click', function() {
        var task = $(this).closest('.task');
        if (task.hasClass("edit")) {
            task.removeClass("edit");
            checkbox.detach();
        } else {
            $(".task").removeClass("edit");
            task.addClass("edit");
            checkbox.appendTo(task.find(".action"));
        }
    });

    $("#newTask").click(function () {
        var title = $("#newTaskTitle").val();
        $('<tr class="task" id="task' + ($('.todos tbody tr').size() + 1) + '">' +
            '<td class="action"></td><td class="title">' + title + '</td></tr>')
            .appendTo(".todos tbody");
        $("#newTaskTitle").val('');
        $("#success").fadeIn().delay(1500).fadeOut();

    });


    $("#checkTask").live('click', function(e) {
        var task = $(this).closest('.task');
        if (task.hasClass('done')) {
            task.removeClass('done');
        } else {
            task.addClass('done');
        }
    });

    $("#success").hide();
    $("#success .close").click(function() {
        $("#success").fadeOut();
    });
});