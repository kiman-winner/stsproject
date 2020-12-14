<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>마이페이지</title>

<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<link href="/css/intro.css" type="text/css" rel="stylesheet" />
<link href="/css/member/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/mypage/visual.png") no-repeat center;
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



			<main class="main">
				<h2 class="main title">${member.member_name}님의게시글</h2>
				<table class="table">
					<thead>
						<tr>
							<th class="w60">번호</th>
							<th class="expand">제목</th>
							<th class="w100">작성자</th>
							<th class="w60">작성일</th>
							<th class="w60">조회수</th>
							<th class="w60">댓글수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="communityDTO" items="${list}">
							<tr>
								<td>${communityDTO.community_num }</td>
								<td class="title indent text-align-left"><a
									href="../../main/community/detail${pageMaker.makeSearch(pageMaker.criteria.page)}&community_num=${communityDTO.community_num}">
										${communityDTO.title} </a></td>
								<td>${communityDTO.writer_id}</td>
								<td><fmt:formatDate value="${communityDTO.regdate}"
										pattern="yyyy-MM-dd" /></td>

								<td>${communityDTO.viewcount}</td>
								<td>${communityDTO.replycount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="indexer margin-top align-right">
					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span class="text-orange text-strong">${pageMaker.criteria.page}/${pageMaker.totalCount}</span>
					</div>
				</div>

				<div class="margin-top align-center pager">

					<ul class="-list- center">
						<!-- 페이징 -->
						<c:if test="${pageMaker.prev}">
							<span id="btn-prev"
								OnClick="location.href='mycommunity${pageMaker.makeSearch(pageMaker.startPage - 1)}'"></span>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=text-orange text-strong' : ''}"/>>
								<a href="mycommunity${pageMaker.makeSearch(idx)}">${idx}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<span id="btn-next"
								OnClick="location.href='mycommunity${pageMaker.makeSearch(pageMaker.endPage + 1)}'"></span>
						</c:if>
					</ul>


				</div>

			</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>


