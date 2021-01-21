<%--
  Created by IntelliJ IDEA.
  User: jinwo
  Date: 2021-01-19
  Time: 오전 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Youtube Search Result</title>

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->

    <script lang="javascript">
                //after all document read, function start -> so, you can use doc.variance, jsp value
          $(document).ready(function(){
            // id : #, class : .
            // $('#submit').click(function(){

            var iframe_id = "player";
            // var json = {
            //     artist : $('#artist').val(),
            //     title : $('#title').val()
            // };

            // This code loads the IFrame Player API code asynchronously. // It must be out of .ajax Scope. because it is loads Iframe Player API
            // https://stackoverflow.com/questions/31065032/yt-is-not-defined-uncaught-referenceerror-youtube-api hint.
            // https://developers.google.com/youtube/iframe_api_reference?hl=ko#Loading_a_Video_Player
            const tag = document.createElement('script');
            tag.src = "https://www.youtube.com/iframe_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

            // The API will call this function when the video player is ready.
            function onPlayerReady(event) {
                console.log("플레이어 자동 실행");
                event.target.playVideo();
            }

            // This function creates an <iframe> (and YouTube player)
            // after the API code downloads.
            function onYouTubeIframeAPIReady(data){
                console.log("iframe ready: " + data);
                var player = new YT.Player(iframe_id, {
                    height: 480,
                    width: 854,
                    videoId: data,
                    playerVars: {'wmode': 'opaque', 'autohide': 1 , 'enablejsapi': 1 , 'origin': 'http://localhost:8080/member/logon', 'rel': 0},
                    events: {
                        'onReady' : onPlayerReady
                    }
                });
            }

            $('#play').click(function() {
                onYouTubeIframeAPIReady($('#videoID').val());
            });


        // AJAX : get VideoID & setting Iframe to show Video.
        // $.ajax({
        //     type: "post",
        //     url: "/ytube/search.do",
        //     datatype: "json",
        //     data: json,
        //     success: function(data){
        //         $("#videoID").empty();
        //         $("#videoID").text(data);
        //         $("#videoIDd").empty();
        //         $("#videoIDd").text(data);
        //
        //         onYouTubeIframeAPIReady(data);
        //
        //     },
        //     error: function(err){
        //         alert("err :" + err);
        //     }
        // });
        // });

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

        #play
        {
            position: relative;
            left:135px;
            bottom: 20px;
        }




    </style>
</head>
<body>
<h1> Muse </h1>
<h2>YouTube Player</h2>
<button id="back page" type="button" onclick="history.back()">Back Page</button><br>
<button id="play" type="button" id="play">재생하기</button>
<input type="hidden" id="videoID" value=${sessionScope.youtubedt.videoID} /><br>
<div id="player"></div>
</body>
</html>
