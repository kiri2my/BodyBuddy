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
		<nav class="sidebar sidebar-offcanvas" id="sidebar">

                <ul class="nav">

                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="normalMemList()">
                            <i class="mdi mdi-account-multiple menu-icon"></i>
                            <span class="menu-title" >내 회원목록</span>
                        </a>

                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="advertisemanage()">
                            <i class="mdi mdi-bulletin-board menu-icon"></i>
                            <span class="menu-title">내 프로그램 광고 관리</span>
                        </a>
                    </li>
                    <li class="nav-item">
                    	<a class="nav-link" href="#" onclick="programDailyCheck()">
                    		<i class="mdi mdi-calendar-check menu-icon"></i>
                    		<span class="menu-title">프로그램 출결</span>
                    	</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" href="#" onclick="trainerModifyT()">
                            <i class="mdi mdi-account-card-details menu-icon"></i>
                            <span class="menu-title">내 정보 관리</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="profileModifyT()">
                            <i class="mdi mdi-account-card-details menu-icon"></i>
                            <span class="menu-title">내 프로필 관리</span>
                        </a>
                    </li>
                   
                  
                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="questionList()">
                            <i class="mdi mdi-comment-question-outline menu-icon"></i>
                            <span class="menu-title">내 문의 관리</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="salesList()">
                            <i class="mdi mdi-square-inc-cash menu-icon"></i>
                            <span class="menu-title">내 판매 내역</span>
                        </a>
                    </li>
                    <li class="nav-item"><a class="nav-link"
						href="#" onclick="salesPage()"> <i
							class="mdi mdi-chart-areaspline menu-icon"></i> <span
							class="menu-title">매출/실적 관리</span>
						</a>
						</li>

                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="memberdeleteT()">
                            <i class="mdi mdi-walk menu-icon"></i>
                            <span class="menu-title">회원 탈퇴하기</span>
                        </a>
                    </li>


                </ul>
            </nav>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->


</body>

</html>
