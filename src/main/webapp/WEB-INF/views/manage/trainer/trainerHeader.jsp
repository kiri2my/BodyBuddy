<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
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
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY(지도없음,로그인함)</title>
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

	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="navbar-brand-wrapper d-flex justify-content-center">
				<div
					class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
					<a class="navbar-brand brand-logo" href="main.html"
						style="color: #71c016;">BODY BUDDY</a> <a
						class="navbar-brand brand-logo-mini" href="main.html"
						style="color: #71c016;">BODY BUDDY</a>
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-sort-variant"></span>
					</button>
				</div>
			</div>
			<div
				class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<ul class="navbar-nav mr-lg-4 w-100">
					<li>
						<button type="button" class="btn btn-outline-dark btn-fw">전체보기</button>
					</li>&nbsp;&nbsp;&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success">피트니스
							센터</button>
					</li>&nbsp;&nbsp;&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success">홈트레이닝</button>
					</li>&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success">필라테스</button>
					</li>&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success">요가</button>
					</li>&nbsp;&nbsp;
				</ul>
				<ul class="navbar-nav navbar-nav-right">
					
					<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
						id="profileDropdown"> <img src="images/faces/mydefault.jpg"
							alt="profile" /> <span class="nav-profile-name">사용자
								이름(M_NAME)</span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="profileDropdown">
							<a class="dropdown-item"> <i
								class="mdi mdi-settings text-primary"></i> 마이페이지
							</a> <a class="dropdown-item" href="logout"> <i
								class="mdi mdi-logout text-primary"></i> 로그아웃
							</a>
						</div></li>
				</ul>
			</div>
		</nav>
		<!-- partial -->
	</div>
</body>

</html>
