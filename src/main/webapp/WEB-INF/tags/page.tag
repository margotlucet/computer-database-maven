<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="resultsPerPage" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="pageCount" required="true" type="java.lang.Integer"%>
<%@ attribute name="numberOfElements" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="totalElements" required="true"
	type="java.lang.Integer"%>
<%@ attribute name="actionPrefix" required="true"
	type="java.lang.String"%>

<ul class="pagination">

	<c:if test="${ page == 1 }">
		<li class="disabled"><a> &laquo; </a></li>
		<li class="disabled"><a> &lsaquo; </a></li>
	</c:if>
	<c:if test="${ page > 1 }">
		<li><a
			href="${actionPrefix} page=${1}&resultsPerPage=${resultsPerPage}">&laquo;
		</a></li>
		<li><a
			href="${actionPrefix} page=${page-1}&resultsPerPage=${resultsPerPage}">&lsaquo;
		</a></li>
	</c:if>
	<c:if test="${pageCount<10}">
		<c:forEach var="i" begin="1" end="${pageCount}">

			<c:if test="${page == i }">
				<li class="active"><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>

			</c:if>
			<c:if test="${page!=i}">
				<li><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${(pageCount>=10) && (page<6) }">
		<c:forEach var="i" begin="1" end="9">
			<c:if test="${page == i }">
				<li class="active"><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>

			</c:if>
			<c:if test="${page!=i}">
				<li><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>
			</c:if>
		</c:forEach>
	</c:if>

	<c:if test="${(pageCount>9) && (page>5) && ((pageCount-page)<=4)}">
		<c:forEach var="i" begin="${pageCount-8}" end="${pageCount}">
			<c:if test="${page == i }">
				<li class="active"><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>

			</c:if>
			<c:if test="${page!=i}">
				<li><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>
			</c:if>
		</c:forEach>
	</c:if>

	<c:if test="${(pageCount>9) && (page>5) && ((pageCount-page)>4)}">
		<c:forEach var="i" begin="${page-4}" end="${page+4}">
			<c:if test="${page == i }">
				<li class="active"><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>

			</c:if>
			<c:if test="${page!=i}">
				<li><a
					href="${actionPrefix}page=${i}&resultsPerPage=${resultsPerPage}&search=${search}">
						${i} </a></li>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${ page == pageCount }">
		<li class="disabled"><a>&rsaquo;</a></li>
		<li class="disabled"><a>&raquo;</a></li>
	</c:if>
	<c:if test="${ page != pageCount }">
		<li><a
			href="${actionPrefix}page=${page+1}&resultsPerPage=${resultsPerPage}&search=${search}">
				&rsaquo;</a></li>
		<li><a
			href="${actionPrefix}page=${pageCount}&resultsPerPage=${resultsPerPage}&search=${search}">
				&raquo;</a></li>
	</c:if>

</ul>
