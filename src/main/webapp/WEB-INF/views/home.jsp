<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>SpringMVC ToDo List</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Welcome to SpringMVC ToDo List</h1>
	<a href="<c:url value="/todos" />">ToDo List</a> 
</body>