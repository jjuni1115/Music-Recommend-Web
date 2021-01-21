<%--
  Created by IntelliJ IDEA.
  User: jinwo
  Date: 2021-01-19
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Integer" %>
<html>
<head>
    <title>Recommend</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->

    <script lang="javascript">
        $(document).ready(function() {
            var output_data = "";
            for(var i=0; i<${recresult[0].size()}; i++){
                <%--var k = Number(i);--%>
                <%--var a = ${recresult[0].musicID[i]};--%>
                // console.log(a);
                i = parseInt(i);
                output_data += "<tr>";
                output_data += "<td>" + ${recresult[0].get(i).musicID} + "</td>";
                output_data += "<td>" + ${recresult[0].get(i).count} + "</td>";
                output_data += "<td>" + ${recresult[0].get(i).playlistID} + "</td>";
                output_data += "</tr>";
            }
            $("#dynamicTableBody").append(output_data);

            var output_data2 = "";
            for(let i=0; i<${recresult[1].size()}; i++){
                output_data2 += "<tr>";
                output_data2 += "<td>" + ${recresult[1].get(i).musicID} + "</td>";
                output_data2 += "<td>" + ${recresult[1].get(i).count} + "</td>";
                output_data2 += "<td>" + ${recresult[1].get(i).playlistID} + "</td>";
                output_data2 += "</tr>";
            }
            $("#dynamicTableBody2").append(output_data2);
        });
    </script>

    <style>








    </style>

</head>
<body>
Hello
<input type="file" id="recresult" value=${recresult[0].get(0)}>

<table id="dynamicTable">
<thead>

<tr>
    <th>MusicID</th>
    <th>Count</th>
    <th>playlistID</th>
</tr>

</thead>
<tbody id="dynamicTableBody">
</tbody>
</table>

<table id="dynamicTable2">
    <thead>
    <tr>
        <th>MusicID</th>
        <th>Count</th>
        <th>playlistID</th>
    </tr>
    </thead>
    <tbody id="dynamicTableBody2">
    </tbody>
</table>

</body>
</html>
