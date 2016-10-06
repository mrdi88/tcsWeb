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
        <title>Archive cars</title>
        <link href="${pageContext.request.contextPath}/resources/css/style_pickdate.css" media="screen" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/archive/css/cars.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/javascript/will_pickdate.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery.mousewheel.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/archive/javascript/cars.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="period">
            <form id="periodForm" action="${pageContext.request.contextPath}/archive?cmd=getCars" method="post" autocomplete="off">
                
                с <input type="text" name="from" size="20" style="display: inline-block;" id="start_time"/><input type="text" size="40" style="display: none;"/>
                по <input type="text" name="to" size="20" style="display: inline-block;" id="stop_time"/><input type="text" size="40" style="display: none;"/>       
                <input type="submit" value="Период">
            </form>
        </div>
        <div id="selectCar">
            <table id="cars" width="100%" cellpadding="2" >
                <th class="cars_header">№</th>
                <th class="cars_header">Номер авто</th>
                <th class="cars_header">Точка выгрузки</th>
                <th class="cars_header">Номер силоса</th>
                <th class="cars_header">Номенклатура</th>
                <th class="cars_header">Класс</th>
                <th class="cars_header">Влаж-ть,%</th>
                <th class="cars_header">Время въезда</th>
            </table>
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
            <div class="sample">
                <p>Проба</p>
                <p>Имя пробы</p><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                <p>Влажность, %</p><input class="humidity" type="text" name="humidity" value="" readonly><br>
                <p>Номенклатура</p><input class="nomenclature" type="text" name="nomenclature" value="" readonly><br>
                <p>Класс</p><input class="class" type="text" name="class" value="" readonly><br>
                <p>Точка выгрузки</p><input class="destination" type="text" name="destination" value="" readonly><br>
                <p>Номер силоса</p><input class="siloNumber" type="text" name="siloNumber" value="" readonly><br><br>
            </div>
        </div> 
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>

    