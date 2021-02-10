<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/8/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <title>login page</title>
<%--  <link rel="stylesheet" href="CSS/index.css">--%>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <!-- JQuery -->

<c:if test="${empty sessionScope.member}">
  <script>
    // Protect backward page, When session.invalidate
    history.pushState(null, null, location.href);
    window.onpopstate = function() {
      history.go(1);
    };
  </script>
</c:if>


  <script lang="javascript">

    //after all document read, function start -> so, you can use doc.variance, jsp value
    $(document).ready(function() {
      // id : #, class : .
      $('#To_login').click(function() {
        var json = {
          account : $('#account').val(),
          password : $('#password').val()
        };

        // for(let i=0; i<Object.keys(json).length; i++){
        if (json["account"].length < 4) {
          alert($(json["account"]).attr("placeholder") + "입력해주세요.");
          $("#" + json["account"]).focus();
          return;
        }
      });
    });
  </script>


</head>
<style>
  <%@ include file="../CSS/index.css"%>
</style>

<body>

  <h1>Muse</h1>
  <h2>어서오세요 당신만의 음악을 알려드립니다. </h2>

  <div id="login_box">
    <h3>Member Login</h3>
    <ul id="input_button">
      <li id="id_pass">
        <ul>
          <form action="/member/logon" method="post">
          <li><span>ID</span> <input type="text" id="account"
                                     name="account" placeholder="아이디"><br></li>
          <!-- id -->
          <li id="pass"><span>PW</span> <input type="password"
                                               id="password" name="password" placeholder="비밀번호"><br>

            <li>
              <button type="submit" id="To_login">To Login</button>
            </li>

          </form>
        </ul>
      </li>

    </ul>
    <ul id="btns">
      <li><form action="/member/signup" method="get">
        <%--    <input type="text" name="account" placeholder="아이디2"><br>--%>
        <%--    <input type="password" name="password" placeholder="비밀번호2"><br>--%>
        <button type="submit" id="signup">To Sign Up</button>
      </form></li>
    </ul>
  </div>

<div>${msg}</div>

</body>
</html>


