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
        <title>Archive cars</title>
        <link href="${pageContext.request.contextPath}/resources/css/archiveCars.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/style_pickdate.css" media="screen" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/javascript/will_pickdate.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery.mousewheel.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="period">
            <form id="periodForm" action="${pageContext.request.contextPath}/archive?cmd=getCars" method="post" autocomplete="off">
                <p>с</p> <input type="text" name="from" size="40" style="display: inline-block;" id="start_time"/><input type="text" size="40" style="display: none;"/>
                <p>по</p> <input type="text" name="to" size="40" style="display: inline-block;" id="stop_time"/><input type="text" size="40" style="display: none;"/>       
                <input type="submit" value="Период">
            </form>
        </div>
        <div id="selectCar">
            <table id="cars" width="100%" cellpadding="2" >
                <th class="cars_header">№</th>
                <th class="cars_header">Номер авто</th>
                <th class="cars_header">Имя пробы</th>
                <th class="cars_header">Напр-ление</th>
                <th class="cars_header">Засор-ть,%</th>
                <th class="cars_header">Клейк-на,%</th>
                <th class="cars_header">Влаж-ть,%</th>
                <th class="cars_header">Время въезда</th>
            </table>
	</div>
        <div id="params">
            Номер машины<br><input class="carNumber" type="text" name="carNumber" value="" readonly><br>
            Номер ТТН<br><input class="ttnNumber" type="text" name="ttnNumber" value="" readonly><br><br>
            Водитель<br>
            Имя<br><input class="name" type="text" name="name" value="" readonly><br>
            Организация<br><input class="organization" type="text" name="organization" value="" readonly><br>
            Номер телефона<br><input class="mobileNumber" type="text" name="mobileNumber" value="" readonly><br><br>
            Проба<br>
            Имя пробы<br><input class="sampleName" type="text" name="sampleName" value="" readonly><br>
            Засоренность, %<br><input class="weediness" type="text" name="weediness" value="" readonly><br>
            Клейковина, %<br><input class="gluten" type="text" name="gluten" value="" readonly><br>
            Влажность, %<br><input class="humidity" type="text" name="humidity" value="" readonly><br><br>
        </div> 
    </body>
</html>
<script>
    var  start_time =""; 
    var stop_time=""; 
    var selectedId=0;
    var carList;
    $(window).load( function(){
        setDefultdate();
        $( "#paramForm .queueId").val(-1); 
	$.ajax({
            type     : "POST",
            cache    : false,
            url      : "${pageContext.request.contextPath}/archive?cmd=getCars",
            data     : {"from":start_time,"to":stop_time},
            success  : function(data) {
                setData(data);
            }
        });
    });
    $('#update').click(function () {
        start_time = document.getElementById('start_time').value;
        stop_time = document.getElementById('stop_time').value; 
        alert(start_time+"-"+stop_time);
    });
    $(function(){
        $('#periodForm input[type="text"]').will_pickdate({ 
            format: 'Y/m/d H:i:s', 
            inputOutputFormat: 'Y/m/d H:i:s',
            days: ['Вс','Пн', 'Вт', 'Ср', 'Чт','Пт', 'Сб'],
            months: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
            timePicker: true,
            militaryTime: true,
            allowEmpty:true ,
            yearsPerPage:3,
            allowEmpty:true,
            startDay: 1,
            positionOffset: { x: 0, y: 0 }
            });
    });	
    $( "#periodForm" ).submit(function( event ) {
        event.preventDefault();
        console.log('Sending request to '+$(this).attr('action')+' with data: '+$(this).serialize());
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : $(this).attr('action'),
            data     : $(this).serialize(),
            success  : function(data) {
                setData(data);
            }
        });
    });
    function setData(data) { 
	var cars=data.cars;
        carList=cars;
        var tableCars = document.getElementById("cars");
        //clean table
        for(var i = tableCars.rows.length-1; i > 0; i--){
            tableCars.deleteRow(i);
        }
        var newrow;
        var createDate;
        var cell;
        //set data in table
	for (var i = 0; i < cars.length; i++) { 
            newrow = tableCars.insertRow(i+1);
            newrow.id=cars[i].id;
            if (i%2>0) newrow.classList.add("even");
            else newrow.classList.add("odd");
            cell=newrow.insertCell(-1);
            cell.classList.add("rowNumber");
            cell.innerHTML = i+1;
            cell=newrow.insertCell(-1);
            cell.classList.add("sampleName");
            if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.name;
            cell=newrow.insertCell(-1);
            cell.classList.add("carNumber");
            cell.innerHTML = cars[i].carNumber;
            cell=newrow.insertCell(-1);
            cell.classList.add("destination");
            cell.innerHTML = cars[i].destination;
            cell=newrow.insertCell(-1);
            cell.classList.add("weediness");
            if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.weediness;
            cell=newrow.insertCell(-1);
            cell.classList.add("gluten");
            if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.gluten;
            cell=newrow.insertCell(-1);
            cell.classList.add("humidity");
            if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.humidity;
            createDate = new Date(cars[i].createDate).toLocaleString("ru-ru", dateOptions);
            cell=newrow.insertCell(-1);
            cell.classList.add("createDate");
            cell.innerHTML = createDate;
            //select
            if (selectedId==cars[i].id){
                selectCar( newrow );
            }
            newrow.onclick = function() {
                selectCar( this );
            };
	}
    }
    //set data foor chosed car
    function selectCar(e){
        selectedId = e.id;
        var car=null;
        for (var i=0; i<carList.length; i++){
            if (carList[i].id==selectedId) car=carList[i];
        }
        if (car!=null){
            $( "#params .carNumber").val( car.carNumber );
            $( "#params .ttnNumber").val( car.ttnNumber );
            $( "#params .name").val( car.driver.name );
            $( "#params .organization").val( car.driver.organization );
            $( "#params .mobileNumber").val( car.driver.mobileNumber );
            if (car.cargo.sample!=null){
                $( "#params .sampleName").val( car.cargo.sample.name );
                $( "#params .weediness").val( car.cargo.sample.weediness );
                $( "#params .gluten").val( car.cargo.sample.gluten );
                $( "#params .humidity").val( car.cargo.sample.humidity );
            } else{
                $( "#params .sampleName").val( "" );
                $( "#params .weediness").val( "" );
                $( "#params .gluten").val( "" );
                $( "#params .humidity").val( "" );
            }
            $( "#params .carId").val( car.id );
        }
        $(".selected").removeClass("selected");
        e.classList.add("selected");
    }
    var dateOptions = {
                day: "numeric", month: "numeric", year: "numeric",
		hour: "2-digit", minute: "2-digit"
	};
    function zeroPad(val){
        if(val>0 && val<10)
        {
            return "0"+val;
        }
        return val;
    }
    function setDefultdate(){
        var now = new Date();
        var curr_month = now.getMonth() + 1;
        start_time = now.getFullYear()+"/"+zeroPad(curr_month)+"/"+zeroPad(now.getDate())+" "+ "00:00:00";
        stop_time = now.getFullYear()+"/"+zeroPad(curr_month)+"/"+zeroPad(now.getDate())+" "+
                zeroPad(now.getHours())+":"+zeroPad(now.getMinutes())+":"+zeroPad(now.getSeconds());
        document.getElementById('start_time').value =  start_time;
        document.getElementById('stop_time').value =  stop_time;
        document.getElementById('start_time_display').value =  start_time;
        document.getElementById('stop_time_display').value =  stop_time;
    }; 
</script>

    