<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0054)https://v4-alpha.getbootstrap.com/examples/offcanvas/? -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Library</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/offcanvas.css"/>" rel="stylesheet" />

    <!--AmgularJs css library-->
    <link href="<c:url value="/resources/css/loading-bar.min.css"/>" rel="stylesheet" />
    <!--Angular Libraries-->
    <script src="<c:url value="/resources/js/angular/angular.min.js"/>"></script>
    <script src="<c:url value="/resources/js/angular/angular-sanitize.js"/>"></script>
    <script src="<c:url value="/resources/js/angular/angular-route.min.js"/>"></script>
    <script src="<c:url value="/resources/js/angular/angular-resource.js"/>"></script>
    <script src="<c:url value="/resources/js/angular/loading-bar.min.js"/>"></script>

</head>

<body ng-app="mainLibraryApp" ng-controller="mainLibraryController" >

<nav class="navbar navbar-toggleable-md fixed-top navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">

    </div>
</nav>

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-12 col-md-9" >

            <div class="answerMessages" dynamic="html2"></div>
            <div class="bookBlock" dynamic="html"></div>

        </div><!--/span-->

        <div class="col-6 col-md-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Интерфейс</a>
                <a href="#" ng-click="addBookToLibrary()"  class="list-group-item">Добавить книгу</a>
                <a href="#" ng-click="showBooksLibrary()"  class="list-group-item">Просмотр библитеки</a>
            </div>
        </div><!--/span-->
    </div><!--/row-->

    <hr>

    <footer>
        <p>© HiendSys Company 2017</p>
    </footer>

</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/resources/js/jquery-3.1.1.slim.min.js"/>"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<c:url value="/resources/js/tether.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js"/>"></script>
<script src="<c:url value="/resources/js/offcanvas.js"/>"></script>
<script src="<c:url value="/resources/js/index.js"/>"></script>

</body></html>