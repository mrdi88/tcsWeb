$(window).load( function(){
    $.get(contextPath+"/queue?cmd=getQueueList",{ "_": $.now() }, function(data) {setData(data);});
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
        cell.classList.add("humidity");
        cell.innerHTML = cards[i].car.cargo.sample.humidity;
        cell=newrow.insertCell(-1);
        cell.classList.add("nomenclature");
        cell.innerHTML = cards[i].car.cargo.sample.nomenclature;
        cell=newrow.insertCell(-1);
        cell.classList.add("class");
        cell.innerHTML = cards[i].car.cargo.sample.cultureClass;
        cell=newrow.insertCell(-1);
        cell.classList.add("destination");
        cell.innerHTML = cards[i].car.destination;
    }
}