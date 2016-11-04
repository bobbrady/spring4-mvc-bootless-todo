<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>ToDo List</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<div class="todoForm">
		<h1>Collective ToDos...</h1>
		<form method="POST" name="todoForm">
			<input type="hidden" name="latitude"> <input type="hidden"
				name="longitude">
			<textarea name="content" cols="80" rows="5"></textarea>
			<br /> <input type="submit" value="Add" />
		</form>
	</div>
	<div class="listTitle">
		<h1>Recent ToDos: ${fn:length(todoList)}</h1>
		<ul class="todoList">
			<c:forEach items="${todoList}" var="todo">
				<li id="todo_<c:out value="todo.id"/>"><a
					href="/todos/${todo.id}">
						<div class="todoMessage">
							<c:out value="${todo.content}" />
						</div>
				</a>
					<div>
						<span class="todoTime"><c:out value="${todo.time}" /></span> <span
							class="todoLocation">(<c:out value="${todo.latitude}" />,
							<c:out value="${todo.longitude}" />)
						</span>
					</div></li>
			</c:forEach>
		</ul>
		<c:if test="${fn:length(todoList) gt 20}">
			<hr />
			<s:url value="/todos?count=${nextCount}" var="more_url" />
			<a href="${more_url}">Show more</a>
		</c:if>
	</div>
</body>
</html>