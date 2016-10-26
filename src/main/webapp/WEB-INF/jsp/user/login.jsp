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
        <title>Войти</title>
        <script src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/user/javascript/login.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/font/Roboto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/general.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/user/css/login.css" rel="stylesheet" type="text/css"/>	
        <style>
            @font-face {
                font-family: 'Roboto';
                font-style: normal;
                font-weight: 400;
                src: local('Roboto'), local('Roboto-Regular'), url(${pageContext.request.contextPath}/resources/font/roboto.woff2) format('woff2');                
              }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <c:url value="/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post"> 
            <div id="textField">
                <c:if test="${param.error != null}">        
                    <p style="font-family: Roboto;">
                        Неверное имя пользователя или пароль.
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">       
                    <p>
                        Вы вышли из системы.
                    </p>
                </c:if>
                </div>
            <div id="userField">
                <img src="${pageContext.request.contextPath}/resources/user.png">
                <input type="text" id="username" name="username"/>	
            </div>
            <div id="passwordField">
                <img src="${pageContext.request.contextPath}/resources/password.png">
                <input type="password" id="password" name="password"/>	
            </div>
            <input type="hidden"                        
                name="${_csrf.parameterName}"
                value="${_csrf.token}"/>
            <div id="buttonField">
                <button type="submit" class="btn">ВОЙТИ</button>
            </div>
        </form>
    </body>
</html>
<script>
    var contextPath="${pageContext.request.contextPath}";
</script>