<%--
  Created by IntelliJ IDEA.
  User: Jiu
  Date: 2020/4/3
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach begin="1" end="100" var="sn">
        ${sn}
    </c:forEach>
</body>
</html>
