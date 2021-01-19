<%--
  Created by IntelliJ IDEA.
  User: JJH
  Date: 2021-01-18
  Time: 오후 10:07
  To change this template use File | Settings | File Templates.
--%>
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
                        console.log(args);
                        if(args.length>0){
                            $("#songs").find('option').remove();
                            for(var idx=0;idx<args.length;idx++){
                                $("#songs").append("<option value='"+args[idx]['title']+"'>"
                                +args[idx]['title']+"</option>");
                                console.log(args['title'])
                            }
                        }
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

음악검색: <input type="text" id="keyword" name="keyword">
<button type="submit" id="To_search">search</button><br>
<select name="songs" id="songs" size="15">
    <option value="" selected>--선택--</option>
</select>

</body>
</html>
