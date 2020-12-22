<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>마이페이지</title>

<link href="/css/member/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/mypage/visual.png") no-repeat center;
}

#member_id {
	background-color: gray;
}
</style>

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
				<h1>마이페이지</h1>

				<nav class="menu text-menu first margin-top">
					<h1>마이페이지 메뉴</h1>
					<ul>
						<li><a class="" href="/student/mycommunity">내가 작성한 글</a></li>
						<li><a class="" href="/student/updatemember">개인정보 수정</a></li>
						<li><a class="" href="/student/updatepwd">비밀번호 변경</a></li>
						<li><a class="" href="/student/deletemember">회원 탈퇴</a></li>
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
				<h2 class="main title">회원탈퇴</h2>

				<form name="form" action="deletemember.do" id="form1" method="post">
					<input type="hidden" id="member_id" name="member_id"
						value="${member.member_id}" /> <br> <br>
					<p>정말 탈퇴하시겠습니까?</p>


					<input id="submit-Button" type="submit" name="btn" value="탈퇴"
						style="height: 30px; margin: 20px;" class="btn-text btn-default" />


				</form>


			</main>


		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>


