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
        <title>Cards</title>
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/style_pickdate.css" media="screen" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/card/css/cards.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/javascript/will_pickdate.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery.mousewheel.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/card/javascript/cards.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="content">
            <div id="content_selectCard">
                <div id="header_selectCard">
                    <table id="header_cards" width="100%" cellpadding="2" >
                        <th class="rowNumber">№</th>
                        <th class="carNumber">Номер авто</th>
                        <th class="destination">Точка выгрузки</th>
                        <th class="siloNumber">Номер силоса</th>
                        <th class="nomenclature">Номенклатура</th>
                        <th class="class">Класс</th>
                        <th class="humidity">Влаж-ть,%</th>
                        <th class="createDate">Время въезда</th>
                    </table>
                </div>
                <div id="selectCard">
                    <table id="cards" width="100%" height="100%" cellpadding="2" >
                    </table>
                </div>
            </div>
            <div id="params">
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
                <div class="sample_1">
                    <p>Проба</p>
                    <p>Имя пробы</p><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                    <p>Влажность, %</p><input class="humidity" type="text" name="humidity" value="" readonly><br>
                    <p>Номенклатура</p><input class="nomenclature" type="text" name="nomenclature" value="" readonly><br>
                    <p>Класс</p><input class="class" type="text" name="class" value="" readonly><br>
                </div>
                <div class="sample_2">   
                    <br>
                    <p>Точка выгрузки</p><input class="destination" type="text" name="destination" value="" readonly><br>
                    <p>Номер силоса</p><input class="siloNumber" type="text" name="siloNumber" value="" readonly><br><br>
                </div>
            </div> 
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>

    