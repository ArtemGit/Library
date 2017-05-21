
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/bookForm.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/bookForm2.css"/>" rel="stylesheet" />

<div class="generic-container"  >
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Форма добавления контента журналов</span></div>
        <div class="formcontainer">
            <form method="POST" action="/books/uploadBook/${idBook}" ng-model="myForm" name="myForm"  class="form-horizontal" enctype="multipart/form-data">

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bcontent">Содержание журнала</label>
                        <div class="col-md-7">
                            <input type="file" ng-model="book.content"  name="bookContent" id="bcontent" class="bcontent form-control input-sm"
                                   valid-file  accept="text/html,text/plain,application/plain,application/pdf,application/rtf,application/msword"  required/>
                            <div class="has-error" ng-show="myForm.bookContent.$dirty">
                                <span ng-show="myForm.bookContent.$error.length">Слишком большой файл  max 20MB</span>
                                <span ng-show="myForm.bookContent.$error.required">Это поле обязательно для заполнения html,pdf,txt,rtf,doc files</span>
                                <span ng-show="myForm.bookContent.$invalid">Это поле заполненно некорректно</span>

                            </div>
                        </div>
                    </div>
                </div>
                    <div class="form-actions floatRight">
                        <input type="submit"  value="Добавить книгу" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">

                    </div>
            </form>
        </div>
    </div>
    </div>
