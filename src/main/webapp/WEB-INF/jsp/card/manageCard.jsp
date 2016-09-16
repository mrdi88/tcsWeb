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
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
    </head>
    <body>
        <div style="float:left">
            <form id="addForm" action="${pageContext.request.contextPath}/card?cmd=add" method="post">
                <div>
                Номер карты:<br><input class="cardNumber" type="text" name="cardNumber" value="" readonly><br><br>
                Водитель<br>
                Имя<br><input class="name" type="text" name="name" value=""><br>
                Организация<br><input class="organization" type="text" name="organization" value=""><br>
                Номер телефона<br><input class="mobileNumber" type="text" name="mobileNumber" value=""><br><br>
                Машина<br>
                Номер машины<br><input class="firstNumber" type="text" name="firstNumber" value=""><br>
                Номер прицепа<br><input class="secondNumber" type="text" name="secondNumber" value=""><br><br>
                </div>
                <div style="float: right;">
                <input type="submit" value="Добавить">
                </div>
            </form>
        </div>
        <div style="float:left; padding-left: 50px;">
            <form id="deleteForm" action="${pageContext.request.contextPath}/card?cmd=delete" method="post">
                <div style="float:left;">
                    Номер карты:<br><input class="cardNumber" type="text" name="" value="" readonly><br><br>
                    Водитель<br>
                    Имя<br><input class="name" type="text" name="name" value="" readonly><br>
                    Организация<br><input class="organization" type="text" name="organization" value="" readonly><br>
                    Номер телефона<br><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
                    Машина<br>
                    Номер машины<br><input class="firstNumber" type="text" name="firstNumber" value="" readonly><br>
                    Номер прицепа<br><input class="secondNumber" type="text" name="secondNumber" value="" readonly><br>
                    Время въезда<br><input class="createDate" type="text" name="createDate" value="" readonly><br><br>
                </div>
                <div style="float: left; padding-left: 20px; padding-top: 18px;">
                    Груз<br>
                    Вес при въезде, кг<br><input class="weightIn" type="text" name="weightIn" value="" readonly><br>
                    Вес при выезде, кг<br><input class="weightOut" type="text" name="weightOut" value="" readonly><br>
                    Место разгрузки<br><input class="dischargingPlace" type="text" name="dischargingPlace" value="" readonly><br>
                    Время разгрузки<br><input class="dischargeDate" type="text" name="dischargeDate" value="" readonly><br><br>
                    Проба<br>
                    Имя пробы<br><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                    Засоренность, %<br><input class="weediness" type="text" name="weediness" value="" readonly><br>
                    Клейковина, %<br><input class="gluten" type="text" name="gluten" value="" readonly><br>
                    Влажность, %<br><input class="humidity" type="text" name="humidity" value="" readonly><br><br>
                    <input class="cardId" type="hidden" name="cardId" value="" readonly>
                </div>
                <div >
                    <input style="float: right;" type="submit" value="Удалить">
                </div>
            </form>
        </div>
    </body>
</html>
<script>
    $(window).load( function(){
	$.get("${pageContext.request.contextPath}/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
        $.get("${pageContext.request.contextPath}/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    });
    $( "#addForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#addForm .cardNumber").val()==""){
            alert("Поднесите непривязанную карту к считывателю");
            return;
        }
        if ($( "#addForm .name").val()==""){
            alert("Введите имя водителя");
            return;
        }
        if ($( "#addForm .firstNumber").val()==""){
            alert("Введите номер автомобиля");
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
                    $( "#addForm .cardNumber").val( "" );
                    $( "#addForm .name").val( "" );
                    $( "#addForm .organization").val( "" );
                    $( "#addForm .mobileNumber").val( "" );
                    $( "#addForm .firstNumber").val( "" );
                    $( "#addForm .secondNumber").val( "" );
                }
                else{
                    alert("Ошибка создания карты");
                }
            }
        });
    });
    $( "#deleteForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#deleteForm .cardId").val()==""){
            alert("Поднесите карту к считывателю");
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
                    $( "#deleteForm .cardNumber").val("");
                    $( "#deleteForm .name").val( "" );
                    $( "#deleteForm .organization").val( "" );
                    $( "#deleteForm .mobileNumber").val( "" );
                    $( "#deleteForm .firstNumber").val( "" );
                    $( "#deleteForm .secondNumber").val( "" );
                    $( "#deleteForm .createDate").val( "" );
                    $( "#deleteForm .weightIn").val( "" );
                    $( "#deleteForm .weightOut").val( "" );
                    $( "#deleteForm .dischargingPlace").val( "" );
                    $( "#deleteForm .dischargeDate").val( "" );
                    $( "#deleteForm .sampleName").val( "" );
                    $( "#deleteForm .weediness").val( "" );
                    $( "#deleteForm .gluten").val( "" );
                    $( "#deleteForm .humidity").val( "" );
                    $( "#deleteForm .cardId").val( "" ); 
                }
                else{
                    alert("ошибка удаления карты");
                }
            }
        });
    });
    function setNewCardNumber(data) { 
	var cardNumber= data.cardNumber;
        if (cardNumber!=null && cardNumber!=0){
            $( "#addForm .cardNumber" ).val( cardNumber );
        }
	$.get("${pageContext.request.contextPath}/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
    }
    function setCardData(data) { 
        var card=data.card;
	if (card!=null && card!=""){
            $( "#deleteForm .cardNumber").val( card.cardNumber );
            $( "#deleteForm .name").val( card.car.driver.name );
            $( "#deleteForm .organization").val( card.car.driver.organization );
            $( "#deleteForm .mobileNumber").val( card.car.driver.mobileNumber );
            $( "#deleteForm .firstNumber").val( card.car.firstNumber );
            $( "#deleteForm .secondNumber").val( card.car.secondNumber );
            var createDate = new Date(card.car.createDate).toLocaleString("ru-ru", dateOptions);
            $( "#deleteForm .createDate").val(createDate);
            $( "#deleteForm .weightIn").val( card.car.cargo.weightIn );
            $( "#deleteForm .weightOut").val( card.car.cargo.weightOut );
            $( "#deleteForm .dischargingPlace").val( card.car.cargo.dischargingPlace );
            $( "#deleteForm .dischargeDate").val( card.car.cargo.dischargeDate );
            if (card.car.cargo.sample!=null){
                $( "#deleteForm .sampleName").val( card.car.cargo.sample.name );
                $( "#deleteForm .weediness").val( card.car.cargo.sample.weediness );
                $( "#deleteForm .gluten").val( card.car.cargo.sample.gluten );
                $( "#deleteForm .humidity").val( card.car.cargo.sample.humidity );
            }else{
                $( "#deleteForm .sampleName").val( "" );
                $( "#deleteForm .weediness").val( "" );
                $( "#deleteForm .gluten").val( "" );
                $( "#deleteForm .humidity").val( "" );
            }
            $( "#deleteForm .cardId").val( card.id );
        } 
	$.get("${pageContext.request.contextPath}/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    }	
    var dateOptions = {
		weekday: "short", year: "numeric", month: "short",
		day: "numeric", hour: "2-digit", minute: "2-digit"
            };
</script>

    