$(function() {
    var checkbox = $("<input type='checkbox' id='checkTask'>");

    // FIXME : to uncomment for step 1
//    $(".task .title").live('click', function() {
//        var task = $(this).closest('.task');
//        if (task.hasClass("edit")) {
//            task.removeClass("edit");
//            checkbox.detach();
//        } else {
//            $(".task").removeClass("edit");
//            task.addClass("edit");
//            // FIXME : uncomment following lines for step 3
//            // checkbox.attr('checked', task.hasClass('done'));
//
//            var node = document.getElementById('checkTask');
//            if(node === null){
//                checkbox.appendTo(task.find(".action"));
//            // FIXME : remove this else section for step 4
//            } else {
//                document.getElementById(task.attr("id")).getElementsByTagName('td')[0].appendChild(node);
//            }
//        }
//    });


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