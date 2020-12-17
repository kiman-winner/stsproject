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
var fileIndex = 1;
	function goBack() {
		window.history.back();
	}

	function fn_addFile() {	// 파일 추가 버튼 
		
		$("#fileIndex")
				.append(
						"<div><input type='file' class='btn-default' style='float:left;' name='file_"
								+ (fileIndex++)
								+ "'>"
								+ "<button type='button'class='btn-text btn-default fileDelBtn' style='float:right;'>"
								+ "삭제" + "</button></div>");
	}

	$(document).on("click",".fileDelBtn", function(){	//파일 삭제 
		$(this).parent().remove();
		
	});
	
		var fileNoArry = new Array();
		var fileNameArry = new Array();
		function fn_del(value, name){	//파일 삭제 
			
			fileNoArry.push(value);
			fileNameArry.push(name);
			$("#fileNoDel").attr("value", fileNoArry);
			$("#fileNameDel").attr("value", fileNameArry);

			$(this).parent().remove();
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
				<h2 class="main title">공지사항 수정</h2>

				<form action="modify.do" method="post" enctype="multipart/form-data">
				<input id="notice_num" type="hidden" name=notice_num
						class="width-half" value="${notice_num}"
						 /> <input type="hidden" name="page"
						value="${searchCriteria.page}"> <input type="hidden"
						name="searchType" value="${searchCriteria.searchType}"> <input
						type="hidden" name="keyword" value="${searchCriteria.keyword}">
						<!-- 삭제 파일 -->
						<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
					<div class="margin-top first">
						<h3 class="hidden">공지사항 입력</h3>
						<table class="table">
							<tbody>
								<tr>
									<th>제목</th>
									<td colspan="3" class="text-align-left indent"><input
										id="title" type="text" name="title" class="width-half"
										required="required" value="${title}" placeholder="제목을 입력하세요" /></td>
								</tr>
								<tr>
									<th><label>내용</label></th>
									<td colspan="3" class="text-align-left indent"><textarea
											class="form-control" id="exampleFormControlTextarea1"
											name="content" rows="10" placeholder="내용을 입력하세요">${content}</textarea></td>
								</tr>
							<tr>
									<td colspan="1"><button type="button" onclick="fn_addFile()" class="fileAdd_btn btn-text btn-default">파일추가</button></td>
									<td colspan="2" id="fileIndex"><c:forEach var="file"
											items="${file}" varStatus="var">
											<div>
												<input type="hidden" id="FILE_NO"
													name="FILE_NO_${var.index}" value="${file.FILE_NO }">
												<input type="hidden" id="FILE_NAME" name="FILE_NAME"
													value="FILE_NO_${var.index}"> <a href="#"
													id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
												<button id="fileDel" class="btn-text btn-default fileDelBtn"
													onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');"
													type="button">삭제</button>
												<br>
											</div>
										</c:forEach></td>
								</tr>
								<tr>
									<td id="td3" colspan="3"><input type="hidden" name=""
										value="" /> <c:if test="${member!=null}">
											<input id="submit-Button" type="submit" name="btn"
												value="게시물 수정" style="height: 30px; margin: 20px;"
												class="btn-text btn-default" />
										</c:if> <c:if test="${member==null}">
											<p>로그인이 필요한 서비스 입니다.</p>
										</c:if></td>
									<td colspan="1"><input id="cancelBtn" name="cancelbtn"
										type="button" value="돌아가기" style="height: 30px;"
										class="btn-text btn-default" onclick="history.back();" /></td>
								</tr>
							</tbody>
						</table>
					</div>

				</form>

			</main>

		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<%@include file="/WEB-INF/view/include/footer.jsp"%>

</body>

</html>



