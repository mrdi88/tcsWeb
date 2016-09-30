$(window).load( function(){
    $.get(contextPath+"/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
    $.get(contextPath+"/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
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
        if ($( "#addForm .carNumber").val()==""){
            alert("Введите номер автомобиля");
            return;
        }
        if ($( "#addForm .ttnNumber").val()==""){
            alert("Введите номер ТТН");
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
                    $( "#addForm .firstName").val( "" );
                    $( "#addForm .lastName").val( "" );
                    $( "#addForm .organization").val( "" );
                    $( "#addForm .mobileNumber").val( "" );
                    $( "#addForm .carNumber").val( "" );
                    $( "#addForm .ttnNumber").val( "" );
                    $( "#addForm .culture").val( "" );
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
                    $( "#deleteForm .firstName").val( "" );
                    $( "#deleteForm .lastName").val( "" );
                    $( "#deleteForm .organization").val( "" );
                    $( "#deleteForm .mobileNumber").val( "" );
                    $( "#deleteForm .carNumber").val( "" );
                    $( "#deleteForm .ttnNumber").val( "" );
                    $( "#deleteForm .createDate").val( "" );
                    $( "#deleteForm .weightIn").val( "" );
                    $( "#deleteForm .weightOut").val( "" );
                    $( "#deleteForm .dischargingPlace").val( "" );
                    $( "#deleteForm .dischargeDate").val( "" );
                    $( "#deleteForm .sampleName").val( "" );
                    $( "#deleteForm .humidity").val( "" );
                    $( "#deleteForm .nomenclature").val( "" );
                    $( "#deleteForm .class").val( "" );
                    $( "#deleteForm .destination").val( "" );
                    $( "#deleteForm .siloNumber").val( "" );
                    $( "#deleteForm .cardId").val( "" ); 
                }
                else{
                    alert("ошибка удаления карты");
                }
            }
        });
    });
});
function setNewCardNumber(data) { 
    var cardNumber= data.cardNumber;
    if (cardNumber!=null && cardNumber!=0){
        $( "#addForm .cardNumber" ).val( cardNumber );
    }
    $.get(contextPath+"/card?cmd=getNewCardNumber",{ "_": $.now() }, function(data) {setNewCardNumber(data);});
}
function setCardData(data) { 
    var card=data.card;
    if (card!=null && card!=""){
        $( "#deleteForm .cardNumber").val( card.cardNumber );
        $( "#deleteForm .firstName").val( card.car.driver.firstName );
        $( "#deleteForm .lastName").val( card.car.driver.lastName );
        $( "#deleteForm .organization").val( card.car.driver.organization );
        $( "#deleteForm .mobileNumber").val( card.car.driver.mobileNumber );
        $( "#deleteForm .carNumber").val( card.car.carNumber );
        $( "#deleteForm .ttnNumber").val( card.car.ttnNumber );
        $( "#deleteForm .culture").val( card.car.nomenclature );
        var createDate = "";
        if (card.car.createDate!=null){
            createDate = new Date(card.car.createDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#deleteForm .createDate").val(createDate);
        $( "#deleteForm .weightIn").val( card.car.cargo.weightIn );
        $( "#deleteForm .weightOut").val( card.car.cargo.weightOut );
        $( "#deleteForm .dischargingPlace").val( card.car.cargo.dischargingPlace );
        var dischDate = "";
        if (card.car.cargo.dischargeDate!=null){
            dischDate = new Date(card.car.cargo.dischargeDate).toLocaleString("ru-ru", dateOptions);
        }
        $( "#deleteForm .dischargeDate").val( dischDate );
        if (card.car.cargo.sample!=null){
            $( "#deleteForm .sampleName").val( card.car.cargo.sample.name );
            $( "#deleteForm .humidity").val( card.car.cargo.sample.humidity );
            $( "#deleteForm .nomenclature").val( card.car.cargo.sample.nomenclature );
            $( "#deleteForm .class").val( card.car.cargo.sample.cultureClass );
            $( "#deleteForm .destination").val( card.car.destination );
            $( "#deleteForm .siloNumber").val( card.car.siloNumber );
        }else{
            $( "#deleteForm .sampleName").val( "" );
            $( "#deleteForm .humidity").val( "" );
            $( "#deleteForm .nomenclature").val( "" );
            $( "#deleteForm .class").val( "" );
            $( "#deleteForm .destination").val( "" );
            $( "#deleteForm .siloNumber").val( "" );
        }
        $( "#deleteForm .cardId").val( card.id );
    } 
    $.get(contextPath+"/card?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
}	
var dateOptions = {
            weekday: "short", year: "numeric", month: "short",
            day: "numeric", hour: "2-digit", minute: "2-digit"
};