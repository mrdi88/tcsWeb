var selectedId=0;
var cardList;
$(window).load( function(){
    //include the csrf token within all Ajax requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : contextPath+"/card?cmd=getCurrentCards",
        data     : {},
        success  : function(data) {
            setData(data);
        }
    });
});
function setData(data) { 
    var cards=data.cards;
    cardList=cards;
    var tableCars = document.getElementById("cards");
    //clean table
    for(var i = tableCars.rows.length-1; i >= 0; i--){
        tableCars.deleteRow(i);
    }
    var newrow;
    var createDate;
    var cell;
    //set data in table
    for (var i = 0; i < cards.length; i++) { 
        newrow = tableCars.insertRow(i);
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
        cell.classList.add("destination");
        cell.innerHTML = cards[i].car.destination;
        cell=newrow.insertCell(-1);
        cell.classList.add("siloNumber");
        cell.innerHTML = cards[i].car.siloNumber;
        cell=newrow.insertCell(-1);
        cell.classList.add("nomenclature");
        if (cards[i].car.cargo.sample!=null) cell.innerHTML = cards[i].car.cargo.sample.nomenclature;
        cell=newrow.insertCell(-1);
        cell.classList.add("class");
        if (cards[i].car.cargo.sample!=null) cell.innerHTML = cards[i].car.cargo.sample.cultureClass;
        cell=newrow.insertCell(-1);
        cell.classList.add("humidity");
        if (cards[i].car.cargo.sample!=null) cell.innerHTML = cards[i].car.cargo.sample.humidity;
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
//set data foor chosed card
function selectCard(e){
    selectedId = e.id;
    var card=null;
    for (var i=0; i<cardList.length; i++){
        if (cardList[i].id==selectedId) card=cardList[i];
    }
    if (card!=null){
        $( "#params .firstName").val( card.car.driver.firstName );
        $( "#params .lastName").val( card.car.driver.lastName );
        $( "#params .organization").val( card.car.driver.organization );
        $( "#params .mobileNumber").val( card.car.driver.mobileNumber );
        $( "#params .carNumber").val( card.car.carNumber );
        $( "#params .ttnNumber").val( card.car.ttnNumber );
        $( "#params .culture").val( card.car.nomenclature );
        var createDate = "";
        if (card.car.createDate!=null){
            createDate = new Date(card.car.createDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#params .createDate").val( createDate );
        $( "#params .weightIn").val( card.car.cargo.weightIn );
        $( "#params .weightOut").val( card.car.cargo.weightOut );
        $( "#params .dischargingPlace").val( card.car.cargo.dischargingPlace );
        var dischargeDate = "";
        if (card.car.cargo.dischargeDate!=null){
            dischargeDate = new Date(card.car.cargo.dischargeDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#params .dischargeDate").val( dischargeDate );
        $( "#params .destination").val( card.car.destination );
        $( "#params .siloNumber").val( card.car.siloNumber );
        if (card.car.cargo.sample!=null){
            $( "#params .sampleName").val( card.car.cargo.sample.name );
            $( "#params .humidity").val( card.car.cargo.sample.humidity );
            $( "#params .nomenclature").val( card.car.cargo.sample.nomenclature );
            $( "#params .class").val( card.car.cargo.sample.cultureClass );
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
