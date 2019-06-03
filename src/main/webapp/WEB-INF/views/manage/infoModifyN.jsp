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
									style="color: #71c016;">BODY BUDDY</a>
							</div>
							<br>
							<div>

								<p class="title">프로필사진</p>
								<input type="file" id="input_img" />
							</div>

							<div>
								<div class="img_wrap">
									<img id="img" />
								</div>
							</div>
							<form action="infomodifyn" class="pt-3" name="infomodifyn"
								id="infomodifyn" method="post">

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-email-outline text-primary"></i>
											</span>
										</div>
										<input type="email" name="m_id"
											class="form-control form-control-lg border-left-0"
											value="gozldsos@naver.com" disabled>
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
											class="form-control form-control-lg border-left-0"
											id="exampleInputPassword" placeholder="패스워드를 입력해주세요">
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
										<input type="text" name="m_name"
											class="form-control form-control-lg border-left-0"
											value="윤상기" disabled>
									</div>
								</div>


								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-cellphone text-primary"></i>
											</span>
										</div>
										<input type="tel" name="m_phone"
											class="form-control form-control-lg border-left-0"
											placeholder="전화번호를 입력해주세요">
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_birth"
											class="form-control form-control-lg border-left-0"
											value="1992년 11월 7일" disabled>
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_addr"
											class="form-control form-control-lg border-left-0"
											placeholder="시/도-군/구">
										<button type="button" class="btn btn-outline-secondary btn-md">주소
											검색</button>
										<input type="text" name="m_addr"
											class="form-control form-control-lg border-left-0"
											placeholder="상세주소 입력">
									</div>
								</div>




								<div class="mb-4">
									<div class="form-check">



										<i class="input-helper"></i>
									</div>
								</div>
								<div class="mt-3">
									<a
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										href="main.jsp">정보수정하기</a>
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
<script type="text/javascript" src="./js/jquery-3.1.0.min.js"
	charset="utf-8"></script>
<script type="text/javascript">
	var sel_file;

	$(document).ready(function() {
		$("#input_img").on("change", handleImgFileSelect);
	});

	function handleImgFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		filesArr.forEach(function(f) {
			if (!f.type.match("image.*")) {
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}

			sel_file = f;

			var reader = new FileReader();
			reader.onload = function(e) {
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}// img미리보기 End
	
	
	$(function() {

		$("#infomodifyn").validate({
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
				phone : {
					required : true,
					digits : true,
					number: true,
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
					number : "숫자만 입력가능합니다",
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
