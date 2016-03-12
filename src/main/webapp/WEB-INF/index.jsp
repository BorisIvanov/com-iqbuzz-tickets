<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.getServerName()}:${req.getServerPort()}${req.getContextPath()}"/>
<c:set var="protocol" value="${req.protocol}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sale of tickets</title>
    <link href="//<c:out value='${baseURL}' />/resources/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//<c:out value='${baseURL}' />/resources/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h1>Sale of tickets
                    <small>and ticket reservation</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Seances</h3>
                </div>
                <div class="panel-body">
                    <ul class="nav nav-pills">
                        <c:set var="first" value="0"/>
                        <c:forEach items="${seances}" var="seance">
                            <c:if test="${first == 0}">
                                <li class="active">
                                    <a href="#">${seance}</a>
                                </li>
                            </c:if>
                            <c:if test="${first > 0}">
                                <li>
                                    <a href="#">${seance}</a>
                                </li>
                            </c:if>
                            <c:set var="first" value="1"/>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <button type="button" class="btn btn-success" id="sale">Sale</button>
                </div>
            </div>
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="form-group">
                        <div class="col-sm-5">
                            <div class="input-group person-group">
                                <span class="input-group-addon">Person</span>
                                <input type="text" class="form-control" id="person">
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <button type="button" class="btn btn-info" id="reservation">Reservation</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <ul class="list-group">
                <c:forEach var="i" begin="1" end="${rows}">
                    <li class="list-group-item app-row">

                        <div class="btn-group app-left">
                            <p class="text-muted"><c:out value="${i}"/></p>
                        </div>

                        <div class="btn-group">
                            <c:forEach var="j" begin="1" end="${seats}">
                                <button type="button" class="btn btn-default"
                                        data-row="<c:out value="${i}"/>"
                                        data-seat="<c:out value="${j}"/>">
                                    <c:out value="${j}"/>
                                </button>
                            </c:forEach>
                        </div>

                        <div class="btn-group app-right">
                            <p class="text-muted"><c:out value="${i}"/></p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible fade in" role="alert">
                    <h4>Error!</h4>
                    <p></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
    var res = {
        url: {
            ticket: {
                list: "//<c:out value='${baseURL}' />/ticket/list/",
                sale: "//<c:out value='${baseURL}' />/ticket/sale/",
                reservation: "//<c:out value='${baseURL}' />/ticket/reservation/"
            }
        }
    };
</script>
<script src="//<c:out value='${baseURL}' />/resources/js/lib/jquery-2.2.1.min.js"></script>
<script src="//<c:out value='${baseURL}' />/resources/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<script src="//<c:out value='${baseURL}' />/resources/js/app/main.js"></script>
</body>
</html>
