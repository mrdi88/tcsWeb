$(window).load( function(){
    //include the csrf token within all Ajax requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $.get(contextPath+"/laboratory?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
    $( "#assignForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#assignForm .cardId").val()==""){
            alert("Поднесите карту к считывателю");
            return;
        }
        if ($( "#assignForm .sampleName").val()==""){
            alert("Введите номер пробы");
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
                    $( "#assignForm .cardNumber").val( "" );
                    $( "#assignForm .firstName").val( "" );
                    $( "#assignForm .lastName").val( "" );
                    $( "#assignForm .organization").val( "" );
                    $( "#assignForm .mobileNumber").val( "" );
                    $( "#assignForm .carNumber").val( "" );
                    $( "#assignForm .ttnNumber").val( "" );
                    $( "#assignForm .culture").val( "" );
                    $( "#assignForm .createDate").val( "" );
                    $( "#assignForm .cardId").val( "" );
                    $( "#assignForm .sampleName").val( "" );
                }
                else{
                    alert("ошибка привязки пробы");
                }
            }
        });
    });
});

function setCardData(data) { 
    var card=data.card;
    if (card!=null && card!=""){
        $( "#assignForm .cardNumber").val( card.cardNumber );
        $( "#assignForm .firstName").val( card.car.driver.firstName );
        $( "#assignForm .lastName").val( card.car.driver.lastName );
        $( "#assignForm .organization").val( card.car.driver.organization );
        $( "#assignForm .mobileNumber").val( card.car.driver.mobileNumber );
        $( "#assignForm .carNumber").val( card.car.carNumber );
        $( "#assignForm .ttnNumber").val( card.car.ttnNumber );
        $( "#assignForm .culture").val( card.car.nomenclature );
         var createDate = new Date(card.car.createDate).toLocaleString("ru-ru", dateOptions);
        $( "#assignForm .createDate").val( createDate );
        if (card.car.cargo.sample!=null){
           $( "#assignForm .sampleName").val( card.car.cargo.sample.name ); 
        } else $( "#assignForm .sampleName").val( "" );
        $( "#assignForm .cardId").val( card.id );
    } 
    $.get(contextPath+"/laboratory?cmd=getExistCardData",{ "_": $.now() }, function(data) {setCardData(data);});
}
var dateOptions = {
            weekday: "short", year: "numeric", month: "short",
            day: "numeric", hour: "2-digit", minute: "2-digit"
};