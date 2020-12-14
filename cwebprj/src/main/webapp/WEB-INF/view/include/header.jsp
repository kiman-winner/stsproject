<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

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
						<li><a href="/main/community/list">커뮤니티</a></li>
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
								
									<li><a href="/member/join">회원가입</a></li>	<!-- 로그인 안 했을 시에만 보이기 -->
							</c:if>


							
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