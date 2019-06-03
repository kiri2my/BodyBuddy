<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Majestic Admin</title>
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
<link rel="shortcut icon" href="images/favicon.png">
<style type="text/css">/* Chart.js */
@
-webkit-keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
@
keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	-webkit-animation: chartjs-render-animation 0.001s;
	animation: chartjs-render-animation 0.001s;
}

.content-wrapper {
	text-align: center;
}

.sorting {
	text-align: center;
}

.odd {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container-scroller">



		<div class="row">
			<div class="col-md-12 grid-margin"></div>
		</div>


		<div class="row">
			<div class="col-md-12 stretch-card">
				<div class="card">
					<div class="card-body">
						<p class="card-title">내 프로그램</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
								<div class="row">
									<div class="col-sm-12 col-md-6"></div>
									<div class="col-sm-12 col-md-6"></div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" aria-sort="ascending"
														aria-label="Name: activate to sort column descending"
														style="width: 100px;">업체명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 100px;">프로그램명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Office: activate to sort column ascending"
														style="width: 100px;">트레이너</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Price: activate to sort column ascending"
														style="width: 144px;">이용기간</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Date: activate to sort column ascending"
														style="width: 100px;">출결확인</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Gross amount: activate to sort column ascending"
														style="width: 100px;">이용상태</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Gross amount: activate to sort column ascending"
														style="width: 100px;">상담내역</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Gross amount: activate to sort column ascending"
														style="width: 100px;">후기</th>
												</tr>
											</thead>
											<tbody>

												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>PT</td>
													<td>유림하</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>상담내역보기</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>PT</td>
													<td>유림하</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>상담내역보기</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>PT</td>
													<td>유림하</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>상담내역보기</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>PT</td>
													<td>유림하</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>상담내역보기</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>PT</td>
													<td>유림하</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>상담내역보기</td>
													<td>후기쓰기</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-5"></div>
									<div class="col-sm-12 col-md-7"></div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<div id="se" class="card-body">
						<p class="card-title">일반 피트니스</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
								<div class="row">
									<div class="col-sm-12 col-md-6"></div>
									<div class="col-sm-12 col-md-6"></div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" aria-sort="ascending"
														aria-label="Name: activate to sort column descending"
														style="width: 105px;">업체명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 105px;">이용기간</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Office: activate to sort column ascending"
														style="width: 96px;">출결확인</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Price: activate to sort column ascending"
														style="width: 37px;">이용상태</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Date: activate to sort column ascending"
														style="width: 69px;">후기쓰기</th>
											</thead>
											<tbody>

												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>후기쓰기</td>
												</tr>
												<tr role="row" class="odd">
													<td class="sorting_1">ICIA피트니스</td>
													<td>19.05.20~19.06.20</td>
													<td>출결현황보기</td>
													<td>이용중</td>
													<td>후기쓰기</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-5"></div>
									<div class="col-sm-12 col-md-7"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




</body>
</html>