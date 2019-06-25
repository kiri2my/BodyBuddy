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
	href="${pageContext.request.contextPath}/resources/vendors	/base/vendor.bundle.base.css">
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
			<div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<!-- <ul class="navbar-nav mr-lg-4 w-100">
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
				</ul> -->
				<ul class="navbar-nav navbar-nav-right">
					<li class="nav-item dropdown mr-4"><a
						class="nav-link count-indicator dropdown-toggle d-flex align-items-center justify-content-center notification-dropdown"
						id="notificationDropdown" href="#" data-toggle="dropdown"> <i
							class="mdi mdi-bell mx-0"></i> <span class="count"></span>
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="notificationDropdown">
							<p class="mb-0 font-weight-normal float-left dropdown-header">알림</p>
							<a class="dropdown-item">
								<div class="item-content">
									<h6 class="font-weight-normal">김X솔 회원 박X솔 트레이너의 XXX프로그램
										결제함</h6>
									<p class="font-weight-light small-text mb-0 text-muted">방금
										전</p>
								</div>
							</a> <a class="dropdown-item">
								<div class="item-content">
									<h6 class="font-weight-normal">차X헌 트레이너의 소속 승인요청 대기중</h6>
									<p class="font-weight-light small-text mb-0 text-muted">2 일
										전</p>
								</div>
							</a>
						</div></li>
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
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">

				<ul class="nav">

					<li class="nav-item">
					<a class="nav-link"	href="#" onclick="location.href='/memberlist'"> 
					<i class="mdi mdi-account-multiple menu-icon"></i> 
					<span class="menu-title">회원 관리</span>
					</a></li>

					<li class="nav-item"><a class="nav-link"
						href="#" onclick="location.href='/trainerlist'"> <i
							class="mdi mdi-human-handsup menu-icon"></i> <span
							class="menu-title">소속 트레이너 관리</span>
					</a></li>

					<li class="nav-item">
					<a class="nav-link" href="#" onclick="location.href='/dailycheck'" data-toggle="collapse" aria-expanded="false" aria-controls="aaaa">
							<i class="mdi mdi-calendar-check menu-icon"></i> 
							<span class="menu-title">출결/근태 관리</span> <i class="menu-arrow"></i>
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
			<!-- partial -->
			<div class="main-panel" style="width: 100%">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-md-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body dashboard-tabs p-0">
									<ul class="nav nav-tabs px-4" role="tablist">
										<li class="nav-item"><a class="nav-link active"
											id="question-tab" data-toggle="tab" href="#question"
											role="tab" aria-controls="question" aria-selected="true"
											style="border-bottom-color: #71c016">받은 문의</a></li>
										<li class="nav-item"><a class="nav-link"
											id="memberList-tab" data-toggle="tab" href="#memberList"
											role="tab" aria-controls="memberList" aria-selected="false"
											style="border-bottom-color: #71c016">가입 회원</a></li>
										<li class="nav-item"><a class="nav-link"
											id="advertise-tab" data-toggle="tab" href="#advertise"
											role="tab" aria-controls="advertise" aria-selected="false"
											style="border-bottom-color: #71c016">등록중인 광고</a></li>

									</ul>
									<div class="tab-content py-0 px-0">
										<div class="tab-pane fade show active" id="question"
											role="tabpanel" aria-labelledby="question-tab">
											<div class="d-flex flex-wrap justify-content-xl-between">
												<div
													class="d-none d-xl-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-calendar-today icon-lg mr-3 text-primary"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">날짜</small>
														<div class="dropdown">
															<a
																class="btn btn-secondary dropdown-toggle p-0 bg-transparent border-0 text-dark shadow-none font-weight-medium"
																href="#" role="button" id="dropdownMenuLinkA"
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">
																<h5 class="mb-0 d-inline-block">Sysdate</h5>
															</a>
															<div class="dropdown-menu"
																aria-labelledby="dropdownMenuLinkA">
																<a class="dropdown-item" href="#">어제...</a> <a
																	class="dropdown-item" href="#">엊그제...</a> <a
																	class="dropdown-item" href="#">date3...</a>
															</div>
														</div>
													</div>
												</div>

												<div
													class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-new-box mr-3 icon-lg text-danger"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">새 항목</small>
														<h5 class="mr-2 mb-0">n개</h5>
													</div>
												</div>
												<div class="table-responsive">
													<table class="table table-hover">
														<thead>
															<tr>
																<th>User</th>
																<th>Product</th>
																<th>Sale</th>
																<th>Status</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Jacob</td>
																<td>Photoshop</td>
																<td class="text-danger">28.76% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-danger">Pending</label></td>
															</tr>
															<tr>
																<td>Messsy</td>
																<td>Flash</td>
																<td class="text-danger">21.06% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-warning">In
																		progress</label></td>
															</tr>

														</tbody>
													</table>
												</div>

											</div>
										</div>
										<div class="tab-pane fade" id="memberList" role="tabpanel"
											aria-labelledby="memberList-tab">
											<div class="d-flex flex-wrap justify-content-xl-between">
												<div
													class="d-none d-xl-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-calendar-today icon-lg mr-3 text-primary"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">날짜</small>
														<div class="dropdown">
															<a
																class="btn btn-secondary dropdown-toggle p-0 bg-transparent border-0 text-dark shadow-none font-weight-medium"
																href="#" role="button" id="dropdownMenuLinkA"
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">
																<h5 class="mb-0 d-inline-block">Sysdate</h5>
															</a>
															<div class="dropdown-menu"
																aria-labelledby="dropdownMenuLinkA">
																<a class="dropdown-item" href="#">어제...</a> <a
																	class="dropdown-item" href="#">엊그제...</a> <a
																	class="dropdown-item" href="#">date3...</a>
															</div>
														</div>
													</div>
												</div>

												<div
													class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-new-box mr-3 icon-lg text-danger"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">새 항목</small>
														<h5 class="mr-2 mb-0">n개</h5>
													</div>
												</div>
												<div class="table-responsive">
													<table class="table table-hover">
														<thead>
															<tr>
																<th>User</th>
																<th>Product</th>
																<th>Sale</th>
																<th>Status</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Jacob</td>
																<td>Photoshop</td>
																<td class="text-danger">28.76% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-danger">Pending</label></td>
															</tr>
															<tr>
																<td>Messsy</td>
																<td>Flash</td>
																<td class="text-danger">21.06% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-warning">In
																		progress</label></td>
															</tr>
															<tr>
																<td>John</td>
																<td>Premier</td>
																<td class="text-danger">35.00% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-info">Fixed</label></td>
															</tr>
															<tr>
																<td>Peter</td>
																<td>After effects</td>
																<td class="text-success">82.00% <i
																	class="mdi mdi-arrow-up"></i></td>
																<td><label class="badge badge-success">Completed</label></td>
															</tr>

														</tbody>
													</table>
												</div>

											</div>
										</div>
										<div class="tab-pane fade" id="advertise" role="tabpanel"
											aria-labelledby="advertise-tab">
											<div class="d-flex flex-wrap justify-content-xl-between">
												<div
													class="d-none d-xl-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-calendar-today icon-lg mr-3 text-primary"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">날짜</small>
														<div class="dropdown">
															<a
																class="btn btn-secondary dropdown-toggle p-0 bg-transparent border-0 text-dark shadow-none font-weight-medium"
																href="#" role="button" id="dropdownMenuLinkA"
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">
																<h5 class="mb-0 d-inline-block">Sysdate</h5>
															</a>
															<div class="dropdown-menu"
																aria-labelledby="dropdownMenuLinkA">
																<a class="dropdown-item" href="#">어제...</a> <a
																	class="dropdown-item" href="#">엊그제...</a> <a
																	class="dropdown-item" href="#">date3...</a>
															</div>
														</div>
													</div>
												</div>

												<div
													class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<i class="mdi mdi-new-box mr-3 icon-lg text-danger"></i>
													<div class="d-flex flex-column justify-content-around">
														<small class="mb-1 text-muted">새 항목</small>
														<h5 class="mr-2 mb-0">n개</h5>
													</div>
												</div>
												<div class="table-responsive">
													<table class="table table-hover">
														<thead>
															<tr>
																<th>User</th>
																<th>Product</th>
																<th>Sale</th>
																<th>Status</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Jacob</td>
																<td>Photoshop</td>
																<td class="text-danger">28.76% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-danger">Pending</label></td>
															</tr>
															<tr>
																<td>Messsy</td>
																<td>Flash</td>
																<td class="text-danger">21.06% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-warning">In
																		progress</label></td>
															</tr>
															<tr>
																<td>John</td>
																<td>Premier</td>
																<td class="text-danger">35.00% <i
																	class="mdi mdi-arrow-down"></i></td>
																<td><label class="badge badge-info">Fixed</label></td>
															</tr>
															<tr>
																<td>Peter</td>
																<td>After effects</td>
																<td class="text-success">82.00% <i
																	class="mdi mdi-arrow-up"></i></td>
																<td><label class="badge badge-success">Completed</label></td>
															</tr>
															<tr>
																<td>Dave</td>
																<td>53275535</td>
																<td class="text-success">98.05% <i
																	class="mdi mdi-arrow-up"></i></td>
																<td><label class="badge badge-warning">In
																		progress</label></td>
															</tr>
														</tbody>
													</table>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>



					</div>

					<div class="row" style="height: 700px">


						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<div class="chartjs-size-monitor"
										style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
										<div class="chartjs-size-monitor-expand"
											style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
											<div
												style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
										</div>
										<div class="chartjs-size-monitor-shrink"
											style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
											<div
												style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
										</div>
									</div>
									<h4 class="card-title">트레이너 현황판</h4>
									<canvas id="barChart" width="380" height="189"
										class="chartjs-render-monitor"
										style="display: block; height: 211px; width: 423px;"></canvas>
								</div>
							</div>
						</div>


					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted text-center text-sm-left d-block d-sm-inline-block">Team
							FiveMan assembled in Incheon ICIA Academy 2019. All rights
							reserved. Thanks to ji-hun Cha.<i
							class="mdi mdi-heart text-danger"></i>
						</span>

					</div>
				</footer>
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->

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
