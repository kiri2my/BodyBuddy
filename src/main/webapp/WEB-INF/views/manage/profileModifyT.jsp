<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<link rel="shortcut icon" href="images/favicon.png" />
<style>
.img_wrap {
	width: 300px;
	margin-top: 50px;
}

.img_wrap img {
	max-width: 100%;
}
</style>
</head>
<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">
							<div class="brand-logo">
								<a class="navbar-brand brand-logo" href="main.jsp"
									style="color: #71c016;">트레이너 프로필 수정</a>
							</div>
							<br>
							<form action="profileinsert" class="pt-3" name="profileinsert"
								id="profileinsert" method="post">

								<div class="form-group">
									<div class="input-group">
											
											<textarea rows="8" cols="8" name="t_career"
												class="form-control form-control-lg border-left-0">
												
														${m.t_career}
											
											</textarea>
										
										
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="c_bname"
											class="form-control form-control-lg border-left-0"
											id="sample6_address" placeholder= "현재나의소속업체 : ${m.t_cid}">
										<button type="button" onclick="TfindC()" class="btn btn-outline-secondary btn-md">업체
											검색</button>
									</div>
								</div>
								<div id=TfindC>
								
								</div>

								<div class="mb-4">
									<div class="form-check">



										<i class="input-helper"></i>
									</div>
								</div>
								<div class="mt-3">
									<a
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										href="main.jsp">프로필등록하기</a>
									<a
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										href="main.jsp">프로필수정하기</a>
																		<a
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										href="main.jsp">프로필삭제하기</a>
								</div>

							</form>
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

<script type="text/javascript" src="./js/jquery-3.1.0.min.js"
	charset="utf-8">
</script>
<script type="text/javascript">
function TfindC(){
	var name = $('#sample6_address').val();
	$.ajax({
		type : "get",
		url : "TfindC",
		data : {
			name : name
		},
		dataType : "html",
		success : function(data) {
			alert(data);
			$('#TfindC').html(data);
		},
		error : function() {
			alert('업체 검색 실패');
		}
	});
}
</script>


</html>
