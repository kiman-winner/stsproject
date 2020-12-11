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

						$("#modifyBtn")
								.on(
										"click",
										function(evt) { //게시글 수정 버튼 클릭 시 
											 formObj.attr("action", "modify");
										        formObj.attr("method", "get");
										        formObj.submit();
											
										});
						$(".btn-list")
						.on(
								"click",
								function(evt) { //게시글 목록 버튼 클릭 시 
									 formObj.attr("action", "list");
								        formObj.attr("method", "get");
								        formObj.submit();
									
								});

							
					}); 

					function replyUpdate (reply_num,writer_id,content) { 	//댓글 수정 
						 var childwindow =window.open("detail/replyUpdateForm?reply_num="+reply_num+"&content="+content+"&writer_id="+writer_id
								,
								"updateForm","width=430,height=250,scrollbars=no,resizable=no");
						childwindow.focus();
					
					}
</script>


<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/main/visual.png") no-repeat center;
}

#writer_id {
	background-color: gray;
}

main .reply {
	display: table;
	table-layout: fixed;
	width: 100%;
	border-top: 1px solid #000;
}

.replybtn {
	padding-left: 630px;
}

#replylabel {
	font-family: "맑은 고딕";
	font-weight: bold;
	font-size: 15px;
	color: #000;
	margin-top: 50px;
	text-decoration: none;
}

th {
	background: #f5f5f5;
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
					<form name="readForm" role="form" method="post">
						<input type="hidden" id="community_num" name="community_num"
							value="${detail.community_num}" /> <input type="hidden"
							name="page" value="${searchCriteria.page}"> <input
							type="hidden" name="searchType"
							value="${searchCriteria.searchType}"> <input
							type="hidden" name="keyword" value="${searchCriteria.keyword}">
							<input
							type="hidden" name="title" value="${detail.title}">
							<input
							type="hidden" name="content" value="${detail.content}">
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
								<td class="text-align-left text-indent"><fmt:formatDate
										value="${detail.regdate}" pattern="yyyy-MM-dd" /></td>
								<th>작성자</th>
								<td>${detail.writer_id}</td>
							</tr>
							<tr>

								<th>조회수</th>
								<td>${detail.viewcount}</td>
								<th>댓글수</th>
								<td>${detail.replycount}</td>
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
						<button type="submit" id="modifyBtn"></button>
						<button type="submit" id="deleteBtn"></button>

					</div>
					<!-- 로그인 안 했을 시에만 보이기 -->
				</c:if>


				<div class="margin-top text-align-center">

					<button class="btn btn-list" >목록</button>

				</div>

				<c:if test="${member!=null}">

					<!-- 댓글 작성 -->
					<label id="replylabel">답변하기</label>


					<form name="replyForm" id="form1" method="post"
						action="detail/replyWrite">
						<input type="hidden" id="community_num" name="community_num"
							value="${detail.community_num}">

						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>댓글 작성자</label></th>
									<td colspan="3" class="text-align-left indent"><input
										id="writer_id" type="text" name=writer_id class="width-half"
										required="required" value="${member.member_id}" readonly /></td>
								</tr>

								<tr>
									<th><label>내용</label></th>
									<td colspan="3" class="text-align-left indent"><textarea
											class="form-control" id="exampleFormControlTextarea1"
											name="content" rows="5" placeholder="내용을 입력하세요"
											required="required"></textarea></td>
								</tr>
							</tbody>
						</table>
						<div class="replybtn">
							<input id="submit-Button" type="submit" name="btn" value=""
								style="height: 30px; margin: 20px;" class="btn-answer" />
						</div>
					</form>

				</c:if>

				<!-- 댓글 -->


				<c:forEach items="${replyList}" var="replyList" varStatus="status">

					<table class="table margin-top first">
						<tbody>
							<tr>
								<th><label>작성자 아이디</label></th>
								<td colspan="3" class="text-align-left indent">작성자 :
									${replyList.writer_id}</td>

								<th><label>작성 날짜</label></th>
								<td colspan="1" class="text-align-left indent"><fmt:formatDate
										value="${replyList.regdate}" pattern="yyyy-MM-dd" /></td>
							</tr>

							<tr>
								<th><label>내용</label></th>
								<td colspan="4" class="text-align-left indent">
									<p>${replyList.content}</p>
								</td>
							</tr>
						</tbody>
					</table>

					<!-- 댓글 수정 삭제  -->
					<c:if test="${member.member_id ==replyList.writer_id}">
						<div id="reply_modifydeltediv">
							<button id="replymodifyBtn"
								onclick="replyUpdate(${replyList.reply_num},'${replyList.writer_id}','${replyList.content}')"></button>

							<form name="replyForm" role="form" method="post"
								action="detail/replyDelete">
								<input type="hidden" id="reply_num" name="reply_num"
									value="${replyList.reply_num}" /> <input type="hidden"
									id="community_num" name="community_num"
									value="${detail.community_num}" /> <input id="replydeleteBtn"
									type="submit" name="btn" value="" style="height: 30px;" />



							</form>
						</div>

					</c:if>

				</c:forEach>



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
