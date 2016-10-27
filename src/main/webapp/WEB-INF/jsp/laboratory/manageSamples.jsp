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
        <title>Manage samples</title>
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/laboratory/css/manageSamples.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/laboratory/javascript/manageSamples.js"></script>	
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="selectCard">
            <div id="cards">
                <table id="cardsHeader" width="100%" cellpadding="2" >
                    <th class="cards_header rowNumber">№</th>
                    <th class="cards_header sampleName">Имя пробы</th>
                    <th class="cards_header carNumber">Номер авто</th>
                    <th class="cards_header destination">Точка выгрузки</th>
                    <th class="cards_header siloNumber">Номер силоса</th>
                    <th class="cards_header humidity">Влаж-сть,%</th>
                    <th class="cards_header nomenclature">Номенклатура</th>
                    <th class="cards_header class">Класс</th>
                    <th class="cards_header createDate">Время въезда</th>
                </table>
                <table id="cardsContent" width="100%" cellpadding="2" >
                </table>
            </div>
	</div>
        <div id="params">
            <form id="paramForm" action="${pageContext.request.contextPath}/laboratory/manage?cmd=assignParams" method="post" autocomplete="off">
                <div class="data">
                    <p class="title">Машина</p>
                    <div class="block1">
                        <p>Имя</p><input class="firstName" type="text" name="firstName" value="" readonly><br>
                        <p>Фамилия</p><input class="lastName" type="text" name="lastName" value="" readonly><br>
                        <p>Организация</p><input class="organization" type="text" name="organization" value="" readonly><br>
                        <p>Номер телефона</p><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br>
                    </div>
                    <div class="block2">
                        <p>Номер машины</p><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
                        <p>Номер ТТН</p><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br>
                        <p>Культура</p><input class="culture" type="text" name="culture" value="" readonly><br>
                    </div>
                </div>
                <div class="sample">
                    <p class="title">Проба</p>
                    <div class="block1">
                        <p>Имя пробы</p><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                        <p>Номенклатура</p><input class="nomenclature" type="text" maxlength="20" name="nomenclature" value="" ><br>
                        <p>Класс</p><input class="class" type="text" name="class" maxlength="20" value="" ><br>
                    </div>
                    <div class="block2">
                        <p>Влажность, %</p><input class="humidity" type="text" maxlength="5" name="humidity" value="" ><br>
                        <p>Точка выгрузки</p>
                        <select size="1" class="queueId" name="queueId">
                            <option disabled>Выберите направление</option>
                            <option value="0">не назначено</option>
                            <c:forEach var="queueName" items="${queueList}">
                                <option value=${queueName.id}>${queueName.name}</option>
                            </c:forEach>       
                        </select>
                        <p>Номер силуса</p><input class="siloNumber" maxlength="5" type="text" name="siloNumber" value="" ><br>
                        <div class="submit">
                            <input type="submit" value="Применить">
                        </div>
                    </div>
                </div>
                <input class="cardId" type="hidden" name="cardId" value="" readonly>
            </form>
        </div> 
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";

</script>

    