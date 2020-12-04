<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


	<header id="header">

		<div class="content-container">
			<!-- ---------------------------<header>--------------------------------------- -->

			<h1 id="logo">
				<a href="/index"> <img src="/images/logo.png" alt="CDM렉처로고" />
					<!--  로고   -->
				</a>
			</h1>

			<section>
				<nav id="main-menu">
					<!--  메인메뉴   -->
					<h1>메인메뉴</h1>
					<ul>
						<li><a href="/main/intro">홈페이지 소개</a></li>
						<li><a href="/main/study">강좌선택</a></li>
						<li><a href="/main/qna">QnA</a></li>
					</ul>
				</nav>

				<div class="sub-menu">
					<nav id="acount-menu">
						<h1 class="hidden">회원메뉴</h1>
						<!--  회원메뉴   -->

						<ul>
							<c:if test="${member != null }">
								<li>${member.member_name}님환영합니다.</li>
							</c:if>
							<li><a href="/index">HOME</a></li>

							<c:if test="${member != null }">
								<!-- 로그인 시  -->
								<li><a href="/member/logout">로그아웃</a></li>

							</c:if>
							<c:if test="${member == null }">
								<!-- 로그인 안한 상태 -->
								<!-- 로그인 시  -->
								<li><a href="/member/login">로그인</a></li>
							</c:if>


							<li><a href="/member/join">회원가입</a></li>
						</ul>
					</nav>

					<nav id="member-menu" class="linear-layout">
						<h1 class="hidden">고객메뉴</h1>
						<ul class="linear-layout">
							<li><a href="/member/home"><img
									src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
							<li><a href="/customer/notice/list"><img
									src="/images/txt-customer.png" alt="고객센터" /></a></li>
						</ul>
					</nav>

				</div>
			</section>

		</div>

	</header>

	<!-- --------------------------- <body> --------------------------------------- -->





	<!-- content 부분 -->

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
	<!-- ----- 공지사항 줄 ------------------------------------------------------------------------------ -->
	<div id="information">
		<div class="content-container">
			<section class="notice">
				<h1 class="title">QNA</h1>

				<ul class="list margin-top">
					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 8강까지의 예제 코드</a>
					</span> <span>2019-08-18</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 DI 예제 코드</a>
					</span> <span>2019-08-15</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉쌤 9월 초 국기과정 모집 안내</a>
					</span> <span>2019-06-11</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉처 강의 수강 방식 안내</a>
					</span> <span>2019-05-24</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">자바 구조적인 프로그래밍 강의 예제 파일</a>
					</span> <span>2019-04-24</span></li>

				</ul>
			</section>


			<section class="course-info">
				<h1 class="title text-center">강좌 선택</h1>
				<ul class="list">
					<li>현재 강좌가 없습니다.</li>
				</ul>
			</section>
			<section class="notice">
				<h1 class="title">공지사항</h1>
				<ul class="list margin-top">

					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 8강까지의 예제 코드</a>
					</span> <span>2019-08-18</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">스프링 DI 예제 코드</a>
					</span> <span>2019-08-15</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉쌤 9월 초 국기과정 모집 안내</a>
					</span> <span>2019-06-11</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">뉴렉처 강의 수강 방식 안내</a>
					</span> <span>2019-05-24</span></li>

					<li><span class="notice-title"> <a
							href="notice/detail.html">자바 구조적인 프로그래밍 강의 예제 파일</a>
					</span> <span>2019-04-24</span></li>

				</ul>
			</section>
		</div>
	</div>

	</section>

	<!-- ------- CDM 졸업학교 ------------------------------------------------------------------------------------------------- -->

	<div class="content-container">
		<h3 class="-text- center green bold -margin- top">CDM의 졸업학교</h3>
		<ul class="-list- horizontal center -margin- bottom top b20">
			<li><a target="_blank" href="http://www.mjc.ac.kr"> <img
					src="images/mjc.png" alt="명지전문대" /></a></li>
		</ul>
	</div>
	</main>

	<!-- ------------------- <footer> --------------------------------------- -->


	<footer id="footer">
		<div class="content-container">
			<h2 id="footer-logo">
				<img src="/images/logo-footer.png" alt="회사로고">
			</h2>

			<div id="company-info">
				<dl>
					<dt>주소:</dt>
					<dd>서울특별시</dd>
					<dt>관리자메일:</dt>
					<dd>chlehdalscjswo@gmail.com</dd>
				</dl>
				<dl>
					<dt>상호:</dt>
					<dd>cdm렉쳐</dd>
					<dt>대표:</dt>
					<dd>최동민</dd>
					<dt>전화번호:</dt>
					<dd>010-3099-5315</dd>
				</dl>
				<div id="copyright" class="margin-top">Copyright ⓒ
					cdmlecture.com 2020 All Right Reserved. Contact
					chlehdalscjswo@gmail.com for more information</div>
			</div>
		</div>
	</footer>








</body>
</html>

































