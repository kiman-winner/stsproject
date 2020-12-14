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
<script>
	
	// 비밀번호 유효성 검사 및 아이디 체크 버튼 누른지 확인 
	function pwdcheck() {

		if (form.newpassword.value != form.passwordcheck.value) {
			alert("패스워드와 패스워드 확인이 일치하지 않습니다.");
			form.newpassword.value = "";
			form.passwordcheck.value = "";
			form.newpassword.focus();

			return false;
		}
		
	}
</script>

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
				<h2 class="main title">개인정보 수정</h2>

				<form name="form" onsubmit="return pwdcheck();" action="updatepwdPost" id="form1" method="post">
					<fieldset>
						<legend class="hidden">회원정보</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>아이디</label></th>
									<td colspan="3" class="text-align-left indent"><input
										id="member_id" type="text" name="member_id" class="width-half"
										required="required" value="${member.member_id}" readonly /></td>

								</tr>
								<tr>
									<th><label>기존 비밀번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										type="password" id="password" name="password" class=""
										required placeholder="비밀번호 입력" /></td>
								</tr>
								<tr>
									<th><label>새로운 비밀번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										type="password" id="newpassword" name="newpassword" class=""
										required placeholder="비밀번호 입력" /></td>
								</tr>
								<tr>
									<th><label>비밀번호 확인</label></th>
									<td colspan="3" class="text-align-left indent"><input
										class="" id="passwordcheck" name="passwordcheck"
										type="password" required /></td>
								</tr>

								<tr>
									<td colspan="4"><input type="hidden" name="" value="" />
										<input id="submit-Button" type="submit" name="btn" value="확인"
										style="height: 30px; margin: 20px;"
										class="btn-text btn-default" /></td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</form>


			</main>


		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>


