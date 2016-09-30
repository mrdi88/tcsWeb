var selectedId=0;
var cardList;
$(window).load( function(){
    $( "#paramForm .queueId").val(-1); 
    $.get(contextPath+"/laboratory/manage?cmd=getAssignedCards",{ "_": $.now() }, function(data) {setCards(data);});
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
                    $( "#paramForm .culture").val( "" );
                    $( "#paramForm .firstName").val( "" );
                    $( "#paramForm .lastName").val( "" );
                    $( "#paramForm .name").val( "" );
                    $( "#paramForm .organization").val( "" );
                    $( "#paramForm .mobileNumber").val( "" );
                    $( "#paramForm .sampleName").val( "" );
                    $( "#paramForm .nomenclature").val( "" );
                    $( "#paramForm .class").val( "" );
                    $( "#paramForm .siloNumber").val( "" );
                    $( "#paramForm .humidity").val( "" );
                    $( "#paramForm .cardId").val( "" );
                    $( "#paramForm .queueId").val(-1); 
                    selectedId=0;
                    $.get(contextPath+"/laboratory/manage?cmd=getAssignedCards",{ "_": $.now() }, function(data) {setCards(data);});
                }
                else{
                    alert("Ошибка установки параметров");
                }
            }
        });
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
        cell.classList.add("siloNumber");
        cell.innerHTML = cards[i].car.siloNumber;
        cell=newrow.insertCell(-1);
        cell.classList.add("humidity");
        cell.innerHTML = cards[i].car.cargo.sample.humidity;
        cell=newrow.insertCell(-1);
        cell.classList.add("nomenclature");
        cell.innerHTML = cards[i].car.cargo.sample.nomenclature;
        cell=newrow.insertCell(-1);
        cell.classList.add("class");
        cell.innerHTML = cards[i].car.cargo.sample.cultureClass;
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
        $( "#paramForm .culture").val( card.car.nomenclature );
        $( "#paramForm .firstName").val( card.car.driver.firstName );
        $( "#paramForm .lastName").val( card.car.driver.lastName );
        $( "#paramForm .organization").val( card.car.driver.organization );
        $( "#paramForm .mobileNumber").val( card.car.driver.mobileNumber );
        $( "#paramForm .sampleName").val( card.car.cargo.sample.name );
        $( "#paramForm .nomenclature").val( card.car.cargo.sample.nomenclature );
        $( "#paramForm .class").val( card.car.cargo.sample.cultureClass );
        $( "#paramForm .siloNumber").val( card.car.siloNumber );
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