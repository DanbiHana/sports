<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>랜덤 뽑기</title>
		<style type="text/css">
			@font-face {
			    font-family: 'HakgyoansimDunggeunmisoTTF-B';
			    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2408-5@1.0/HakgyoansimDunggeunmisoTTF-B.woff2') format('woff2');
			    font-weight: 700;
			    font-style: normal;
			}
			
			.header_team_logo {
				display: none;
			}
			
			.main_section {
				margin-top: 40px;
			}
			
			
			.random_whole {
				position: relative;
				width: 100%;
				height: auto;
				margin: 0 auto;
			}
			
			
			.random_info {
				width: 100%;
				height: auto;
				margin: 20px auto 0 auto;			
			}
			
			.random_info h1 {
				display: flex;
				justify-content: center;
				font-size: 40px;
				line-height: 60px;
				font-weight: bold;
				text-align: center;
			    font-family: 'HakgyoansimDunggeunmisoTTF-B';
			    margin-bottom: 10px;
			}
			
			.random_info h1 span {
				display: block;
				font-size: 40px;
				line-height: 60px;
				font-weight: bold;
				color: #306d3a;
			    font-family: 'HakgyoansimDunggeunmisoTTF-B';
				margin-left: 5px;
			}
			
			.random_info h1 span:nth-child(3) {
				color: #96c59d;
				margin-left: 10px;
			}
			
			.random_info h1 span:nth-child(5),
			.random_info h1 span:nth-child(6) {
				color: #bb0000;
			}
			
			.random_info h1 span:nth-child(5) {
				margin-left: 10px;
			}
			
			.random_info p {
				font-size: 16px;
				line-height: 24px;
				font-weight: 500;
				color: #999;
				text-align: center;
				margin: 0 auto;
			}
			
			.random_info p + p {
				margin-top: 5px;
			}
			
			
			
			
			.random_box {
				width: 100%;
				height: auto;
				margin: 0 auto;
			}
			
			.random_img_whole {
				position: relative;
				width: 100%;
				max-width: 400px;
				height: auto;
				margin: 0 auto;
			}
			
			.random_img_whole img {
				display: block;
				width: 100%;
				height: auto;
				margin: 0 auto;
				background-size: contain;
			}
			
			img.random_img_handle {
				position: absolute;
				width: 100px;
				height: auto;
				left: 50%;
			    bottom: 75px;
			    transform: translateX(-50%);
			}
			
			img.random_img_logo{
				position: absolute;
				width: 100px;
				height: auto;
			    top: 110px;
				left: 50%;
			    transform: translateX(-50%);
			}
			
			button.random_play_btn {
				position: absolute;
				display: block;
				width: 100px;
				height: 40px;
				color: #f9f9f9;
				font-weight: bold;
				border: 1px solid #f9f9f9;
				background-color: #00640050;
				border-radius: 5px;
				bottom: 65px;
				left: 50%;
				transform: translateX(-50%);
				cursor: url('./image/baseball/cursor/pointer2.png'), auto !important;
			}
			
			button.random_play_btn:hover {
				color: #006400;
				background-color: #f9f9f9;
				border-color: #006400;
			}
			
			
			button.re_random_show {
				display: block;
				width: 180px;
				height: 40px;
				color: #093d09;
				background-color: #f9f9f9;
				font-weight: bold;
				border: 1px solid #093d09;
				border-radius: 40px;
				margin: 20px auto 0 auto;
				cursor: url('./image/baseball/cursor/pointer2.png'), auto !important;
			}
			
			button.re_random_show:hover {
				color: #f9f9f9;
				background-color: #006400;
			}
			
			
			.random_show_whole {
				position: fixed;
				width: 100vw;
				height: 100vh;
				top: 0;
				left: 0;
				opacity: 0;
				z-index: -1;
				transition: 1s;
			}

			.random_show_whole.random_show {
				opacity: 1;
				z-index: 4;
				transition: 1s;
			}
			
			.random_show_bg {
				position: absolute;
				width: 100%;
				height: 100%;
				background: rgba(0,0,0,0.5);
				top: 0;
				left: 0;
			}
			
			.random_show_box {
				position: absolute;
				width: calc(100% - 100px);
				max-width: 460px;
				height: auto;
				margin: 0 auto;
				top: 50%;
				left: 50%;
				transform: translate(-50%, -50%);
				background-color: #f9f9f9;
				border-radius: 10px;
				padding: 20px;
			}
			
			.random_show_team {
				display: flex;
				justify-content: center;
				align-items: center;
				width: 100%;
				height: auto;
				margin: 0 auto 20px auto;
			}
			
			.random_show_team img {
				display: block;
				min-width: 60px;
				width: 60px;
				height: auto;
				background-size: contain;
				margin: 0 20px 0 0;
			}
			
			p.random_player_txt {
				font-size: 24px;
				line-height: 36px;
				font-weight: bold;
				color: #999;
				text-align: center;
				margin: 0;
			}
			
			.random_show_flex {
				display: flex;
				flex-wrap: nowrap;
				justify-content: space-between;
				align-items: stretch;
			}
			
			.random_player_img {
				width: 100%;
				max-width: 300px;
				height: auto;
				border-radius: 10px;
				overflow: hidden;
			}
			
			.random_player_img img {
				display: block;
				width: 100%;
				height: 100%;
				object-fit: cover;
			}
			
			.random_show_txt {
				width: 100%;
				height: auto;
				margin: 0 0 0 20px;
			}
			
			.random_show_txt div {
				width: 100%;
				height: auto;
				margin: 0 auto;
			}
			
			.random_show_txt div + div {
				margin-top: 20px;
			}
			
			.random_show_txt div p:nth-child(1) {
				font-size: 14px;
				line-height: 20px;
				font-weight: 400;
				color: #999;
			}
			
			.random_show_txt div p:nth-child(2),
			.random_show_txt div p:nth-child(2) span {
				font-size: 18px;
				line-height: 27px;
				font-weight: 500;
				color: #333;
			}
			
			.random_btn_flex {
				display: flex;
				flex-wrap: nowrap;
				justify-content: center;
				align-items: center;
				width: 100%;
				height: auto;
				margin: 20px auto 0 auto;
			}
			
			button.random_btn {
				display: block;
				width: 100%;
				height: 40px;
				border-radius: 5px;
				margin: 0;
				border: 1px solid transparent;
				background-color: transparent;
			}
			
			button.random_btn.random_btn_hide {
				color: #0c400c;
				background-color: #f9f9f9;
				border-color: #0c400c;
			}
			
			button.random_btn.random_btn_hide:hover {
				background-color: #0c400c20;
			}

			button.random_btn.random_btn_copy {
				color: #f9f9f9;
				background-color: #0c400c;
				margin-left: 20px;
			}

			button.random_btn.random_btn_copy:hover {
				background-color: #053705;
			}
			
			
			
			.no_random_data {
				position: absolute;
				width: max-content;
				max-width: 100%;
				height: auto;
				top: 48%;
				left: 50%;
				transform: translate(-50%, -50%);
				padding: 50px;
				background: rgba(255,255,255,0.9);
			}
			
			p.no_data_txt {
				padding: 0;
			}

			
			#random_result_copy {
				position: fixed;
				width: 1px;
				height: 1px;
				top: -200vh;
				left: -200vw;
				opacity: 0;
			}
		</style>
		<script type="text/javascript">
			var randomInfoArray = [];//선수들 불러왔을 때 값 저장
			function randomReplay() {
				location.href = 'baseballRandom?name=ALL';
			}
		</script>
	</head>
	<body>
		<div class="random_whole">
			<!-- 랜덤 리스트 반복문 -->
			<c:forEach items="${randomList}" var="randomList">
				<script type="text/javascript">
					var tname = `${randomList.tname}`;
					var pname = `${randomList.pname}`;
					var pnumber = `${randomList.pnumber}`;
					var pbirth = `${randomList.pbirth}`;
					var height = `${randomList.height}`;
					var weight = `${randomList.weight}`;
					var main = `${randomList.main}`;
					var image = `${randomList.pimage}`;
					
					randomInfoArray.push([tname]+","+[pname]+","+[pnumber]+","+[pbirth]+","+[height]+","+[weight]+","+[main]+","+[image]);
				</script>
			</c:forEach>
			<c:if test="${randomList != null}">
				<!-- 랜덤 리스트가 있을 때 로딩 -->
				<div class="random_info">
					<h1><span>랜</span><span>덤</span> <span>뽑</span><span>기</span> <span>결</span><span>과</span></h1>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						var random_num = Math.random();
						random_num = random_num * randomInfoArray.length;
						random_num = Math.floor(random_num);
						if(random_num >= randomInfoArray.length) {
							random_num = randomInfoArray.length - 1;
						};
						console.log(random_num);
						console.log(randomInfoArray.length);
						
						var randomRealArray = randomInfoArray[random_num].split(',');
						console.log(randomRealArray);
						var team_name = null;
						var team_img = null;
						if(randomRealArray[0] == 'KIA' || randomRealArray[0] == 'kia') {
							team_name = '기아';
							team_img = './image/baseball/logo/KIA.png';
							$('.random_player_txt').css('color', '#04131e');
						}
						else if(randomRealArray[0] == 'KT' || randomRealArray[0] == 'kt') {
							team_name = '케이티';
							team_img = './image/baseball/logo/KT.png';
							$('.random_player_txt').css('color', '#0f1417');
						}
						else if(randomRealArray[0] == 'LG' || randomRealArray[0] == 'lg') {
							team_name = '엘지';
							team_img = './image/baseball/logo/LG.png';
							$('.random_player_txt').css('color', '#706e6f');
						}
						else if(randomRealArray[0] == 'NC' || randomRealArray[0] == 'nc') {
							team_name = '엔씨';
							team_img = './image/baseball/logo/NC.png';
							$('.random_player_txt').css('color', '#ad907b');
						}
						else if(randomRealArray[0] == 'SSG' || randomRealArray[0] == 'ssg') {
							team_name = '신세계';
							team_img = './image/baseball/logo/SSG.png';
							$('.random_player_txt').css('color', '#c2192c');
						}
						else if(randomRealArray[0] == 'DOOSAN' || randomRealArray[0] == 'doosan') {
							team_name = '두산';
							team_img = './image/baseball/logo/두산.png';
							$('.random_player_txt').css('color', '#dd2524');
						}
						else if(randomRealArray[0] == 'LOTTE' || randomRealArray[0] == 'lotte') {
							team_name = '롯데';
							team_img = './image/baseball/logo/롯데.png';
							$('.random_player_txt').css('color', '#0e2443');
						}
						else if(randomRealArray[0] == 'SAMSUNG' || randomRealArray[0] == 'samsung') {
							team_name = '삼성';
							team_img = './image/baseball/logo/삼성.png';
							$('.random_player_txt').css('color', '#0460a7');
						}
						else if(randomRealArray[0] == 'KIWOOM' || randomRealArray[0] == 'kiwoom') {
							team_name = '키움';
							team_img = './image/baseball/logo/키움.png';
							$('.random_player_txt').css('color', '#7c1925');
						}
						else if(randomRealArray[0] == 'HANHWA' || randomRealArray[0] == 'hanhwa') {
							team_name = '한화';
							team_img = './image/baseball/logo/한화.png';
							$('.random_player_txt').css('color', '#eb5c1d');
						}

						$('#player_logo').attr('src', team_img);
						$('#player_img').attr('src', './image/baseball/player/'+randomRealArray[7]);
						$('#player_team').html(team_name);//팀이름
						$('#player_name').html(randomRealArray[1]);//플레이어이름
						$('#player_num').html(randomRealArray[2]);//등번호
						$('#player_birth').html(randomRealArray[3]);//생일
						$('#player_height').html(randomRealArray[4]);//키
						$('#player_weight').html(randomRealArray[5]);//몸무게
						$('#player_position').html(randomRealArray[6]);//포지션
						
						$('#random_result_copy').attr('value', '팀: '+ randomRealArray[0] + ', 선수 이름: ' + randomRealArray[1] + ', 선수 번호: ' + randomRealArray[2] + ', 생일: ' +  randomRealArray[3] + ', 키: ' + randomRealArray[4] + ', 몸무게: ' + randomRealArray[5] + ', 포지션: ' + randomRealArray[6])
						$('.random_img_logo').attr('src', team_img);
						
						resultShow();
					});
					
					function resultCopy() {
						$('#random_result_copy').select();
						document.execCommand('copy');
						alertShow('결과 복사', '랜덤뽑기 결과가 복사 되었습니다.');
						return false;
					}
					
					function resultShow() {
						$('body').css('overflow', 'auto');
						$('.random_show_whole').addClass('random_show');
					}
					
					function resultHide() {
						$('body').css('overflow', 'auto');
						$('.random_show_whole').removeClass('random_show');
					}

					function NotReload(){
					    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
					        event.keyCode = 0;
					        event.cancelBubble = true;
					        event.returnValue = false;
					    }
					}

					document.onkeydown = NotReload;
					
					function rePopShow() {
						resultShow();
					}
				</script>
				<input type="text" id="random_result_copy" value="">
				<div class="random_box">
					<div class="random_img_whole">
						<img alt="로고 이미지" src="./image/baseball/logo/all.png" class="random_img_logo">
						<img alt="오락기 이미지" src="./image/baseball/random/baseball_random_end.png" class="random_img_bg">
						<img alt="핸들 이미지" src="./image/baseball/random/baseball_random_handle.png" class="random_img_handle">
						<button type="button" onclick="randomReplay()" class="random_play_btn">다시 뽑기</button>
					</div>
					<button type="button" onclick="rePopShow()" class="re_random_show">결과 다시 보기</button>
				</div>
				<div class="random_show_whole">
					<div class="random_show_bg"></div>
					<div class="random_show_box">
						<div class="random_show_team">
							<img alt="랜덤 로고 이미지" src="" id="player_logo">
							<p id="player_team" class="random_player_txt">팀 이름</p>
						</div>
						<div class="random_show_flex">
							<div class="random_player_img">
								<img alt="랜덤으로 나온 선수 이미지" src="" id="player_img">
							</div>
							<div class="random_show_txt">
								<div>
									<p>플레이어</p>
									<p id="player_name">플레이어 이름</p>
								</div>
								<div>
									<p>선수 번호</p>
									<p id="player_num">선수 번호</p>
								</div>
								<div>
									<p>생일</p>
									<p id="player_birth">생일</p>
								</div>
								<div>
									<p>키</p>
									<p><span id="player_height"></span>cm</p>
								</div>
								<div>
									<p>몸무게</p>
									<p><span id="player_weight"></span>kg</p>
								</div>
								<div>
									<p>포지션</p>
									<p id="player_position">포지션</p>
								</div>
							</div>
						</div>
						<div class="random_btn_flex">
							<button type="button" onclick="resultHide()" class="random_btn random_btn_hide">닫기</button>
							<button type="button" onclick="resultCopy()" class="random_btn random_btn_copy">결과 복사하기</button>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${empty randomList}">
				<div class="no_random_data">
					<p class="no_data_txt">팀/선수 정보가 없습니다.</p>
				</div>
			</c:if>
		</div>
	</body>
</html>