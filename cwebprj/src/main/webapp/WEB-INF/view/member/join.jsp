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
	var id_checkBtn = false;

	function idCheck() {

		var member_id = $('#member_id').val(); //member controller 에 요청 /member/idCheck

		if (member_id.length<6||member_id.length>20
				|| /[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/.test(member_id)
				|| /[~!@\#$%<>^&*\()\-=+_\’]/.test(member_id)) //DB조회가 필요없는 유효성 검사 
		{
			alert("아이디 형식을 다시 확인해주세요");
			$('#member_id').val("");
			return;
		}

		$
				.ajax({

					url : '${pageContext.request.contextPath}/member/idCheck?member_id='
							+ member_id,
					type : 'get',
					success : function(data) {
						console.log("1 = 중복o / 0 = 중복x : " + data);

						if (data == 1) {
							$('#member_id').val("");
							$('#member_id').focus();
							alert("아이디가 이미 있습니다.");
							id_checkBtn = false;
						} else {
							if (confirm("아이디 사용가능합니다. 사용하시겠습니까?")) {
								$("#member_id").attr("readonly", true);
								$("#member_id").css("background-color", "gray");
								$('#id-check-button').attr("disabled", "true");
								id_checkBtn = true;
							} else
								id_checkBtn = false;
						}
					},
					error : function() {
						console.log("실패");
					}
				});
	}

	// 비밀번호 유효성 검사 및 아이디 체크 버튼 누른지 확인 
	function pwdcheck() {
		 var pw = $("#password").val();	
		 var num = pw.search(/[0-9]/g);
		 var eng = pw.search(/[a-z]/ig);
		 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		 if(pw.length < 10 || pw.length > 20){	//유효성 검사
		  alert("10자리 ~ 20자리 이내로 입력해주세요.");
		  return false;
		 }else if(pw.search(/\s/) != -1){
		  alert("비밀번호는 공백 없이 입력해주세요.");
		  return false;
		 }else if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
		  alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		  return false;
		 }
		 
		if (form.password.value != form.passwordcheck.value) {	//비밀번호 체크 확인
			alert("패스워드와 패스워드 확인이 일치하지 않습니다.");
			form.password.value = "";
			form.passwordcheck.value = "";
			form.password.focus();

			return false;
		}

		if (id_checkBtn == false) {
			alert("아이디 중복검사를 해주세요");
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
				<h2 class="main title">회원가입 폼</h2>

				<div class="breadcrumb" style="margin-top: -20px;">
					<h3 class="hidden">경로</h3>
				</div>


				<form onsubmit="return pwdcheck();" name="form"
					action="join.do" id="form1" method="post">
					<fieldset>
						<legend class="hidden">회원정보</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>아이디</label></th>
									<td colspan="3" class="text-align-left indent"><input
										id="member_id" type="text" name="member_id" class="width-half"
										required="required" value="" placeholder="영문, 숫자 6~20자 이내 조합" />
										<button type="button" class="btn-text btn-default"
											id="id-check-button" onclick="idCheck()">중복검사</button> <!-- <input class="btn-text btn-default" type="button" id="id-check-button" value="중복확인" />	 -->

									</td>

								</tr>
								<tr>
									<th><label>비밀번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										type="password" id="password" name="password" class=""
										required placeholder="비밀번호 입력 *10~20자리 영문,숫자,특수문자 중 2가지 혼합" /></td>
									
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


