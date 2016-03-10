$(document).ready(function(){
    $(".app-row .btn-default").on("click", seatClick);
    $(".nav-pills li").on("click", seanceClick);
});

function seanceClick(){
    var $this = $(this);
}

function seatClick(e){
    var $this = $(this);
    var seat = $this.data("seat");
    var row = $this.data("row");
    console.log(seat, row);
}