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
        <title>Manage card</title>
        <script src="${pageContext.request.contextPath}/javascript/jquery-1.11.3.js"></script>	
    </head>
    <body>
        <div style="float:left">
            <form id="addForm" action="${pageContext.request.contextPath}/card?cmd=add" method="post">
                card number: <br><input id="cardNumber" type="text" name="cardNumber" value="" readonly><br><br>
                driver<br>
                name <br><input type="text" name="name" value="Dima"><br>
                organization <br><input type="text" name="organization" value="Avectis"><br>
                mobileNumber <br><input type="text" name="mobileNumber" value="+375292224444"><br><br>
                car <br>
                firstNumber <br><input type="text" name="firstNumber" value="4700-EM1"><br>
                secondNumber <br><input type="text" name="secondNumber" value="4700-EM2"><br><br>
                <input type="submit" value="Add">
            </form>
        </div>
        <div style="float:left">
            <form id="deleteForm" action="${pageContext.request.contextPath}/card?cmd=delete" method="post">
                card number: <br><input id="cardNumber2" type="text" name="" value="" readonly><br><br>
                driver<br>
                name <br><input type="text" name="name" value=""><br>
                organization <br><input type="text" name="organization" value=""><br>
                mobileNumber <br><input type="text" name="mobileNumber" value=""><br><br>
                car <br>
                firstNumber <br><input type="text" name="firstNumber" value=""><br>
                secondNumber <br><input type="text" name="secondNumber" value=""><br><br>
                <input type="submit" value="Delete">
            </form>
        </div>
    </body>
</html>
<script>
    $(window).load( function(){
	$.get("${pageContext.request.contextPath}/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
        $.get("${pageContext.request.contextPath}/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    });
    function setNewCardNumber(data) { 
	var cardNumber= data.cardNumber;
        if (cardNumber!=null && cardNumber!=0){
            $( "#cardNumber" ).val( cardNumber );
        }
	$.get("${pageContext.request.contextPath}/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
    }
    function setCardData(data) { 
	if (data.card!=null && data.card!=""){
            $( "#cardNumber2").val( data.cardNumber );
        } 
	$.get("${pageContext.request.contextPath}/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    }	
</script>

    