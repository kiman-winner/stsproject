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

<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#deleteBtn")
								.on(
										"click",
										function(evt) { //등록 버튼 클릭 시 
											var result = confirm('정말 삭제 하시겠습니까?');
											if (result)
												location
														.replace('delete?community_num=${detail.community_num}')
										});
						$("#modifyBtn")
						.on(
								"click",
								function(evt) { //등록 버튼 클릭 시 
										location.replace('modify?title=${detail.title}&content=${detail.content}&community_num=${detail.community_num}')
								});


					}); //게시글 등록
</script>


<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/main/visual.png") no-repeat center;
}
#writer_id{
background-color : gray;
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

			<main>
				<h2 class="main title">커뮤니티</h2>

				<div class="margin-top first">
					<h3 class="hidden">커뮤니티 내용</h3>
					<table class="table">
						<tbody>
							<tr>
								<th>제목</th>
								<td class="text-align-left text-indent text-strong text-orange"
									colspan="3">${detail.title}</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td class="text-align-left text-indent" colspan="3"><fmt:formatDate
										value="${detail.regdate}" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${detail.writer_id}</td>
								<th>조회수</th>
								<td>${detail.viewcount}</td>
							</tr>

							<tr class="content">
								<td colspan="4">${detail.content}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<c:if test="${member.member_id == detail.writer_id }">
				
					<!-- 수정 삭제 버튼  -->
					<div id="modifydeltediv">
						<button id="modifyBtn"></button>
						<button id="deleteBtn" ></button>

					</div>
					<!-- 로그인 안 했을 시에만 보이기 -->
				</c:if>


				<div class="margin-top text-align-center">

					<a class="btn btn-list" href="list">목록</a>

				</div>

				<div class="margin-top">
					<table class="table border-top-default">
						<tbody>

							<tr>
								<th>다음글</th>
								<td colspan="3" class="text-align-left text-indent">다음글 부분</td>
							</tr>




							<tr>
								<th>이전글</th>
								<td colspan="3" class="text-align-left text-indent"><a
									class="text-blue text-strong" href="">이전글 부분</a></td>
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