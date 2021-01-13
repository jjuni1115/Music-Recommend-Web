<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/8/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>login page</title>

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->

    <script lang="javascript">
      // Protect backward page, When session.invalidate
      history.pushState(null, null, location.href);
      window.onpopstate = function () {
        history.go(1);
      };
      //after all document read, function start -> so, you can use doc.variance, jsp value
      $(document).ready(function(){
        // id : #, class : .
        $('#To_login').click(function(){
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


  <body>
  <h2>안녕하세요!</h2>
  <form action="/member/logon" method="post">
    <input type="text" id="account" name="account" placeholder="아이디"><br>
    <input type="password" id="password" name="password" placeholder="비밀번호"><br>
    <button type="submit" id = "To_login">To Login</button>
  </form>

  <form action="/member/signup" method="get">
<%--    <input type="text" name="account" placeholder="아이디2"><br>--%>
<%--    <input type="password" name="password" placeholder="비밀번호2"><br>--%>
    <button type="submit" id="signup">To Sign Up</button>
  </form>
  <div>
    ${msg}
  </div>
  </body>

</html>