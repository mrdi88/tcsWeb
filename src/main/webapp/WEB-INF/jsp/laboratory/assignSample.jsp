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
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/laboratory/css/assignSample.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/laboratory/javascript/assignSample.js"></script>	
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="content">
            <div id="card">
                <p class="tittle">Привязка имени пробы</p>
                <form id="assignForm" action="${pageContext.request.contextPath}/laboratory?cmd=assignSample" method="post" autocomplete="off">
                    <div class="existCardNumber">
                        <span>Номер карты:<input class="cardNumber" type="text" name="" value="" readonly></span>
                    </div>
                    <hr style="width: 100%">
                    <div class="block1">
                        <p>Водитель</p>
                        <span>Имя<input class="firstName" type="text" name="firstName" value="" readonly></span>
                        <span>Фамилия<input class="lastName" type="text" name="lastName" value="" readonly></span>
                        <span>Организация<input class="organization" type="text" name="organization" value="" readonly></span>
                        <span>Номер телефона<input class="mobileNumber" type="text" name="mobileNumber" value="" readonly></span>
                    </div>    
                    <div class="block2">
                        <p>Машина</p>
                        <span>Номер машины<input class="carNumber" type="text" name="carNumber" value="" readonly></span>
                        <span>Номер ТТН<input class="ttnNumber" type="text" name="ttnNumber" value="" readonly></span>
                        <span>Культура<input class="culture" type="text" name="nomenclature" value="" readonly></span>
                        <span>Время въезда<input class="createDate" type="text" name="createDate" value="" readonly></span>
                    </div>
                     <hr style="width: 100%">
                    <span class="assignField">Имя пробы<input class="sampleName" maxlength="10" type="text" name="sampleName" value="">
                        
                    </span>
                    <input class="cardId" type="hidden" name="cardId" value="" readonly>
                    <div class="submit">
                        <input type="submit" value="Привязать">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>
    