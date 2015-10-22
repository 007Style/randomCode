<%@page contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="com.midnightcookies.Midnight" var="bundle" scope="page"/>
<fmt:message key="nonesuch" bundle="${bundle}"/> 

<c:choose>
    <c:when test="${requestScope['nonesuch'] != null}">
        <c:out value="${requestScope.nonesuch}"/>
    </c:when>
    <c:when test="${requestScope['javax.servlet.error.request_uri'] != null}">
        <c:out value="${requestScope['javax.servlet.error.request_uri']}"/>
    </c:when>
    <c:otherwise>???</c:otherwise>
</c:choose>

