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

					<li class="nav-item"><a class="nav-link" href="infoprogramn?m_id=${mb.m_id}"
						onclick="normalMemList()"><!-- onclick="location.href='/memberlist'" --> <i
							class="mdi mdi-dumbbell menu-icon"></i> <span
							class="menu-title">내 프로그램 가기</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="infomodifyfrmn?m_id=${mb.m_id}"
						onclick="trainerMemList()"> <i
							class="mdi mdi-file-document-box-outline menu-icon"></i> <span
							class="menu-title">내 정보 수정</span>
					</a></li>

						<li class="nav-item"><a class="nav-link"
						href="dibsn?m_id=${mb.m_id}"> <i
							class="mdi mdi-heart-box-outline menu-icon"></i> <span class="menu-title">찜내역보기</span>
					</a></li>


					<li class="nav-item"><a class="nav-link"
						href="payhistoryn?m_id=${mb.m_id}"> <i
							class="mdi mdi-square-inc-cash menu-icon"></i> <span class="menu-title">결제 내역</span>
					</a></li>
	

					<li class="nav-item"><a class="nav-link"
						href="memberdelten"> <i
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
