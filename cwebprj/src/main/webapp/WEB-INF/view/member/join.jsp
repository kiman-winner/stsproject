<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>회원가입</title>

<link href="/css/member/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/member/visual.png") no-repeat center;
}
</style>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>

<script>
	//아이디 유효성 검사(1 = 중복 / 0 != 중복)
	$('#member_id')
			.blur(
					function() {
						alert("idCheck");

						// id = "id_reg" / name = "userId"
						var member_id = $('#member_id').val();
						$
								.ajax({

									url : '${pageContext.request.contextPath}/member/idCheck?member_id='
											+ member_id,
									type : 'get',
									success : function(data) {
										console.log("1 = 중복o / 0 = 중복x : "
												+ data);

										if (data == 1) {
											// 1 : 아이디가 중복되는 문구
											$("#id_check").text(
													"사용중인 아이디입니다 :p");
											$("#id_check").css("color", "red");
										} else {

											if (idJ.test(user_id)) {
												// 0 : 아이디 길이 / 문자열 검사
												$("#id_check").text("");

											} else if (user_id == "") {

												$('#id_check').text(
														'아이디를 입력해주세요 :)');
												$('#id_check').css('color',
														'red');

											} else {

												$('#id_check')
														.text(
																"아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
												$('#id_check').css('color',
														'red');
											}
										}
									},
									error : function() {
										console.log("실패");
									}
								});
					});

	// 비밀번호 유효성 검사
	function pwdcheck() {

		if (form.password.value != form.passwordcheck.value) {
			alert("패스워드와 패스워드 확인이 일치하지 않습니다.");
			form.password.value = "";
			form.passwordcheck.value = "";
			form.password.focus();

			return false;
		}
	}
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
						<li><a class="" href="/member/pwd-reset">비밀번호 재발급</a></li>

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
				<h2 class="main title">회원가입 폼</h2>

				<div class="breadcrumb" style="margin-top: -20px;">
					<h3 class="hidden">경로</h3>
				</div>


				<form onsubmit="return pwdcheck();" name="form"
					action="/member/join/joinPost" id="form1" method="post">
					<fieldset>
						<legend class="hidden">회원정보</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>아이디</label></th>
									<td colspan="3" class="text-align-left indent"><input
										id="member_id" type="text" name="member_id" class="width-half"
										required="required" value="" placeholder="영문과 숫자 6~20자 이내"
										pattern="^\w{6,20}$" /></td>
								</tr>
								<tr>
									<th><label>비밀번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										type="password" id="password" name="password" class=""
										required placeholder="비밀번호 입력" /></td>
								</tr>
								<tr>
									<th><label>비밀번호 확인</label></th>
									<td colspan="3" class="text-align-left indent"><input
										class="" id="passwordcheck" name="passwordcheck"
										type="password" required /></td>
								</tr>
								<tr>
									<th><label>이름</label></th>
									<td colspan="3" class="text-align-left indent"><input
										class="width-half" name="member_name" type="text" value=""
										required="required" /></td>
								</tr>
								<tr>
									<th><label>이메일</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="email" type="email" class="width-half" required
										placeholder="예) cdm@cdmlecture.com" value="" /></td>
								</tr>

								<tr>
									<th><label>생년월일</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="birth" type="date" class="width-half" required
										placeholder="예) 1996-08-02" value="" /></td>
								</tr>
								<tr>
									<th><label>핸드폰번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="phone" type="text" class="width-half"
										pattern="^01[016789]-\d{3,4}-\d{4}$"
										placeholder="예) 010-1111-2222" required value="" /></td>
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


