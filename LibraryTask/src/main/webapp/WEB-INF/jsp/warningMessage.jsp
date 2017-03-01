<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Предупреждающее сообщение -->
<div class="alert alert-warning Myclose" ng-click="deleteAlert()">
    <!-- Кнопка для закрытия сообщения, созданная с помощью элемента span -->
    <span class="close " data-dismiss="alert" >X</span>
    ${message}
</div>
