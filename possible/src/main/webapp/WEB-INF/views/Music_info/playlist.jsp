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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->
    <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){                       //search music
            $("#To_search").click(function (){
                //var radioVal = $('input[name="chk_info"]:checked').val();
                //console.log(radioVal)
                //var params={"keyword":$('input[name="chk_info"]:checked').val()+"//"+$("#keyword").val()};
                var params={"keyword":$('input[name="chk_info"]:checked').val(),
                            "name":$("#keyword").val()}
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

            //  $('#isShare:checked').change  // 공유 버튼 체크 Toggle
            $('#isShare').change(function () {
                // let isShare = $('#isShare').is(":checked");
                var list_name = $("#my_playlist")[0].value;
                console.log(list_name);
                var param ={
                    "user_ID" : ${sessionScope.member.id},
                    "list_name" : list_name
                }

                $.ajax({
                    type: "get",
                    url: "/Music_info/toggle_share",
                    data: param,
                    success: function (data) {
                    },
                    error: function (error) {
                        alert("Error Occured");
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
                        $('#playlist').find('option').remove();
                        $(args).find("item").each(function (){
                            $("#playlist").append("<option value='"+$(this).find("title").text()+"//"+$(this).find("artist").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"</option>");
                        })
                        //$("#playlist").append("<option value='"+$("#songs").val()+"'>"+list[0]+" - "+list[1]+"</option>");
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
                //var param={'keyword':sessionScope.member.id}+"//"+$("#my_playlist").val()};
                var param={"keyword": ${sessionScope.member.id},
                            "name":$("#my_playlist")[0].value};
                $.ajax({
                    type:"POST",
                    url:'/Music_info/load_playlist',
                    data:param,
                    datatype:'json',
                    success:function(args) {
                        $("#playlist option").remove();

                        // console.log(args);
                        // console.log(Object.keys($(args).find("item").find("title")).length - 2);  //  음원 갯수
                        let lenObject = (Object.keys($(args).find("item").find("title")).length - 2 + 1);  // Object type으로 받아와서 사용, Object 길이는 [음원 개수 + 공유여부(1개)]
                        for(let i=0; i<(lenObject - 1); i++){
                            $("#playlist").append("<option value='"+$(args).find("item").find("title")[i].textContent+
                                "//"+$(args).find("item").find("artist")[i].textContent+
                                "'>"+$(args).find("item").find("title")[i].textContent+
                                " - "+$(args).find("item").find("artist")[i].textContent+"</option>");
                        }


                        if ($(args).find("item")[lenObject - 1].textContent == "true"){
                            console.log("공유여부 : " + $(args).find("item")[lenObject - 1].textContent);
                            $("#isShare").prop("checked", true);
                        }
                        else {
                            console.log("공유여부 : " + $(args).find("item")[lenObject - 1].textContent);
                            $("#isShare").prop("checked", false);
                        }
                    },error:function(error){
                        alert("error: load playlist");
                }
                //})
            })
            })
        })

    </script>
    <script lang="javascript">
        $(document).ready(function(){                      //내 플레이리스트 더블클릭시
            $('#playlist').dblclick(function(){
                $("#dialog-message").dialog({
                    modal:true,
                    buttons:{
                        "재생":function(){$(this).dialog('close');
                                var artistTitle = $('#playlist')[0].value.split("//");  // artist, title split
                                location.href = "/ytube/searchDo?" + "id=" + ${sessionScope.member.id} + "&account=" +
                                ${sessionScope.member.account} + "&Artist=" + artistTitle[1] + "&title=" + artistTitle[0];},
                        "삭제":function(){
                            $(this).dialog('close');
                            var params={"keyword":${sessionScope.member.id}+"//"+$("#playlist").val()+"//"+$("#my_playlist").val()};
                            $.ajax({
                                type:"POST",
                                url:'/Music_info/delete_music',
                                data:params,
                                datatype:'json',
                                success:function(args) {
                                    alert("delete success");
                                    $('#playlist').find('option').remove();
                                    $(args).find("item").each(function (){
                                        $("#playlist").append("<option value='"+$(this).find("title").text()+"//"+$(this).find("artist").text()+"'>"+$(this).find("title").text()+" - "+$(this).find("artist").text()+"</option>");
                                    })

                                },error:function(error){
                                    alert("delete error!");
                                }
                            })
                            },
                        "취소":function(){$(this).dialog('close');}
                    }

                })
            });
        })
    </script>

    <script type="text/javascript">
        $(document).ready(function (){
            $("#new_playlist").click(function (){
                <%--var obj = new Object();--%>
                <%--obj.id = ${sessionScope.member.id};--%>
                <%--obj.first_name = "${sessionScope.member.first_name}";--%>
                <%--obj.last_name = "${sessionScope.member.last_name}";--%>
                <%--obj.list_name = $("#add_playlist")[0].value;--%>
                <%--var param = JSON.stringify(obj);--%>
                console.log($("#my_playlist")[0].length);
                console.log($("#my_playlist")[0]);
                let playlistNum = $("#my_playlist")[0].length - 1;

                // 플레이리스트 중복 체크 후 ajax 날리기
                for(var i=1; i<=playlistNum; i++){
                    console.log($("#my_playlist").find("option")[i].textContent);
                    if($("#my_playlist").find("option")[i].textContent == $("#add_playlist")[0].value){
                        alert("동일한 플레이리스트가 존재합니다.")
                        return;
                    }
                }
                var param={
                        "id" : "${sessionScope.member.id}",
                        "list_name" : $("#add_playlist")[0].value,
                        "first_name" : "${sessionScope.member.first_name}",
                        "last_name" : "${sessionScope.member.last_name}",
                        "is_share" : $("#share").is(":checked")
                    }
                console.log(param);
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

    <script>
        $(function delete_music(){
            $("#playlist").dblclick(function (){

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
    <input type="checkbox" id="share" name="share" value="공유">공유
    <input type="text" id="add_playlist" name="add_playlist" required="required">
    <button type="submit" id="new_playlist" name="new_playlist">생성</button><br>
    플레이리스트
    <select name="my_playlist" id="my_playlist">
        <option value="" selected>--선택--</option>
    </select>
    <button type="submit" id="load" name="load">불러오기</button> 공유 여부<input type="checkbox" id="isShare" name="isShare" value="공유">
    <br>
    <select name="playlist" id="playlist" size="15">
        <option value="" selected>--선택--</option>
    </select>
</div>
<div id="dialog-message" title="option" style="display:none">
    옵션을 선택하세요.
</div>
</body>
</html>
