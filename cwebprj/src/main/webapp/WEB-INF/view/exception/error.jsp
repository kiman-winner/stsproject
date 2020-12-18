<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<title>에러발생</title>
<meta charset="UTF-8">

<link href="/css/layout.css" type="text/css" rel="stylesheet" />
<link href="/css/index.css" type="text/css" rel="stylesheet" />
<link href="/css/intro.css" type="text/css" rel="stylesheet" />
<!-- css임포트 -->

</head>

<body>
	<!-- header 부분 -->


	<%@include file="/WEB-INF/view/include/header.jsp"%>

	<!-- --------------------------- <body> --------------------------------------- -->

	<!-- content 부분 -->
	<main>
	
		<!-- ----- 커뮤니티 줄 ------------------------------------------------------------------------------ -->
		<div id="information">
			<div class="content-container">
				<section class="notice">
					<h1 class="title">에러발생</h1>
						<h2>재접속 해주시기 바랍니다.</h2><br><br>
					<h3>
						<i class="fa fa-warning text-red"></i> ${exception.getMessage()}
					</h3>
					<ul>
						<c:forEach items="${exception.getStackTrace()}" var="stack">
							<li>${stack.toString()}</li>
						</c:forEach>
					</ul>
				</section>
			</div>
		</div>

	</main>

</body>
</html>

