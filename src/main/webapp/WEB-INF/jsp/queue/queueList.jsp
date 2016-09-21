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
            <c:forEach var="queue" items="${queueList}">
                <tr>
                    <td>queue</td>
                    <td>${queue.id}</td>
                    <td>${queue.name}</td>
                    <c:forEach var="card" items="${queue.cards}">
                        <td>card&nbsp;${card.id}&nbsp;${card.car.carNumber}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <form action="queue?cmd=add" method="post">
            queue name <br><input type="text" name="queueName" value=""><br>
            <input type="submit" value="add">
        </form>
        <br>
        <form action="queu?cmd=delete" method="post">
            queue id <br><input type="text" name="queueId" value="0"><br>
            <input type="submit" value="delete">
        </form>
        <br>
        <form action="queue?cmd=addCardToQueue" method="post">
            queue id <br><input type="text" name="queueId" value=""><br>
            card id <br><input type="text" name="cardId" value=""><br>
            <input type="submit" value="addCardToQueue">
        </form>
        <br>
        <form action="queue?cmd=deleteCardFromQueue" method="post">
            queue id <br><input type="text" name="queueId" value=""><br>
            card id <br><input type="text" name="cardId" value=""><br>
            <input type="submit" value="deleteCardFromQueue">
        </form>
        <br>
        <form action="queue?cmd=moveCardFromTo" method="post">
            fromId id <br><input type="text" name="fromId" value=""><br>
            toId id <br><input type="text" name="toId" value=""><br>
            card id <br><input type="text" name="cardId" value=""><br>
            <input type="submit" value="moveCardFromTo">
        </form>
    </body>
</html>

