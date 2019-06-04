<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: ArtemPC
  Date: 04.06.2019
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SessionTest</title>
</head>
<body>
<%
    LinkedList<Integer> intarray = (LinkedList<Integer>) session.getAttribute("sessionAr");
    int j = 0;
    if (intarray != null) {
        for (Integer num :
                intarray) {
            out.println("<br>" + num.toString() + " " + j);
            j++;
        }
    }
%>
</body>
</html>
