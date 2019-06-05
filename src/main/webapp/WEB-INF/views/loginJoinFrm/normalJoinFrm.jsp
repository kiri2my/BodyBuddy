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
<title>BODY BUDDY(지도없음,로그인함)</title>
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

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

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
							<h5>아이콘이 있으면 필수내용입니다</h5>
							<form action="normalmemberjoin" class="pt-3" name="memberjoin"
								id="memberjoin" method="post">

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-email-outline text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_id" id="m_id"
											class="form-control form-control-lg border-left-0"
											placeholder="이메일을 입력해주세요" />
										<button type="button" class="btn btn-outline-secondary btn-md"
											id="idCheck">중복 확인</button>
										<br />
									</div>
								</div>

								<div class="form-group">

									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-lock-outline text-primary"></i>
											</span>
										</div>

										<input type="password" name="m_pw"
											class="form-control form-control-lg border-left-0" id="m_pw"
											placeholder="패스워드를 입력해주세요">

									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-lock-outline text-primary"></i>
											</span>
										</div>
										<input type="password" name="confirm_pw"
											class="form-control form-control-lg border-left-0"
											id="confirm_pw" placeholder="패스워드 확인">

									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-account-outline text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_name" id="m_name"
											class="form-control form-control-lg border-left-0"
											placeholder="이름을 입력해주세요">
									</div>
								</div>


								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-cellphone text-primary"></i>
											</span>
										</div>
										<input type="text" name="m_phone" id="m_phone"
											class="form-control form-control-lg border-left-0"
											placeholder="전화번호를 입력해주세요 (-)제외">
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_birth" id="m_birth"
											class="form-control form-control-lg border-left-0"
											placeholder="생년월일 8자리를 입력해주세요 ex)19900601">
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_addr" id="addr"
											class="form-control form-control-lg border-left-0"
											placeholder="시/도-군/구">
										<button type="button" class="btn btn-outline-secondary btn-md">주소
											검색</button>
										<input type="text" name="m_exaddr" id="m_exaddr"
											class="form-control form-control-lg border-left-0"
											placeholder="상세주소 입력"> <input type="hidden"
											name="m_kind" id="m_kind" value="n" />

									</div>
								</div>





								<div class="mb-4">
									<div class="form-check">
										<label class="form-check-label text-muted"> <input
											type="checkbox" class="form-check-input" id="agree"
											name="agree"> 모든 회원 약관에 동의합니까? <i
											class="input-helper"></i></label>
									</div>
								</div>
								<div class="mt-3">

									<input id="joinbtn"
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										type="submit" value="회원가입" disabled="disabled" />
								</div>
								<div class="text-center mt-4 font-weight-light">
									이미 회원가입 하셨나요?<a href="login.html" class="text-primary"> 로그인</a>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>


<script>
	console.log($('#joinbtn'));

	$('#idCheck').click(function() {
		$.ajax({
			url : "checkid",
			type : "post",
			data : {
				"m_id" : $('#m_id').val()
			},
			dataType  : "html",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				if (data < 1) {
					alert(" 사용가능한 아이디입니다  ");
					console.log(data);
					$('#joinbtn').prop("disabled", false);
					console.log(m_id);
				} else {
					alert(" 중복된 아이디입니다 ");
					$('#joinbtn').prop("disabled", true);
				}
			},
			error : function(error) {
				console.log(error);
				alert(" 실패 ");

			}
		});//end ajax

	});//end click

	$(function() {

		$("#memberjoin").validate({
			rules : {
				m_id : {
					required : true,
					email : true,
					maxlength : 40
				},
				m_pw : {
					required : true,
					minlength : 5,
					maxlength : 12
				},
				confirm_pw : {
					required : true,
					equalTo : "#m_pw"
				},
				m_name : {
					required : true,
					maxlength : 10
				},
				m_phone : {
					required : true,
					digits : true,
					maxlength : 11
				},
				m_birth : {
					required : true,
					digits : true,
					minlength : 8,
					maxlength : 8,
				},
				m_addr : "required",
				agree : "required"
			//email: true	
			},//end rules
			messages : {
				m_id : {
					required : "이메일를 입력 해주세요.",
					email : "이메일 형식으로 입력 해주세요",
					maxlength : "40자 이내만 가능합니다"
				},
				m_pw : {
					required : "암호를 입력 해주세요.",
					minlength : "암호는 5자 이상이어야 합니다.",
					maxlength : "암호는 최대 12자까지 가능합니다."
				},
				confirm_pw : {
					required : "암호를 한 번 더 입력해 주세요.",
					equalTo : "암호가 일치하지 않습니다."
				},
				m_phone : {
					required : "번호를 입력해 주세요.",
					digits : "숫자입력만 가능합니다.",
					maxlength : "번호를 확인해주세요"
				},
				m_name : {
					required : "이름을 입력해 주세요.",
					maxlength : "이름은 최대 10자까지 가능합니다."
				},
				m_birth : {
					required : "생년월일을 입력 해주세요",
					minlength : "잘못 입력하셨습니다",
					maxlength : "잘못 입력하셨습니다",
					digits : "숫자입력만 가능합니다.",

				},
				m_addr : "주소를 입력 해주세요",
				agree : "개인정보 보호 동의해 체크해주세요"

			}
		//end messages

		});
		//end validate

	});
	//end function
</script>

</html>


