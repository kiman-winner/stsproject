<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header">

	<div class="content-container">
		<!-- ---------------------------<header>--------------------------------------- -->

		<h1 id="logo">
			<a href="/index"> <img src="/images/logo.png" alt="CDM렉처로고" /> <!--  로고   -->
			</a>
		</h1>

		<section>
			<nav id="main-menu">
														<!--  메인메뉴   -->
				<h1>메인메뉴</h1>
				<ul>
					<li><a href="/intro">홈페이지 소개</a></li>
					<li><a href="/study">강좌선택</a></li>
					<li><a href="/qna">QnA</a></li>
				</ul>
			</nav>

			<div class="sub-menu">
				<nav id="acount-menu">
					<h1 class="hidden">회원메뉴</h1> 		<!--  회원메뉴   -->
					
					<ul>
						<li><a href="/index">HOME</a></li>
						<li><a href="/member/login.html">로그인</a></li>
						<li><a href="/member/agree.html">회원가입</a></li>
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
