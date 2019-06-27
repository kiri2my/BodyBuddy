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
<title>BODY BUDDY</title>
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
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">

				<div class="row w-100 mx-0">
					<div class="col-lg-7 mx-auto">
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">
							<div class="brand-logo">
								<a class="navbar-brand brand-logo" href="/"
									style="color: #71c016;">BODY BUDDY</a>
							</div>
							
								<h4 class="card-title"></h4>
									<div class="media">
									
									</div>
								

						</div>
						



					</div>
				</div>



			</div>
			<!-- content-wrapper ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>
	<!-- endinject -->

</body>
<script>
var forget = "${forget}";
console.log(forget);
if(forget=="id"){
	$(".card-title").html("아이디를 잊으셨습니까?");
	$(".media").html("<i class='mdi mdi-alert-circle icon-md text-info d-flex align-self-start mr-3'></i>" + 
			"	<div class='media-body'>" + 
			"		<p class='card-text'>찾으시는 아이디는 ${m_id}입니다.</p>" + 
			"	</div>");
}else if(forget=="pw"){
	$(".card-title").html("소중한 비밀번호를 잊으셨습니까?");
	$(".media").html("<i class='mdi mdi-alert-circle icon-md text-info d-flex align-self-start mr-3'></i>" + 
			"	<div class='media-body'>" + 
			"		<p class='card-text'>${m_id}님의임시 비밀번호는 ${m_pw}입니다. 정신차리십시오.</p>" + 
			"	</div>");
}

</script>
</html>



