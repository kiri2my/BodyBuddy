<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.modal {
	text-align: center;
}

.modal:before {
	display: inline-block;
	vertical-align: middle;
	content: " ";
	height: 100%;
}

.modal-dialog {
	display: inline-block;
	text-align: left;
	vertical-align: middle;
}
</style>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
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
	<div class="row" style="height: 700px">
		<div class="col-md-12 stretch-card">
			<!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도-->
			<div class="card">
				<div class="card-body">
					<p class="card-title">문의 관리</p>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							<c:set var="question" value="${qList }" />
							<c:if test="${empty question }">
									문의가 없습니다.
								</c:if>
							<c:if test="${!empty question }">
								<thead>
									<tr>
										<th>광고명</th>
										<th>문의내용</th>
										<th>작성자</th>
										<th>등록일</th>
										<th>답변상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="question" items="${qList }">
										<tr>
											<td><a href="#">${question.ad_title }</a></td>
											<td><a href="#">${question.qa_wcontent }</a></td>
											<td><a href="#">${question.qa_writer }</a></td>
											<td>${question.qa_wdate }</td>
											<input type="hidden" class="mm" value="${question.qa_num}">
											<c:if test="${empty question.qa_adate}">
												<td><a href="#myModal" role="button" class="abtn"
													data-toggle="modal" id="mm">답변하기</a></td>
											</c:if>
											<c:if test="${!empty question.qa_adate}">
												<td><input type="button" id="qa_answercheck"
													name="qa_answercheck" value="답변확인하기" /></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 모달 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 800px; height: 650px;">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Modal 제목</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<tr>
						<td>${question.qa_wdate}</td>
					</tr>
					<input type="text" disabled="disabled"
						value="{question.qa_wcontent}"
						style="text-align: center; width: 750px; height: 150px;" />
					<%-- $ --%>
				</div>

				<div class="modal-body">
					<input type="text" id="qa_acontent" name="qa_acontent"
						placeholder="답변 저정후 수정이 불가능 합니다"
						style="text-align: center; width: 750px; height: 150px;" />
				</div>


				<div class="modal-footer">
					<button id="aSave" type="button" class="btn btn-default"
						data-dismiss="modal">답변저장</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
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
	$(".abtn").each(function() {
		$(this).click(function() {
			var as = $(this).parents().eq(1).children().eq(4).val();
			alert("성공!!" + as + "아아아");
			console.log(as);

			$.ajax({
				url : "qaNum",
				type : "post",
				data : {
					"qa_num" : as
				},
				dataType : "html",
				/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
				success : function(data) {
					alert("얍얍얍");
				},
				error : function(error) {
					console.log(error);
					alert(" 실패 ");

				}
			})
		});//end ajax

	});//end click

	//$(".abtn").each(function() {

	//var ab = $(".mm").val();

	//})
</script>


</html>