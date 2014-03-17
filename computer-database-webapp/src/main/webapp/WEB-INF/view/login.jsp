<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery.js"></script>
<section id="main">
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h1>Login</h1>
	<form action="loginCheck" method="POST" >
		<fieldset>
			<div class="clearfix">
				<label for="username"><spring:message
						code="label.login.username" /> :</label>
				<div class="input">
					<input type="text" id="username" name="usernameSecurity" />
					<span class="help-inline">Required</span>

				</div>
			</div>

			<div class="clearfix">
				<label for="password"><spring:message
						code="label.login.password" /> :</label>
				<div class="input">
					<input type="password" id="password" name="passwordSecurity"
						 />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<button type="submit" class="btn primary">
				<spring:message code="label.login.button" />
			</button>
			<spring:message code="or" />
			<a href="displaycomputers" class="btn"><spring:message
					code="label.buttoncancel" /></a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />