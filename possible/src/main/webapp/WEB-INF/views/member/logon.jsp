<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 1/9/2021
  Time: 2:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Log-on</title>
    <c:if test="${empty sessionScope.member.account}"> <!--necessary jstl dependency_taglib-->
        <script>
            alert("로그인 실패");
            history.back();
        </script>
    </c:if>

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->

    <script lang="javascript">
        //after all document read, function start -> so, you can use doc.variance, jsp value
        $(document).ready(function(){
        });
    </script>

</head>

<style>
    <%@ include file ="../../CSS/logon.css" %>
</style>
<body>

<h1>Muse</h1>


<div id="up-menu">
    <button onclick="javascript:location.href='/';">홈</button>
    <button onclick="javascript:location.href='/member/logout';">로그아웃</button>
</div>

<br>
<div id="middle-menu">
    <form action="/member/detail" method="post">
        <input type="hidden" name="id" value="${sessionScope.member.id}"/>
        <input type="hidden" name="account" value="${sessionScope.member.account}" />
        <button type="submit" name="detail" value="회원정보">회원정보</button>
    </form>
</div>

<div id="search-form">
    <form action="/ytube/searchDo" method="get">
        Artist : <input type="text" name="Artist" id="artist"/><br>
        Title : <input type="text" name="title" id="title" minlength="1"/>
        <input type="hidden" name="id" value="${sessionScope.member.id}"/>
        <input type="hidden" name="account" value="${sessionScope.member.account}" />
        <button id="submit" type="submit">음악검색</button><br>
    </form>
</div>
<%--    Serial ID : <p id="videoIDd"></p>--%>

    <form action="/Music_info/playlist" method="post">
        <input type="hidden" name="id" value=${sessionScope.member.id} />
        <input type="hidden" name="account" value=${sessionScope.member.account} />
        <input type="hidden" name="first_name" value=${sessionScope.member.first_name} />
        <input type="hidden" name="last_name" value=${sessionScope.member.last_name} />
        Music Recommend : <button type="submit" id="submit_recommend" >플레이 리스트 & 음악 추천</button><br>
    </form>
</div>
<div id="player" class="youtube">
</div>


<div id="login info">
    id = ${sessionScope.member.id}
    account = ${sessionScope.member.account}
    password = ${sessionScope.member.password}
    age = ${sessionScope.member.age}
    name = ${sessionScope.member.first_name}
<br>
    <c:if test="${not empty sessionScope.member.id}">
        ${sessionScope.member.account}님 로그인 성공
    </c:if>
</div>


</body>
</html>



<script>
    // id : #, class : .
    // $('#submit').click(function(){
    //     var iframe_id = "player" + player;
    //     var json = {
    //         artist : $('#artist').val(),
    //         title : $('#title').val()
    //     };
    //
    //     // This code loads the IFrame Player API code asynchronously. // It must be out of .ajax Scope. because it is loads Iframe Player API
    //     // https://stackoverflow.com/questions/31065032/yt-is-not-defined-uncaught-referenceerror-youtube-api hint.
    //     // https://developers.google.com/youtube/iframe_api_reference?hl=ko#Loading_a_Video_Player
    //     const tag = document.createElement('script');
    //     tag.src = "https://www.youtube.com/iframe_api";
    //     var firstScriptTag = document.getElementsByTagName('script')[0];
    //     firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
    //
    //     // The API will call this function when the video player is ready.
    //     function onPlayerReady(event) {
    //         console.log("플레이어 자동 실행");
    //         event.target.playVideo();
    //     }
    //
    //
    //     // This function creates an <iframe> (and YouTube player)
    //     // after the API code downloads.
    //     function onYouTubeIframeAPIReady(data){
    //         console.log("iframe ready: " + data);
    //         var player = new YT.Player(iframe_id, {
    //             height: 140,
    //             width: 220,
    //             videoId: data,
    //             playerVars: {'wmode': 'opaque', 'autohide': 1 , 'enablejsapi': 1 , 'origin': 'http://localhost:8080/member/logon', 'rel': 0},
    //             events: {
    //                 'onReady' : onPlayerReady
    //             }
    //         });
    //         player.setPlayerQuality = "small";
    //     }
    //
    //     // AJAX : get VideoID & setting Iframe to show Video.
    //     $.ajax({
    //         type: "post",
    //         url: "/ytube/search.do",
    //         datatype: "json",
    //         data: json,
    //         success: function(data){
    //             $("#videoID").empty();
    //             $("#videoID").text(data);
    //             $("#videoIDd").empty();
    //             $("#videoIDd").text(data);
    //
    //             onYouTubeIframeAPIReady(data);
    //
    //         },
    //         error: function(err){
    //             alert("err :" + err);
    //         }
    //     });
    // });



</script>
