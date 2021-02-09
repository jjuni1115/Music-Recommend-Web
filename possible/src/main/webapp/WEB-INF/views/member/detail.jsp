<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/12/2021
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<html>
<head>
    <title>detail</title>
    <script>
        $(document).ready(function() {
            // id : #, class : .
            $('#checkButton').click(function() {
                if($('#pw')[0].value == ${sessionScope.member.password }){
                    $('#changeButton').attr("disabled", false);
                    alert("비밀번호가 확인되었습니다.");
                }
            })
        })
    </script>

</head>
<body>
<div>
    회원 정보<br>
    ${sessionScope.member.id}<br>
    ${sessionScope.member.account}<br>
    ${sessionScope.member.last_name}<br>
    ${sessionScope.member.first_name}<br>
    ${sessionScope.member.age}<br>
    ${sessionScope.member.class_}<br>
    ${sessionScope.member.money}<br>
    ${sessionScope.member.sex}<br>
</div>

<div id="infoChange">
        비밀번호 확인 <input type="password"
                       id="pw" name="pw" placeholder="비밀번호">
        <button id="checkButton">비밀번호 확인</button>
    <form action="/member/infoChange" method="post">
        <input type="hidden" name="id" value="${sessionScope.member.id}">
        <input type="hidden" name="account" value="${sessionScope.member.account}">
        <input type="hidden" name="password" value="${sessionScope.member.password}">
        <input type="hidden" name="last_name" value="${sessionScope.member.last_name}">
        <input type="hidden" name="first_name" value="${sessionScope.member.first_name}">
        <input type="hidden" name="age" value="${sessionScope.member.age}">
        <input type="hidden" name="class_" value="${sessionScope.member.class_}">
        <input type="hidden" name="money" value="${sessionScope.member.money}">
        <input type="hidden" name="sex" value="${sessionScope.member.sex}">

        <button type="submit" id="changeButton" disabled="disabled">회원정보 변경</button>
    </form>
</div>

</body>
</html>
