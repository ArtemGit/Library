<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Сообщение об успешном выполнения действия -->
<div class="alert alert-success Myclose"  ng-click="deleteAlert()">
    <!-- Кнопка для закрытия сообщения, созданная с помощью элемента button -->
    <button type="button" class="close" data-dismiss="alert">?</button>
    ${message}
</div>