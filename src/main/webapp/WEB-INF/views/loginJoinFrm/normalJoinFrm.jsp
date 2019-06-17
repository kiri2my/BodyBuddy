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
												<i class="mdi mdi-email-outline text-primary"></i>
											</span>
										</div>
										<input type="text" name="emailNum" id="emailNum"
											class="form-control form-control-lg border-left-0"
											placeholder="인증번호" /> <input type="button"
											class="btn btn-outline-secondary btn-md" id="sendRndNum"
											value="인증번호발송" disabled="disabled"/> <input type="hidden"
											class="btn btn-outline-secondary btn-md" id="sendRndNumCheck"
											name="mailenum" value="인증번호확인" /> <br />
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

										<input type="text" name="m_addr"
											class="form-control form-control-lg border-left-0"
											id="m_addr" placeholder="시/도-군/구">
										<button type="button" onclick="sample6_execDaumPostcode()"
											class="btn btn-outline-secondary btn-md">주소 검색</button>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_exaddr"
											class="form-control form-control-lg border-left-0"
											placeholder="상세주소 입력"> <input type="hidden" value="n"
											id="m_kind" name="m_kind" />
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

</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
	//인증메일전송
	var mail = null;
	var oldid= null;
	$("#m_id").on("propertychange change keyup paste input", function() {
	    var currentVal = $(this).val();
	    if(currentVal == oldid) {
	        return;
	    }
	 
	    oldid = currentVal;
	    $('#sendRndNumCheck').prop("type", "hidden");
		$('#sendRndNum').prop("type", "button");
		$('#joinbtn').prop("disabled", true);
		$('#sendRndNum').prop("disabled", true);
	});
	 

	$('#sendRndNum').click(function() {
		$.ajax({
			url : "sendrndnum",
			type : "post",
			dataType : "html",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				$('#sendRndNumCheck').prop("type", "button");
				$('#sendRndNum').prop("type", "hidden");
				mail = data;
				console.log("data" + data);
				console.log(mail);
				alert("인증번호를  발송하였습니다")
			},
			error : function(error) {
				console.log(error);
				alert(" 실패 ");

			}
		});//end ajax

	});//end click
	//인증번호 체크
	$("#sendRndNumCheck").click(function() {
		console.log(mail);
		if ($("#emailNum").val() == mail) {
			$('#joinbtn').prop("disabled", false);
			alert("인증되었습니다")
		} else {
			alert("인증번호를 다시 확인해주세요")
		}
	})

	//중복아이디 체크
	$('#idCheck').click(function() {
		console.log($('#m_id').val());
		oldid = $('#m_id').val();
		$.ajax({
			url : "checkid",
			type : "post",
			data : {
				"m_id" : $('#m_id').val()
			},
			dataType : "html",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				if (data < 1) {
					alert(" 사용가능한 아이디입니다  ");
					console.log(data);
					console.log(m_id);
					$('#sendRndNum').prop("disabled", false);
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
				m_exaddr : "상세주소를 입력 해주세요",
				agree : "개인정보 보호 동의해 체크해주세요"

			}
		//end messages

		});
		//end validate

	});
	//end function
	function sample6_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.

				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.

				document.getElementById("m_addr").value = addr;
				// 커서를 상세주소 필드로 이동한다.

			}
		}).open();
	} //다음주소api End
</script>

</html>


