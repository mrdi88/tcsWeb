var start_time =""; 
var stop_time=""; 
var selectedId=0;
var carList;
$(window).load( function(){
    //include the csrf token within all Ajax requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
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
    setDefultdate();
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : contextPath+"/archive?cmd=getCars",
        data     : {"from":start_time,"to":stop_time},
        success  : function(data) {
            setData(data);
        }
    });
    $( "#periodForm" ).submit(function( event ) {
        event.preventDefault();
        var from=new Date(this["start_time"].value);
        var to=new Date(this["stop_time"].value);
        //format: 'Y/m/d H:i:s', 
        var from_UTCstr=from.getUTCDate();
        var from_str=""+from.getUTCFullYear()+"/"+(from.getUTCMonth()+1)+"/"+from.getUTCDate()+" "+
            from.getUTCHours()+":"+from.getUTCMinutes()+":"+from.getUTCSeconds();
        var to_str=""+to.getUTCFullYear()+"/"+(to.getUTCMonth()+1)+"/"+to.getUTCDate()+" "+
            to.getUTCHours()+":"+to.getUTCMinutes()+":"+to.getUTCSeconds();
        
        console.log('Sending request to '+$(this).attr('action')+' with data: '+$(this).serialize());
        $.ajax({
            type     : "POST",
            cache    : false,
            url      : $(this).attr('action'),
            data     : {"from":from_str,"to":to_str},
            success  : function(data) {
                setData(data);
            }
        });
    });
});
function setData(data) { 
    var cars=data.cars;
    carList=cars;
    var tableCars = document.getElementById("cars");
    //clean table
    for(var i = tableCars.rows.length-1; i >= 0; i--){
        tableCars.deleteRow(i);
    }
    var newrow;
    var createDate;
    var cell;
    //set data in table
    for (var i = 0; i < cars.length; i++) { 
        newrow = tableCars.insertRow(i);
        newrow.id=cars[i].id;
        if (i%2>0) newrow.classList.add("even");
        else newrow.classList.add("odd");
        cell=newrow.insertCell(-1);
        cell.classList.add("rowNumber");
        cell.innerHTML = i+1;
        cell=newrow.insertCell(-1);
        cell.classList.add("carNumber");
        cell.innerHTML = cars[i].carNumber;
        cell=newrow.insertCell(-1);
        cell.classList.add("destination");
        cell.innerHTML = cars[i].destination;
        cell=newrow.insertCell(-1);
        cell.classList.add("siloNumber");
        cell.innerHTML = cars[i].siloNumber;
        cell=newrow.insertCell(-1);
        cell.classList.add("nomenclature");
        if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.nomenclature;
        cell=newrow.insertCell(-1);
        cell.classList.add("class");
        if (cars[i].cargo.sample!=null) cell.innerHTML = cars[i].cargo.sample.cultureClass;
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
        $( "#params .firstName").val( car.driver.firstName );
        $( "#params .lastName").val( car.driver.lastName );
        $( "#params .organization").val( car.driver.organization );
        $( "#params .mobileNumber").val( car.driver.mobileNumber );
        $( "#params .carNumber").val( car.carNumber );
        $( "#params .ttnNumber").val( car.ttnNumber );
        $( "#params .culture").val( car.nomenclature );
        var createDate = "";
        if (car.createDate!=null){
            createDate = new Date(car.createDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#params .createDate").val( createDate );
        $( "#params .weightIn").val( car.cargo.weightIn );
        $( "#params .weightOut").val( car.cargo.weightOut );
        $( "#params .dischargingPlace").val( car.cargo.dischargingPlace );
        var dischargeDate = "";
        if (car.cargo.dischargeDate!=null){
            dischargeDate = new Date(car.cargo.dischargeDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#params .dischargeDate").val( dischargeDate );
        $( "#params .destination").val( car.destination );
        $( "#params .siloNumber").val( car.siloNumber );
        if (car.cargo.sample!=null){
            $( "#params .sampleName").val( car.cargo.sample.name );
            $( "#params .humidity").val( car.cargo.sample.humidity );
            $( "#params .nomenclature").val( car.cargo.sample.nomenclature );
            $( "#params .class").val( car.cargo.sample.cultureClass );
        } else{
            $( "#params .sampleName").val( "" );
            $( "#params .humidity").val( "" );
            $( "#params .nomenclature").val( "" );
            $( "#params .class").val( "" );
        }
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