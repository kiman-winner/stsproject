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
	$(document).ready(
			function() {
				$("#newBtn").on("click", function(evt) { //등록 버튼 클릭 시 

					if(${member!=null})
					self.location = "register";
					else
						{alert('로그인이 필요한 서비스입니다.');
						self.location = "../../member/login";	//로그인 요청 
						}
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

#newBtn {
	margin-left: 620px;
	margin-top:20px;
	width: 100px;
	background-color: #8977ad;
	color:white;
	height:30px;
	border:0;
	border-radius: 12px;
	outline:0;
}
#newBtn:hover {
background-color: #8b00ff;
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
					<form class="table-form">
						<fieldset>
							<legend class="hidden">커뮤니티 검색 필드</legend>
							<label class="hidden">검색분류</label> <select name="f">
								<option value="title">제목</option>
								<option value="writerId">작성자</option>
							</select> <label class="hidden">검색어</label> <input type="text" name="q"
								value="" /> <input class="btn btn-search" type="submit"
								value="검색" />
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
								<th class="w100">작성일</th>
								<th class="w60">조회수</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="n" items="${list}">
								<tr>
									<td>${n.id }</td>
									<td class="title indent text-align-left"><a href="detail">${n.title}
											커뮤니티게시글</a></td>
									<td>${n.writerId}</td>
									<td>${n.regDate}</td>
									<td>${n.hit }</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<div class="indexer margin-top align-right">
					<h3 class="hidden">현재 페이지</h3>
					<div>
						<span class="text-orange text-strong">1</span> / 1 pages
					</div>
				</div>



				<button id="newBtn">게시글 작성</button>
				
				
				
				
				<div class="margin-top align-center pager">

					<div>
						<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
					</div>
					<ul class="-list- center">
						<li><a class="-text- orange bold" href="?p=1&t=&q=">1</a></li>
					</ul>
					<div>
						<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
					</div>
				</div>



			</main>



		</div>
	</div>










	<!-- ------------------- <footer> --------------------------------------- -->
	<%@include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>
