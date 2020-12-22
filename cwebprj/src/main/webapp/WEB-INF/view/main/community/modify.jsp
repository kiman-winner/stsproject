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
	var fileIndex = 1;
	function goBack() {
		window.history.back();
	}

	function fn_addFile() { // 파일 추가 버튼 

		$("#fileIndex")
				.append(
						"<div><input type='file' class='btn-default' style='float:left;' name='file_"
								+ (fileIndex++)
								+ "'>"
								+ "<button type='button'class='btn-text btn-default fileDelBtn' style='float:right;'>"
								+ "삭제" + "</button></div>");
	}

	$(document).on("click", ".fileDelBtn", function() { //파일 삭제 
		$(this).parent().remove();

	});

	var fileNoArry = new Array();
	var fileNameArry = new Array();
	function fn_del(value, name) { //파일 삭제 

		fileNoArry.push(value);
		fileNameArry.push(name);
		$("#fileNoDel").attr("value", fileNoArry);
		$("#fileNameDel").attr("value", fileNameArry);

		$(this).parent().remove();
	}
</script>


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

#cancelBtn {
	margin-left: 100px;
	margin-top: 0px;
	color: white;
	height: 30px;
	outline: 0;
}

#td3 {
	padding-left: 300px;
}

#community_num {
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

			<!-- --------------------------- main --------------------------------------- -->

			<main class="main">
				<h2 class="main title">게시글 수정</h2>

				<form name="form" action="modify.do" id="form1"
					enctype="multipart/form-data" method="post">
					<input id="community_num" type="hidden" name=community_num
						class="width-half" value="${community_num}" /> <input
						type="hidden" name="page" value="${searchCriteria.page}">
					<input type="hidden" name="searchType"
						value="${searchCriteria.searchType}"> <input type="hidden"
						name="keyword" value="${searchCriteria.keyword}">
					<!-- 삭제 파일 -->
					<input type="hidden" id="fileNoDel" name="fileNoDel[]" value="">
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value="">
					<input id="writer_id" type="hidden" name=writer_id
						class="width-half" required="required" value="${member.member_id}"
						readonly />
					<fieldset>
						<legend class="hidden">게시글 수정</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>제목</label></th>
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
									<td colspan="1"><button type="button"
											onclick="fn_addFile()"
											class="fileAdd_btn btn-text btn-default">파일추가</button></td>
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
								<tr><!-- 수정, 돌아가기 버튼 -->
									<td id="td3" colspan="3"><input type="hidden" name=""
										value="" /> <input id="submit-Button" type="submit"
										name="btn" value="게시물 수정" style="height: 30px; margin: 20px;"
										class="btn-text btn-default" /></td>
									<td colspan="1"><input id="cancelBtn" name="cancelbtn"
										type="button" value="돌아가기" style="height: 30px;"
										class="btn-text btn-default" onclick="history.back();" /></td>
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
