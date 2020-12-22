<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Intro</title>
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

	<section>
		<!-- ----- 홈페이지 소개 시작 줄 --------------------------------------------------------------------------------------------------------- -->
		<main id="main" class="intro">
			<div class="content-container list">
				<div class="item first">
					<h4 class="-text- purple bold">
						<img src="/images/icon-intro-item.png"
							style="vertical-align: middle; margin-right: 10px;" /> 제작자는
						누구신가요?
					</h4>
					<div class="p">
						<h1>안녕하세요 여러분</h1>
						현재 명지전문대 컴퓨터공학과 졸업예정인 최동민 이라고 합니다. <br> <br> 현재 사는 곳은
						서울시 은평구 응암동입니다. <br>

					</div>
				</div>
				<div class="item">
					<h4 class="-text- purple bold">
						<img src="/images/icon-intro-item.png"
							style="vertical-align: middle; margin-right: 10px;" />홈페이지를 제작하신
						이유는 뭔가요?
					</h4>
					<div class="p">
						<p>
							처음 코딩을 배우고자 하는사람들에게 누구나 배울수 있다는 것을 <br>알려주기 위해 제작 하였습니다.
						</p>
					</div>
				</div>
				<div class="item">
					<h4 class="-text- purple bold">
						<img src="/images/icon-intro-item.png"
							style="vertical-align: middle; margin-right: 10px;" /> 홈페이지 이용
						방법을 설명해주세요
					</h4>
					<div class="p">
						<p>
							강좌 선택 - 강좌를 클릭하여 공부해보세요~<br> QnA - 모두에게 질문하고 싶으신 것을 무엇이든
							물어보세요!(로그인 필요)<br> 마이 페이지 - 개인 정보 수정 <br> 고객센터 - 공지사항 등
							확인 <br>
						</p>

					</div>
				</div>

			</div>
		</main>

		<script>
			window
					.addEventListener(
							"load",
							function(event) {
								var itemClick = function(e) { //함수 정의

									for (var i = 0; i < itemTitles.length; i++) {
										itemTitles[i].nextElementSibling.style.height = "0px";
										itemTitles[i].nextElementSibling.style.padding = "0px";
									}
									if (e.target == itemTitles[0]) {
										e.target.nextElementSibling.style.height = "100px";
										e.target.nextElementSibling.style.padding = "10px 20px 20px 20px";
									} else if (e.target == itemTitles[1]) {
										e.target.nextElementSibling.style.height = "100px";
										e.target.nextElementSibling.style.padding = "10px 20px 20px 20px";
									} else if (e.target == itemTitles[2]) {
										e.target.nextElementSibling.style.height = "100px";
										e.target.nextElementSibling.style.padding = "10px 20px 20px 20px";
									}
								}

								var itemTitles = document
										.querySelectorAll(".intro .item h4"); //노드 리스트 찾기 

								for (var i = 0; i < itemTitles.length; i++) {
									itemTitles[i].onclick = itemClick; //클릭 이벤트 할당
								}

							});
		</script>

	</section>

	<!-- ------------------- <footer> --------------------------------------- -->
	<%@include file="/WEB-INF/view/include/footer.jsp"%>
</body>
</html>
















