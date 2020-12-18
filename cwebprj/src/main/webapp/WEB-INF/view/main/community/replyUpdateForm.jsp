<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>답변 수정하기</title>
<meta charset="UTF-8">

<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<link href="/css/intro.css" type="text/css" rel="stylesheet" />

<link href="/css/main/layout.css" type="text/css" rel="stylesheet" />
<!-- css임포트 -->

<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	function cloe{
window.close();
		}
</script>


<style>
#visual .content-container {
	height: inherit;
	display: flex;
	align-items: center;
	background: url("../../images/main/visual.png") no-repeat center;
}

#replylabel {
	font-family: "맑은 고딕";
	font-weight: bold;
	font-size: 15px;
	color: #000;
	margin-top: 30px;
	text-decoration: none;
}

textarea {
	padding: 5px;
	width: 300px;
	min-height: 50px;
	line-height: 24px;
}

.updatetable {
	display: table;
	table-layout: fixed;
	width: 400px;
	border-top: 2px solid #8977ad;
}
</style>

</head>

<body>

	<!-- 댓글 수정 -->
	<label id="replylabel">답변 수정하기</label>


	<form name="replyUpdateForm" id="form1" method="post"
		action="replyUpdate">
		<input type="hidden" id="reply_num" name="reply_num"
			value="${reply_num}"> <input id="writer_id" type="hidden"
			name=writer_id class="width-half" required="required"
			value="${writer_id}" readonly />

		<table class="updatetable">
			<tbody>
				<tr>
					<th><label>내용</label></th>
					<td colspan="3" class="text-align-left indent"><textarea
							class="form-control" id="exampleFormControlTextarea1"
							name="content" rows="5" placeholder="내용을 입력하세요"
							required="required">${content}</textarea></td>
				</tr>
			</tbody>
		</table>
		<div id="updatediv">
			<input id="submit-Button" type="submit" name="btn" value=""
				style="height: 30px; margin: 20px;" class="btn-update"
				onclick="close();" />
		</div>
	</form>

</body>
</html>
