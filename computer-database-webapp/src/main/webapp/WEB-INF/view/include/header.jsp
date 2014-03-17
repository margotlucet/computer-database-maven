<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript" src="js/jquery.form-validator.js"></script>
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="displaycomputers"> Application </a> <a href="?lang=fr"><img
				style="border: 0;" src="images/france.jpg" alt="FR"></a> <a
				href="?lang=de"><img style="border: 0;"
				src="images/allemagne.jpg" alt="DE"></a> <a href="?lang=en"><img
				style="border: 0;" src="images/angleterre.jpg" alt="EN"></a>
			<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				<a href="logout" id="logoutSecurity" class="btn danger" style="float:right"> Logout</a>
			</sec:authorize>
		</h1>

	</header>