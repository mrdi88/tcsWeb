var selectedUser=0;
var userList;
var userRoles;
$(window).load( function(){
    //include the csrf token within all Ajax requests
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $.get(contextPath+"/user/manage?cmd=getUsersList",{ "_": $.now() }, function(data) {setUsers(data);});

$("#btnLeft").click(function () {
    var selectedItem = $("#rightValues option:selected");
    $("#leftValues").append(selectedItem);
});

$("#btnRight").click(function () {
    var selectedItem = $("#leftValues option:selected");
    $("#rightValues").append(selectedItem);
});

$("#rightValues").change(function () {
    var selectedItem = $("#rightValues option:selected");
    $("#txtRight").val(selectedItem.text());
});    

});
    
function setUsers(data) { 
    var users=data.users;
    userList=users;
    userRoles=data.roles
    var tableUsers = document.getElementById("users");
    //clean table
    for(var i = tableUsers.rows.length-1; i > 0; i--){
        tableUsers.deleteRow(i);
    }
    var newrow;
    var createDate;
    var cell;
    //set data in table
    for (var i = 0; i < users.length; i++) { 
        newrow = tableUsers.insertRow(i+1);
        newrow.id=users[i].id;
        if (i%2>0) newrow.classList.add("even");
        else newrow.classList.add("odd");
        
        cell=newrow.insertCell(-1);
        cell.classList.add("rowNumber");
        cell.innerHTML = i+1;
        
        cell=newrow.insertCell(-1);
        cell.classList.add("login");
        cell.innerHTML = users[i].username;
        
        cell=newrow.insertCell(-1);
        cell.classList.add("roles");
        var comaFlag=false;
        for (var j=0; j<users[i].userRole.length; j++ ){
           if(comaFlag) cell.innerHTML +=", ";
           cell.innerHTML += users[i].userRole[j].role;
           comaFlag=true;
        }
        //select
        if (selectedUser==users[i].username){
            selectCard( newrow );
        }
        newrow.onclick = function() {
            //selectUser( this );
        };
    }
}


