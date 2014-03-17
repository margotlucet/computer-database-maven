<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery.js"></script>
<section id="main">
    <%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

    <h1>
        <spring:message code="label.logout"></spring:message>
    </h1>

    <a href="displaycomputers" class="btn"><spring:message
            code="label.buttoncancel" /></a>

</section>

<jsp:include page="include/footer.jsp" />