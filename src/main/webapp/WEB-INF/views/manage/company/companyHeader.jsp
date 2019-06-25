<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
<link rel="stylesheet" href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="images/favicon.png" />
</head>

<body>
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div class="navbar-brand-wrapper d-flex justify-content-center">
                <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
                    <a class="navbar-brand brand-logo" href="company?m_id=${m_id }" style="color: #71c016;"><img src="resources/img/logo.jpg"></a>
                    <a class="navbar-brand brand-logo-mini" href="company" style="color: #71c016;">BODY BUDDY</a>
                    <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                        <span class="mdi mdi-sort-variant"></span>
                    </button>
                </div>
            </div>
            <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
                <!-- <ul class="navbar-nav mr-lg-8 w-100">
                    <li>
                        <button type="button" class="btn btn-outline-dark btn-fw navbar-btn">전체보기</button>
                    </li>&nbsp;&nbsp;&nbsp;&nbsp;
                    <li>
                        <button type="button" class="btn btn-outline-success navbar-btn">피트니스 센터</button>
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
                     
                </ul> -->
                <!--로그인/로그아웃-->
                <ul class="navbar-nav navbar-nav-right">
                    <li class="nav-item dropdown mr-4">
                        <a class="nav-link count-indicator dropdown-toggle d-flex align-items-center justify-content-center notification-dropdown" id="notificationDropdown" href="#" data-toggle="dropdown">
                            <i class="mdi mdi-bell mx-0"></i>
                            <span class="count"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="notificationDropdown">
                            <p class="mb-0 font-weight-normal float-left dropdown-header">알림</p>
                            <a class="dropdown-item">
                                <div class="item-content">
                                    <h6 class="font-weight-normal">김X솔 회원 박X솔 트레이너의 XXX프로그램 결제함</h6>
                                    <p class="font-weight-light small-text mb-0 text-muted">
                                        방금 전
                                    </p>
                                </div>
                            </a>
                            <a class="dropdown-item">
                                <div class="item-content">
                                    <h6 class="font-weight-normal">차X헌 트레이너의 소속 승인요청 대기중</h6>
                                    <p class="font-weight-light small-text mb-0 text-muted">
                                        2 일 전
                                    </p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item nav-profile dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                            <img src="resources/images/faces/mydefault.jpg" alt="profile" />
                            <span class="nav-profile-name" id='sessionId'>${mb.m_name }(${mb.m_id })</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
						<!-- <a class="dropdown-item">
                                <i class="mdi mdi-settings text-primary"></i>
                               	 마이페이지
                            </a> -->
						<a class="dropdown-item" href="logout">
                                <i class="mdi mdi-logout text-primary"></i>
                                로그아웃
                            </a>
                        </div>
                    </li> 
                </ul>
                <!--로그인/로그아웃 끝-->
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