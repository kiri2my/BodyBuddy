<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
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
<link rel="stylesheet" href="css/bootstrap.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png" />
<link rel="stylesheet" href="js/bootstrap.js" />
</head>

<body>
	<div class="tabbable">
		<!-- 왼쪽과 오른쪽 탭에만 필요 -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">매출</a></li>
			<li><a href="#tab2" data-toggle="tab">실적</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>분류</th>
								<th>결제 수량</th>
								<th>단가</th>
								<th>총 금액</th>

							</tr>
						</thead>
						<tbody>

							<tr>
								<th><a href="">일반광고</a></th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>
							<tr>
								<th><a href="">요가</a></th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>
							<tr>
								<th><a href="">플라잉 요가</a></th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>

						</tbody>
						<footer>
							<tr>
								<td>총 합계</td>
								<td>30,000,000원</td>
							</tr>
						</footer>
					</table>
				</div>
			</div>
			<div class="tab-pane" id="tab2">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>트레이너명</th>
								<th>프로그램명</th>
								<th>실적수</th>
								<th>단가</th>
								<th>금액</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<th><a href="">장동건</a></th>
								<th>요가</th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>
							<tr>
								<th><a href="">강동원</a></th>
								<th>플라잉</th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>
							<tr>
								<th><a href="">시우민</a></th>
								<th>핫요가</th>
								<th>100</th>
								<th>100,000원</th>
								<th>10,000,000원</th>
							</tr>

						</tbody>
						<footer>
							<tr>
								<td>총 합계</td>
								<td>30,000,000원</td>
							</tr>
						</footer>
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-md-7 grid-margin stretch-card">
					<div class="card">
						<div class="card-body">
							<p class="card-title">Cash deposits</p>
							<p class="mb-4">To start a blog, think of a topic about and
								first brainstorm party is ways to write details</p>
							<div id="cash-deposits-chart-legend"
								class="d-flex justify-content-center pt-3"></div>
							<canvas id="cash-deposits-chart"></canvas>
						</div>
					</div>
				</div>
				<div class="col-md-5 grid-margin stretch-card">
					<div class="card">
						<div class="card-body">
							<p class="card-title">Total sales</p>
							<h1>$ 28835</h1>
							<h4>Gross sales over the years</h4>
							<p class="text-muted">Today, many people rely on computers to
								do homework, work, and create or store useful information.
								Therefore, it is important</p>
							<div id="total-sales-chart-legend"></div>
						</div>
						<canvas id="total-sales-chart"></canvas>
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
			<script src="css/bootstrap.css"></script>
			<script src="css/style.css"></script>
			<script src="js/dataTables.bootstrap4.js"></script>
			<script src="js/bootstrap.js"></script>
			<!-- End custom js for this page-->
</body>

</html>
