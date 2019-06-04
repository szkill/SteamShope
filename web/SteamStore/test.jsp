<%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 04.06.2019
  Time: 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<c:set var="salary" scope="session" value="${2000*2}"/>
<c:if test="${salary > 2000}">
<p>My salary is:  <c:out value="${salary}"/><p>

    </c:if>
</body>
</body>
</html>
