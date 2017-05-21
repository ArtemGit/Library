
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/bookForm.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/bookForm2.css"/>" rel="stylesheet" />

<div class="generic-container"  >
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Форма добавления журрналов</span></div>
        <div class="formcontainer">
            <form  ng-submit="submit(book)"  ng-model="myForm" name="myForm" id="myFile" class="form-horizontal" >
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bname">Название журнала</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^(([^\s]+)(\s{0,1})([^\s]*))*$/" ng-model="book.name"
                                   name="bookName" id="bname" class="book-string form-control input-sm"
                                   placeholder="Введите название журнала" required ng-minlength="5" />
                            <div class="has-error" ng-show="myForm.bookName.$dirty">
                                <span ng-show="myForm.bookName.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bookName.$error.minlength">Минимальная длина  5 символов</span>
                                <span ng-show="myForm.bookName.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.bookName.$error.pattern">Наименование журнала может содержать буквы пробелы и цифры</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bplace">Место издания</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^(([^\s]+)(\s{0,1})([^\s]*))*$/" ng-model="book.publicationPlace"
                                   name="publicationPlace" id="bplace"
                                   class="book-string form-control input-sm"
                                   placeholder="Введите место публикации журнала" required ng-minlength="5"   />
                            <div class="has-error" ng-show="myForm.publicationPlace.$dirty">
                                <span ng-show="myForm.publicationPlace.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.publicationPlace.$error.minlength">Минимальная длина  5 символов</span>
                                <span ng-show="myForm.publicationPlace.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.publicationPlace.$error.pattern">
                                    Место издания журнала должно соответствовать шаблону 'фраза фраза'</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bpubHouse">Издательство</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^(([^\s]+)(\s{0,1})([^\s]*))*$/" ng-model="book.publishHouse"
                                   name="publishHouse" id="bpubHouse" class="book-string form-control input-sm"
                                   placeholder="Укажите издательство" required ng-minlength="5" />
                            <div class="has-error" ng-show="myForm.publishHouse.$dirty">
                                <span ng-show="myForm.publishHouse.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.publishHouse.$error.minlength">Минимальная длина  5 символов</span>
                                <span ng-show="myForm.publishHouse.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.publishHouse.$error.pattern">Издательство журнала должно соответствовать шаблону 'фраза фраза либо фраза'</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bPublishStartYear">Издаётся начиная c</label>
                        <div class="col-md-7">
                            <input type="number" name="bookStartYear" ng-model="book.publishStartYear" id="bPublishStartYear"
                                   class="book-year form-control input-sm" placeholder="Введите год начала издания"
                                   required ng-maxlength="4" ng-minlength="4"  minlength="4"  maxlength="4" min="1900" min="2025" />
                            <div class="has-error" ng-show="myForm.bookStartYear.$dirty">
                                <span ng-show="myForm.bookStartYear.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bookStartYear.$error.maxlength">Вводтите год в формате 2000 или 1995</span>
                                <span ng-show="myForm.bookStartYear.$error.minlength">Вводтите год в формате 2000 или 1995</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bPublishEndYear">Издавался до года</label>
                        <div class="col-md-7">
                            <input type="number" name="bookEndYear" ng-model="book.publishEndYear" id="bPublishEndYear"
                                   class="book-year form-control input-sm"
                                   ng-maxlength="4" ng-minlength="4"   maxlength="4"  min="1900" />
                            <div class="has-error" ng-show="myForm.bookEndYear.$dirty">
                                <span ng-show="myForm.bookEndYear.$error.maxlength">Вводтите год в формате 2000 или 1995</span>
                                <span ng-show="myForm.bookEndYear.$error.minlength">Вводтите год в формате 2000 или 1995</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bElectVerStartYear">Электронная версия доступна с года</label>
                        <div class="col-md-7">
                            <input type="number" name="bookElectVerStartYear" ng-model="book.electVerStartYear" id="bElectVerStartYear"
                                   class="book-year form-control input-sm"
                                   ng-maxlength="4" ng-minlength="4"    maxlength="4"  min="1990"  min="2025"/>
                            <div class="has-error" ng-show="myForm.bookElectVerStartYear.$dirty">
                                <span ng-show="myForm.bookElectVerStartYear.$error.maxlength">Вводтите год в формате 2000 или 1995</span>
                                <span ng-show="myForm.bookElectVerStartYear.$error.minlength">Вводтите год в формате 2000 или 1995</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bElectVerEndYear">Электронная версия доступна до года</label>
                        <div class="col-md-7">
                            <input type="number" name="bookElectVerEndYear" ng-model="book.electVerEndYear" id="bElectVerEndYear"
                                   class="book-year form-control input-sm"
                                   ng-maxlength="4" ng-minlength="4"   maxlength="4"  min="1900" />
                            <div class="has-error" ng-show="myForm.bookElectVerEndYear.$dirty">
                                <span ng-show="myForm.bookElectVerEndYear.$error.maxlength">Вводтите год в формате 2000 или 1995</span>
                                <span ng-show="myForm.bookElectVerEndYear.$error.minlength">Вводтите год в формате 2000 или 1995</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bissn" >ISSN</label>
                        <div class="col-md-7">
                            <input type="number" name="bookbissn" ng-model="book.issn" id="bissn"
                                   class="book-year form-control input-sm" placeholder="Введите ISSN"
                                   required ng-maxlength="8" ng-minlength="8"   maxlength="8" min="000000000"  />
                            <div class="has-error" ng-show="myForm.bookbissn.$dirty">
                                <span ng-show="myForm.bookbissn.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bookbissn.$error.maxlength">Вводтите 8 значное число</span>
                                <span ng-show="myForm.bookbissn.$error.minlength">Вводтите 8 значное число</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bcountry">Страна</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^[A-Za-zА-Яа-я]*$/" ng-model="book.country"
                                   name="bookcountry" id="bcountry" class="book-string form-control input-sm"
                                   placeholder="Введите страну" required ng-minlength="4"/>
                            <div class="has-error" ng-show="myForm.bookcountry.$dirty">
                                <span ng-show="myForm.bookcountry.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.bookcountry.$error.minlength">Минимальная длина  4 символов</span>
                                <span ng-show="myForm.bookcountry.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.bookcountry.$error.pattern">Страна может содержать буквы пробелы, символы</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="blanguage">Язык</label>
                        <div class="col-md-7">
                            <input type="text" ng-pattern="/^[a-zа-я]{2,}$/" ng-model="book.language"
                                   name="booklanguage" id="blanguage" class="book-string form-control input-sm"
                                   placeholder="Введите страну" required ng-minlength="2" />
                            <div class="has-error" ng-show="myForm.booklanguage.$dirty">
                                <span ng-show="myForm.booklanguage.$error.required">Это поле обязательно для заполнения</span>
                                <span ng-show="myForm.booklanguage.$error.minlength">Минимальная длина  2 символа</span>
                                <span ng-show="myForm.booklanguage.$invalid">Это поле заполненно некорректно</span>
                                <span  ng-show="myForm.booklanguage.$error.pattern">Язык может содержать буквы</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="bdescription">Краткое описание</label>
                        <div class="col-md-7">

                            <textarea id="bdescription" name="bookdescription"  ng-model="book.description" cols="50" rows="5" maxlength="1000" size="1000"  placeholder="Введите описание..." required class="bdescription form-control input-sm"></textarea>
                            <div class="has-error" ng-show="myForm.bookdescription.$dirty">
                                <span ng-show="myForm.bookdescription.$error.required">Это поле обязательно для заполнения</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 cwontrol-lable" >Наличие подписки</label>
                        <div class="col-md-7">
                                <input type="checkbox" ng-model="checkboxModel.value"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">

                        <input type="submit"  value= "{{!ifExist(book) ? 'Добавить журнал' : 'Обновить описание журнала'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>