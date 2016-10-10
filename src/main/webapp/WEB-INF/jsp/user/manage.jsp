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
        <div id="newUser">
            <p>
                Новый пользователь
            </p>
            <form  action="${pageContext.request.contextPath}/user?cmd=newUser" method="post">       
                <p>
                    <label for="username">Логин</label>
                    <input type="text" id="username" name="username"/>	
                </p>
                <p>
                    <label for="password">Пароль</label>
                    <input type="password" id="password" name="password"/>	
                </p>
                <p>
                    <label for="passwordRepeat">Повторите пароль</label>
                    <input type="password" id="passwordRepeat" name="passwordRepeat"/>	
                </p>
                <button type="submit" class="btn">Создать</button>
            </form>
        </div>
        <div id="changePassword">
            <p>
                Изменить пароль
            </p>
            <form  action="${pageContext.request.contextPath}/user?cmd=changePassword" method="post">       
                <p>
                    <label for="username">Логин</label>
                    <input type="text" id="username" name="username"/>	
                </p>
                <p>
                    <label for="password">Пароль</label>
                    <input type="password" id="password" name="password"/>	
                </p>
                <p>
                    <label for="newPassword">Новый пароль</label>
                    <input type="password" id="password" name="newPassword"/>	
                </p>
                <p>
                    <label for="newPasswordRepeat">Повторите пароль</label>
                    <input type="password" id="passwordRepeat" name="newPasswordRepeat"/>	
                </p>
                <button type="submit" class="btn">Изменить пароль</button>
            </form>
        </div>
        <div id="setRoles">
            <form  action="${pageContext.request.contextPath}/user?cmd=newUser" method="post">       
                <p>
                    <label for="username">Логин</label>
                    <input type="text" id="username" name="username"/>	
                </p>
                <div>
                    <select id="leftValues" size="5" multiple></select>
                </div>
                <div>
                    <input type="button" id="btnLeft" value="&lt;&lt;" />
                    <input type="button" id="btnRight" value="&gt;&gt;" />
                </div>
                <div>
                    <select id="rightValues" size="4" multiple></select>
                    <div>
                        <input type="text" id="txtRight" />
                    </div>
                </div>
                <button type="submit" class="btn">Применить</button>
            </form>
        </div>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";   
</script>

    