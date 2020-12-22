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
		window.addEventListener("load", function(event) {//이메일, 전화번호 찾기 교환 
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
		<h2 class="main title">아이디 간편 찾기</h2>
		
		<form method="post" action="find-id.do">
		<div class="margin-top first color-orange text-align-center">
			※가입할 때 등록한 정보로 아이디를 찾습니다.
		</div>
		<div class="margin-top border table"
			style="width:568px;min-height: 190px;box-sizing: border-box;border-radius: 10px;margin-left: auto;margin-right: auto;">
			<div class="margin-top padding border-ver text-align-center color-green text-strong"
				style="width:568px;box-sizing: border-box;margin-left: auto;margin-right: auto;">
				회원가입 시 등록하신 이름, 휴대폰번호 또는 이메일 주소를 입력해 주세요.
			</div>
			<div>
			
				<table class="table border-top-none">
					<tr>
						<td colspan="4">
							<input type="radio" name="type" value="phone" style="vertical-align: middle;" checked /><label style="margin-left: 5px;margin-right: 10px;">휴대폰 번호로 찾기</label>
							<input type="radio" name="type" value="email" style="vertical-align: middle;"  /><label style="margin-left: 5px;">이메일 주소로 찾기</label>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td colspan="3" class="text-align-left indent"><input type="text" name="member_name" class="width-half" required placeholder="이름을 입력하세요" value="" /></td>
					</tr>
					<tr class="phone ">
						<th>휴대폰 번호</th>
						<td colspan="3" class="text-align-left indent"><input type="text" name="phone" class="width-half" placeholder="예) 010-5555-7777" value="" /></td>
					</tr>
					<tr class="email hidden">
						<th>이메일 주소</th>
						<td colspan="3" class="text-align-left indent"><input type="text" name="email" class="width-half" placeholder="예) user@servername.com" value="" /></td>
					</tr>
					
				</table>
			
			</div>
			<!-- ---------------------------------------------------------------------------------------------------- -->
			
			<!-- ---------------------------------------------------------------------------------------------------- -->
		</div>
		<div class="margin-top text-align-center">
			<input type="hidden" name="" value="" />
			<input class="btn-text btn-default" type="submit" value="아이디 찾기" />
			<a class="btn-text btn-cancel" href="join">회원가입 페이지로 이동</a>
			<a class="btn-text btn-cancel" href="pwd-reset">비밀번호 리셋 페이지로 이동</a>
		</div>
		</form>
	</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>