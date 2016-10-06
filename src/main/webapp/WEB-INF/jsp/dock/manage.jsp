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
        <!-- header for CSRF-TOKEN -->
        <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>s
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/dock/javascript/manage.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/dock/css/manage.css" rel="stylesheet" type="text/css"/>
        <title>Управление доком</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div class="docks">
            <div class="dock" id="dock1">
                <p>R01</p>
                <div class="screen listScreen">
                    <p>Выберите автомобиль</p>
                    <table class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Влажность, %</th>
                        <th class="header">Номенклатура</th>
                        <th class="header">Класс</th>
                        <th class="header">Номер силоса</th>
                    </table>
                </div>
                <div class="screen confirmationScreen">
                    <p>Вызвать автомобиль?</p>
                    <p class="carNumber"></p>
                    <form class="confirmationForm" action="${pageContext.request.contextPath}/dock?cmd=callCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this);">
                        
                    </form>
                </div>
                <div class="screen arrivalScreen">
                    <p>Вызван автомобиль</p>
                    <p class="carNumber"></p>
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this);  clearInfo(this);">
                    </form>
                </div>
                <div class="screen releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        <div class="data1">
                            <p>Имя</p><input class="firstName" type="text" value="" readonly><br>
                            <p>Фамилия</p><input class="lastName" type="text" value="" readonly><br>
                            <p>Организация</p><input class="organization" type="text" value="" readonly><br>
                        </div>
                        <div class="data2">
                            <p>Номер авто</p><input class="carNumber" type="text" value="" readonly><br>
                            <p>Номер cилоса</p><input class="siloNumber" type="text" value="" readonly><br>
                            <p>Вес брутто, кг</p><input class="weightIn" type="text" value="" readonly><br>
                        </div>
                        <div class="data3">
                            <p>Влажность, %</p><input class="humidity" type="text" value="" readonly><br>
                            <p>Номенклатура</p><input class="nomenclature" type="text" value="" readonly><br>
                            <p>Класс</p><input class="class" type="text" value="" readonly><br>
                        </div>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <div class="submit">
                            <input class="cancel" type="button" value="Отмена" onclick="resetScreen(this)">
                            <input class="accept" type="submit" value="Отпустить">
                        </div>
                    </form>
                </div>
            </div>
            <div class="dock" id="dock2">
                <p>R02</p>
                <div class="screen listScreen">
                    <p>Выберите автомобиль</p>
                    <table class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Влажность, %</th>
                        <th class="header">Номенклатура</th>
                        <th class="header">Класс</th>
                        <th class="header">Номер силоса</th>
                    </table>
                </div>
                <div class="screen confirmationScreen">
                    <p>Вызвать автомобиль?</p>
                    <p class="carNumber"></p>
                    <form class="confirmationForm" action="${pageContext.request.contextPath}/dock?cmd=callCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this);">
                    </form>
                </div>
                <div class="screen arrivalScreen">
                    <p>Вызван автомобиль</p>
                    <p class="carNumber"></p>
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this);  clearInfo(this);">
                    </form>
                </div>
                <div class="screen releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        <div class="data1">
                            <p>Имя</p><input class="firstName" type="text" value="" readonly><br>
                            <p>Фамилия</p><input class="lastName" type="text" value="" readonly><br>
                            <p>Организация</p><input class="organization" type="text" value="" readonly><br>
                        </div>
                        <div class="data2">
                            <p>Номер авто</p><input class="carNumber" type="text" value="" readonly><br>
                            <p>Номер cилоса</p><input class="siloNumber" type="text" value="" readonly><br>
                            <p>Вес брутто, кг</p><input class="weightIn" type="text" value="" readonly><br>
                        </div>
                        <div class="data3">
                            <p>Влажность, %</p><input class="humidity" type="text" value="" readonly><br>
                            <p>Номенклатура</p><input class="nomenclature" type="text" value="" readonly><br>
                            <p>Класс</p><input class="class" type="text" value="" readonly><br>
                        </div>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <div class="submit">
                            <input class="cancel" type="button" value="Отмена" onclick="resetScreen(this)">
                            <input class="accept" type="submit" value="Отпустить">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    contextPath="${pageContext.request.contextPath}";
</script>