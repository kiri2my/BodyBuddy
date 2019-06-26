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
<title>BODY BUDDY</title>
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
<link rel="shortcut icon" href="images/favicon.png" />
<link rel="stylesheet" href="css/bootstrap.css">
</head>

<body>
	<div class="container-scroller">

		<div class="row" style="height: 700px">
			<div class="col-md-12 stretch-card">
				<!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도-->
				<div class="card">
					<div class="card-body">
						<p class="card-title">광고 관리</p>
						<div class="table-responsive">
							<table id="recent-purchases-listing" class="table">
								<c:set var="advertise" value="${adList }" />
								<c:if test="${empty advertise }">
									광고가 없습니다.
								</c:if>
								<c:if test="${!empty advertise }">
									<thead>
										<tr>
											<th>카테고리</th>
											<th>광고명</th>
											<th>등록일</th>
											<th>상태</th>
											<th>담당트레이너</th>
											<th>관리</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="advertise" items="${adList}">
											<c:set var="name" value="만료됨" />
											<c:if test="${name ne advertise.ad_status}">
												<tr id="${advertise.ad_code}" name="ad_code">
													<td>${advertise.ad_category}</td>
													<input type="hidden" class="mm"
														value="${advertise.ad_code}" />
													<td>${advertise.ad_title}</td>
													<td>${advertise.ad_date}</td>
													<td>${advertise.ad_status}</td>
													<td>${advertise.op_trainer }</td>
													<td><input class="eBtn" type="button" value="수정"><input
														class="dBtn" type="button" value="삭제"></td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</c:if>
							</table>
							<button class="btn btn-secondary" onclick="advertisewritefrm()"
								type="button" style="display: inline;" id="adWriteFrm">신규 등록</button>
						</div>
					</div>
				</div>
			</div>
		</div>



		

	</div>



	<!-- plugins:js -->
	<script src="vendors/base/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script src="vendors/chart.js/Chart.min.js"></script>
	<script src="vendors/datatables.net/jquery.dataTables.js"></script>
	<script src="vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/template.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="js/dashboard.js"></script>
	<script src="js/data-table.js"></script>
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.bootstrap4.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<!-- End custom js for this page-->
</body>
<script>
	$(".dBtn").each(function() {
		$(this).click(function() {
			var num = $(this).parents().eq(1).children().eq(1).val();
			console.log(num);
			var id = "#" + num;
			console.log(id);
			$.ajax({
				url : "deletead",
				type : "post",
				data : {
					"ad_code" : num
				},
				dataType : "html",
				/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
				success : function(data) {
					console.log(data)

					$(id).prop("style", "display: none");
				},
				error : function(error) {
					console.log(error);
					alert(" 실패 ");

				}
			})
		});//end ajax

	});//end click

	$(".eBtn").each(function() {
		$(this).click(function() {
			var num = $(this).parents().eq(1).children().eq(1).val();
			console.log(num);
			$.ajax({
				type : "post",
				url : "advertisemodifyfrm",
				data : {
					"ad_code" : num
				},
				dataType : "html",
				error : function() {
					alert('통신실패!!');
				},
				success : function(data) {
					/* $('#main').hide(); */
					$('#main').html(data);
				}
			});
		})
	})
	

</script>
</html>
