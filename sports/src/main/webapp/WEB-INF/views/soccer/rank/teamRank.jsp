<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin-top: 50px;
	width: 1000px;
}
th,td{
	text-align: center;
}
td img {
	width:30px;
}
</style>
</head>
<body>
<input type="button" value="결과 등록" onclick="location.href='resultRegist'">
<input type="button" value="결과 수정" onclick="location.href='resultEdit'">
<table align="center" border="1">
	<tr>
		<th>Rank<br>(순위)</th>
		<th>Team<br>(팀)</th>
		<th>Round<br>(라운드)</th>
		<th>WinScore<br>(승점)</th>
		<th>Win<br>(승)</th>
		<th>Draw<br>(무)</th>
		<th>Lose<br>(패)</th>
		<th>Gain<br>(득점)</th>
		<th>Lost<br>(실점)</th>
		<th>Range<br>(득실차)</th>
		<th>Assist<br>(도움)</th>
		<th>Foul<br>(파울)</th>
	</tr>
	<c:forEach items="${strlist}" var="r">
		<tr>
			<td>${r.rank}</td>
			<td><img src="./image/soccer/logo/${r.team}.png">${r.team}</td>
			<td>${r.round}</td>
			<td>${r.winscore}</td>
			<td>${r.win}</td>
			<td>${r.draw}</td>
			<td>${r.lose}</td>
			<td>${r.gain}</td>
			<td>${r.lost}</td>
			<td>${r.range}</td>
			<td>${r.assist}</td>
			<td>${r.foul}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>