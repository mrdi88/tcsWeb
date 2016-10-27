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
        <title>Состояние очередей</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- meta for CSRF-TOKEN -->
        <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/queue/javascript/queueList.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/queue/css/queueList.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="content">
            <div class="queueBlock">
                <p>Ожидание</p>
                <table class="queueTable_header" width="100%" cellpadding="2" >
                    <th class="rowNumber">№</th>
                    <th class="carNumber">Номер авто</th>
                    <th class="humidity">Влаж-ть,%</th>
                    <th class="nomenclature">Номенклатура</th>
                    <th class="class">Класс</th>
                    <th class="destination">Точка выгрузки</th>
                </table>
                <table id="buffer" class="queueTable" width="100%" cellpadding="2" >
                </table>
            </div>
            <div class="queueBlock">
                <p>Информационный экран</p>
                <table class="queueTable_header" width="100%" cellpadding="2" >
                    <th class="rowNumber">№</th>
                    <th class="carNumber">Номер авто</th>
                    <th class="humidity">Влаж-ть,%</th>
                    <th class="nomenclature">Номенклатура</th>
                    <th class="class">Класс</th>
                    <th class="destination">Точка выгрузки</th>
                </table>
                <table id="infoTable" class="queueTable" width="100%" cellpadding="2" >
                </table>
            </div>
            <div class="queueBlock">
                <p>Точка выгрузки №1</p>
                <table class="queueTable_header" width="100%" cellpadding="2" >
                    <th class="rowNumber">№</th>
                    <th class="carNumber">Номер авто</th>
                    <th class="humidity">Влаж-ть,%</th>
                    <th class="nomenclature">Номенклатура</th>
                    <th class="class">Класс</th>
                    <th class="destination">Точка выгрузки</th>
                </table>
                <table id="R01" class="queueTable" width="100%" cellpadding="2" >
                </table>
            </div>
            <div class="queueBlock">
                <p>Точка выгрузки №2</p>
                <table class="queueTable_header" width="100%" cellpadding="2" >
                    <th class="rowNumber">№</th>
                    <th class="carNumber">Номер авто</th>
                    <th class="humidity">Влаж-ть,%</th>
                    <th class="nomenclature">Номенклатура</th>
                    <th class="class">Класс</th>
                    <th class="destination">Точка выгрузки</th>
                </table>
                <table id="R02" class="queueTable" width="100%" cellpadding="2" >
                </table>
            </div>
        </div>
    </body>
</html>

<script>
    var contextPath="${pageContext.request.contextPath}";
</script>