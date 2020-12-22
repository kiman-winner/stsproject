<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>공지사항</title>

<link href="/css/main/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />

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
	background: url("../../images/customer/visual.png") no-repeat center;
}
</style>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function() {

		var formObj = $("form[name='readForm']");

		var replyformObj = $("form[name='replyForm']");

		// 삭제 post 전송 
		$("#deleteBtn").on("click", function() {

			if (confirm("정말 삭제하시겠습니까??") == true) { //확인

				formObj.attr("action", "delete");
				formObj.attr("method", "post");
				formObj.submit();

			} else {
				return false;
			}

		});

		$("#modifyBtn").on("click", function(evt) { //게시글 수정 버튼 클릭 시 
			formObj.attr("action", "modify");
			formObj.attr("method", "get");
			formObj.submit();

		});
		$(".btn-list").on("click", function(evt) { //게시글 목록 버튼 클릭 시 
			formObj.attr("action", "list");
			formObj.attr("method", "get");
			formObj.submit();

		});
	});
	function fn_fileDown(fileNo) { //파일 다운로드 
		var formObj = $("form[name='readForm']");
		$("#FILE_NO").attr("value", fileNo);
		formObj.attr("action", "detail/fileDown");
		formObj.submit();
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
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current" href="/customer/notice/list">공지사항</a></li>
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

				<div class="margin-top first">
					<h3 class="hidden">공지사항 내용</h3>

					<form name="readForm" role="form" method="post">
						<input type="hidden" id="notice_num" name="notice_num"
							value="${detail.notice_num}" /> <input type="hidden" name="page"
							value="${searchCriteria.page}"> <input type="hidden"
							name="searchType" value="${searchCriteria.searchType}"> <input
							type="hidden" name="keyword" value="${searchCriteria.keyword}">
						<input type="hidden" name="title" value="${detail.title}">
						<input type="hidden" name="content" value="${detail.content}">
						<input type="hidden" id="FILE_NO" name="FILE_NO" value="">

					</form>
					<table class="table">
						<tbody>
							<tr>
								<th>제목</th>
								<td class="text-align-left text-indent text-strong text-orange"
									colspan="3">${detail.title}</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td class="text-align-left text-indent" colspan="1"><fmt:formatDate
										value="${detail.regdate}" pattern="yyyy-MM-dd" /></td>
								<th colspan="1">조회수</th>
								<td>${detail.viewcount}</td>
							</tr>

							<tr class="content">

								<td>${detail.content}</td>
							</tr>
							<!-- 파일 목록 -->
							<tr>
								<th>첨부파일</th>
								<td colspan="2">
									<div class="form-group" style="border: 1px solid #dbdbdb;">
										<c:forEach var="file" items="${file}">
											<a href="#"
												onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
										</c:forEach>
									</div>
								</td>
							</tr>

						</tbody>
					</table>
				</div>

				<c:if test="${member.member_id == 'atlas69' }">

					<!-- 수정 삭제 버튼  -->
					<div id="modifydeltediv">
						<button type="submit" id="modifyBtn"></button>
						<button type="submit" id="deleteBtn"></button>

					</div>
					<!-- 로그인 안 했을 시에만 보이기 -->
				</c:if>


				<div class="margin-top text-align-center">
					<a class="btn btn-list" href="list">목록</a>
				</div>

			</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>



