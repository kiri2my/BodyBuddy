<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- plugins:css -->
  <link rel="stylesheet" href="../../vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="../../vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="../../css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="../../images/favicon.png" />
  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">
							<div class="brand-logo">
								<a class="navbar-brand brand-logo" href="/"
									style="color: #71c016;">BODY BUDDY</a>
							</div>
							<h5>아이디 찾기 </h5>
							<form action="forgetid" class="pt-3" name="forgetid"
								id="forgetid" method="post">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-account-box text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_name" id="m_name"
											class="form-control form-control-lg border-left-0"
											placeholder="이름을 입력하세요" />
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-cellphone-android text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_phone" id="m_phone"
											class="form-control form-control-lg border-left-0"
											placeholder="휴대폰 번호를 입력하세요" />
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-cake-variant text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_birth" id="m_birth"
											class="form-control form-control-lg border-left-0"
											placeholder="생년월일을 입력하세요(19940402)" />
									</div>
								</div>

								<div class="mt-3">

									<input
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										type="submit" value="찾기">
								</div>
								<div class="text-center mt-4 font-weight-light">
									<a href="forget" class="text-primary">비밀번호를 찾으시나요?</a>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>


</html>


