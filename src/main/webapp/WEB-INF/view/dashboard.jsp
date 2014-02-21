<jsp:include page="include/header.jsp" />
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<section id="main">
	<h1 id="homeTitle" style="FONT-FAMILY: 'Bitstream Vera Sans';">${pageResult.resultCount}
		Computers found</h1>
	<div id="actions">
		<form action="DisplayComputers" method="GET">
			<div class="input">
				<input type="search" id="searchbox" name="search" value=""
					placeholder="Search name">
			</div>
			<label for="resultsPerPage">Results per page</label>
			<div class="input">
				<select name="resultsPerPage" id="resultsPerPage">
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>25</option>
				</select>
			</div>
			<input type="submit" id="searchsubmit" value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="AddComputer">Add Computer</a>
	</div>


	<div class="alert ">${message.message}</div>

	<div class="pagination">
		<h:page totalElements="${pageResult.resultCount}" actionPrefix="DisplayComputers?"
			pageCount="${pageResult.pageCount}" resultsPerPage="${pageResult.resultsPerPage}" page="${pageResult.currPage}"
			numberOfElements="${pageResult.currentResultCount}">
		</h:page>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th>Computer Name</th>
				<th>Introduced Date</th>
				<!-- Table header for Discontinued Date -->
				<th>Discontinued Date</th>
				<!-- Table header for Company -->
				<th>Company</th>
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
