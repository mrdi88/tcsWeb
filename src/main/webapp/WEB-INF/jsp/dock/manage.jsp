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
                <div class="listScreen">
                    <p>Выберите автомобиль</p>
                    <table id="infoTable" class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Дата въезда</th>
                    </table>
                </div>
                <div class="arrivalScreen">
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input type="submit" value="Да">
                        <input type="submit" value="Нет">
                    </form>
                </div>
                <div class="releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        Имя<br><input class="driverName" type="text" value="" readonly><br><br>
                        Номер авто<br><input class="carNumber" type="text" value="" readonly><br><br>
                        Время въезда<br><input class="dateIn" type="text" value="" readonly><br><br>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input type="submit" value="Отпустить">
                    </form>
                </div>
            </div>
            <div class="dock" id="dock2">
                <div class="listScreen">
                    <p>Выберите автомобиль</p>
                    <table id="infoTable" class="queueTable" width="100%" cellpadding="2" >
                        <th class="header">№</th>
                        <th class="header">Номер авто</th>
                        <th class="header">Дата въезда</th>
                    </table>
                </div>
                <div class="arrivalScreen">
                    <p>Автомобиль приехал?</p>
                    <form class="arrivalForm" action="${pageContext.request.contextPath}/dock?cmd=acceptCar" method="post" autocomplete="off">
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input type="submit" value="Да">
                        <input type="submit" value="Нет">
                    </form>
                </div>
                <div class="releaseScreen">
                    <form class="releaseForm" action="${pageContext.request.contextPath}/dock?cmd=releaseCar" method="post" autocomplete="off">
                        Имя<br><input class="driverName" type="text" value="" readonly><br><br>
                        Номер авто<br><input class="carNumber" type="text" value="" readonly><br><br>
                        Время въезда<br><input class="dateIn" type="text" value="" readonly><br><br>
                        <input class="cardId" type="hidden" name="cardId" value="" >
                        <input class="queueName" type="hidden" name="queueName" value="" >
                        <input type="submit" value="Отпустить">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    $(window).load( function(){
        //$.get("${pageContext.request.contextPath}/queue?cmd=getQueueList",{ "_": $.now() }, function(data) {setData(data);});
    });

    function setData(data) { 
	var queues=data.queues;
        for (var i=0; i<queues.length; i++){
            switch(queues[i].name){
                case "Buffer":
                    setQueueTableData(document.getElementById("buffer"), queues[i]);
                    break;
                case "MainTable":
                    setQueueTableData(document.getElementById("infoTable"), queues[i]);
                    break;
                case "R01":
                    setQueueTableData(document.getElementById("R01"), queues[i]);
                    break;
                case "R02":
                    setQueueTableData(document.getElementById("R02"), queues[i]);
                    break;        
            }
        }
    }
    function setQueueTableData(table, queue) { 
        //clean table
        for(var i = table.rows.length-1; i > 0; i--){
                table.deleteRow(i);
        }
        var newrow;
        var cell;
        var cards=queue.cards;
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
            cell=newrow.insertCell(-1);
            cell.classList.add("weediness");
            cell.innerHTML = cards[i].car.cargo.sample.weediness;
            cell=newrow.insertCell(-1);
            cell.classList.add("gluten");
            cell.innerHTML = cards[i].car.cargo.sample.gluten;
            cell=newrow.insertCell(-1);
            cell.classList.add("humidity");
            cell.innerHTML = cards[i].car.cargo.sample.humidity;
        }
    }
</script>