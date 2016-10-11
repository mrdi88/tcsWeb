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

    //register events for roles form
    $("#toExist").click(function () {
        var selectedItem = $("#availableRoles option:selected");
        $("#existRoles").append(selectedItem);
    });

    $("#toAvailable").click(function () {
        var selectedItem = $("#existRoles option:selected");
        $("#availableRoles").append(selectedItem);
    });

    $("#availableRoles").change(function () {
        var selectedItem = $("#availableRoles option:selected");
        $("#txtAvailable").val(selectedItem.text());
    });    
    //changeRolesForm submit event
    $( "#changeRolesForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#changeRolesForm .username").val()==""){
            alert("Выберите пользователя");
            return;
        }
        changeRoles(this);
    });
    //register event for add user form
    $( "#newUserForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#newUserForm .username").val()==""){
            alert("Введите имя пользователя");
            return;
        }
        var password=$("#newUserForm .password").val();
        var passwordRepeat=$("#newUserForm .passwordRepeat").val();
        if (password=="" || passwordRepeat==""){
            alert("Введите пароль");
            return;
        }
        //check passwords
        if (password!=passwordRepeat){
            alert("Введенные пароли не совпадают");
            return;
        }
        addUser(this);
    });
    //events for change password form
    $( "#changePasswordForm" ).submit(function( event ) {
        event.preventDefault();
        if ($( "#changePasswordForm .username").val()==""){
            alert("Введите имя пользователя");
            return;
        }
        var newPassword=$("#changePasswordForm .newPassword").val();
        var newPasswordRepeat=$("#changePasswordForm .newPasswordRepeat").val();
        if (newPassword=="" || newPasswordRepeat==""){
            alert("Введите пароль");
            return;
        }
        //check passwords
        if (newPassword!=newPasswordRepeat){
            alert("Введенные пароли не совпадают");
            return;
        }
        changePassword(this);
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
        newrow.id=users[i].username;
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
        //select if user was selected before data update
        if (selectedUser==users[i].username){
            selectUser( newrow );
        }
        newrow.onclick = function() {
            selectUser( this );
        };
    }
}
//set data for chosed card
function selectUser(e){
    selectedUser = e.id;
    var user=null;
    //remove options
    $('#existRoles').find('option').remove();
    $('#availableRoles').find('option').remove();
    for (var i=0; i<userList.length; i++){
        if (userList[i].username==selectedUser) user=userList[i];
    }
    if (user!=null){
        $( "#changeRolesForm .username").val( user.username );
        for (var i=0; i<userRoles.length; i++ ){
            var hasRole=false;
            for (var j=0; j<user.userRole.length; j++){
                if (user.userRole[j].role==userRoles[i]){
                    hasRole=true;
                }
            }
            //if user has role
            if (hasRole){
                $("#existRoles").append($('<option></option>').val(i).html(userRoles[i])); 
            }else{
                $("#availableRoles").append($('<option></option>').val(i).html(userRoles[i])); 
            }
        }
    }
    $("#users .selected").removeClass("selected");
    e.classList.add("selected");
}
//change user roles via ajaax query
function changeRoles(form){
    //get username from form
    var username=form.username.value;
    //get option as user roles
    var options=$('#existRoles').find('option');
    var roles="";
    var comaFlag=false;
    for (var i=0;i<options.length; i++){
        if (comaFlag) roles+=",";
        roles+=options[i].text;
        comaFlag=true;
    }
    console.log('Sending request to '+$(form).attr('action')+' with data: '+$(form).serialize());
    //send ajax query
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(form).attr('action'),
        data     : {"username":username,"roles":roles},
        success  : function(data) {
            if (data.result=="true"){
                //clear form
                $( "#changeRolesForm .username").val( "" );
                //remove options
                $('#existRoles').find('option').remove();
                $('#availableRoles').find('option').remove();
                selectedUser="";
                $.get(contextPath+"/user/manage?cmd=getUsersList",{ "_": $.now() }, function(data) {setUsers(data);});
            }
            else{
                alert("Ошибка");
            }
        }
    });
}
//add user via ajax query
function addUser(form){
    //get username from form
    var username=form.username.value;
    console.log('Sending request to '+$(form).attr('action')+' with data: '+$(form).serialize());
    //send ajax query
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(form).attr('action'),
        data     : $(form).serialize(),
        success  : function(data) {
            if (data.result=="true"){
                //clear form
                $( "#newUserForm .username").val( "" );
                $( "#newUserForm .password").val( "" );
                $( "#newUserForm .passwordRepeat").val( "" );
                //update user list
                $.get(contextPath+"/user/manage?cmd=getUsersList",{ "_": $.now() }, function(data) {setUsers(data);});
            }
            else{
                alert("Ошибка");
            }
        }
    });
}
//delete user via ajax query
function deleteUser(username){
    if (username==""){
        alert("Выберите пользователя");
        return;
    }
    console.log('Sending request to '+contextPath+"/user/manage?cmd=deleteUser"+' with data: '+"username:"+username);
    //send ajax query
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : contextPath+"/user/manage?cmd=deleteUser",
        data     : {"username":username},
        success  : function(data) {
            if (data.result=="true"){
                //clear form
                $( "#changeRolesForm .username").val( "" );
                //remove options
                $('#existRoles').find('option').remove();
                $('#availableRoles').find('option').remove();
                selectedUser="";
                $.get(contextPath+"/user/manage?cmd=getUsersList",{ "_": $.now() }, function(data) {setUsers(data);});
            }
            else{
                alert("Ошибка");
            }
        }
    });
}
//change password via ajax
function changePassword(form){
    //get username from form
    var username=form.username.value;
    console.log('Sending request to '+$(form).attr('action')+' with data: '+$(form).serialize());
    //send ajax query
    $.ajax({
        type     : "POST",
        cache    : false,
        url      : $(form).attr('action'),
        data     : $(form).serialize(),
        success  : function(data) {
            if (data.result=="true"){
                //clear form
                $( "#changePasswordForm .username").val( "" );
                $( "#changePasswordForm .newPassword").val( "" );
                $( "#changePasswordForm .newPasswordRepeat").val( "" );
                //update user list
                $.get(contextPath+"/user/manage?cmd=getUsersList",{ "_": $.now() }, function(data) {setUsers(data);});
            }
            else{
                alert("Ошибка");
            }
        }
    });
}
