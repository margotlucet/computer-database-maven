<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery.js"></script>
<section id="main">

	<h1>Edit Computer</h1>
	<form action="EditComputer" method="POST" id="edit-computer">
		<fieldset>
			<div class="input">
				<input readonly type="hidden" name="id" value="${computer.id}" />
			</div>
			<div class="input">
				<input readonly type="hidden" name="companyName"
					id="${computer.companyName}" />
			</div>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" value="${computer.name}"
						data-validation="length"
						data-validation-length="1-20"
						data-validation-error-msg="The name has to be a value between 1-20 characters" />
					<span class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="text" name="introducedDate" data-validation="date"
						data-validation-format="dd/mm/yyyy"
						data-validation-optional="true"
						data-validation-error-msg="The date format must be DD/MM/YYYY "
						value="${computer.introduced}" /> <span class="help-inline">DD/MM/YYYY</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="text" name="discontinuedDate" data-validation="date"
						data-validation-error-msg="The date format must be DD/MM/YYYY "
						data-validation-format="dd/mm/yyyy"
						data-validation-optional="true" value="${computer.discontinued}" />
					<span class="help-inline">DD/MM/YYYY</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Current Company Name:
					${computer.companyName}</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<c:forEach var="company" items="${companies}">
							<option value="${company.id}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary" name="id">
			or <a href="DeleteComputer?id=${computer.id}" class="btn danger"
				onclick="return confirm('You are about to delete this computer, are you sure?')">
				Delete </a> or <a href="DisplayComputers" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />