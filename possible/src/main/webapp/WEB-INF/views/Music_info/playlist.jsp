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
        $(document).ready(function (){                       //search music
            $("#To_search").click(function (){
                //var radioVal = $('input[name="chk_info"]:checked').val();
                //console.log(radioVal)
                var params={"keyword":$('input[name="chk_info"]:checked').val()+"//"+$("#keyword").val()};
                $.ajax({
                    type:"POST",
                    url:"/Music_info/list",
                    data:params,
                    datatype: "json",
                    success:function (args){
                        $('#songs').find('option').remove();
                        $(args).find("item").each(function (){
                            console.log($(this).find("title").text())
                            $("#songs").append("<option value='"+$(this).find("title").text()+"//"+$(this).find("artist").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"</option>");
                        })
                    },
                    error:function (error){
                        alert("error: search music");
                    }
                })
            })
        })
    </script>

    <script type="text/javascript">
        $(function (){
            $("#songs").dblclick(function(){                 //add playlist
                var params={"keyword":${sessionScope.member.id}+"//"+$("#songs").val()+"//"+$("#my_playlist").val()};
                $.ajax({
                    type:"POST",
                    url:"/Music_info/insert_playlist",
                    data:params,
                    datatype: "json",
                    success:function (args) {
                        alert("플레이리스트 저장");
                        var list=$("#songs").val().split("//");
                        $("#playlist").append("<option value='"+$("#songs").val()+"'>"+list[0]+" - "+list[1]+"</option>");
                    },
                    error:function (error){
                    alert("error: add playlist");
                }
            })
        })
        })
    </script>
    <script type="text/javascript">
        $(function load (){                               //load my playlist
            $('#load').click(function(){
                var param={'keyword':${sessionScope.member.id}+"//"+$("#my_playlist").val()};
                $.ajax({
                    type:'POST',
                    url:'/Music_info/load_playlist',
                    data:param,
                    datatype:'json',
                    success:function(args) {
                        $("#playlist option").remove();
                        $(args).find("item").each(function () {
                            $("#playlist").append("<option value='"+$(this).find("title").text()+"//"+$(this).find("artist").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"</option>");
                        })

                    },error:function(error){
                        alert("error: load playlist");
                }
                //})
            })
            })
        })
    </script>
    <script lang="javascript">
        $(document).ready(function(){
            $('#playlist').dblclick(function(){
                var artistTitle = $('#playlist')[0].value.split("//");  // artist, title split
                location.href = "/ytube/searchDo?" + "id=" + ${sessionScope.member.id} + "&account=" +
                ${sessionScope.member.account} + "&Artist=" + artistTitle[1] + "&title=" + artistTitle[0];
                // getMapping & searchDo method needs memberId, memberAccount, artist, title parameter
            });
        })
    </script>

    <script type="text/javascript">
        $(document).ready(function (){
            $("#new_playlist").click(function (){
                var param={'keyword':${sessionScope.member.id}+'//'+$("#add_playlist").val()};
                $.ajax({
                    type:'POST',
                    url:'/Music_info/new_playlist',
                    data:param,
                    datatype:'json',
                    success:function(args) {
                        alert("Create playlist sucessfully");
                    },error:function(error){
                        alert("error: Create playlist");
                    }
                    //})
                })
            })
        })
    </script>
    <script>
        $(function load_my_playlist (){
            var param={'keyword':${sessionScope.member.id}};
            $.ajax({
                type:'POST',
                url:'/Music_info/load_my_playlist',
                data:param,
                datatype:'json',
                success:function(args) {
                    console.log(args)
                    $(args).find("item").each(function () {
                        $("#my_playlist").append("<option value='"+$(this).text()+"'>"+$(this).text()+"</option>");
                    })

                },error:function(error){
                    alert("error: load_my_playlist");
                }
                //})
            })
        })
    </script>

</head>

<style>
    <%@ include file ="../../CSS/playlist.css" %>
</style>

<body>
<div class="box1">
    <h1>Muse</h1>
    <h2>playlist</h2>
</div>
<button onclick="history.back()">뒤로가기</button> &nbsp; ID : ${sessionScope.member.id} &nbsp; ACCOUNT : ${sessionScope.member.account}
<br><br>


<input type="radio" name="chk_info" value="title" checked="checked">제목
<input type="radio" name="chk_info" value="artist">가수
<input type="text" id="keyword" name="keyword" required="required">
<button type="submit" id="To_search">search</button><br>
<select name="songs" style="width:300px" id="songs" size="10">
    <option value="" selected>--선택--</option>
</select>
<div class="right" id="list">
    <form action="/Music_info/share" method="post">
        <input type="hidden" name="id" value="${sessionScope.member.id}">

        <button type="submit" name="share_playlist" id="share_playlist">공유 플레이리스트</button>
    </form>

    <input type="text" id="add_playlist" name="add_playlist" required="required">
    <button type="submit" id="new_playlist" name="new_playlist">생성</button><br>
    플레이리스트
    <select name="my_playlist" id="my_playlist">
        <option value="" selected>--선택--</option>
    </select>
    <button type="submit" id="load" name="load">불러오기</button>
    <br>
    <select name="playlist" id="playlist" size="15">
        <option value="" selected>--선택--</option>
    </select>
</div>
</body>
</html>
