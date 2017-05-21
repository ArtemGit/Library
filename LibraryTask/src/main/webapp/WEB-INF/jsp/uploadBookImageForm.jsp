
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/bookForm.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/bookForm2.css"/>" rel="stylesheet" />

<div class="generic-container"  >
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Форма добавления обложки журналу</span></div>
        <div class="formcontainer">
            <form method="POST" action="/books/upload/image/book/${idBook}" ng-model="myForm" name="myForm" class="form-horizontal"
                  enctype="multipart/form-data">
                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="image">Содержание журнала</label>
                    <div class="col-md-7">
                        <input type="file" ng-model="image" name="image" id="image"
                               class="username form-control input-sm" valid-file image-with-preview
                               accept="image/jpeg,image/jpg,image/png" required multiple/>
                        <div class="has-error" ng-show="myForm.image.$dirty">
                            <span ng-show="myForm.image.$error.required">Это поле обязательно для заполнения .jpeg,.jpg,.png файлы</span>
                            <span ng-show="myForm.image.$invalid">Это поле заполненно некорректно</span>
                        </div>
                    </div>
                </div>
                <div class="form-actions floatRight">
                    <input type="submit" value="Загрузить обложку" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                </div>
            </form>

        </div>
    </div>
    </div>

