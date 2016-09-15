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
            <c:forEach var="card" items="${cardList}">
                <tr>
                    <td>card</td>
                    <td>${card.id}</td>
                    <td>${card.cardNumber}</td>
                    <td>${card.createDate}</td>
                    <td>car</td>
                    <td>${card.car.id}</td>
                    <td>${card.car.firstNumber}</td>
                    <td>${card.car.secondNumber}</td>
                    <td>driver</td>
                    <td>${card.car.driver.id}</td>
                    <td>${card.car.driver.name}</td>
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
        <a href="card.std?view=newCard"> add new card </a>
        <br><br>
        <form action="card.std?cmd=delete" method="post">
            card id <br><input type="text" name="cardId" value="0"><br><br>
            <input type="submit" value="Delete">
        </form>
    </body>
</html>
