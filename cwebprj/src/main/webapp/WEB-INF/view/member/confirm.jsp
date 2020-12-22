<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>환영합니다.</title>
<meta charset="UTF-8">
<link href="/css/member/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/member/visual.png") no-repeat center;
}
</style>
</head>

<body>
	<!-- header 부분 -->
	<%@include file="/WEB-INF/view/include/header.jsp"%>

	<!-- --------------------------- <visual> --------------------------------------- -->
	<!-- visual 부분 -->

	<div id="visual">
		<div class="content-container"></div>
	</div>
	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">

			<!-- --------------------------- aside --------------------------------------- -->
			<!-- aside 부분 -->

			<aside class="aside">
				<h1>회원가입</h1>

				<nav class="menu text-menu first margin-top">
					<h1>회원메뉴</h1>
					<ul>
						<li><a class="" href="/member/login">로그인</a></li>
						<li><a class="" href="/member/join">회원가입</a></li>
						<li><a class="" href="/member/find-id">아이디찾기</a></li>
						<li><a class="" href="/member/pwd-reset">비밀번호 리셋</a></li>

					</ul>
				</nav>


				<nav class="menu">
					<h1>CDM 졸업학교</h1>
					<ul>
						<li><a target="_blank" href="http://www.mjc.ac.kr"><img
								src="/images/mjc.png" alt="명지전문대로고" /></a></li>
					</ul>
				</nav>

			</aside>
			<!-- --------------------------- main --------------------------------------- -->

			<!-- content 부분 -->



			<main>
				<!-- 로그인 완료 시  -->
				<h2 class="main title">가입확인</h2>

				
					<div class="margin-top first"
						style="background: url('../images/member/bg-login.png') no-repeat center; height: 300px;">
						<div class="text-align-center">
							<img style="margin-left: 170px; margin-top: 70px;"
								alt="시디엠렉처의 회원가입이 완료되었습니다."
								src="../images/member/txt-join-welcome.png">
						</div>
						<div id="confirmloginBtn"> <!-- 로그인 버튼 -->
							<button class="btn login-btn" onclick="location.href='/member/login'"></button>
						</div>
					</div>
				
			</main>


		</div>
	</div>
	<!-- ------------------- <footer> --------------------------------------- -->



	<%@include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>