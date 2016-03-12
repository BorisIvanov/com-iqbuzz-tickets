$.postJSON = function (url, data, callback) {
    lockUI();
    return jQuery.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        success: function (response) {
            if (response.error) {
                $(".modal-body p").text(response.error);
                $("#modal").modal("show");
            } else {
                callback(response);
            }
            unlockUI();
        },
        error: unlockUI
    });
};

function lockUI() {
    $(".app-row button, #sale, #reservation, #person").prop("disabled", true);
    $(".nav-pills li").off();
}

function unlockUI() {
    $(".app-row button, #sale, #reservation, #person").prop("disabled", false);
    $(".nav-pills li").on("click", seanceClick);
}

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
}

function getSeatSelected() {
    var seats = $(".app-row .btn-primary");
    if (seats.length == 0) {
        return null;
    }
    var seance = $(".nav-pills li.active").text().trim();
    var data = {tickets:[]};
    $.each(seats, function () {
        var $this = $(this);
        data.tickets.push({
            "seat": $this.data("seat"),
            "row": $this.data("row"),
            "seance": seance
        });
    });
    return data;
}
function saleClick() {
    var data = getSeatSelected();
    if (data) {
        $.postJSON(res.url.ticket.sale, data.tickets, function () {
            $(".app-row .btn-primary").removeClass("btn-primary").addClass("btn-success");
        });
    }
}

function reservationClick() {
    var val = $("#person").val();
    if (val) {
        $(".person-group").removeClass("has-error");
        var data = getSeatSelected();
        if (data) {
            data.person = val;
            $.postJSON(res.url.ticket.reservation, data, function () {
                $(".app-row .btn-primary").removeClass("btn-primary").addClass("btn-info");
            });
        }
    } else {
        $(".person-group").addClass("has-error");
    }
}