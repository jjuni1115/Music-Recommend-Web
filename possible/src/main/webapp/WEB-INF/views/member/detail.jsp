<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/12/2021
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
</head>
<body>
회원 정보
${sessionScope.member.id}
${sessionScope.member.account}
${sessionScope.member.password }
${sessionScope.member.last_name}
${sessionScope.member.first_name}
${sessionScope.member.age}
${sessionScope.member.class_}
${sessionScope.member.money}
${sessionScope.member.sex}
</body>
</html>
