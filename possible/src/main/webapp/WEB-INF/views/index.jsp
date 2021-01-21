<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/8/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <title>login page</title>

  <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script>
  <!-- JQuery -->

  <script lang="javascript">
    // Protect backward page, When session.invalidate
    history.pushState(null, null, location.href);
    window.onpopstate = function() {
      history.go(1);
    };
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

  <style>
    h1 {
      FONT-SIZE: 78px;
      COLOR: #eeeeee;
      PADDING-BOTTOM: 10px;
      TEXT-ALIGN: left;
      PADDING-TOP: 10px;
      PADDING-LEFT: 10px;
      BORDER-LEFT: #00b5ff 8px solid;
    }

    h2 {
      FONT-SIZE: 12px;
      COLOR: #eeeeee;
      PADDING-BOTTOM: 10px;
      TEXT-ALIGN: left;
      PADDING-TOP: 10px;
      PADDING-LEFT: 10px;
      BORDER-LEFT: #00b5ff 8px solid;
    }


    button {
      background-color: #00b5ff;
      border: none;
      color: #ffffff;
      cursor: pointer;
      display: inline-block;
      font-family: 'BenchNine', Arial, sans-serif;
      font-size: 1em;
      font-size: 10px;
      line-height: 1em;
      outline: none;
      padding: 6px 20px 5px;
      position: relative;
      text-transform: uppercase;
      font-weight: 700;
    }

    /* 이미지 파일 불러오기 불가능 */
    body {
      background-image: url("../views/images/background2.png");
      background-size : auto;
      font-family:"맑은고딕", "돋움";
      font-size:12px;
      color: deepskyblue;
    }

    * {
      margin:0;
      padding:0;
    }
    ul {
      list-style-type: none;
    }

    #login_box {
      width:220px;
      height:120px;
      border:solid 1px #bbbbbb;
      border-radius:15px;
      margin:10px 0 0 10px;
      padding:10px 0 0 15px;
    }
    h2 {
      font-family:"Arial";
      margin-bottom:10px;
    }
    #login_box input {
      width:100px;
      height:18px;
    }
    #id_pass, #login_btn {
      display: inline-block;
      vertical-align: top;
    }
    #id_pass span {
      display: inline-block;
      width:20px;
    }
    #pass {
      margin-top:3px;
    }
    #login_btn button {
      margin-left:5px;
      padding:12px;
      border-radius:5px;
    }
    #btns {
      margin:12px 0 0 0;
      text-decoration:underline;
    }
    #btns li {
      margin-left:10px;
      display:inline;
    }

    #To_login{
      position: relative;
      left:135px;
      bottom:30px;


    }




  </style>

</head>


<body>



<div class="box1">
  <h1>Muse</h1>
  <h2>어서오세요 당신만의 음악을 알려드립니다. </h2>
</div>




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