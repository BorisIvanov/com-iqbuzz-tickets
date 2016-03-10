<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.getServerName()}:${req.getServerPort()}/${req.getContextPath()}"/>
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
        <div class="page-header">
            <h1>Sale of tickets
                <small>and ticket reservation</small>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Seances</h3>
            </div>
            <div class="panel-body">
                <ul class="nav nav-pills">
                    <c:forEach items="${seances}" var="seance">
                        <li role="presentation" class="active">
                            <a href="#">${seance}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
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

    <div class="row">
        <div class="alert alert-success" role="alert"></div>
        <div class="alert alert-info" role="alert"></div>
        <div class="alert alert-warning" role="alert"></div>
        <div class="alert alert-danger" role="alert"></div>
    </div>

</div>
<script src="//<c:out value='${baseURL}' />/resources/js/lib/jquery-2.2.1.min.js"></script>
<script src="//<c:out value='${baseURL}' />/resources/bootstrap-3.3.6/js/bootstrap.min.js"></script>
<script src="//<c:out value='${baseURL}' />/resources/js/app/main.js"></script>
</body>
</html>
