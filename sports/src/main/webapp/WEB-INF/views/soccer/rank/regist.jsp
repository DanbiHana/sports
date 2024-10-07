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
			margin: 50px auto 0 auto;
			width: 1000px;
		}
		th{
			text-align: center;
		}		
		tr,td{
			border: 1px solid #e8e8e8;
			text-align: center;
		}
		td img {
			width:30px;
		}
	</style>
	<script type="text/javascript">
		function selectTeam(){		
			var team = $('#team').val();
			$.ajax({
				type: "post",
				url: "selectRankTeam",
				async: true,
				data: {"team":team},
				dataType : 'json',
				success:function(data){
					var content = "<table class= \"table\">"+
									"<tr>"+
										"<th rowspan=\"2\">No.</th>"+
										"<th>Player</th>"+
										"<th>Gain</th>"+
										"<th>Assist</th>"+
										"<th>AttackPoint</th>"+
										"<th>Match</th>"+
										"<th>Rate</th>"+
										"<th rowspan=\"2\">MOM</th>"+
										"<th>Best11</th>"+
										"<th>Shoot</th>"+
										"<th>OnTargetShot</th>"+
									"</tr>"+
									"<tr class=\"thkor\">"+
										"<th>( 선수 )</th>"+
										"<th>( 득점 )</th>"+
										"<th>( 도움 )</th>"+
										"<th>( 공격포인트 )</th>"+
										"<th>( 경기 수 )</th>"+
										"<th>( 평점 )</th>"+
										"<th>( 선정 수 )</th>"+
										"<th>( 슈팅 )</th>"+
										"<th>( 유효 슈팅 )</th>"+
									"</tr>";
						
						for(var i = 0; i < list.length; i++){
							content += "<input type=\"hidden\" name=\"playernum\" value=\""+list[i]['playernum']+"\">";
							content += "<tr>";
							content += "<td>"+ i +"</td>";
							content += "<td> <input type=\"text\" name=\"pname\" value=\""+list[i]['playerDTO']['pname']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"gain\" value=\""+list[i]['gain']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"assist\" value=\""+list[i]['assist']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"attackpoint\" value=\""+list[i]['attackpoint']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"round\" value=\""+list[i]['round']+"\" readonly> </td>";
							content += "<td> <input type=\"text\" name=\"avgscore\" value=\""+list[i]['avgscore']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"mom\" value=\""+list[i]['mom']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"best11\" value=\""+list[i]['best11']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"shoot\" value=\""+list[i]['shoot']+"\" readonly> </td>";
							content += "<td> <input type=\"number\" name=\"possibleshoot\" value=\""+list[i]['possibleshoot']+"\" readonly> </td>";
							content += "</tr>";
						}
						content += "</table>"
						$('#result').html(content);
				},
				error:function(){
					alert("에라이");
				}
			});
		}
	</script>
</head>
<body>
	<div class="selectTeam">
		<select name="team" id="team">
			<option value="kangwon">강원</option>
			<option value="gwangju">광주</option>
			<option value="gimcheon">김천</option>
			<option value="daegu">대구</option>
			<option value="daejeon">대전</option>
			<option value="seoul">서울</option>
			<option value="suwon">수원</option>
			<option value="ulsan">울산</option>
			<option value="incheon">인천</option>
			<option value="jeonbuk">전북</option>
			<option value="jeju">제주</option>
			<option value="pohang">포항</option>
		</select>
		<input type="button" value="조회" onclick="selectTeam()">
	</div>
	<form action="soccerplayersave">
	<div id="result">
		
	</div>
	</form>
</body>
</html>