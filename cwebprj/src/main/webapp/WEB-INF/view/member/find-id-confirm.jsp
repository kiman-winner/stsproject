<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>아이디찾기</title>

<link href="/css/member/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/member/visual.png") no-repeat center;
}
</style>
	<script>
		window.addEventListener("load", function(event) {
			var phoneTr = document.querySelector(".phone");
			var emailTr = document.querySelector(".email");
			
			var phoneRadio = document.querySelector("input[value='phone']");
			var emailRadio = document.querySelector("input[value='email']");
			
			phoneRadio.onclick = function(){
				phoneTr.classList.remove("hidden");
				emailTr.classList.add("hidden");				
			};
			
			emailRadio.onclick = function(){				
				phoneTr.classList.add("hidden");
				emailTr.classList.remove("hidden");
			};
		});
	</script>
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
						<li><a class="" href="/member/find-pwd">비밀번호 찾기</a></li>

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
		<h2 class="main title">아이디 간편 찾기 결과</h2>
		

		<form >
		<div class="margin-top first color-orange text-align-center">
			※가입할 때 등록한 정보로 아이디를 찾았습니다.
		</div>
		<div class="margin-top border table"
			style="width:568px;min-height: 190px;box-sizing: border-box;border-radius: 10px;margin-left: auto;margin-right: auto;">
			
			<!-- ---------------------------------------------------------------------------------------------------- -->
			<div class="margin-top padding border-ver text-align-center color-green text-strong"
				style="width:568px;box-sizing: border-box;margin-left: auto;margin-right: auto;">
				회원 정보로 검색한 정보는 다음과 같습니다.
			</div>
			<div style="margin-top:30px;">
				<table class="table" style="width:80%;margin-left:10%;">					
					<tr>
						<th>아이디</th>
						<td class="text-align-left indent">${member_id}</td>
					</tr>
				</table>
			</div>
			
			<!-- ---------------------------------------------------------------------------------------------------- -->
		</div>
		<div class="margin-top text-align-center">			
			<a class="btn-text btn-default" href="login">로그인</a>
			<a class="btn-text btn-cancel" href="find-pwd">비밀번호 찾기</a>
		</div>
		</form>
	</main>

	

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>