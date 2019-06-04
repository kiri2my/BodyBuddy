<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY(지도있음,로그인안함)</title>
<!-- plugins:css -->
<link rel="stylesheet" href="vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet" href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png" />
</head>

<body>
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
				<ul class="navbar-nav mr-lg-8 w-100">
					<li>
						<button type="button"
							class="btn btn-outline-dark btn-fw navbar-btn">전체보기</button>
					</li>&nbsp;&nbsp;&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success navbar-btn">피트니스
							센터</button>
					</li>&nbsp;&nbsp;&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success navbar-btn">홈트레이닝</button>
					</li>&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success navbar-btn">필라테스</button>
					</li>&nbsp;&nbsp;
					<li>
						<button type="button" class="btn btn-outline-success navbar-btn">요가</button>
					</li>&nbsp;&nbsp;

				</ul>
				<ul class="nav navbar-nav navbar-right">
				<a href="login">
					<li>
						<button type="button" class="btn btn-outline-secondary btn-sm">LOGIN</button>
					</li>
					</a>
				</ul>
			</div>
    </nav>
	<!-- plugins:js -->
	<script src="vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script src="vendors/chart.js/Chart.min.js"></script>
	<script src="vendors/datatables.net/jquery.dataTables.js"></script>
	<script src="vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="js/dashboard.js"></script>
	<script src="js/data-table.js"></script>
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.bootstrap4.js"></script>
	<!-- End custom js for this page-->
</body>
</html>