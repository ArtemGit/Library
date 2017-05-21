<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .form-inline .form-group input {
        width: 170px;
        margin-top: 15px;
    }
    .cl-m
    {
        margin-left: 30px;
    }
</style>
<hr>
<div class="panel-heading"><span class="lead">Поиск</span></div>
<hr>
<div class="formcontainer">
    <form ng-submit="filterStart()">
        <div class="form-inline">
            <div class="form-group cl-m">
                <label class="sr-only" for="issn">Email</label>
                <input type="number" class="form-control" ng-model="issn" id="issn" placeholder="ISSN" min="000000000"
                       max="999999999">
            </div>
            <div class="form-group cl-m">
                <label class="sr-only" for="publicationPlace">Место издания</label>
                <input type="text" class="form-control" id="publicationPlace"
                       placeholder="Место издания" ng-model="publicationPlace">
            </div>
        </div>
        <div class="form-inline">
            <div class="form-group cl-m">
                <label class="sr-only" for="name">Название журнала</label>
                <input type="text" class="form-control" ng-model="name" id="name" placeholder="Название журнала">
            </div>
            <div class="form-group cl-m">
                <label class="sr-only" for="publishHouse">Издательство</label>
                <input type="text" class="form-control" ng-model="publishHouse" id="publishHouse"
                       placeholder="Издательство">
            </div>
        </div>
        <div class="form-inline">
            <div class="form-group cl-m">
                <label class="sr-only" for="publishStartYear">Издаётся с года</label>
                <input type="number" class="form-control" ng-model="publishStartYear" id="publishStartYear"
                       placeholder="Издаётся с года" min="1900" max="2400">
            </div>
            <div class="form-group cl-m">
                <label class="sr-only" for="publishEndYear">Издаётся до года</label>
                <input type="number" class="form-control" id="publishEndYear" ng-model="publishEndYear"
                       placeholder="Издается до года"
                       min="1900" max="2400">
            </div>
        </div>
        <div class="form-inline cl-m">
            <div class="form-group cl-m">
                <label for="signed"><input type="checkbox" id="signed"
                                           ng-click="changeCheckbox()" value="{{signed}}"/>Есть подписка</label>
            </div>
            <div class="form-group cl-m">
                <input type="submit" class="btn btn-primary" value="Поиск"/>
            </div>
        </div>
    </form>
</div>
<hr>
<br>