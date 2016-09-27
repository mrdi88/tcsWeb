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
        <title>Manage samples</title>
        <link href="${pageContext.request.contextPath}/resources/css/manageSamples.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
    </head>
    <body>
        <div id="selectCard">
            <table id="cards" width="100%" cellpadding="2" >
                <th class="cards_header">№</th>
                <th class="cards_header">Имя пробы</th>
                <th class="cards_header">Номер авто</th>
                <th class="cards_header">Напр-ление</th>
                <th class="cards_header">Засор-ть,%</th>
                <th class="cards_header">Клейк-на,%</th>
                <th class="cards_header">Влаж-ть,%</th>
                <th class="cards_header">Время въезда</th>
            </table>
	</div>
        <div id="params">
            <form id="paramForm" action="${pageContext.request.contextPath}/laboratory/manage?cmd=assignParams" method="post" autocomplete="off">
                Номер машины<br><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
                Номер ТТН<br><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br><br>
                Водитель<br>
                Имя<br><input class="name" type="text" name="name" value="" readonly><br>
                Организация<br><input class="organization" type="text" name="organization" value="" readonly><br>
                Номер телефона<br><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
                Проба<br>
                Имя пробы<br><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
                Засоренность, %<br><input class="weediness" type="text" name="weediness" value="" ><br>
                Клейковина, %<br><input class="gluten" type="text" name="gluten" value="" ><br>
                Влажность, %<br><input class="humidity" type="text" name="humidity" value="" ><br><br>
                Направление<br>
                <select size="1" class="queueId" name="queueId">
                    <option disabled>Выберите направление</option>
                    <option value="0">не назначено</option>
                    <c:forEach var="queueName" items="${queueList}">
                        <option value=${queueName.id}>${queueName.name}</option>
                    </c:forEach>       
                </select>
                <br><br>
                <input class="cardId" type="hidden" name="cardId" value="" readonly>
                <input style="float: right;" type="submit" value="Применить">
            </form>
        </div> 
    </body>
</html>
<script>
    var selectedId=0;
    var cardList;
    $(window).load( function(){
        $( "#paramForm .queueId").val(-1); 
	$.get("${pageContext.request.contextPath}/laboratory/manage?cmd=getAssignedCards",{ "_": $.now() }, function(data) {setCards(data);});
    });
    //setInterval(function() {
	//$.get("${pageContext.request.contextPath}/laboratory/manage?cmd=getAssignedCards",{ "_": $.now() }, function(data) {setCards(data);});
    //}, 3000);
    $( "#paramForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#paramForm .cardId").val()==""){
            alert("Выберите пробу");
            return;
        }
        if ($( "#paramForm .weediness").val()==""){
            alert("Введите параметр 'Засоренность'");
            return;
        }
        if ($( "#paramForm .gluten").val()==""){
            alert("Введите параметр 'Клейкость'");
            return;
        }
        if ($( "#paramForm .humidity").val()==""){
            alert("Введите параметр 'Влажность'");
            return;
        }
        if ($( "#paramForm .queueId").val()==null){
            alert("Выберите направление");
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
                    $( "#paramForm .carNumber").val( "" );
                    $( "#paramForm .ttnNumber").val( "" );
                    $( "#paramForm .name").val( "" );
                    $( "#paramForm .organization").val( "" );
                    $( "#paramForm .mobileNumber").val( "" );
                    $( "#paramForm .sampleName").val( "" );
                    $( "#paramForm .weediness").val( "" );
                    $( "#paramForm .gluten").val( "" );
                    $( "#paramForm .humidity").val( "" );
                    $( "#paramForm .cardId").val( "" );
                    $( "#paramForm .queueId").val(-1); 
                    selectedId=0;
                    $.get("${pageContext.request.contextPath}/laboratory/manage?cmd=getAssignedCards",{ "_": $.now() }, function(data) {setCards(data);});
                }
                else{
                    alert("Ошибка установки параметров");
                }
            }
        });
    });
    function setCards(data) { 
	var cards=data.cards;
        cardList=cards;
        var tableCards = document.getElementById("cards");
        //clean table
        for(var i = tableCards.rows.length-1; i > 0; i--){
            tableCards.deleteRow(i);
        }
        var newrow;
        var createDate;
        var cell;
        //set data in table
	for (var i = 0; i < cards.length; i++) { 
            newrow = tableCards.insertRow(i+1);
            newrow.id=cards[i].id;
            if (i%2>0) newrow.classList.add("even");
            else newrow.classList.add("odd");
            cell=newrow.insertCell(-1);
            cell.classList.add("rowNumber");
            cell.innerHTML = i+1;
            cell=newrow.insertCell(-1);
            cell.classList.add("sampleName");
            cell.innerHTML = cards[i].car.cargo.sample.name;
            cell=newrow.insertCell(-1);
            cell.classList.add("carNumber");
            cell.innerHTML = cards[i].car.carNumber;
            cell=newrow.insertCell(-1);
            cell.classList.add("destination");
            cell.innerHTML = cards[i].car.destination;
            cell=newrow.insertCell(-1);
            cell.classList.add("weediness");
            cell.innerHTML = cards[i].car.cargo.sample.weediness;
            cell=newrow.insertCell(-1);
            cell.classList.add("gluten");
            cell.innerHTML = cards[i].car.cargo.sample.gluten;
            cell=newrow.insertCell(-1);
            cell.classList.add("humidity");
            cell.innerHTML = cards[i].car.cargo.sample.humidity;
            createDate = new Date(cards[i].car.createDate).toLocaleString("ru-ru", dateOptions);
            cell=newrow.insertCell(-1);
            cell.classList.add("createDate");
            cell.innerHTML = createDate;
            //select
            if (selectedId==cards[i].id){
                selectCard( newrow );
            }
            newrow.onclick = function() {
                selectCard( this );
            };
	}
    }
    //set data for chosed card
    function selectCard(e){
        selectedId = e.id;
        var card=null;
        for (var i=0; i<cardList.length; i++){
            if (cardList[i].id==selectedId) card=cardList[i];
        }
        if (card!=null){
            $( "#paramForm .carNumber").val( card.car.carNumber );
            $( "#paramForm .ttnNumber").val( card.car.ttnNumber );
            $( "#paramForm .name").val( card.car.driver.name );
            $( "#paramForm .organization").val( card.car.driver.organization );
            $( "#paramForm .mobileNumber").val( card.car.driver.mobileNumber );
            $( "#paramForm .sampleName").val( card.car.cargo.sample.name );
            $( "#paramForm .weediness").val( card.car.cargo.sample.weediness );
            $( "#paramForm .gluten").val( card.car.cargo.sample.gluten );
            $( "#paramForm .humidity").val( card.car.cargo.sample.humidity );
            $( "#paramForm .cardId").val( card.id );
            //alert($( "#paramForm .queueId option:selected").text());
            $( "#paramForm .queueId").val(-1); 
            if (card.car.destination!="" && card.car.destination!=null) {
                $("#paramForm .queueId :contains('" + card.car.destination + "')").prop("selected", true);
            }
        }
        $(".selected").removeClass("selected");
        e.classList.add("selected");
    }
    var dateOptions = {
                day: "numeric", month: "numeric", year: "numeric",
		hour: "2-digit", minute: "2-digit"
	};
</script>

    