<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">
	<h1 id="homeTitle" style="FONT-FAMILY: 'Bitstream Vera Sans';">${pageResult.resultCount}
		<spring:message code="label.datafound" />
	</h1>
	<c:if test="${message.valid==true}">
		<div>
			<strong> ${message.message}</strong>
		</div>
	</c:if>

	<div id="actions">
		<form action="DisplayComputers" method="GET">
			<div class="input">
				<input type="search" id="searchbox" name="search" value=""
					placeholder="Search name">
			</div>
			<label for="resultsPerPage"><spring:message
					code="label.resultsPerPage" /></label>
			<div class="input">
				<select name="resultsPerPage" id="resultsPerPage">
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>25</option>
				</select>
			</div>
			<button type="submit" id="searchsubmit" class="btn primary">
				<spring:message code="label.buttonsearch" />
			</button>
		</form>
		<a class="btn success" id="add" href="AddComputer"><spring:message code="label.add"/></a>
	</div>

	<div class="pagination">
		<h:page totalElements="${pageResult.resultCount}"
			actionPrefix="DisplayComputers?" pageCount="${pageResult.pageCount}"
			resultsPerPage="${pageResult.resultsPerPage}"
			page="${pageResult.currPage}"
			numberOfElements="${pageResult.currentResultCount}">
		</h:page>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th><spring:message code="label.table.header.computer"/></th>
				<th><spring:message code="label.table.header.introduced"/></th>
				<!-- Table header for Discontinued Date -->
				<th><spring:message code="label.table.header.discontinued"/></th>
				<!-- Table header for Company -->
				<th><spring:message code="label.table.header.company"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="computer" items="${pageResult.elementList}">
				<tr>
					<td><a href="EditComputer?id=${computer.id}">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</section>

<jsp:include page="include/footer.jsp" />
