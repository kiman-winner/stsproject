<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>공지사항</title>


<link href="/css/main/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/customer/visual.png") no-repeat center;
}

#newBtn {
	margin-left: 620px;
	margin-top: 20px;
	border: 0;
	height: 50px;
	background: url(../../images/btn-write.png) no-repeat center;
	outline: 0;
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
						<li><a class="current" href="/customer/notice/list">공지사항</a></li>
						<!-- <li><a class="" href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li> -->

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

			<main class="main">
				<h2 class="main title">공지사항</h2>


				<div class="search-form margin-top first align-right">
					<h3 class="hidden">공지사항 검색폼</h3>
					<form class="table-form" action="list">
						<fieldset>
							<legend class="hidden">공지사항 검색 필드</legend>
							<label class="hidden">검색분류</label> <select name="searchType">
								<option value="n"
									<c:out value="${searchCriteria.searchType == null ? 'selected' : ''}"/>>선택
								</option>
								<option value="t"
									<c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
								<option value="c"
									<c:out value="${searchCriteria.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
							</select> <label class="hidden">검색어</label> <input type="text"
								name="keyword" value="${searchCriteria.keyword}" /> <input
								class="btn btn-search" type="submit" value="검색"
								placeholder="검색어" />
						</fieldset>
					</form>
				</div>

				<div class="notice margin-top">
					<h3 class="hidden">공지사항 목록</h3>
					<table class="table">
						<thead>
							<tr>
								<th class="w60">번호</th>
								<th class="expand">제목</th>
								<th class="w60">작성일</th>
								<th class="w60">조회수</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="noticeDTO" items="${list}">
								<tr>
									<td>${noticeDTO.notice_num }</td>
									<td class="title indent text-align-left"><a
										href="detail${pageMaker.makeSearch(pageMaker.criteria.page)}&notice_num=${noticeDTO.notice_num}">
											${noticeDTO.title} </a></td>
									<td><fmt:formatDate value="${noticeDTO.regdate}"
											pattern="yyyy-MM-dd" /></td>
									<td>${noticeDTO.viewcount}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<div class="indexer margin-top align-right">
					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span class="text-orange text-strong">${pageMaker.criteria.page}/${pageMaker.totalCount}</span>
					</div>
				</div>


				<c:if test="${member.member_id == 'atlas69' }">
					<button id="newBtn"></button>
				</c:if>
				<div class="margin-top align-center pager">

					<ul class="-list- center">
						<!-- 페이징 -->
						<c:if test="${pageMaker.prev}">
							<span id="btn-prev"
								OnClick="location.href='list${pageMaker.makeSearch(pageMaker.startPage - 1)}'"></span>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=text-orange text-strong' : ''}"/>>
								<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<span id="btn-next"
								OnClick="location.href='list${pageMaker.makeSearch(pageMaker.endPage + 1)}'"></span>
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



