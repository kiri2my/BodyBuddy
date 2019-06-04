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
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png">
<style>
.content-wrapper {
	margin-right: auto;
}
</style>
</head>

<body>
	<div class="container-scroller">




		<div class="col-md-12 grid-margin grid-margin-md-0 stretch-card">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">후기쓰기 페이지</h4>
					<div class="btn-group">
						<button type="button"
							class="btn btn-outline-secondary dropdown-toggle"
							data-toggle="dropdown" aria-expanded="false">평점</button>
						<div class="dropdown-menu" x-placement="bottom-start"
							style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px);">
							<a class="dropdown-item">5점</a> <a class="dropdown-item">4점</a> <a
								class="dropdown-item">3점</a> <a class="dropdown-item">2점</a> <a
								class="dropdown-item">1점</a>
						</div>
					</div>
					<br>
					<textarea rows="10" cols="230"></textarea>

				</div>
				<button type="button" class="btn btn-dark btn-lg btn-block">후기글쓰기</button>
			</div>

		</div>

	</div>





	<script src="vendors/base/vendor.bundle.base.js"></script>
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>


</body>
</html>