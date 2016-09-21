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
        <title>Applicant List</title>
    </head>
    <body>
        <table cellspacing="0" border="1" cellpadding="5">
            <c:forEach var="car" items="${carList}">
                <tr>
                    <td>car</td>
                    <td>${car.id}</td>
                    <td>${car.carNumber}</td>
                    <td>${car.ttnNumber}</td>
                    <td>driver</td>
                    <td>${car.driver.id}</td>
                    <td>${car.driver.name}</td>
                    <td>${car.driver.mobileNumber}</td>
                    <td>${car.driver.organization}</td>
                    <td>cargo</td>
                    <td>${car.cargo.id}</td>
                    <td>${car.cargo.weightIn}</td>
                    <td>${car.cargo.weightOut}</td>
                    <td>${car.cargo.dischargingPlace}</td>
                    <td>${car.cargo.dischargeDate}</td>
                    <td>${car.cargo.loadingPlace}</td>
                    <td>${car.cargo.loadingDate}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

