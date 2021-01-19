<%--
  Created by IntelliJ IDEA.
  User: jinwo
  Date: 2021-01-19
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Recommend</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.5.1.min.js"></script> <!-- JQuery -->

    <script lang="javascript">
        $(document).ready(function() {
            console.log($('#recresult').val());
            console.log($('#recresult').val()[0].get(0));
        });
    </script>

</head>
<body>
Hello
${sessionScope.Rec_Result[0].get(0).getMusicID()}
<input type="hidden" id="recresult" value=${sessionScope.Rec_Result} />
${sessionScope.Rec_Result.MusicID}

</body>
</html>
