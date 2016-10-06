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
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/queue/css/queueList.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="queues">
            <div class="queueBlock">
                <p>buffer</p>
                <table id="buffer" class="queueTable" width="100%" cellpadding="2" >
                    <th class="header">№</th>
                    <th class="header">Номер авто</th>
                    <th class="header">Влаж-ть,%</th>
                    <th class="header">Номенклатура</th>
                    <th class="header">Класс</th>
                    <th class="header">Точка выгрузки</th>
                </table>
            </div>
            <div class="queueBlock">
                <p>infoTable</p>
                <table id="infoTable" class="queueTable" width="100%" cellpadding="2" >
                    <th class="header">№</th>
                    <th class="header">Номер авто</th>
                    <th class="header">Влаж-ть,%</th>
                    <th class="header">Номенклатура</th>
                    <th class="header">Класс</th>
                    <th class="header">Точка выгрузки</th>
                </table>
            </div>
            <div class="queueBlock">
                <p>R01</p>
                <table id="R01" class="queueTable" width="100%" cellpadding="2" >
                    <th class="header">№</th>
                    <th class="header">Номер авто</th>
                    <th class="header">Влаж-ть,%</th>
                    <th class="header">Номенклатура</th>
                    <th class="header">Класс</th>
                    <th class="header">Точка выгрузки</th>
                </table>
            </div>
            <div class="queueBlock">
                <p>R02</p>
                <table id="R02" class="queueTable" width="100%" cellpadding="2" >
                    <th class="header">№</th>
                    <th class="header">Номер авто</th>
                    <th class="header">Влаж-ть,%</th>
                    <th class="header">Номенклатура</th>
                    <th class="header">Класс</th>
                    <th class="header">Точка выгрузки</th>
                </table>
            </div>
        </div>
    </body>
</html>

<script>
    var contextPath="${pageContext.request.contextPath}";
</script>