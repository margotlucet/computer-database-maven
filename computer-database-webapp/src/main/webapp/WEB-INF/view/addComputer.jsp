<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">

	<h1>	<spring:message code="label.add" /></h1>
	<form:form action="AddComputer" method="POST" commandName="computer">
		<fieldset>

			<div class="clearfix">
				<label for="name"><spring:message code="label.table.header.computer" /> :</label>
				<div class="input">
					<form:input type="text" path="name" value="${computer.name}" />
					<span class="help-inline">Required</span>
					<div>
						<font color='red'><form:errors path='name' /></font>
					</div>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced"><spring:message code="label.table.header.introduced" /> :</label>
				<div class="input">
					<form:input type="text" path="introduced"
						value="${computer.introduced}" />
					<div>
						<font color='red'><form:errors path='introduced' /></font>
					</div>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="label.table.header.discontinued" /> :</label>
				<div class="input">
					<form:input type="text" path="discontinued"
						value="${computer.discontinued}" />
					<div>
						<font color='red'><form:errors path='discontinued' /></font>
					</div>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="label.table.header.company" /> :</label>
				<div class="input">
					<form:select name="company" path="company.id">
						<form:option value="0">--</form:option>
						<c:forEach var="company" items="${companies}">
							<form:option value="${company.id}">${company.name}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<button type="submit" class="btn primary">
			<spring:message code="label.add" />
			</button>
			 <spring:message code="or" /> <a
				href="DisplayComputers" class="btn"><spring:message code="label.buttoncancel" /></a>
		</div>
	</form:form>
</section>



<jsp:include page="include/footer.jsp" />