<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 2021-02-09
  Time: 오후 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<html>
<head>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}../CSS/infoChange.css" type="text/css" />--%>
    <title>InfoChange</title>
        <script>
            $(document).ready(function(){
                console.log(${sessionScope.member.sex});
                if('${sessionScope.member.sex}' == '1'){
                    $('#sexMen').attr("checked", true);
                }
                else{
                    $('#sexWomen').attr("checked", true);
                }

            });
        </script>
</head>

<!-- link 로 css 안가져와질 때 아래처럼 가져올 수 있다. -->
<style>
    <%@ include file="../../CSS/infoChange.css"%>
</style>

<body>
<div id="content">
    <h3>${sessionScope.member.first_name} ${sessionScope.member.last_name} (님) 정보 변경</h3>
    <form action="/member/changeCommit" method="get">
        <table class='w-pct60'>
            <tr>
                <th class='w-px160'>First Name</th>
                <td><input type="text" name="first_name" value="${sessionScope.member.first_name}"/></td>
            </tr>
            <tr>
                <th class='w-px160'>Last Name</th>
                <td><input type="text" name="last_name" value="${sessionScope.member.last_name}"/></td>
            </tr>
            <tr>
                <th class='w-px160'>Sex</th>
                <td>
                    <label><input type="radio" id="sexMen" name="gender" value="1" />남</label>
                    <label><input type="radio" id="sexWomen" name="gender" value="0" />여</label>
                </td>
            </tr>

            <tr>
                <th class='w-px160'>ID</th>
                <td><input type="hidden" name="id" value="${sessionScope.member.id}"/>
                    ${sessionScope.member.id}</td>
            </tr>
            <tr>
                <th class='w-px160'>ACCOUNT</th>
                <td><input type="hidden" name="account" value="${sessionScope.member.account}"/>
                    ${sessionScope.member.account}</td>
            </tr>
            <tr>
                <th class='w-px160'>Password</th>
                <td><input type="text" name="password" value="${sessionScope.member.password}"/></td>
            </tr>
        </table>
    </form>

    <div class="btnSet">
        <a class="btn-fill" onclick="$('form').submit()">저장</a>
        <a class="btn-empty" href="list.cu">취소</a>
    </div>
</div>


</body>
</html>
