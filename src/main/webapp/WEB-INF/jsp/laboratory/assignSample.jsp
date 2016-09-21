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
        <title>Привязать карту</title>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
    </head>
    <body>
        <div style="max-width: 172px;">
            <form id="assignForm" action="${pageContext.request.contextPath}/laboratory?cmd=assignSample" method="post" autocomplete="off">
                <div>
                    Номер карты:<br><input class="cardNumber" type="text" name="" value="" readonly><br><br>
                    Водитель<br>
                    Имя<br><input class="name" type="text" name="name" value=""><br>
                    Организация<br><input class="organization" type="text" name="organization" value="" readonly><br>
                    Номер телефона<br><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
                    Машина<br>
                    Номер машины<br><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
                    Номер ТТН<br><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br>
                    Время въезда<br><input class="createDate" type="text" name="createDate" value="" readonly><br><br>
                    имя пробы<br><input class="sampleName" type="text" name="sampleName" value=""><br><br>
                    <input class="cardId" type="hidden" name="cardId" value="" readonly>
                </div>
                <div style="float: right;">
                    <input type="submit" value="Привязать">
                </div>
            </form>
        </div>
    </body>
</html>
<script>
    $(window).load( function(){
	$.get("${pageContext.request.contextPath}/laboratory?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    });
    $( "#assignForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#assignForm .cardId").val()==""){
            alert("Поднесите карту к считывателю");
            return;
        }
        if ($( "#assignForm .sampleName").val()==""){
            alert("Введите номер пробы");
            return;
        }
        console.log('Sending request to '+$(this).attr('action')+' with data: '+$(this).serialize());
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : $(this).attr('action'),
            data     : $(this).serialize(),
            success  : function(data) {
                if (data.result=="true"){
                    $( "#assignForm .cardNumber").val( "" );
                    $( "#assignForm .name").val( "" );
                    $( "#assignForm .organization").val( "" );
                    $( "#assignForm .mobileNumber").val( "" );
                    $( "#assignForm .carNumber").val( "" );
                    $( "#assignForm .ttnNumber").val( "" );
                    $( "#assignForm .createDate").val( "" );
                    $( "#assignForm .cardId").val( "" );
                    $( "#assignForm .sampleName").val( "" );
                }
                else{
                    alert("ошибка привязки пробы");
                }
            }
        });
    });
    function setCardData(data) { 
        var card=data.card;
	if (card!=null && card!=""){
            $( "#assignForm .cardNumber").val( card.cardNumber );
            $( "#assignForm .name").val( card.car.driver.name );
            $( "#assignForm .organization").val( card.car.driver.organization );
            $( "#assignForm .mobileNumber").val( card.car.driver.mobileNumber );
            $( "#assignForm .carNumber").val( card.car.carNumber );
            $( "#assignForm .ttnNumber").val( card.car.ttnNumber );
             var createDate = new Date(card.car.createDate).toLocaleString("ru-ru", dateOptions);
            $( "#assignForm .createDate").val( createDate );
            if (card.car.cargo.sample!=null){
               $( "#assignForm .sampleName").val( card.car.cargo.sample.name ); 
            } else $( "#assignForm .sampleName").val( "" );
            $( "#assignForm .cardId").val( card.id );
        } 
	$.get("${pageContext.request.contextPath}/laboratory?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    }
    var dateOptions = {
		weekday: "short", year: "numeric", month: "short",
		day: "numeric", hour: "2-digit", minute: "2-digit"
            };
</script>

    