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
            // id : #, class : .
            $('#submit').click(function(){
                var json = {
                    artist : $('#artist').val(),
                    title : $('#title').val()
                };

                $.ajax({
                    type: "post",
                    url: "/ytube/search.do",
                    datatype: "json",
                    data: json,
                    success: function(data){
                        $("#videoID").empty();
                        $("#videoID").append(data);
                        $("#videoIDcheck").empty();
                        $("#videoIDcheck").append(data);

                        // This code loads the IFrame Player API code asynchronously.
                        const tag = document.createElement('script');

                        tag.src = "https://www.youtube.com/iframe_api";
                        var firstScriptTag = document.getElementsByTagName('script')[0];
                        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

                        // This function creates an <iframe> (and YouTube player)
                        // after the API code downloads.
                        var player;
                        function onYouTubeIframeAPIReady(){
                            player = new YT.Player('player', {
                                height: 480,
                                width: 854,
                                videoId: data,
                                event: {
                                    'onReady': onPlayerReady,
                                    'onStateChange': onPlayerStateChange
                                }
                            });
                        }

                        onYouTubeIframeAPIReady();

                        // 4. The API will call this function when the video player is ready.
                        function onPlayerReady(event) {
                            alert("on playerReady")
                            event.target.playVideo();
                        }

                        var done = false;
                        function onPlayerStateChange(event){
                            if (event.data == YT.PlayerState.PLAYING && !done){
                                alert("onplayer if")
                                setTimeout(stopVideo, 300000);
                                done=true;
                            }
                        }
                        function stopVideo(){
                            player.stopVideo();
                        }

                    },
                    error: function(err){
                        alert("err :" + err);
                    }
                });
            });
        });

    </script>
</head>
<body>
id = ${sessionScope.member.id}
account = ${sessionScope.member.account}
password = ${sessionScope.member.password}
age = ${sessionScope.member.age}
<br>
<c:if test="${not empty sessionScope.member.id}">
    ${sessionScope.member.account}님 로그인 성공
</c:if>

<div id="player">

</div>

<div>
    <button onclick="javascript:location.href='/';">홈</button>
    <button onclick="javascript:location.href='/member/logout';">로그아웃</button>
</div>

<br>
<form action="/member/detail" method="post">
    <input type="hidden" name="id" value="${sessionScope.member.id}"/>
    <input type="hidden" name="account" value="${sessionScope.member.account}" />
    <button type="submit" name="detail" value="회원정보">회원정보</button>
</form>
<div>
    <form action="/ytube/youtube_get" method="get">
        <button type="submit" name="youtube_get" value="value">youtube 값 가져와보기</button>
    </form>
</div>
<div>
    Artist : <input type="text" name="artist" id="artist" minlength="1"/><br>
    Title : <input type="text" name="title" id="title" minlength="1"/>
    <button id="submit" type="submit">음악검색</button><br>
    Serial : <p id="videoID"></p>
</div>

<%--<iframe id="player" type="text/html" width="854" height="480" frameborder="0"></iframe>--%>
</body>
</html>