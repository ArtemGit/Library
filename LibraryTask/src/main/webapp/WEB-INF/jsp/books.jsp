<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row" >
    <div class="col-6 col-lg-4" class="bookPost" ng-repeat="b in listBooks">
        <h2 ng-bind="b.name"></h2>
        <p ng-bind="b.author"></p>
        <p><a class="btn btn-secondary"  ng-click="bookDetails(b)" href="#" role="button">Просмотреть детали »</a></p>
    </div><!--/span-->
</div><!--/row-->
