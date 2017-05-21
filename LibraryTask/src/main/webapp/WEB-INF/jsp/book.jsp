<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    th, td {
        text-align: center;
    }
    .length-m
    {
        width: 170px;
    }
    .m-word-wrap
    {
        word-wrap: break-word;
    }
</style>
<div class="row">
    <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
            <h3 ng-bind="book.name"></h3>
            <img src="<c:url value="{{imageName(book)}}"/>" alt="картинка">
        </div>
    </div>
    <div class="col-sm-6 col-md-8 m-word-wrap">
        <p ng-bind="book.description"></p>
    </div>

</div>
<div class="row">
    <div class="col-md-3">
        <a ng-click="deleteBook(book.idbook)" class="btn btn-primary length-m" role="button">Удалить</a>
    </div>
    <div class="col-md-3">
        <a ng-click="download(book.idbook)"   class="btn btn-default length-m" role="button" target="_blank">Скачать</a>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <a ng-click="watchBook(book.idbook)"  class="btn btn-primary length-m" role="button">Просмотреть контент</a>
    </div>
    <div class="col-md-3">
        <a ng-click="edit(book)"  class="btn btn-default length-m" role="button" target="_blank">Обновить</a>
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        <a  ng-click="uploadImage(book.idbook)"  class="btn btn-primary length-m" role="button">Загрузить обложку</a>
    </div>
    <div class="col-md-3">
        <a  ng-click="upload(book.idbook)" class="btn btn-default length-m" role="button" >Загрузить журнал</a>
    </div>
</div>


</div>
<div class="tablecontainer">
    <table class="table table-hover">
        <thead style="text-align: center;">
        <tr>
            <th>Место издания</th>
            <th>Издательство</th>
            <th>Издаётся с</th>
            <th>Издавался до</th>
            <th>Электронная верссия</br>доступна с</th>
            <th>Электронная верссия</br>доступна до</th>
            <th>ISSN</th>
            <th>Страна</th>
            <th>Язык</th>
            <th>Наличие подписки</th>
        </tr>
        </thead>
        <tbody style="align-content: center">
        <tr>
            <td><span ng-bind="book.publicationPlace"></span></td>
            <td><span ng-bind="book.publishHouse"></span></td>
            <td><span ng-bind="book.publishStartYear"></span></td>
            <td><span ng-bind="book.publishEndYear"></span></td>
            <td><span ng-bind="book.electVerStartYear"></span></td>
            <td><span ng-bind="book.electVerEndYear"></span></td>
            <td><span ng-bind="book.issn"></span></td>
            <td><span ng-bind="book.country"></span></td>
            <td><span ng-bind="book.language"></span></td>
            <td><span ng-bind="book.signed?'Да':'Нет'"></span></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="file" dynamic="fileHtml"></div>