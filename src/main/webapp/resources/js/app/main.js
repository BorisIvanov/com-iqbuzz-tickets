var refreshTimeoutDelay = 60 * 1000;
var costSum = 0;
var reservationInfo = null;


jQuery.ajaxSetup({error: unlockUI});
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
    $(".app-row button, #sale, #reservation, #person, #find-reservation").prop("disabled", true);
    $(".nav-pills li").off();
}

function unlockUI() {
    $(".app-row button, #sale, #reservation, #person, #find-reservation").prop("disabled", false);
    $(".nav-pills li").on("click", seanceClick);
}

$(document).ready(function () {
    $(".app-row .btn-default").on("click", seatClick);
    $(".nav-pills li").on("click", seanceClick);
    $("#sale").on("click", saleClick);
    $("#reservation").on("click", reservationClick);
    $("#find-reservation").on("click", reservationFind);
    seanceGet();
    setInterval(seanceGet, refreshTimeoutDelay);
});

function seanceClick() {
    $(".nav-pills li").removeClass("active");
    $(this).addClass("active");
    seanceGet();
}

function seanceGet() {
    lockUI();
    $(".app-row button").prop("disabled", true).removeClass("btn-primary btn-success btn-info");
    $.get(res.url.ticket.list + $(".nav-pills li.active").text().trim(), function (response) {
        for (var i = 0; i < response.length; i++) {
            $("button[data-row='" + response[i].row + "'][data-seat='" + response[i].seat + "']")
                .addClass(response[i].type == 0 ? "btn-success" : "btn-info");
        }
        unlockUI();
    });
}
function costAdd(cost) {
    costSum = costSum + parseFloat(cost);
    $("#costSum").val(costSum);
}
function costReset() {
    costSum = 0;
    $("#costSum").val("");
}
function seatClick() {
    var $this = $(this);
    var status = $this.data("status");
    if (status) {
        if (status == "selected") {
            $this.data("status", "").removeClass("btn-primary");
            costAdd($this.data("cost"));
        }
    } else {
        $this.data("status", "selected").addClass("btn-primary");
        costAdd($this.data("cost"));
    }
}

function getSeatSelected() {
    var seats = $(".app-row .btn-primary");
    if (seats.length == 0) {
        return null;
    }
    var seance = $(".nav-pills li.active").text().trim();
    var data = {ticketList: []};
    $.each(seats, function () {
        var $this = $(this);
        data.ticketList.push({
            "seat": $this.data("seat"),
            "row": $this.data("row"),
            "seance": seance,
            "cost": $this.data("cost")
        });
    });
    return data;
}
function saleClick() {
    var data = getSeatSelected();
    if (data) {
        $.postJSON(res.url.ticket.sale, data.ticketList, function () {
            $(".app-row .btn-primary").removeClass("btn-primary").addClass("btn-success");
            costReset();
        });
    }
}

function getPerson() {
    var val = $("#person").val().trim();
    if (val) {
        $(".person-group").removeClass("has-error");
    } else {
        $(".person-group").addClass("has-error");
    }
    return val;
}

function reservationClick() {
    var val = getPerson();
    if (val) {
        var data = getSeatSelected();
        if (data) {
            data.person = val;
            $.postJSON(res.url.ticket.reservation, data, function () {
                $(".app-row .btn-primary").removeClass("btn-primary").addClass("btn-info");
                $("#person").val("");
                costReset();
            });
        }
    }
}

function reservationFind() {
    var person = getPerson();
    if (person) {
        lockUI();
        $.get(res.url.ticket.reservation + person, function (response) {
            reservationInfo = {person: person, seances: []};
            var seanceItem = {seance: "", cost: 0, tickets: []};
            for (var i = 0; i < response.length; i++) {
                var item = response[i];
                if (seanceItem.seance == item.seance) {
                    seanceItem.cost = seanceItem.cost + item.cost;
                    seanceItem.tickets.push({seat: item.seat, row: item.row});
                } else {
                    seanceItem = {seance: item.seance, cost: 0, tickets: [{seat: item.seat, row: item.row}]};
                    reservationInfo.seances.push(seanceItem);
                }
            }
            reservationTemplateUpdate();
            unlockUI();
        });
    }
}
function reservationTemplateUpdate() {
    var template = Handlebars.compile($("#reservation-info-template").html());
    $("#reservation-info").text("").append(template(reservationInfo));
    $("#reservation-sale button").on("click", reservationSale);
}

function reservationSale() {
    var seance = $(this).data("seance");
    var person = $(this).data("person");
    $.postJSON(res.url.ticket.reservation_sale, {person: person, seance: seance}, function () {
        var seances = reservationInfo.seances;
        for (var i = 0; i < seances.length; i++) {
            if (seances[i].seance == seance) {
                seances.splice(i, 1);
                break;
            }
        }
        reservationTemplateUpdate();
        seanceGet();
    });
}