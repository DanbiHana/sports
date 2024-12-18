<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style type="text/css">
			.main_section{
				width: calc(100% - 40px);
				max-width: 1020px;
				margin-bottom: 70px;
			}
			h1{
				margin-top: 60px;
			}
			.A{
				display: flex;
				flex-wrap: wrap;
				align-items: stretch;
			}
			.B{
				width: calc(16.6% - 24.4px);
				margin-top: 40px;
				overflow: hidden;
			}
			.B+.B{
				margin-left: 30px;
			}
			.B:nth-child(6n+1){
				margin-left: 0;
			}
			.B:nth-child(1),
			.B:nth-child(2),
			.B:nth-child(3),
			.B:nth-child(4),
			.B:nth-child(5),
			.B:nth-child(6){
				margin-top: 0;
			}
			.B a{
				width: 100%;
				height: 100%;
				overflow: hidden;
			}
			.B img.player{
				width: calc(100% - 6px);
				height: 194px;
				object-fit: cover;
				border: 3px solid transparent;
				border-radius: 10px;
				overflow: hidden;
			}
			.B:hover img.player{
				box-shadow: 0 0 15px #00640040;
			}
			.B p{
				width: 100%;
				font-size: 16px;
				line-height: 24px;
				font-weight: bold;
				color: #333;
				margin: 0 auto;
				padding-top: 5px;
				text-align: center;
			}
			.B p+p{
				font-size: 14px;
				line-height: 20px;
				font-weight: 400;
				color: #999;
				margin-top: 5px;
				padding-top: 0;
			}
			.B p img{
				display: block;
				min-width: 30px;
				width: 30px;
				height: auto;
				background-size: contain;
				margin-right: 10px;
			}
			p.no_data_txt{
				padding: calc((100% - 232px) / 2) 0;
			}
			.hidden{
				position: fixed;
				top: -200vh;
				left: -200vw;
			}
			
			
			@media(max-width: 1000px){
				.B{
					width: calc(33.3% - 13.3px);
				}
				.B +.B{
					margin-left: 20px;
				}
				.B:nth-child(6n+1){
					margin-left: 20px;
				}
				.B:nth-child(3n+1){
					margin-left: 0;
				}
				.B:nth-child(4),
				.B:nth-child(5),
				.B:nth-child(6){
					margin-top: 30px;
				}
			}
			@media(max-width: 400px){
				.B{
					width: calc(50% - 10px);
				}
				.B:nth-child(3n+1){
					margin-left: 20px;
				}
				.B:nth-child(2n+1){
					margin-left: 0;
				}
				.B:nth-child(3){
					margin-top: 30px;
				}
			}
			.selectBox{
				display: flex;
				flex-wrap: nowrap;
				width: 100%;
				justify-content: start;
				align-items: center;
			}
			select{
				min-width: 98px;
				width: 98px;
				border-color: #00640050;
				padding-top: 5px;
			    padding-bottom: 5px;
			    padding-left: 10px;
			    border-radius: 20px;
			    outline: none;
			}
			select:focus{
				border-color: #006400;
			    outline: none;
			}
			.posi_radio{
				width: max-content;
			}
			.posi_radio div{
				width: max-content;
				display: flex;
				flex-wrap: nowrap;
				justify-content: start;
				align-items: center;
			}
			.posi_radio div label{
				display: block;
				width: max-content;
				margin: 0 0 0 5px;
			}
			.posi_radio div label:first-child{
				margin-left: 15px;
			}
			input[name="posival"]{
				display: none;
			}
			input[name="posival"]+span{
				display: block;
				width: max-content;
				max-width: 100%;
				height: auto;
				font-size: 15px;
				line-height: 22px;
				font-weight: 500;
				color: #00640080;
				background-color: transparent;
				border: 1px solid #00640080;
				border-radius: 25px;
				margin-right: 0;
				padding: 6px 20px;
			}
			input[name="posival"]:hover + span{
				background-color: #00640020;
			}
			input[name="posival"]:checked + span{
				color: #f9f9f9;
				background-color: #00640090;
				border-color: #006400;
			}
			input[type="search"]{
				width: calc(100% - 20px);
				max-width: 320px;
				height: 36px;
				margin-left: 10px;
				font-size: 15px;
				border: 1px solid #00640050;
				border-radius: 25px;
				padding: 5px 10px;
				display: none;
			}
			input[type="search"]:focus{
				box-shadow: none;
				outline: none;
				border-color: #006400;
			}
			.btn{
				display: block;
				min-width: 88px;
				width: 88px;
				height: 34px;
				font-size: 15px;
				line-height: 36px;
				color: #006400;
				margin-left: 10px;
				padding: 0;
				background-color: #00640010;
				border: 1px solid #006400;
				outline: none!important;
				border-radius: 25px;
			}
			.btn:hover{
				color: #f9f9f9;
				background-color: #0c400c;
			}
			.d-flex{
				text-align: center;
			}
		</style>
	</head>
	<body>
		<!-- 검색 -->
		<form class="d-flex" id="searchlist" role="search" action="Ssearchlist">
			<input type="text" name="sports" value="야구" class="hidden">
			<input type="text" id="team_area" name="area" value="" class="hidden">
			<div class="selectBox">
				<select name="searchcategory" onchange="searchChange()" class="select">
					<option value="포지션">포지션</option>
					<option value="선수명">선수명</option>
				</select>
				<div class="posi_radio">
					<div>
						<label for="posival1">
							<input type="radio" name="posival" id="posival1" value="투수">
							<span>투수</span>
						</label>
						<label for="posival2">
							<input type="radio" name="posival" id="posival2" value="포수">
							<span>포수</span>
						</label>
						<label for="posival3">
							<input type="radio" name="posival" id="posival3" value="내야수">
							<span>내야수</span>
						</label>
						<label for="posival4">
							<input type="radio" name="posival" id="posival4" value="외야수">
							<span>외야수</span>
						</label>
					</div>
				</div>
		        <input class="search_input" type="search" placeholder="검색어를 입력하세요" aria-label="Search" name="searchval" onkeyup="searchClick()">
		        <button class="btn btn-outline-success" type="button" id="searchClickItem" onclick="playSearch()">검색</button>						
			</div>
      	</form>
		<c:choose>
			<c:when test="${empty list}">
				<p class="no_data_txt">선수 목록이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:if test="${posival == null || posival == 'undefined' || posival=='투수'}">
					<h1>투수</h1>
				</c:if>
				<div class="A">
					<c:forEach items="${list}" var="j">
					<c:if test="${j.main=='투수'}">
						<div class="B">
							<a href="playerdetail?playernum=${j.playernum}&play=야구">
								<img alt="${j.pname} 선수 이미지" src="./image/baseball/player/${j.pimage}" class="player">
								<p>${j.pname}</p>
								<p>| ${j.pnumber} |</p>
							</a>
						</div>
					</c:if>
					</c:forEach>
				</div>
				<c:if test="${posival == null || posival == 'undefined' || posival=='포수'}">
					<h1>포수</h1>
				</c:if>
				<div class="A">
					<c:forEach items="${list}" var="j">
						<c:if test="${j.main=='포수'}">
							<div class="B">
								<a href="playerdetail?playernum=${j.playernum}&play=야구">
									<img alt="${j.tname} 팀 로고" src="./image/baseball/player/${j.pimage}" class="player">
									<p>${j.pname}</p>
									<p>| ${j.pnumber} |</p>
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<c:if test="${posival == null || posival == 'undefined' || posival=='내야수'}">
					<h1>내야수</h1>
				</c:if>
				<div class="A">
					<c:forEach items="${list}" var="j">
						<c:if test="${j.main=='내야수'}">
							<div class="B">
								<a href="playerdetail?playernum=${j.playernum}&play=야구">
								<img alt="${j.tname} 팀 로고" src="./image/baseball/player/${j.pimage}" class="player">
								<p>${j.pname}</p>
								<p>| ${j.pnumber} |</p>
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<c:if test="${posival == null || posival == 'undefined' || posival=='외야수'}">
					<h1>외야수</h1>
				</c:if>
				<div class="A">
					<c:forEach items="${list}" var="j">
						<c:if test="${j.main=='외야수'}">
							<div class="B">
								<a href="playerdetail?playernum=${j.playernum}&play=야구">
								<img alt="${j.tname} 팀 로고" src="./image/baseball/player/${j.pimage}" class="player">
								<p>${j.pname}</p>
								<p>| ${j.pnumber} |</p>
								</a>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</body>
	<script type="text/javascript">
		//야구
		function searchChange(){
			var select_val = $('select[name="searchcategory"] option:selected').val();
			if(select_val=='포지션'){
				$('.posi_radio').show();
				$('.search_input').hide();
				$('input[name="posival"]').prop('checked', false);
			}
			if(select_val=='선수명'){
				$('input[name="posival"]').prop('checked', false);
				$('.posi_radio').hide();
				$('.search_input').show();
			}
		}
		function playSearch() {
			var baseball_area = $('#team_area').val();
			var sports = $('input[name="sports"]').val();
			var searchcategory = $('select[name="searchcategory"]').val();
			var posival = $('input[name="posival"]:checked').val();
			var searchval = $('input[name="searchval"]').val();
			if(searchcategory == '포지션'){
				if(posival == '' || posival == null || posival == 'undefined'){
					alertShow('검색 불가', '포지션을 선택해주세요.');
				}
				else{
					window.location.href='Ssearchlist?name='+baseball_area+'&sports='+sports+'&searchcategory='+searchcategory+'&posival='+posival+'&searchval='+searchval;					
				}
			}else{
				if(searchval == '' || searchval == null || searchval == 'undefined'){
					alertShow('검색 불가', '검색어를 입력해주세요.');
				}
				else{
					window.location.href='Ssearchlist?name='+baseball_area+'&sports='+sports+'&searchcategory='+searchcategory+'&posival='+posival+'&searchval='+searchval;					
				}
			}
		}
		function searchClick() {
			if(window.event.keyCode == 13){
				$('#searchClickItem').click();
			}
		}
        function baseballClick(ths) {
            var ths_id = ths.dataset.id;
            var ths_type = ths.dataset.type;
            sessionSet(ths_id, ths_type);
			window.location.replace('selectTeam'+"?name="+ths_id+"&play=야구");
         }
		$(document).ready(function(){
			var win_pname = window.location.search;
			var win_bname = win_pname.split('&');
			var win_tname = win_bname[0].slice(6);
			$('.header_logo_inner a').removeClass('clicked_on');
			$('#baseball_'+win_tname).addClass('clicked_on');
			$('#team_area').val(win_tname);
			var category = `${category}`;
			var posival = `${posival}`;
			var vocavalue = `${vocavalue}`;
			if(category != null && category != '' && category != 'undefined'){
				$('select[name="searchcategory"] option[value='+category+']').prop('selected', true);
				if(category == '포지션'){
					$('input[name="posival"][value='+posival+']').prop('checked', true);
					$('.posi_radio').show();
					$('.search_input').hide();
				}
				if(category == '선수명'){
					$('.posi_radio').hide();
					$('.search_input').show();
					$('input[name="searchval"]').val(vocavalue);
				}
			}
		});
	</script>
</html>