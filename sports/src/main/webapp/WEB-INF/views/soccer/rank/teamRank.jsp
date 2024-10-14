<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style type="text/css">
		table{
			width: 1200px;
			table-layout: fixed;
		}
		td{
			text-align: center;
			border: 1px solid #e8e8e8;
		}
		th{
			text-align: center;
			background-color: #44674d;
			color: #fff;
		}
		td img {
			width:30px;
		}
		td input[type="text"]{
			padding: 0px;
			text-align: center;
		}
		tr th,
		tr td{
			width: 100%;
			height: 36px;
		}
		tr th:nth-child(1),
		tr td:nth-child(1){
			min-width: 70px;
			width: 70px;
		}		
		tr th:nth-child(2),
		tr td:nth-child(2){
			min-width: 150px;
			width: 150px;
		}
		tr td:nth-child(2){
			text-align: left;
    		padding-left: 35px;
		}
		tr th:nth-child(12),
		tr td:nth-child(12){
			min-width: 200px;
			width: 200px;
		}
	</style>
</head>
<body>
<h1>KLeague 팀 순위</h1>
	<table>
		<thead>
			<tr>
				<th>순위</th>
				<th>팀명</th>
				<th>경기 수</th>
				<th>승점</th>
				<th>승</th>
				<th>무</th>
				<th>패</th>
				<th>득점</th>
				<th>실점</th>
				<th>득실차</th>
				<th>도움</th>
				<th>파울</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="team" items="${list}">
				<tr>
					<td>${team.rank}</td>
					<td>
					<c:choose>
						<c:when test="${team.title=='수원FC'}">
							<img src="image/soccer/logo/수원.png" width="25" height="25"> 
						</c:when>
						<c:otherwise>
							<img src="image/soccer/logo/${team.title}.png" width="25" height="25"> 
						</c:otherwise>
					</c:choose>
						&emsp;${team.title}
					</td>
					<td>${team.round}</td>
					<td>${team.points}</td>
					<td>${team.win}</td>
					<td>${team.draw}</td>
					<td>${team.lose}</td>
					<td>${team.score}</td>
					<td>${team.conceded}</td>
					<td>${team.difference}</td>
					<td>${team.assist}</td>
					<td>${team.foul}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>