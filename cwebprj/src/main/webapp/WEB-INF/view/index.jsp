<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<title>CDM lecture</title>
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

		<div id="visual">
			<div id="bg">
				<img src="images/bgimg.PNG" alt="배경사진">
			</div>
		</div>

		<div id="notice">
			<div class="content-container">
				<span class="title">코딩의 기초부터 시작하자! </span> <span
					style="color: yellow; font-size: 15px;"> FOR </span> <span
					style="color: #00ffff;">IT EXPERT</span>
			</div>
		</div>
		<!-- 커뮤니티 목록 -->
		<div id="information">
			<div class="content-container">
				<section class="notice">
					<h1 class="title">커뮤니티</h1>
					<ul class="list margin-top">
						<c:if test="${fn:length(communitylist) == 0 }">
							<li><span class="notice-title">게시글이 없습니다.</span></li>
						</c:if>
						<c:forEach var="communityDTO" items="${communitylist}">
							<li><span class="notice-title"> <a
									href="/main/community/detail?community_num=${communityDTO.community_num}">${communityDTO.title}</a>
							</span> <span><fmt:formatDate value="${communityDTO.regdate}"
										pattern="yyyy-MM-dd" /></span></li>
						</c:forEach>

					</ul>
				</section>

				<section class="course-info">
					<h1 class="title text-center">강좌 선택</h1>
					<ul class="list">
						<li>현재 강좌가 없습니다.</li>
					</ul>
				</section>

				<!-- 공지사항 목록 -->
				<section class="notice">
					<h1 class="title">공지사항</h1>

					<ul class="list margin-top">
						<c:if test="${fn:length(noticelist) == 0 }">
							<li><span class="notice-title">공지사항이 없습니다.</span></li>
						</c:if>
						<c:forEach var="noticeDTO" items="${noticelist}">
							<li><span class="notice-title"> <a
									href="/customer/notice/detail?notice_num=${noticeDTO.notice_num}">${noticeDTO.title}</a>
							</span> <span><fmt:formatDate value="${noticeDTO.regdate}"
										pattern="yyyy-MM-dd" /></span></li>
						</c:forEach>
					</ul>
				</section>
			</div>
		</div>

	</main>
	<!-- ------- CDM 졸업학교 ------------------------------------------------------------------------------------------------- -->

	<div class="content-container">
		<h3 class="-text- center green bold -margin- top">CDM의 졸업학교</h3>
		<ul class="-list- horizontal center -margin- bottom top b20">
			<li><a target="_blank" href="http://www.mjc.ac.kr"> <img
					src="images/mjc.png" alt="명지전문대" /></a></li>
		</ul>
	</div>


	<!-- ------------------- <footer> --------------------------------------- -->
	<%@include file="/WEB-INF/view/include/footer.jsp"%>


</body>
</html>

































