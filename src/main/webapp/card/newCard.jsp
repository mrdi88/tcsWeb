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
        <form action="card.std?cmd=add" method="post">
            card number: <br><input type="text" name="cardNumber" value="0"><br><br>
            driver<br>
            name <br><input type="text" name="name" value="Dima"><br>
            organization <br><input type="text" name="organization" value="Avectis"><br>
            mobileNumber <br><input type="text" name="mobileNumber" value="+375292224444"><br><br>
            car <br>
            firstNumber <br><input type="text" name="firstNumber" value="4700-EM1"><br>
            secondNumber <br><input type="text" name="secondNumber" value="4700-EM2"><br><br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>

    