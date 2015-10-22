<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="midnight" uri="/WEB-INF/tlds/midnight.tld"%>

<fmt:setBundle basename="com.midnightcookies.Midnight" var="bundle" scope="page"/>
<h3><fmt:message key="madeCookie" bundle="${bundle}"/></h3>
<p>
    <fmt:message key="name" bundle="${bundle}"/> = 
    <c:out value="${requestScope.cookie.name}"/><br>
    <fmt:message key="value" bundle="${bundle}"/> = 
    <c:out value="${requestScope.cookie.value}"/>  
</p>

<form method="get" action="Tray.jsp">
    <fmt:message key="cookieTray" bundle="${bundle}" 
                 var="buttonLabel" scope="page"/>
    <midnight:button/>
</form>
