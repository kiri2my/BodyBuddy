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

	<!-- partial:partials/_navbar.html -->
	<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
		<div class="navbar-brand-wrapper d-flex justify-content-center">
			<div
				class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
				<a class="navbar-brand brand-logo"
					href="${pageContext.request.contextPath}/" style="color: #71c016;">BODY
					BUDDY</a> <a class="navbar-brand brand-logo-mini"
					href="${pageContext.request.contextPath}/" style="color: #71c016;">BODY
					BUDDY</a>
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
					<button id="cateAll" type="button"
						class="btn btn-outline-dark btn-fw navbar-btn cate">전체보기</button>
				</li>&nbsp;&nbsp;&nbsp;&nbsp;

				<li>
					<button id="cateNormal" type="button"
						class="btn btn-outline-dark btn-fw navbar-btn cate">일반
						정기회원 모집</button>
				</li>&nbsp;&nbsp;&nbsp;&nbsp;

				<li>

					<button id="cateFitness" type="button"
						class="btn btn-outline-success navbar-btn cate">피트니스</button>
				</li>&nbsp;&nbsp;
				<li>
					<button id="cateHomeTraining" type="button"
						class="btn btn-outline-success navbar-btn cate">홈트레이닝</button>
				</li>&nbsp;&nbsp;
				<li>
					<button id="catePilates" type="button"
						class="btn btn-outline-success navbar-btn cate">필라테스</button>
				</li>&nbsp;&nbsp;
				<li>
					<button id="cateYoga" type="button"
						class="btn btn-outline-success navbar-btn cate">요가</button>
				</li>&nbsp;&nbsp;

			</ul>






			<ul id="headerAlarm" class="nav navbar-nav">
				<a href="login">
					<button type="button" class="btn btn-outline-secondary btn-sm">LOGIN</button>
				</a>
			</ul>
			<ul id="headerKind" class='navbar-nav navbar-nav-right'>

			</ul>

		</div>
	</nav>
	<!-- partial -->

	<!-- page-body-wrapper ends -->

	<!-- container-scroller -->


</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	var kind = "${mb.m_kind}";

	console.log("headerConsoleKind", kind);

	if (kind == 'n' || kind == 't' || kind == 'c') {

		var strAlarm = "<li class='nav-item dropdown mr-4'>\n"
				+ "    <a class='nav-link count-indicator dropdown-toggle d-flex align-items-center justify-content-center notification-dropdown' id='notificationDropdown' href='#' data-toggle='dropdown'>\n"
				+ "        <i class='mdi mdi-bell mx-0'></i>\n"
				+ "        <span class='count'></span>\n"
				+ "    </a>\n"
				+ "    <div class='dropdown-menu dropdown-menu-right navbar-dropdown' aria-labelledby='notificationDropdown'>\n"
				+ "        <p class='mb-3 font-weight-normal float-left dropdown-header'>알림</p>\n"
				+ "        <a class='dropdown-item'>\n"
				+ "            <div class='item-content'>\n"
				+ "                <h6 class='font-weight-normal'>김X솔 회원 박X솔 트레이너의 XXX프로그램 결제함</h6>\n"
				+ "                <p class='font-weight-light small-text mb-0 text-muted'>\n"
				+ "                    방금 전\n"
				+ "                </p>\n"
				+ "            </div>\n"
				+ "        </a>\n"
				+ "        <a class='dropdown-item'>\n"
				+ "            <div class='item-content'>\n"
				+ "                <h6 class='font-weight-normal'>차X헌 트레이너의 소속 승인요청 대기중</h6>\n"
				+ "                <p class='font-weight-light small-text mb-0 text-muted'>\n"
				+ "                    2 일 전\n" + "                </p>\n"
				+ "            </div>\n" + "        </a>\n" + "    </div>\n"
				+ "</li>\n";

		$('#headerAlarm').html(strAlarm);

		var strKind = "<li class='nav-item nav-profile dropdown'>\n"
				+ "    <a class='nav-link dropdown-toggle' href='#' data-toggle='dropdown' id='profileDropdown'>\n"
				+ "        <img src='resources/images/faces/mydefault.jpg' alt='profile' />\n"
				+ "        <span class='nav-profile-name'>${mb.m_name}</span>\n"
				+ "    </a>\n"
				+ "    <div class='dropdown-menu dropdown-menu-right navbar-dropdown' aria-labelledby='profileDropdown'>\n"
				+ "        <a href=";

		if (kind == 'n') {
			strKind += "infoprogramn?m_id=${mb.m_id} \n";
			console.log(strKind);
		} else if (kind == 't') {
			strKind += "trainer?m_id=${mb.m_id} \n";
		} else if (kind == 'c') {
			strKind += "company?m_id=${mb.m_id}  \n";
		}
		strKind += "' class='dropdown-item'>\n"
				+ "            <i class='mdi mdi-settings text-primary'></i>\n"
				+ "            마이페이지\n" + "        </a>\n"
				+ "        <a href='logout' class='dropdown-item'>\n"
				+ "            <i class='mdi mdi-logout text-primary'></i>\n"
				+ "            로그아웃\n" + "        </a>\n" + "    </div>\n"
				+ "</li>\n";

		$('#headerKind').html(strKind);
	}
</script>
</html>