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
        <title>List card</title>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/card/javascript/manageCard.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/card/css/manageCard.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="content">
            <table cellspacing="0" border="1" cellpadding="5">
                <c:forEach var="card" items="${cardList}">
                    <tr>
                        <td>card</td>
                        <td>${card.id}</td>
                        <td>${card.cardNumber}</td>
                        <td>${card.createDate}</td>
                        <td>car</td>
                        <td>${card.car.id}</td>
                        <td>${card.car.carNumber}</td>
                        <td>${card.car.ttnNumber}</td>
                        <td>driver</td>
                        <td>${card.car.driver.id}</td>
                        <td>${card.car.driver.firstName}</td>
                        <td>${card.car.driver.lastName}</td>
                        <td>${card.car.driver.mobileNumber}</td>
                        <td>${card.car.driver.organization}</td>
                        <td>cargo</td>
                        <td>${card.car.cargo.id}</td>
                        <td>${card.car.cargo.weightIn}</td>
                        <td>${card.car.cargo.weightOut}</td>
                        <td>${card.car.cargo.dischargingPlace}</td>
                        <td>${card.car.cargo.dischargeDate}</td>
                        <td>${card.car.cargo.loadingPlace}</td>
                        <td>${card.car.cargo.loadingDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>   
</html>
 <script>
        var contextPath="${pageContext.request.contextPath}";
 </script>

