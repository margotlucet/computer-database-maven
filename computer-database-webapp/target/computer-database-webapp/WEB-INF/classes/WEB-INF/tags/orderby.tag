<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="field" required="true" type="java.lang.String" %>
<%@ attribute name="text" required="true" type="java.lang.String" %>
<%@ attribute name="pageData" required="true" type="com.servlet.wrapper.PageWrapper" %>

<c:choose>
    <c:when test="${pageData.resultsOrderedBy == field}">
        <c:if test="${pageData.orderDirection == 'ASC'}">
            <a href="Home?orderBy=${field }&orderDirection=DESC">${text} <span
                class="glyphicon glyphicon-sort-by-alphabet"
                style="float: left; margin-right: .3em;"></span>
            </a>
        </c:if>
        <c:if test="${pageData.orderDirection == 'DESC'}">
            <a href="Home?orderBy=${field }&orderDirection=ASC">${text} <span
                class="ui-icon ui-icon-carat-1-s"
                style="float: left; margin-right: .3em;"></span>
            </a>
        </c:if>
    </c:when>
    <c:otherwise>
        <a href="Home?orderBy=${field }">${text}<span
            class="ui-icon ui-icon-carat-2-n-s"
            style="float: left; margin-right: .3em;"></span>
        </a>
    </c:otherwise>
</c:choose>
