<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>로그인</title>

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



			<main>
				<h2 class="main title">로그인</h2>

				<div class="margin-top first">
					<h3 class="hidden">로그인 정보 입력</h3>
					<form action="login.do" class="login-form" method="post">
						<fieldset>
							<legend class="hidden">로그인 폼</legend>
							<!-- 로그인을 안했을 시 -->
							<c:if test="${member == null }">
								<h4>
									<img src="../images/member/txt-title.png" />
								</h4>

								<ul class="login-box">
									<li><label for="uid">아이디</label> 
									<input type="text"
										name="member_id" placeholder="아이디" /></li>
									<li><label for="pwd">비밀번호</label> <input type="password"
										name="password" placeholder="비밀번호" /></li>
								</ul>
								<div class="login-btn-box">
									<input type="hidden" name="" value="" /> <input type="submit"
										class="btn login-btn" />
								</div>
								<ul class="login-option">
									<li><span>아이디 또는 비밀번호를 분실하셨나요?</span> <a href="find-id">
											<img alt="ID/PWD 찾기" src="../images/member/btn-find.png" />
									</a></li>
									<li class="margin-top"><span>아이디가 없으신 분은 회원가입을
											해주세요.</span> <a href="join"> <img alt="회원가입"
											src="../images/member/btn-join.png" />
									</a></li>
								</ul>
							</c:if>

						</fieldset>
					</form>
				</div>

			</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>


