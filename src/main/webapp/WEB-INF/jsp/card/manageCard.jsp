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
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="newCard">
            <form id="addForm" action="${pageContext.request.contextPath}/card?cmd=add" method="post" autocomplete="off">
                <div class="cardNumber">
                    <p>Номер карты:</p><input class="cardNumber" type="text" name="cardNumber" value="" readonly><br><br>
                </div>
                <div class="driver">
                    <p>Водитель</p>
                    <p>Имя</p><input class="firstName" type="text" name="firstName" value=""><br>
                    <p>Фамилия</p><input class="lastName" type="text" name="lastName" value=""><br>
                    <p>Организация</p><input class="organization" type="text" name="organization" value=""><br>
                    <p>Номер телефона</p><input class="mobileNumber" type="text" name="mobileNumber" value=""><br><br>
                </div>
                <div class="car">
                    <p>Машина</p>
                    <p>Номер машины</p><input class="carNumber" type="text" name="carNumber" value=""><br>
                    <p>Номер ТТН</p><input class="ttnNumber" type="text" name="ttnNumber" value=""><br>
                    <p>Культура</p><input class="culture" type="text" name="nomenclature" value=""><br><br>
                </div>
                <div class="sumbit">
                    <input type="submit" value="Добавить">
                </div>
            </form>
        </div>
        <div id="existCard" >
            <form id="deleteForm" action="${pageContext.request.contextPath}/card?cmd=delete" method="post" autocomplete="off">
                <div class="cardNumber">
                    <p>Номер карты:</p><input class="cardNumber" type="text" name="" value="" readonly><br><br>
                </div>
                <div class="driver">
                    <p>Водитель</p>
                    <p>Имя</p><input class="firstName" type="text" name="firstName" value="" readonly><br>
                    <p>Фамилия</p><input class="lastName" type="text" name="lastName" value="" readonly><br>
                    <p>Организация</p><input class="organization" type="text" name="organization" value="" readonly><br>
                    <p>Номер телефона</p><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
                </div>
                <div class="car">
                    <p>Машина</p>
                    <p>Номер машины</p><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
                    <p>Номер ТТН</p><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br>
                    <p>Культура</p><input class="culture" type="text" name="nomenclature" value="" readonly><br>
                    <p>Время въезда</p><input class="createDate" type="text" name="createDate" value="" readonly><br><br>
                </div>
                <div class="cargo">
                    <p>Груз</p>
                    <p>Вес при въезде, кг</p><input class="weightIn" type="text" name="weightIn" value="" readonly><br>
                    <p>Вес при выезде, кг</p><input class="weightOut" type="text" name="weightOut" value="" readonly><br>
                    <p>Место разгрузки</p><input class="dischargingPlace" type="text" name="dischargingPlace" value="" readonly><br>
                    <p>Время разгрузки</p><input class="dischargeDate" type="text" name="dischargeDate" value="" readonly><br><br>
                </div>
                <div class="sample">
                    <p>Проба</p>
                    <p>Имя пробы</p><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                    <p>Влажность, %</p><input class="humidity" type="text" name="humidity" value="" readonly><br>
                    <p>Номенклатура</p><input class="nomenclature" type="text" name="nomenclature" value="" readonly><br>
                    <p>Класс</p><input class="class" type="text" name="class" value="" readonly><br>
                    <p>Точка выгрузки</p><input class="destination" type="text" name="destination" value="" readonly><br>
                    <p>Номер силоса</p><input class="siloNumber" type="text" name="siloNumber" value="" readonly><br><br>
                    <input class="cardId" type="hidden" name="cardId" value="" readonly>
                </div>
                <div class="submit">
                    <input type="submit" value="Удалить">
                </div>
            </form>
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>