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
			<li class="active"><a href="#tab1" data-toggle="tab">일반회원</a></li>
			<li><a href="#tab2" data-toggle="tab">프로그램</a></li>
			<li><a href="#tab3" data-toggle="tab">트레이너</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>회원번호!</th>
								<th>회원명</th>
								<th>생년월일</th>
								<th>등록기간</th>
								<th>출석</th>
								<th>출석현황</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>3</th>
								<th>윤상기</th>
								<th>00년10월23일</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">출석</a></th>
								<th><a href="#" class="btn btn-inverse">보기</a></th>
							</tr>
							<tr>
								<th>2</th>
								<th>윤상기</th>
								<th>00년10월23일</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">출석</a></th>
								<th><a href="#" class="btn btn-inverse">보기</a></th>
							</tr>
							<tr>
								<th>1</th>
								<th>윤상기</th>
								<th>00년10월23일</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">출석</a></th>
								<th><a href="#" class="btn btn-inverse">보기</a></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane" id="tab2">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>트레이너</th>
								<th>프로그램명</th>
								<th>프로그램기간</th>
								<th>프로그램회원</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>윤상기</th>
								<th>요가교실1</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">회원보기</a></th>
							</tr>
							<tr>
								<th>윤상기</th>
								<th>요가교실1</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">회원보기</a></th>
							</tr>
							<tr>
								<th>윤상기</th>
								<th>요가교실1</th>
								<th>2018년 4월 18일~2019년 1월1일</th>
								<th><a href="#" class="btn btn-inverse">회원보기</a></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="tab-pane" id="tab3">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>트레이너명</th>
								<th>근태</th>
								<th>근태 상태</th>
								<th>근태 현황</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>윤상기</th>
								<th>
									<div class="btn-group" role="group">
										<button id="btnGroupDrop1" type="button"
											class="btn btn-secondary dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">선택</button>
										<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
											<a class="dropdown-item" href="#">출근</a> <a
												class="dropdown-item" href="#">결근</a> <a
												class="dropdown-item" href="#">휴무</a> <a
												class="dropdown-item" href="#">휴가</a>
										</div>
									</div>
								</th>
								<th>출근</th>
								<th><a href="#" class="btn btn-inverse">근태 현황</a></th>
							</tr>
							<tr>
								<th>윤상기</th>
								<th>
									<div class="btn-group" role="group">
										<button id="btnGroupDrop1" type="button"
											class="btn btn-secondary dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">선택</button>
										<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
											<a class="dropdown-item" href="#">출근</a> <a
												class="dropdown-item" href="#">결근</a> <a
												class="dropdown-item" href="#">휴무</a> <a
												class="dropdown-item" href="#">휴가</a>
										</div>
									</div>
								</th>
								<th>출근</th>
								<th><a href="#" class="btn btn-inverse">근태 현황</a></th>
							</tr>
							<tr>
								<th>윤상기</th>
								<th>
									<div class="btn-group" role="group">
										<button id="btnGroupDrop1" type="button"
											class="btn btn-secondary dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">선택</button>
										<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
											<a class="dropdown-item" href="#">출근</a> <a
												class="dropdown-item" href="#">결근</a> <a
												class="dropdown-item" href="#">휴무</a> <a
												class="dropdown-item" href="#">휴가</a>
										</div>
									</div>
								</th>
								<th>출근</th>
								<th><a href="#" class="btn btn-inverse">근태 현황</a></th>
							</tr>

						</tbody>

					</table>
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
