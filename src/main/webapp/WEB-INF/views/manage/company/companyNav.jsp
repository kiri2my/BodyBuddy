<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>
</style>
</head>

<body>
	<nav class="sidebar sidebar-offcanvas" id="sidebar">

		<ul class="nav">

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="normalMemList()"> <!-- onclick="location.href='/memberlist'" -->
					<i class="mdi mdi-account-multiple menu-icon"></i> <span
					class="menu-title" id="i">회원 목록</span>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="trainerMemList()"> <i
					class="mdi mdi-human-handsup menu-icon"></i> <span
					class="menu-title">트레이너 관리</span>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="#aaaa"
				data-toggle="collapse" aria-expanded="false" aria-controls="aaaa">
					<i class="mdi mdi-calendar-check menu-icon"></i> <span
					class="menu-title">출결/근태 관리</span> <i class="menu-arrow"></i>
			</a>

				<div class="collapse" id="aaaa">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="normalDailyCheck()">일반회원 출결</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="programDailyCheck()">프로그램 출결</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="trainerDailyCheck()">트레이너 근태</a></li>
					</ul>
				</div></li>

			<li class="nav-item"><a class="nav-link" href="#bbbb"
				data-toggle="collapse" aria-expanded="false" aria-controls="bbbb">
					<i class="mdi mdi-bulletin-board menu-icon"></i> <span
					class="menu-title">업체 광고 관리</span> <i class="menu-arrow"></i>
			</a>

				<div class="collapse" id="bbbb">
					<ul class="nav flex-column sub-menu">
						<li class="nav-item"><a class="nav-link" href="#"
							onclick='advertiseManage()'>광고 관리</a></li>
						<li class="nav-item"><a class="nav-link" href="#"
							onclick='advertiseWriteFrm()'>광고 올리기</a></li>
						<!-- <li class="nav-item"><a class="nav-link" href="#"
							onclick='advertiseModifyFrm()'>광고 수정하기</a></li> -->
						<!-- <li class="nav-item"><a class="nav-link"
							href="pages/ui-features/typography.html">모집광고 수정</a></li> -->
					</ul>
				</div></li>

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="question()"> <i
					class="mdi mdi-comment-question-outline menu-icon"></i> <span
					class="menu-title">문의관리</span>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="salesHistory()"> <i
					class="mdi mdi-square-inc-cash menu-icon"></i> <span
					class="menu-title">판매내역</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#"
				onclick="salesPage()"> <i
					class="mdi mdi-chart-areaspline menu-icon"></i> <span
					class="menu-title">매출/실적 관리</span>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="infoModify()"> <i class="mdi mdi-home-modern menu-icon"></i>
					<span class="menu-title">업체 정보 수정</span>
			</a></li>

			<li class="nav-item"><a class="nav-link" href="#"
				onclick="memberdelete()"> <i class="mdi mdi-walk menu-icon"></i>
					<span class="menu-title">회원 탈퇴하기</span>
			</a></li>

		</ul>
	</nav>
</body>
</html>
