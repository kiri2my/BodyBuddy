<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>COMPANY</title>
<!-- plugins:css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link type="text/css" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
	
</head>

<body>
<!-- 	<div class="container-scroller">
		partial
		<div class="container-fluid page-body-wrapper"> -->
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">

				<ul class="nav" >

					<li class="nav-item"><a class="nav-link" href="#"
						onclick="memberList()"><!-- onclick="location.href='/memberlist'" --> <i
							class="mdi mdi-account-multiple menu-icon"></i> <span
							class="menu-title">회원 관리</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="#"
						onclick="trainerList()"> <i
							class="mdi mdi-human-handsup menu-icon"></i> <span
							class="menu-title">소속 트레이너 관리</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="#"
						onclick="location.href='/dailycheck'" data-toggle="collapse"
						aria-expanded="false" aria-controls="aaaa"> <i
							class="mdi mdi-calendar-check menu-icon"></i> <span
							class="menu-title">출결/근태 관리</span> <i class="menu-arrow"></i>
					</a>

						<div class="collapse" id="aaaa">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/buttons.html">일반회원 출결보기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/typography.html">프로그램 출결보기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/buttons.html">트레이너 근태 보기</a></li>
							</ul>
						</div></li>

					<li class="nav-item"><a class="nav-link" href="#bbbb"
						data-toggle="collapse" aria-expanded="false" aria-controls="bbbb">
							<i class="mdi mdi-bulletin-board menu-icon"></i> <span
							class="menu-title">업체 광고 관리</span> <i class="menu-arrow"></i>
					</a>

						<div class="collapse" id="bbbb">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/buttons.html">새 프로그램 만들기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/typography.html">프로그램 수정</a></li>
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/buttons.html">모집광고 올리기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="pages/ui-features/typography.html">모집광고 수정</a></li>
							</ul>
						</div></li>

					<li class="nav-item"><a class="nav-link"
						href="pages/charts/chartjs.html"> <i
							class="mdi mdi-comment-question-outline menu-icon"></i> <span
							class="menu-title">문의관리</span>
					</a></li>

					<li class="nav-item"><a class="nav-link"
						href="pages/forms/basic_elements.html"> <i
							class="mdi mdi-square-inc-cash menu-icon"></i> <span
							class="menu-title">판매내역</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="pages/forms/basic_elements.html"> <i
							class="mdi mdi-chart-areaspline menu-icon"></i> <span
							class="menu-title">매출/실적 관리</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="index.html">
							<i class="mdi mdi-home-modern menu-icon"></i> <span
							class="menu-title">업체 정보 수정</span>
					</a></li>

					<li class="nav-item"><a class="nav-link"
						href="pages/charts/chartjs.html"> <i
							class="mdi mdi-walk menu-icon"></i> <span class="menu-title">회원
								탈퇴하기</span>
					</a></li>

				</ul>
			</nav>
			

	<!-- plugins:js -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
	<!-- End custom js for this page-->
	<!-- Required meta tags -->
</body>

</html>
