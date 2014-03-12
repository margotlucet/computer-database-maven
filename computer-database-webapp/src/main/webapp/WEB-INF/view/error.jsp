<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
<div align="center">
<h1>Your request could not be established!</h1>
<p>You can't access sensible informations about our web application.</p>
<img src="http://www.ovnis-usa.com/images/CIA_Logo.png"/>
<a href=<c:url value="/index.jsp"/>>Safely go back home</a>
<p>If this problem persist please contact the web master at : aaaaa@excilys.com</p>
</div>
</body>
</html>