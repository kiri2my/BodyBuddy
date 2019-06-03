<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png" />
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<div class="row" style="height: 700px">
		<div class="col-md-12 stretch-card">
			<!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도-->
			<div class="card">
				<div class="card-body">
					<p class="card-title">판매 내역</p>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							<thead>
								<tr>
									<th>판매번호</th>
									<th>광고명</th>
									<th>결제자</th>
									<th>결제금액</th>
									<th>거래상태</th>
									<th>판매일자</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><a href="">A1245</a></th>
									<th><a href="">고정근의 암살교실</a></th>
									<th><a href="">바칸솔</a></th>
									<th>100,000원</th>
									<th>결제완료</th>
									<th>2019.6.3 11:11:45</th>
								</tr>
								<tr>
									<th><a href="">A1246</a></th>
									<th><a href="">회원모집</a></th>
									<th><a href="">장귀림</a></th>
									<th>100,000원</th>
									<th>결제완료</th>
									<th>2019.6.3 11:00:45</th>
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
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<!-- End custom js for this page-->
</body>
</html>