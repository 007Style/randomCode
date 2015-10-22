<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="midnight" uri="/WEB-INF/tlds/midnight.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<fmt:setBundle basename="com.midnightcookies.Midnight" var="bundle" scope="page"/>

<h3><fmt:message key="enterCookie" bundle="${bundle}"/></h3>

<c:if test="${error == 'true'}">
    <p style="color: red">
        <fmt:message key="cookieError" bundle="${bundle}"/>
    </p>
</c:if>

<form method="get" action="CookieMake.jsp">
    <table>
        <tr>
            <td><b><fmt:message key="name" bundle="${bundle}"/>:</b></td>
            <c:choose>
		<c:when test="${error == 'true'}">
			<td><input type="text" size="20" name="name" 
				   value='<%=request.getParameter("name")%>'>
		        </td>
			<c:if test="${requestScope['noname'] == 'noname'}">
			      <td style="color: red">
				  <fmt:message key="${requestScope.noname}" 
					       bundle="${bundle}"/>
			      </td>
			</c:if>
			<c:if test="${requestScope['noname'] == 'badname'}">
			      <td style="color: red">
				  <fmt:message key="${requestScope.noname}" 
					       bundle="${bundle}"/>
			      </td>
			</c:if>
		</c:when>
		<c:otherwise>
			<td><input type="text" size="20" name="name" 
			           value="">
		        </td>
		</c:otherwise>
	    </c:choose>
	</tr>
	<tr>
	    <td><b><fmt:message key="value" bundle="${bundle}"/>:</b></td>
	    <c:choose>
	        <c:when test="${error == 'true'}">
		    <td><input type="text" size="20" name="value" 
			       value='<%=request.getParameter("value")%>'></td>
		    <c:if test="${requestScope.novalue == 'novalue'}">
		        <td style="color: red">
                            <fmt:message key="${requestScope.novalue}" 
                                         bundle="${bundle}"/>
			</td>
                    </c:if>
		</c:when>
		<c:otherwise>
		    <td><input type="text" size="20" name="value" 
			       value=""></td>
                </c:otherwise>
            </c:choose>
        </tr>
	<tr>
	    <td><b><fmt:message key="domain" bundle="${bundle}"/>:</b></td>
	    <td><input type="text" size="20" name="domain" value=""></td>
        </tr>
        <tr>
	    <td><b><fmt:message key="path" bundle="${bundle}"/>:</b></td>
	    <td><input type="text" size="20" name="path" value=""></td>
        </tr>
        <tr>
	    <td><b><fmt:message key="maxAge" bundle="${bundle}"/>:</b></td>
	    <c:choose>
		<c:when test="${error == 'true'}">
		    <td><input type="text" size="20" name="maxage" 
			       value='<%=request.getParameter("maxage")%>'>
                    </td>
		    <c:if test="${requestScope.badnumber == 'badnumber'}">
		        <td style="color: red">
		            <fmt:message key="${requestScope.badnumber}" 
					 bundle="${bundle}"/>
                        </td>
                    </c:if>
                </c:when>
		<c:otherwise>
		    <td><input type="text" size="20" name="maxage"
			       value="360">
	            </td>
                </c:otherwise>
            </c:choose>  
        </tr>
	<tr>
	    <td><b><fmt:message key="secure" bundle="${bundle}"/>:</b></td>
	    <td><input type="checkbox" name="secure"></td>
	</tr>
	<tr>
	    <td>&nbsp;</td>
	    <td> 
	        <fmt:message key="bakeNow" bundle="${bundle}" 
			     var="buttonLabel" scope="page"/>
	        <midnight:button/>
	    </td>
        </tr>
    </table>
</form>



