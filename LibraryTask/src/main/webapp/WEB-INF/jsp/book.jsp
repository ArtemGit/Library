<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
            <h3 ng-bind="book.name"></h3>
            <p>
            <span ng-bind="book.author"></span>
                <span ng-bind="book.year"></span></p>
            <img src="<c:url value="/resources/img/book.png"/>" alt="картинка">
            <div class="caption">
                <p ng-bind="book.description"></p>
                <a ng-click="deleteBook(book.idbook)" href="#" class="btn btn-primary" role="button">Удалить</a>
                <a ng-click="download(book.idbook)" class="btn btn-default" role="button" target="_blank">Скачать</a>
                <br><br>
                <a ng-click="watchBook(book.idbook)" href="#" class="btn btn-primary" role="button">Просмотреть</a>
                <a ng-click="edit(book)" class="btn btn-default" role="button">Обновить</a>
                </div>
        </div>
    </div>
    <p><p>
</div>
<div class="file" dynamic="fileHtml"></div>