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


          $("#recommend").click(function (){
              var json = {
                  "id": ${sessionScope.member.id},
                  "account": '${sessionScope.member.account}',
                  "videoID": '${sessionScope.youtubedt.videoID}'
              }
              $.ajax({
                  type:"get",
                  url:"/py/recommend_list",
                  data: json,
                  datatype: json,
                  success:function (data){
                      console.log("helloooooo");
                      console.log(data);
                      var output_data = "";
                      var output_data2 = "";
                      var count = $(data).find("userCount").text()[0];
                      console.log($(data).find("userCount").text())
                      var tmp = 0;
                      $(data).find("item").each(function(){
                          console.log(tmp);
                          if(count > tmp){
                              output_data += "<tr>";
                              output_data += "<td>" + $(this).find("artist").text() + "</td>";
                              output_data += "<td>" + $(this).find("title").text() + "</td>";
                              output_data += "</tr>";
                          }
                          else{
                              output_data2 += "<tr>";
                              output_data2 += "<td>" + $(this).find("artist").text() + "</td>";
                              output_data2 += "<td>" + $(this).find("title").text() + "</td>";
                              output_data2 += "</tr>";
                          }
                          tmp = tmp + 1;
                      })
                      $("#dynamicTableBody").append(output_data);
                      $("#dynamicTableBody2").append(output_data2);
                      // success:function (args){
                      //     $('#songs').find('option').remove();
                      //     $(args).find("item").each(function (){
                      //         console.log($(this).find("title").text())
                      //         $("#songs").append("<option value='"+$(this).find("title").text()+"//"+$(this).find("artist").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"</option>");
                      //     })
                      // },

                  },
                  error:function (error){
                      alert("추천할 곡이 없습니다.");
                  }
              })
          })
        })
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

<div>
    <input type="hidden" id="ID" name="id" value=${sessionScope.member.id} />
    <input type="hidden" id="account" name="account" value=${sessionScope.member.account} />
    <input type="hidden" id="videoID" name="videoID2" value="${sessionScope.youtubedt.videoID}" />
    <button type="submit" id="recommend">노래 추천 받기</button>
</div>

<table id="dynamicTable">
    <thead>
    ${sessionScope.youtubedt.title} 곡을 들은 사람이 함께 듣는 노래
    <tr>
        <th colspan="400px">Artist</th>
        <th colspan="500px">Title</th>
    </tr>

    </thead>
    <tbody id="dynamicTableBody">
    </tbody>
</table>

<table id="dynamicTable">
    <thead>
    ${sessionScope.youtubedt.title} 곡과 어울리는 노래
    <tr>
        <th colspan="400px">Artist</th>
        <th colspan="500px">Title</th>
    </tr>

    </thead>
    <tbody id="dynamicTableBody2">
    </tbody>
</table>

</body>
</html>
