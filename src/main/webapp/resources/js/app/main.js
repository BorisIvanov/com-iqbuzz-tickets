/*jQuery.ajaxSetup({
    traditional: true,
    scriptCharset: "utf-8",
    dataType: "json",
    type: "POST",
    cache: false,
    error: function (XMLHttpRequest, textStatus, errorThrown) {
    }
});*/

$.postJSON = function (url, data, callback) {
    return jQuery.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        success: callback,
        error: unlockUI
    });
};

$(document).ready(function () {
    $(".app-row .btn-default").on("click", seatClick);
    $(".nav-pills li").on("click", seanceClick);
    $("#sale").on("click", saleClick);
    $("#reservation").on("click", reservationClick);
    seanceGet();
});

function seanceClick() {
    $(".nav-pills li").removeClass("active");
    $(".app-row button").prop("disabled", true).removeClass("btn-primary, btn-success");
    $(this).addClass("active");
    seanceGet();
}

function seanceGet() {
    lockUI();
    $.get(res.url.ticket.list + $(".nav-pills li.active").text().trim(), function (response) {
        for (var i = 0; i < response.length; i++) {
            $("button[data-row='" + response[i].row + "'][data-seat='" + response[i].seat + "']").addClass("btn-success");
        }
        unlockUI();
    });
}

function seatClick(e) {
    var $this = $(this);
    var seat = $this.data("seat");
    var row = $this.data("row");

    var status = $this.data("status");
    if (status) {
        if (status == "selected") {
            $this.data("status", "").removeClass("btn-primary");
        } else {

        }
    } else {
        $this.data("status", "selected").addClass("btn-primary");
    }
    //console.log(seat, row);
}
function lockUI() {
    $(".app-row button, #sale, #reservation").prop("disabled", true);
    $(".nav-pills li").off();
}

function unlockUI() {
    $(".app-row button, #sale, #reservation").prop("disabled", false);
    $(".nav-pills li").on("click", seanceClick);
}



function saleClick() {
    var seats = $(".app-row .btn-primary");
    if (seats.length == 0) {
        return;
    }

    lockUI();
    var seance = $(".nav-pills li.active").text().trim();

    var data = [];
    $.each(seats, function () {
        var $this = $(this);
        data.push({
            "seat": $this.data("seat"),
            "row": $this.data("row"),
            "seance": seance
        });
    });

    $.postJSON(res.url.ticket.sale, data, function (response) {
        $(".app-row .btn-primary").removeClass("btn-primary").addClass("btn-success");
        unlockUI();
    });
}

function reservationClick() {

}