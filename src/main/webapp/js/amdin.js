$(function () {
    var tr = $("#tBody tr");
    tr.mouseover(function () {
        $(this).css("background-color", "#AEF2E5");
        $(this).children("td").css("background-color", "#AEF2E5");
    }).mouseout(function () {
        $(this).css("background-color", "#FFFFFF");
        $(this).children("td").css("background-color", "#FFFFFF");
    });
});