<%-- 
    Document   : currentCars
    Created on : 08.09.2016, 0:26:56
    Author     : Dima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- meta for CSRF-TOKEN -->
        <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Manage card</title>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/card/javascript/manageCard.js"></script>	
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/card/css/manageCard.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
    </head>
    <body style="font-family: 'Cuprum', sans-serif;">
        <jsp:include page="../header.jsp"/>
        <div id="content">
            <div id="newCard">
                <p class="tittle">Новая карта</p>
                <form id="addForm" action="${pageContext.request.contextPath}/card?cmd=add" method="post" autocomplete="off">
                    <div class="newCardNumber">
                        <p>Номер карты:</p><input class="cardNumber" type="text" name="cardNumber" value="" readonly><br>
                    </div>
                    <hr style="width: 100%">
                    <div class="driver">
                        <p>Имя</p><input class="firstName" type="text" name="firstName" maxlength="30" value=""><br>
                        <p>Фамилия</p><input class="lastName" type="text" name="lastName" maxlength="30" value=""><br>
                        <p>Организация</p><input class="organization" type="text" name="organization" maxlength="30" value=""><br>
                        <p>Номер телефона</p><input class="mobileNumber" type="text" name="mobileNumber" maxlength="13" value=""><br>
                    </div>
                    <div class="car">
                        <p>Номер машины</p><input class="carNumber" type="text" maxlength="10" name="carNumber" value=""><br>
                        <p>Номер ТТН</p><input class="ttnNumber" type="text" maxlength="10" name="ttnNumber" value=""><br>
                        <p>Культура</p><input class="culture" type="text" maxlength="20" name="nomenclature" value=""><br>
                    </div>
                    <div class="submit">
                        <input type="submit" value="Добавить">
                    </div>
                </form>
            </div>
            <div id="existCard" >
                <p class="tittle">Существующая карта</p>
                <form id="deleteForm" action="${pageContext.request.contextPath}/card?cmd=delete" method="post" autocomplete="off">
                    <div class="existCardNumber">
                        <span>Номер карты:<input class="cardNumber" type="text" name="" value="" readonly></span>
                    </div>
                    <hr>
                    <div class="block1">
                        <p>Водитель</p>
                        <span>Имя<input class="firstName" type="text" name="firstName" value="" readonly></span>
                        <span>Фамилия<input class="lastName" type="text" name="lastName" value="" readonly></span>
                        <span>Организация<input class="organization" type="text" name="organization" value="" readonly></span>
                        <span>Номер телефон<input class="mobileNumber" type="text" name="mobileNumber" value="" readonly></span>
                        <hr>
                        <p>Проба</p>
                        <span>Имя пробы<input class="sampleName" type="text" name="sampleName" value="" readonly></span>
                        <span>лажность, %<input class="humidity" type="text" name="humidity" value="" readonly></span>
                        <span>Номенклатура<input class="nomenclature" type="text" name="nomenclature" value="" readonly></span>
                        <span>Класс<input class="class" type="text" name="class" value="" readonly></span>
                        <span>Точка выгрузки<input class="destination" type="text" name="destination" value="" readonly></span>
                        <span>Номер силоса<input class="siloNumber" type="text" name="siloNumber" value="" readonly></span>
                        <input class="cardId" type="hidden" name="cardId" value="" readonly>
                    </div>
                    <div class="block2">
                        <p>Машина</p>
                        <span>Номер машины<input class="carNumber" type="text" name="carNumber" value="" readonly></span>
                        <span>Номер ТТН<input class="ttnNumber" type="text" name="ttnNumber" value="" readonly></span>
                        <span>Культура<input class="culture" type="text" name="nomenclature" value="" readonly></span>
                        <span>Время въезда<input class="createDate" type="text" name="createDate" value="" readonly></span>
                        <hr>
                        <p>Груз</p>
                        <span>Вес при въезде, кг<input class="weightIn" type="text" name="weightIn" value="" readonly></span>
                        <span>Вес при выезде, кг<input class="weightOut" type="text" name="weightOut" value="" readonly></span>
                        <span>Место разгрузки<input class="dischargingPlace" type="text" name="dischargingPlace" value="" readonly></span>
                        <span>Время разгрузки<input class="dischargeDate" type="text" name="dischargeDate" value="" readonly></span>
                        <span class="submit"><input type="submit" value="Удалить"></span>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>