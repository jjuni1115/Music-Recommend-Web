<%--
  Created by IntelliJ IDEA.
  User: jinwoo
  Date: 2021-02-14
  Time: 오후 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Share List</title>
    <style>
        #popup {
            position: absolute;
            overflow: auto;
            width: 300px;
            height: 200px;
            display: none;
        }
    </style>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->
    <script lang="javascript">
        //after all document read, function start -> so, you can use doc.variance, jsp value
        $(document).ready(function(){
            var data = {'id':1}
             $.ajax({
                 type:"POST",
                 url:"/Music_info/load_sharelist",
                 data: data,
                 datatype: 'json',
                 success:function (data){
                     console.log(data);
                     $(data).find("item").each(function(){
                         $("#sharelist").append("<option value='"+$(this).text()+"'>"+$(this).text()+"</option>");
                     })

                     //$(data).find("item").each(function(){

                 },
                 error:function (error){
                     alert("추천할 곡이 없습니다.");
                 }
             })
        })
        $(function load (){                               //load my playlist
            $('#load').click(function(){
                //var param={'keyword':sessionScope.member.id}+"//"+$("#my_playlist").val()};
                var param={"keyword":$("#sharelist").val()};
                $.ajax({
                    type:'POST',
                    url:'/Music_info/sharelist',
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
</head>
<body>
        모두의 플레이리스트 공유 공간입니다.
    // 공유 플레이리스트 테이블을 반정규화를 통해 하나 생성하고 그 테이블을 조회하여 바로 가져오는 방법 (사용자명, 플레이 리스트 정보)
    // share_list 테이블을 바로 조회해서 is_share 가 1인 모든 튜플을 AJAX로 가져와서 보여준다.<br>
        <select name="sharelist" id="sharelist">
            <option value="" selected>--선택--</option>
        </select>
        <button type="submit" id="load" name="load">불러오기</button>
        <br>
        <select name="playlist" id="playlist" size="15">
            <option value="" selected>--선택--</option>
        </select>
</body>
</html>
