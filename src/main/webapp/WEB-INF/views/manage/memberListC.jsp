<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
</head>
<body>
	<div class="row" style="height: 700px">
		<div class="col-md-12 stretch-card">
			<!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도-->
			<div class="card">
				<div class="card-body">
					<p class="card-title">회원 목록</p>
					<form class="navbar-search pull-left">
						<input type="text" class="search-query" placeholder="회원 검색"
							style="margin: 0px 0px 20px 20px">
					</form>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							<thead>
								<tr>
									<th>회원번호</th>
									<th>이름</th>
									<th>이용기간</th>
									<th>남은기간</th>
									<th>트레이너</th>
									<th>연락처</th>
									<th>이용상태</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>2</td>
									<td>고소영</td>
									<td>2019.4.1~2019.6.3</td>
									<td>2</td>
									<td>없음</td>
									<td>010-1234-5678</td>
									<td>이용중</td>
								</tr>
								<tr>
									<td>1</td>
									<td>송혜교</td>
									<td>2019.5.1~2019.7.1</td>
									<td>28</td>
									<td>김현석</td>
									<td>010-2234-5678</td>
									<td>이용중</td>
								</tr>

							</tbody>
						</table>
					</div>
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
				FiveMan assembled in Incheon ICIA Academy 2019. All rights reserved.
				Thanks to ji-hun Cha.<i class="mdi mdi-heart text-danger"></i>
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
	<script src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
	<!-- End custom js for this page-->

</body>
</html>