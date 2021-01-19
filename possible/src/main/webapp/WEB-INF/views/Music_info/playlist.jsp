<%--
  Created by IntelliJ IDEA.
  User: JJH
  Date: 2021-01-18
  Time: 오후 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>playlist</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->
    <script type="text/javascript">
        $(document).ready(function (){
            $("#To_search").click(function (){
                var params={"keyword":$("#keyword").val()};
                $.ajax({
                    type:"POST",
                    url:"/Music_info/list",
                    data:params,
                    datatype: "json",
                    success:function (args){
                        $(args).find("item").each(function (){
                            console.log($(this).find("title").text())
                            $("#songs").append("<option value='"+$(this).find("title").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"'</option>");
                        })
                    },
                    error:function (error){
                        alert("err :" + error);
                    }
                })
            })
        })
    </script>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->
    <script type="text/javascript">
        $(function (){
            $('.songs').click(function(){
                $.ajax({
                    type:"POST",
                    url:"/Music_info/insert_playlist",
                    data:params,
                    datatype: "json",
                    success:function (args) {

                    },
                    error:function (error){
                    alert("err :" + error);
                }
            })
        })
        })
    </script>
</head>
<body>
<button onclick="history.back()">뒤로가기</button> &nbsp; ID : ${sessionScope.member.id} &nbsp; ACCOUNT : ${sessionScope.member.account}
<br><br>
<form action="/py/recommend_list" method="get">
    <input type="hidden" id="ID" name="id" value=${sessionScope.member.id} />
    <input type="hidden" id="account" name="account" value=${sessionScope.member.account} />
    <input type="hidden" id="videoID" name="videoID" value="z3szNvgQxHo" />
    <button type="submit">노래 추천 받기</button>
</form>

음악검색: <input type="text" id="keyword" name="keyword">
<button type="submit" id="To_search">search</button><br>
<select name="songs" id="songs" size="15">
    <option value="" selected>--선택--</option>
</select>

</body>
</html>
