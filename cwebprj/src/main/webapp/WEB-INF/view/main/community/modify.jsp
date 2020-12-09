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

<script>
function goBack(){
	window.history.back();
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
	margin-top:0px;
	color:white;
	height:30px;
	outline:0;
}

#td3{
padding-left:300px;
}

#community_num{
background-color : gray;
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

			<main class="main">
				<h2 class="main title">게시글 수정</h2>

				<form name="form" action="modify/modifyPost" id="form1" method="post">
					<fieldset>
						<legend class="hidden">게시글 수정</legend>
						<table class="table margin-top first">
							<tbody>
								<tr>
									<th><label>글 번호</label></th>
									<td colspan="3" class="text-align-left indent">
	
									<input
										id="community_num" type="text" name=community_num class="width-half"
										required="required" value="${community_num}" readonly/>
									</td>
								</tr>
								<tr>
									<th><label>작성자 아이디</label></th>
									<td colspan="3" class="text-align-left indent">
	
									<input
										id="writer_id" type="text" name=writer_id class="width-half"
										required="required" value="${member.member_id}" readonly/>
									</td>
								</tr>
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
									<td id="td3" colspan="3"><input type="hidden" name="" value="" />
										<input id="submit-Button" type="submit" name="btn"
										value="게시물 수정" style="height: 30px; margin: 20px;"
										class="btn-text btn-default" /></td>
									<td colspan="1"><input id="cancelBtn" name="cancelbtn" type="button"
										value="돌아가기" style="height: 30px;"
										class="btn-text btn-default"  onclick="history.back();" /></td>
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
