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
//on page load
function sendAjaxGetData(){
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : contextPath+"/dock?cmd=getDocksData",
        data     : {},
        success  : function(data) {
            setData(data);
        }
    });
}
$(window).load( function(){
    //set screens
    setDisplay(dock1Id,dockStep[dock1Id]);
    setDisplay(dock2Id,dockStep[dock2Id]);
    //get docks data
    sendAjaxGetData();
    //arrivalForm submit action
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
    //releaseForm submit action
    $( ".releaseForm" ).submit(function( event ) {
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
                    sendAjaxGetData();
                    setDisplay(dockId,1);

                }
                else{
                    alert("Ошибка");
                }
            }
        });
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
        cell=newrow.insertCell(-1);
        cell.classList.add("humidity");
        cell.innerHTML = cards[i].car.cargo.sample.humidity;
        cell=newrow.insertCell(-1);
        cell.classList.add("nomenclature");
        cell.innerHTML = cards[i].car.cargo.sample.nomenclature;
        cell=newrow.insertCell(-1);
        cell.classList.add("class");
        cell.innerHTML = cards[i].car.cargo.sample.cultureClass;
        cell=newrow.insertCell(-1);
        cell.classList.add("siloNumber");
        cell.innerHTML = cards[i].car.siloNumber;
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
        $("#"+dockId+" .releaseForm .firstName").val(card.car.driver.firstName);
        $("#"+dockId+" .releaseForm .lastName").val(card.car.driver.lastName);
        $("#"+dockId+" .releaseForm .organization").val(card.car.driver.organization);
        $("#"+dockId+" .releaseForm .carNumber").val(card.car.carNumber);
        $("#"+dockId+" .releaseForm .siloNumber").val(card.car.siloNumber);
        $("#"+dockId+" .releaseForm .weightIn").val(card.car.cargo.weightIn);
        $("#"+dockId+" .releaseForm .humidity").val(card.car.cargo.sample.humidity);
        $("#"+dockId+" .releaseForm .nomenclature").val(card.car.cargo.sample.nomenclature);
        $("#"+dockId+" .releaseForm .class").val(card.car.cargo.sample.cultureClass);
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