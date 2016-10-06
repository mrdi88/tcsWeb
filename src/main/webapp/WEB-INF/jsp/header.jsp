<%-- 
    Document   : currentCars
    Created on : 08.09.2016, 0:26:56
    Author     : Dima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="header">
    <div id="menu">
        <ul>
          <li><a href="${pageContext.request.contextPath}/card/manage">Управление картами</a></li>
          <li><a href="${pageContext.request.contextPath}/laboratory/assign">Привязка пробы</a></li>
          <li><a href="${pageContext.request.contextPath}/laboratory/manage">Лаборатория</a></li>
          <li><a href="${pageContext.request.contextPath}/queue">Просмотр очередей</a></li>
          <li><a href="${pageContext.request.contextPath}/dock/manage">Управление доками</a></li>
          <li><a href="${pageContext.request.contextPath}/archive">Архив</a></li>
        </ul>
    </div>
    <div id="user">
        <form action="${pageContext.request.contextPath}/logout?${_csrf.parameterName}=${_csrf.token}" method="POST">
            <p>Пользователь:&nbsp;
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.username" var="username" />
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <sec:authentication property="principal" var="username" />
            </sec:authorize>
            Hello ${username}            
            </p>
            <input class="submit" type="submit" value="Выйти">
        </form>
    </div>
</div>