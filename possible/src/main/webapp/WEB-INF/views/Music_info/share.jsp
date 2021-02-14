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
    <script lang="javascript">
        //after all document read, function start -> so, you can use doc.variance, jsp value
        $(document).ready(function(){
            var json = {
                "id": ${sessionScope.member.id},
                "account": '${sessionScope.member.account}'
            }
            // $.ajax({
            //     type:"get",
            //     url:"/py/recommend_list",
            //     data: json,
            //     datatype: json,
            //     success:function (data){
            //         console.log(data);
            //         var output_data = "";
            //         var output_data2 = "";
            //         var count = $(data).find("userCount").text()[0];
            //         console.log($(data).find("userCount").text())
            //         var tmp = 0;
            //         $(data).find("item").each(function(){
            //             console.log(tmp);
            //             if(count > tmp){
            //                 output_data += "<tr>";
            //                 output_data += "<td>" + $(this).find("artist").text() + "</td>";
            //                 output_data += "<td>" + $(this).find("title").text() + "</td>";
            //                 output_data += "</tr>";
            //             }
            //             else{
            //                 output_data2 += "<tr>";
            //                 output_data2 += "<td>" + $(this).find("artist").text() + "</td>";
            //                 output_data2 += "<td>" + $(this).find("title").text() + "</td>";
            //                 output_data2 += "</tr>";
            //             }
            //             tmp += 1;
            //         })
            //         $("#dynamicTableBody").append(output_data);
            //         $("#dynamicTableBody2").append(output_data2);
            //
            //     },
            //     error:function (error){
            //         alert("추천할 곡이 없습니다.");
            //     }
            // })
        })

    </script>
</head>
<body>
        모두의 플레이리스트 공유 공간입니다.
    // 공유 플레이리스트 테이블을 반정규화를 통해 하나 생성하고 그 테이블을 조회하여 바로 가져오는 방법 (사용자명, 플레이 리스트 정보)
    // share_list 테이블을 바로 조회해서 is_share 가 1인 모든 튜플을 AJAX로 가져와서 보여준다.
</body>
</html>
