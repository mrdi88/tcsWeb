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
        <title>Manage samples</title>
        <link href="${pageContext.request.contextPath}/resources/laboratory/css/manageSamples.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/laboratory/javascript/manageSamples.js"></script>	
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="selectCard">
            <table id="cards" width="100%" cellpadding="2" >
                <th class="cards_header">№</th>
                <th class="cards_header">Имя пробы</th>
                <th class="cards_header">Номер авто</th>
                <th class="cards_header">Точка выгрузки</th>
                <th class="cards_header">Номер силоса</th>
                <th class="cards_header">Влаж-ть,%</th>
                <th class="cards_header">Номенклатура</th>
                <th class="cards_header">Класс</th>
                <th class="cards_header">Время въезда</th>
            </table>
	</div>
        <div id="params">
            <form id="paramForm" action="${pageContext.request.contextPath}/laboratory/manage?cmd=assignParams" method="post" autocomplete="off">
                <div class="car">
                    <p>Номер машины</p><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
                    <p>Номер ТТН</p><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br>
                    <p>Культура</p><input class="culture" type="text" name="culture" value="" readonly><br><br>
                </div>
                <div class="driver">
                    <p>Водитель<br>
                    <p>Имя</p><input class="firstName" type="text" name="firstName" value="" readonly><br>
                    <p>Фамилия</p><input class="lastName" type="text" name="lastName" value="" readonly><br>
                    <p>Организация</p><input class="organization" type="text" name="organization" value="" readonly><br>
                    <p>Номер телефона</p><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
                </div>
                <div class="sample">
                    <p>Проба</p>
                    <p>Имя пробы</p><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                    <p>Номенклатура</p><input class="nomenclature" type="text" name="nomenclature" value="" ><br>
                    <p>Класс</p><input class="class" type="text" name="class" value="" ><br>
                    <p>Влажность, %</p><input class="humidity" type="text" name="humidity" value="" ><br>
                    <p>Точка выгрузки</p>
                    <select size="1" class="queueId" name="queueId">
                        <option disabled>Выберите направление</option>
                        <option value="0">не назначено</option>
                        <c:forEach var="queueName" items="${queueList}">
                            <option value=${queueName.id}>${queueName.name}</option>
                        </c:forEach>       
                    </select>
                    <p>Номер силуса</p><input class="siloNumber" type="text" name="siloNumber" value="" ><br><br>
                </div>
                <input class="cardId" type="hidden" name="cardId" value="" readonly>
                <input style="float: right;" type="submit" value="Применить">
            </form>
        </div> 
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";

</script>

    