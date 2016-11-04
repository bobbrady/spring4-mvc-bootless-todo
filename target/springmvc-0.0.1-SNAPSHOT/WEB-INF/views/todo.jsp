<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>ToDo</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
  	<h1>ToDo Details</h1>
    <div>
      <div><c:out value="${todo.content}" /></div>
      <div>
        <span><c:out value="${todo.time}" /></span>
      </div>
    </div>
	<a href="<c:url value="/todos" />">ToDo List</a> 
  </body>
</html>