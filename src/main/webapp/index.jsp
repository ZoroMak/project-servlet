<%@ page import="com.tictactoe.Sign" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Tic-Tac-Toe</title>
    <link href="<c:url value='/static/main.css'/>" rel="stylesheet">
    <script src="<c:url value='/static/jquery-3.6.0.min.js'/>"></script>
</head>
<body>
<h1>Tic-Tac-Toe</h1>
<table>
    <tr>
        <td onclick="window.location='/logic?click=0'">${data.get(0).sign}</td>
        <td onclick="window.location='/logic?click=1'">${data.get(1).sign}</td>
        <td onclick="window.location='/logic?click=2'">${data.get(2).sign}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=3'">${data.get(3).sign}</td>
        <td onclick="window.location='/logic?click=4'">${data.get(4).sign}</td>
        <td onclick="window.location='/logic?click=5'">${data.get(5).sign}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=6'">${data.get(6).sign}</td>
        <td onclick="window.location='/logic?click=7'">${data.get(7).sign}</td>
        <td onclick="window.location='/logic?click=8'">${data.get(8).sign}</td>
    </tr>
</table>

<c:set var="CROSSES" value="${Sign.CROSS}"/>
<c:set var="NOUGHTS" value="${Sign.NOUGHT}"/>

<c:if test="${winner == CROSSES}">
    <h1>CROSSES WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${winner == NOUGHTS}">
    <h1>NOUGHTS WIN!</h1>
    <button onclick="restart()">Start again</button>
</c:if>
<c:if test="${draw}">
    <h1>IT'S A DRAW</h1>
    <button onclick="restart()">Start again</button>
</c:if>

<script>
    function restart() {
        $.ajax({
            url: '/restart',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>

</body>
</html>
