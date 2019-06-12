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
.even {
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
									<div class="col-sm-10">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" 
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">리스트</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">날짜</th>
												</tr>
											</thead>
											<tbody>

										${counselList}
							
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
					</div>
				
</body>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
</html>