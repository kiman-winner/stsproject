<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>커뮤니티</title>
<meta charset="UTF-8">

<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<link href="/css/intro.css" type="text/css" rel="stylesheet" />

<link href="/css/main/layout.css" type="text/css" rel="stylesheet" />
<!-- css임포트 -->

<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/main/visual.png") no-repeat center;
}

.main:not(h2) {
	margin-left: 100px;
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

			<!-- --------------------------- main --------------------------------------- -->

			<main class="main">
				<h2 class="main title">커뮤니티</h2>


				<div class="search-form margin-top first align-right">
					<h3 class="hidden">커뮤니티 검색폼</h3>
					<form class="table-form" action="list">
						<fieldset>
							<legend class="hidden">커뮤니티 검색 필드</legend>
							<label class="hidden">검색분류</label> 
							
							<select name="searchType" >
								<option value="n"
									<c:out value="${searchCriteria.searchType == null ? 'selected' : ''}"/>>선택 </option>
								<option value="t"
									<c:out value="${searchCriteria.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
								<option value="c"
									<c:out value="${searchCriteria.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
								<option value="w"
									<c:out value="${searchCriteria.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
							</select>
							 <label class="hidden">검색어</label> 
							 <input type="text" name="keyword"
								value="${searchCriteria.keyword}" /> 
								<input class="btn btn-search" type="submit"
								value="검색" placeholder="검색어"/>
						</fieldset>
					</form>
				</div>

				<div class="notice margin-top">
					<h3 class="hidden">커뮤니티 목록</h3>
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
										href="detail${pageMaker.makeSearch(pageMaker.criteria.page)}&community_num=${communityDTO.community_num}">
										${communityDTO.title}
									</a></td>
									<td>${communityDTO.writer_id}</td>
									<td><fmt:formatDate value="${communityDTO.regdate}"
											pattern="yyyy-MM-dd" /></td>

									<td>${communityDTO.viewcount}</td>
									<td>${communityDTO.replycount}</td>
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



				<button id="newBtn" onclick="location.href='register'"></button>

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
