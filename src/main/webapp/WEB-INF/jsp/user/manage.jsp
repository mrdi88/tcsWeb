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
        <!-- meta for CSRF-TOKEN -->
        <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Mange users</title>

        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/user/css/manage.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
	<script src="${pageContext.request.contextPath}/resources/user/javascript/manage.js"></script>		
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="users_table">
            <table id="users" width="100%" cellpadding="2" >
                <th class="users_header">№</th>
                <th class="users_header">Логин </th>
                <th class="users_header">Роли</th>
            </table>
        </div>
        <div id="leftSide">
            <div id="newUser">
                <p>
                    Новый пользователь
                </p>
                <form id="newUserForm" autocomplete="off" action="${pageContext.request.contextPath}/user?cmd=newUser" method="post" >       
                    <p>
                        <label for="username">Логин</label>
                        <input type="text" class="username" name="username" value=""/>	
                    </p>
                    <p>
                        <label for="password">Пароль</label>
                        <input type="password" class="password" name="password" value=""/>	
                    </p>
                    <p>
                        <label for="passwordRepeat">Повторите пароль</label>
                        <input type="password" class="passwordRepeat" name="passwordRepeat" value=""/>	
                    </p>
                    <button type="submit" class="btn">Создать</button>
                </form>
            </div>
            <div id="changePassword">
                <p>
                    Изменить пароль
                </p>
                <form id="changePasswordForm" action="${pageContext.request.contextPath}/user?cmd=changePassword" method="post"  autocomplete="off">       
                    <p>
                        <label for="username">Логин</label>
                        <input type="text" class="username" name="username" />	
                    </p>
                    <p>
                        <label for="newPassword">Новый пароль</label>
                        <input type="password" class="newPassword" name="newPassword" />	
                    </p>
                    <p>
                        <label for="newPasswordRepeat">Повторите пароль</label>
                        <input type="password" class="newPasswordRepeat" name="newPasswordRepeat" />	
                    </p>
                    <button type="submit" class="btn">Изменить пароль</button>
                </form>
            </div>
        </div>
        <div id="rightSide">       
            <div id="setRoles">
                <form id="changeRolesForm" action="${pageContext.request.contextPath}/user?cmd=changeRoles" method="post"  autocomplete="off">       
                    <p>
                        <label for="username">Логин</label>
                        <input type="text" class="username" name="username" readonly/>	
                    </p>
                    <div class="roles">
                        <div class="exist">
                            <select id="existRoles" size="5" multiple></select>
                        </div>
                        <div class ="moveBtns">
                            <input type="button" id="toExist" value="&lt;&lt;" />
                            <input type="button" id="toAvailable" value="&gt;&gt;" />
                        </div>
                        <div class="available">
                            <select id="availableRoles" size="4" multiple></select>
                            <input type="text" id="txtAvailable" />
                        </div>
                    </div>
                    <div class="buttons">
                        <input class= "btn" type="button" value="Удалить" onclick="deleteUser(this.form.username.value);">
                        <button type="submit" class="btn">Применить</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";   
</script>

    