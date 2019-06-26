<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
<link rel="stylesheet" href="css/style.css">
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
									style="color: #71c016;">업체 내 정보 수정</a>
							</div>
							<br>

							<form action="" class="pt-3" name="infomodifyupdate"
								id="infomodifyupdate" method="post"
								enctype="multipart/form-data">
								<div>

									<p class="title">업체프로필사진</p>
									<input type="file" id="input_img" name="pf_image" id="pf_image"
										onchange="fileChk(this)" /> <input type="hidden"
										id="fileCheck" value="0" name="fileCheck">
								</div>

								<div>
									<div class="img_wrap">
										<img id="img" src="resources/upload/${m.pf_image }" />
									</div>
								</div>
								<br>
								<div class="form-group">
									<div class="input-group">
										<input type="text" name="c_bname"
											class="form-control form-control-lg border-left-0"
											value="${com.c_bname }" disabled>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" name="c_num"
											class="form-control form-control-lg border-left-0"
											value="${com.c_num }" disabled>
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-email-outline text-primary"></i>
											</span>
										</div>
										<input type="email" name="m_id"
											class="form-control form-control-lg border-left-0"
											value="${com.c_id }" disabled> <input type="hidden"
											name="c_id" id="c_id" value="${com.c_id }">
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
										<input type="text" name="m_name"
											class="form-control form-control-lg border-left-0"
											value="${com.m_name }" disabled>
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
											placeholder="${com.m_phone }" id="m_phone"
											value="${com.m_phone }">

									</div>

								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-prepend bg-transparent">
											<span class="input-group-text bg-transparent border-right-0">
												<i class="mdi mdi-cellphone text-primary"></i>
											</span>
										</div>

										<input type="tel" name="c_bphone" id="c_bphone"
											class="form-control form-control-lg border-left-0"
											placeholder="${com.c_bphone }" value="${com.c_bphone }">
									</div>

								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_birth"
											class="form-control form-control-lg border-left-0"
											value="${com.m_birth }" disabled>
									</div>
								</div>

								<div class="form-group">
									<div class="input-group">

										<input type="text" name="m_addr"
											class="form-control form-control-lg border-left-0"
											id="m_addr" placeholder="시/도-군/구" value="${com.m_addr }">
										<button type="button" onclick="sample6_execDaumPostcode()"
											class="btn btn-outline-secondary btn-md">주소 검색</button>


									</div>

								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" name="m_exaddr" id="m_exaddr"
											class="form-control form-control-lg border-left-0"
											placeholder="상세주소 입력" value="${com.m_exaddr }">
									</div>

								</div>


								<div class="mb-4">
									<div class="form-check">



										<i class="input-helper"></i>
									</div>
								</div>
								<div class="mt-3">
									<button
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										onclick="formData()" value="FormData">정보수정하기</button>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="vendors/base/vendor.bundle.base.js"></script>
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="./js/jquery-3.1.0.min.js"
	charset="utf-8"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
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

		$("#infomodifyc").validate({
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
					number : true,
					maxlength : 11
				},
				c_bphone : {
					required : true,
					digits : true,
					number : true,
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
				c_bphone : {
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
	//end 유효성검사function

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

				document.getElementById("sample6_address").value = addr;
				// 커서를 상세주소 필드로 이동한다.

			}
		}).open();
	} //다음주소api End

	/* $('#rs').on('click', function() {
		$('#fileCheck').val(0);
	});
	 */
	function fileChk(elem) {
		console.dir(elem);
		if (elem.value == "") {
			console.log("empty");
			$('#fileCheck').val(x);
		} else {
			console.log("not empty");
			$('#fileCheck').val(1);
		}
	}

	function formData() {
		//var $obj = $('#b_files').val();
		var form = $('#infomodifyupdate')[0];
		//console.log(1, $obj);
		//console.log(2, $obj[0].files);
		//console.log(3, $obj[0].files[0]);
		//console.log(4, $obj[0].files[1]); 

		//multipart/form-data를 전송시 무조건 사용(파일 업로드)
		//FormData객체는 form의 일부 데이터만 서버에 전송 가능
		//ajax에서 사용
		var formData = new FormData(form);
		//formData.append('c_id', $('#c_id').val());
		//formData.append('m_pw', $('#m_pw').val());
		//formData.append('m_phone', $('#m_phone').val());
		//formData.append('m_addr', $('#m_addr').val());
		//formData.append('m_exaddr', $('#m_exaddr').val());
		//formData.append('c_bphone', $('#c_bphone').val());
		//formData.append('pf_image', $('#pf_image').val());
		//formData.append('fileCheck', $('#fileCheck').val());

		//var files = $obj[0].files; //배열로 파일정보를 반환 

		//for (var i = 0; i < files.length; i++) {
		//	formData.append('b_files', files[i]);
		//} 

		$.ajax({
			url : "infomodifyupdate",
			type : "post",
			data : formData,
			dataType : "json",
			processData : false,
			contentType : false, //multipart의 경우 false ,
			enctype : 'multipart/form-data',
			//contestType : 'application/json'
			success : function(data) {
				alert(data);
				// 	$('#main').html(data); //

				// location.href = '/manage/infoModifyC'; //
			},
			error : function(error) {
				console.log(error);
			}

		});
		//$("#infomodifyupdate").submit();
	}//formData End
</script>
</html>
