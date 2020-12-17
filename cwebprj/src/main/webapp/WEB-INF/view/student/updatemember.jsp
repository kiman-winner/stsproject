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
		
				<form  name="form"
					action="updatemember.do" id="form1" method="post">
					<fieldset>
						<legend class="hidden">회원정보</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>아이디</label></th>
									<td colspan="3" class="text-align-left indent"><input
										id="member_id" type="text" name="member_id" class="width-half"
										required="required" value="${member.member_id}" readonly />
									</td>

								</tr>
								<tr>
									<th><label>이름</label></th>
									<td colspan="3" class="text-align-left indent"><input
										class="width-half" name="member_name" type="text" value="${member.member_name}"
										required="required" /></td>
								</tr>
								<tr>
									<th><label>이메일</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="email" type="email" class="width-half" required
										placeholder="예) cdm@cdmlecture.com" value="${member.email}" /></td>
								</tr>

								<tr>
									<th><label>생년월일</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="birth" type="date" class="width-half" required
										placeholder="예) 1996-08-02" value="<fmt:formatDate value="${member.birth}"
											pattern="yyyy-MM-dd" />" /></td>
								</tr>
								<tr>
									<th><label>핸드폰번호</label></th>
									<td colspan="3" class="text-align-left indent"><input
										name="phone" type="text" class="width-half"
										pattern="^01[016789]-\d{3,4}-\d{4}$"
										placeholder="예) 010-1111-2222" required value="${member.phone}" /></td>
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


