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
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/manageDocks.css" rel="stylesheet" type="text/css"/>
        <title>Управление доком</title>
    </head>
    <body>
        <div class="docks">
            <div class="dock" id="dock1">
                <div class="screen listScreen">
                    <p>Выберите автомобиль</p>
                    <table class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Дата въезда</th>
                    </table>
                </div>
                <div class="screen arrivalScreen">
                    <p>Вызван автомобиль</p>
                    <p class="carNumber"></p>
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this)">
                    </form>
                </div>
                <div class="screen releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        Имя<br><input class="driverName" type="text" value="" readonly><br><br>
                        Номер авто<br><input class="carNumber" type="text" value="" readonly><br><br>
                        Время въезда<br><input class="dateIn" type="text" value="" readonly><br><br>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class="cancel" type="button" value="Отмена" onclick="resetScreen(this)">
                        <input class="accept" type="submit" value="Отпустить">
                    </form>
                </div>
            </div>
            <div class="dock" id="dock2">
                <div class="screen listScreen">
                    <p>Выберите автомобиль</p>
                    <table class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Дата въезда</th>
                    </table>
                </div>
                <div class="screen arrivalScreen">
                    <p>Вызван автомобиль</p><br>
                    <p class="carNumber"></p>
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class= "accept" type="submit" value="Да">
                        <input class= "cancel" type="button" value="Нет" onclick="resetScreen(this)">
                    </form>
                </div>
                <div class="screen releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        Имя<br><input class="driverName" type="text" value="" readonly><br><br>
                        Номер авто<br><input class="carNumber" type="text" value="" readonly><br><br>
                        Время въезда<br><input class="dateIn" type="text" value="" readonly><br><br>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input class="cancel" type="button" value="Отмена" onclick="resetScreen(this)">
                        <input class="accept" type="submit" value="Отпустить">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    //dock id
    var dock1Id="dock1";
    var dock2Id="dock2";
    //save docks queues
    var dockQueues= new Array();
    //to remember seletced card
    var dockSelectedCard= new Array();
    //for setting screens
    var dockStep=new Array();
    dockStep[dock1Id]=1;
    dockStep[dock2Id]=1;
    //set screens
    setDisplay(dock1Id,dockStep[dock1Id]);
    setDisplay(dock2Id,dockStep[dock2Id]);
    //on page load
    $(window).load( function(){
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : "${pageContext.request.contextPath}/dock?cmd=getDocksData",
            data     : {},
            success  : function(data) {
                setData(data);
            }
        });
    });
    $( ".arrivalForm" ).submit(function( event ) {
        event.preventDefault();
        console.log('Sending request to '+$(this).attr('action')+' with data: '+$(this).serialize());
        var dockId;
        if ($.contains( document.getElementById(dock1Id),this[0])){
            dockId=dock1Id;
        }else 
        if ($.contains( document.getElementById(dock2Id),this[0])){
            dockId=dock2Id;
        } 
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : $(this).attr('action'),
            data     : $(this).serialize(),
            success  : function(data) {
                if (data.result=="true"){
                    //display arrival screen
                    setDisplay(dockId,3);
                }
                else{
                    alert("Ошибка");
                }
            }
        });
    });
    function resetScreen(element){
        var dockId=null;
        if ($.contains( document.getElementById(dock1Id),element)){
            dockId=dock1Id;
        }else 
        if ($.contains( document.getElementById(dock2Id),element)){
            dockId=dock2Id;
        } 
        if (dockId!=null){
            dockStep[dockId]=1;
            setDisplay(dockId,dockStep[dockId]);
        }
    }
    //set queue list in table
    function setData(data) { 
	var queues=data.queues;
        if (queues.length==2){
            setDockData(dock1Id, queues[0]);
            setDockData(dock2Id, queues[1]);       
        }
    }
    function setDockData(dockId, queue) { 
        var table=$("#"+dockId+" .listScreen .queueTable")[0];
        //clean table
        for(var i = table.rows.length-1; i > 0; i--){
                table.deleteRow(i);
        }
        var newrow;
        var cell;
        var cards=queue.cards;
        //save dock cards
        dockQueues[dockId]=queue;
        //set data in table
	for (var i = 0; i < cards.length; i++) { 
            newrow = table.insertRow(i+1);
            newrow.id=cards[i].id;
            if (i%2>0) newrow.classList.add("even");
            else newrow.classList.add("odd");
            cell=newrow.insertCell(-1);
            cell.classList.add("rowNumber");
            cell.innerHTML = i+1;
            cell=newrow.insertCell(-1);
            cell.classList.add("carNumber");
            cell.innerHTML = cards[i].car.carNumber;
            createDate = new Date(cards[i].car.createDate).toLocaleString("ru-ru", dateOptions);
            cell=newrow.insertCell(-1);
            cell.classList.add("createDate");
            cell.innerHTML = createDate;
            //select event for row
            newrow.onclick = function() {
                selectCard( this );
            };
        }
    }
    //set next step for choosed card
    function selectCard(e){
        var cards=null;
        var queue=null;
        var queueName=null;
        var dockId;
        if ($.contains( document.getElementById(dock1Id),e)){
            queue=dockQueues[dock1Id]
            cards=queue.cards;
            queueName=queue.name;
            dockId=dock1Id;
        }else 
        if ($.contains( document.getElementById(dock2Id),e)){
            queue=dockQueues[dock2Id]
            cards=queue.cards;
            queueName=queue.name;
            dockId=dock2Id;
        } 
        if (cards!=null){
            var card=null;
            for(var i=0;i<cards.length; i++){
                if (e.id==cards[i].id){
                    card=cards[i];
                    break;
                }
            }
        }
        if (card!=null){
            //set data in arrivalForm
            $("#"+dockId+" .arrivalScreen .carNumber").text(card.car.carNumber);
            $("#"+dockId+" .arrivalForm .cardId").val(e.id);
            $("#"+dockId+" .arrivalForm .queueName").val(queueName);
            //set data in releaseForm
            $("#"+dockId+" .releaseForm .cardId").val(e.id);
            $("#"+dockId+" .releaseForm .queueName").val(queueName);
            $("#"+dockId+" .releaseForm .driverName").val(card.car.driver.name);
            $("#"+dockId+" .releaseForm .carNumber").val(card.car.carNumber);
            var createDate = new Date(cards[i].car.createDate).toLocaleString("ru-ru", dateOptions);
            $("#"+dockId+" .releaseForm .dateIn").val(createDate);
            //display arrival screen
            dockSelectedCard[dockId]=card;
            setDisplay(dockId,2);
        }
    }
    var dateOptions = {
                day: "numeric", month: "numeric", year: "numeric",
		hour: "2-digit", minute: "2-digit"
    };
    //switcihng displays
    function setDisplay(dockId,step){
        $("#"+dockId+" .screen").addClass("hidden");
        switch(step){
            case 1:
                $("#"+dockId+" .listScreen").removeClass("hidden");
                break;
            case 2:
                $("#"+dockId+" .arrivalScreen").removeClass("hidden");
                break;
            case 3:
                $("#"+dockId+" .releaseScreen").removeClass("hidden");
                break;
        }
        dockStep[dockId]=step;
    }
</script>