
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/bookForm.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/bookForm2.css"/>" rel="stylesheet" />

<div class="generic-container"  >
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Форма добавления книг</span></div>
        <div class="formcontainer">
            <form ng-submit="submitEdit()"  ng-model="myForm" name="myForm" id="myFile" class="form-horizontal" >
                <input type="number" ng-model="book.idbook" name="idbook" id="bid" hidden/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bname">Название книги</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^[a-zа-яё\d\s]*$/i" ng-model="book.name" name="baookname" id="bname" class="bookname form-control input-sm" placeholder="Введите название книги" required ng-minlength="5"/>
                            <div class="has-error" ng-show="myForm.baookname.$dirty">
                                <span ng-show="myForm.baookname.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.baookname.$error.minlength">Минимальная длина  5 символов</span>
                                <span ng-show="myForm.baookname.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.baookname.$error.pattern">Наименование книги может содержать буквы пробелы и цифры</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bauthor">Автор</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="book.author"  ng-pattern="/^[a-zа-яё\s]*$/i"  name="bookauthor" id="bauthor" class="bauthor form-control input-sm" placeholder="Введите имя автора" required ng-minlength="5"/>
                            <div class="has-error" ng-show="myForm.bookauthor.$dirty">
                                <span ng-show="myForm.bookauthor.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bookauthor.$error.minlength">Минимальная длина поля автора 5 символов</span>
                                <span ng-show="myForm.bookauthor.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.bookauthor.$error.pattern">Поле автор может содержать только буквы и пробелы</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bYear">Год издания</label>
                        <div class="col-md-7">
                            <input type="number" name="bookYear" ng-model="book.year" id="bYear" class="bookyear form-control input-sm" placeholder="Введите год издания книги" required ng-maxlength="4" ng-minlength="4"/>
                            <div class="has-error" ng-show="myForm.bYear.$dirty">
                                <span ng-show="myForm.bYear.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bYear.$error.maxlength">Вводтите год в формате 2000 или 1995</span>
                                <span ng-show="myForm.bYear.$error.minlength">Вводтите год в формате 2000 или 1995</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bdescription">Краткое описание</label>
                        <div class="col-md-7">

                            <textarea name="bookdescription"  ng-model="book.description" cols="50" rows="5" maxlength="255" size="255"  placeholder="Введите описание..." required class="bdescription form-control input-sm"></textarea>
                            <div class="has-error" ng-show="myForm.bookdescription.$dirty">
                                <span ng-show="myForm.bookdescription.$error.required">Это поле обязательно для заполнения</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="Обновить" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">

                    </div>
                </div>
            </form>
        </div>
    </div>
