<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<form action="profileinsert" onsubmit="return profilemodify()" class="pt-3"
								name="profileinsert" id="profileinsert" method="post">

								<div class="form-group">
									<div class="input-group">

										<textarea rows="8" cols="8" name="t_career"
											class="form-control form-control-lg border-left-0"
											placeholder="쉼표(,)로 항목을 구분해주세요">
											
											${m.t_career}
											</textarea>


									</div>
								</div>

								<div class="form-group">
									<div class="input-group">
										<input type="text" name="c_bname"
											class="form-control form-control-lg border-left-0"
											id="sample6_address" placeholder="현재 소속업체 ID: ${m.t_cid}">
										<button type="button" id = "aa"
											class="btn btn-outline-secondary btn-md">업체 검색</button>
									</div>
								</div>
								
								<div id="test">
								</div>

								<div class="mb-4">
									<div class="form-check">
										<i class="input-helper"></i>
									</div>
								</div>
								<div id="sosok">
									<table id="recent-purchases-listing" class="table">
										<thead>
										<tr>
											<th>업체이름</th>
											<th>내아이디</th>
											<th>상태</th>
											<th>날짜</th>
										</tr>
										</thead>
										<tbody>
										<tr>
											<td>${m1.c_bname }</td>
											<td>${m1.yn_trainer }</td>
											<td>${m1.yn_state }</td>
											<td>${m1.yn_date }</td>
										</tr>
										</tbody>
									</table>
								</div>
								<div class="mt-3">
									<!-- <a
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										href="main.jsp">프로필등록하기</a>  -->
									<input
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
										type='submit' onclick = "profileComplete()" value='프로필수정하기'>
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
 var a = false;


 $("#aa").click(function(){
	 var cName = $("#sample6_address").val();
	 console.log("cName:"+cName);
		$.ajax({
			type : "get",
			url : "TfindC",
			data : {
				cName : cName
			},
			dataType : "html",
			success : function(data) {
				console.log(data);
				/* $('#TfindC').html(); */
				$("#test").html(data);
				/* $("#TfindC").show(); */
				$(".acceptrequest").click(function(){
					var c_id = $(this).prev().val();
					var m_id = "${mb.m_id}";
					console.log(c_id);
					$.ajax({
						type : "GET",
						url : "acceptrequest",
						data : {
							c_id : c_id
						},
						dataType : "html",
						error : function() {
							alert('요청 실패');
						},
						success : function(data) {
							console.log(data);
							alert('요청완료');
							//웹소켓 소속요청 알림 보내기
							if(data=='insert' || data=='update'){
								var jsonSendSosokAlarm = JSON.stringify({c_id:c_id,m_id:m_id});
								$("#msg").val("SS01"+jsonSendSosokAlarm);
								console.log("WebSock6", sock);
								console.log($("#msg").val());
								sock.send($("#msg").val());
							}
							//
						}
					});
				});
				
			},
			error : function(er) {
				console.log(er);
				alert('업체 검색 실패');
			}
		});
 });

	
	 function TfindC(cName) {
		
	} 
	function cancel(cName) {
		alert("cancel ajax 들어옴")
		$.ajax({
			type : "GET",
			url : "cancel",
			data : {
				name : cName
			},
			dataType : "text",
			error : function() {
				alert('취소 실패');
			},
			success : function(data) {
				alert(data);
				$('#state').val(can);
				alert('취소완료');
			}
		});
	}
	
	
	
	
	
	function profileModifyComplete() {
		$.ajax({
			type : "GET",
			url : "acceptrequest",
			data : {
				id : "5555",
				name : "테스트업체명"
			},
			dataType : "html",
			error : function() {
				alert('요청 실패');
			},
			success : function(data) {
				$('#main').html(data);

				alert('요청완료');
			}
		});
	}
	function profileComplete() {
		a = true;
		alert("수정완료");
	}
	

	function profilemodify(){
		return a;
	}
</script>


</html>
