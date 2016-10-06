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
        <title>Привязать карту</title>
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/laboratory/css/assignSample.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/laboratory/javascript/assignSample.js"></script>	
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <form id="assignForm" action="${pageContext.request.contextPath}/laboratory?cmd=assignSample" method="post" autocomplete="off">
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
                <p>имя пробы</p><input class="sampleName" type="text" name="sampleName" value=""><br><br>
                <input class="cardId" type="hidden" name="cardId" value="" readonly>
            </div>
            <div class="sabmit">
                <input type="submit" value="Привязать">
            </div>
        </form>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>
    