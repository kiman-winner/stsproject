<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>공지사항</title>

<link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/customer/visual.png") no-repeat center;
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
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current" href="/customer/notice">공지사항</a></li>
						<li><a class="" href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>

					</ul>
				</nav>


				<nav class="menu">
					<h1>CDM 졸업학교</h1>
					<ul>
						<li><a target="_blank" href="http://www.mjc.ac.kr"><img
								src="/images/mjc.png" alt="명지전문대" /></a></li>

					</ul>
				</nav>

			</aside>

			<!-- --------------------------- main --------------------------------------- -->



			<main>
				<h2 class="main title">공지사항</h2>

				<div class="breadcrumb">
					<h3 class="hidden">breadlet</h3>
					<ul>
						<li>home</li>
						<li>고객센터</li>
						<li>공지사항</li>
					</ul>
				</div>

				<div class="margin-top first">
					<h3 class="hidden">공지사항 내용</h3>
					<table class="table">
						<tbody>
							<tr>
								<th>제목</th>
								<td class="text-align-left text-indent text-strong text-orange"
									colspan="3">스프링 8강까지의 예제 코드</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td class="text-align-left text-indent" colspan="3">2019-08-18
								</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>newlec</td>
								<th>조회수</th>
								<td>148</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3"></td>
							</tr>
							<tr class="content">
								<td colspan="4">안녕하세요. 뉴렉처입니다.
									<div>
										<br>
									</div>
									<div>현재 진행중인 스프링 DI 8강까지의 예제입니다.</div>
									<div>
										<br>
									</div>
									
									<div>
										<br>
									</div>
									<div>
										<br>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="margin-top text-align-center">
					<a class="btn btn-list" href="list.html">목록</a>
				</div>

				<div class="margin-top">
					<table class="table border-top-default">
						<tbody>

							<tr>
								<th>다음글</th>
								<td colspan="3" class="text-align-left text-indent">다음글이
									없습니다.</td>
							</tr>




							<tr>
								<th>이전글</th>
								<td colspan="3" class="text-align-left text-indent"><a
									class="text-blue text-strong" href="">스프링 DI 예제 코드</a></td>
							</tr>


						</tbody>
					</table>
				</div>

			</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>



