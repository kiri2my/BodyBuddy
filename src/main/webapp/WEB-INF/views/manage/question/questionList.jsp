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
											<td>${question.ad_title}</td>
											<td>${question.qa_wcontent}</td>
											<td>${question.qa_writer}</td>
											<td>${question.qa_wdate }</td>
											<c:set var="name" value="t" />
											<input type="hidden" class="mm" value="${question.qa_num}" />
											<c:if test="${name ne question.qa_true}">
												<td id="ss"><a style="display: inline;" href="#myModal" role="button" class="abtn"
													data-toggle="modal" id="mm" >답변하기</a></td>
											</c:if>
											<input type="hidden" class="mm" value="${question.qa_num}" />
											<c:if test="${name eq question.qa_true}">
											<td id="aa"><a style="display: inline;" href="#myModal" role="button" class="cbtn"
													data-toggle="modal" id="cbtn" >답변확인하기</a></td>
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
				
				<input id="qa_num" type="hidden"/>
					<div id="qa_wcontent" style="text-align: center;margin: 50px;"></div>
					<%-- $ --%>
				</div>

				<div class="modal-body">
					<input type="text" id="qa_acontent" name="qa_acontent"
						placeholder="답변 저정후 수정이 불가능 합니다"
						disabled="disabled"
						style="text-align: center; width: 750px; height: 150px;"
						 />
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
	//모달박스 답변 작성
	var qajax = null;
	var qajaxwcon = null;
	var qajaxacon = null;
	var qajaxnum = null;

	
	//답변 저장
	$("#aSave").click(function() {
		var qa_num = $('#qa_num').val();
		var m_id = "${mb.m_id}";
		
		$.ajax({
			url : "questionreply",
			type : "post",
			data : {
				"qa_acontent" : $('#qa_acontent').val(),"qa_num" : qa_num
			},
			dataType : "html",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				$('#qa_acontent').val("")
				var str = "";
				str += '<td id="aa"><a style="display: inline;" href="#myModal" role="button" class="abtn"'
					+'data-toggle="modal" id="cbtn" >답변확인하기</a></td>'
					$('#mm').prop("style", "display: none");
					$('#ss').html(str);$();
				//////////////////////////답변했다고 문의자 일반회원한테 알림보내기
					console.log(":::"+m_id+","+qa_num);
					var jsonSendAnsAlarm = JSON.stringify({qa_num:qa_num,m_id:m_id});
					$("#msg").val("AQ01"+jsonSendAnsAlarm);
					console.log("WebSock5", sock);
					console.log($("#msg").val());
					sock.send($("#msg").val());
				//////////////
					
					
			},
			error : function(error) {
				console.log(error);
				alert(" 실패 ");

			}
		});//end ajax

	});//end click

	//답변하기 + 질문내용 가져오기

	$(".abtn").each(function() {
		$(this).click(function() {
			var num = $(this).parents().eq(1).children().eq(4).val();
			console.log(num);

			$.ajax({
				url : "qnacheck",
				type : "post",
				data : {
					"qa_num" : num
				},
				dataType : "json",
				/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
				success : function(data) {
					qajax = data;
					qajaxnum = data[0].qa_num;
					qajaxwcon = data[0].qa_wcontent;
					qajaxacon = data[0].qa_acontent;
					console.log("1 = ", qajaxnum)
					console.log("2 = ", qajaxwcon)
					console.log("3 = ", qajaxacon)
					$('#qa_wcontent').html(qajaxwcon);
					$('#qa_num').val(qajaxnum);
					$('#qa_acontent').prop("disabled", false);

				},
				error : function(error) {
					console.log(error);
					alert(" 실패 ");

				}
			})
		});//end ajax

	});//end click
	
	$(".cbtn").each(function() {
		$(this).click(function() {
			var num = $(this).parents().eq(1).children().eq(4).val();
			console.log(num);

			$.ajax({
				url : "qnacheck",
				type : "post",
				data : {
					"qa_num" : num
				},
				dataType : "json",
				/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
				success : function(data) {
					qajax = data;
					qajaxnum = data[0].qa_num;
					qajaxwcon = data[0].qa_wcontent;
					qajaxacon = data[0].qa_acontent;
					console.log("1-1 = ", qajaxnum)
					console.log("2-2 = ", qajaxwcon)
					console.log("3-3 = ", qajaxacon)
					$('#qa_wcontent').html(qajaxwcon);
					$('#qa_acontent').val(qajaxacon);
					$('#qa_num').val(qajaxnum);
					$('#qa_acontent').prop("disabled", true);

				},
				error : function(error) {
					console.log(error);
					alert(" 실패 ");

				}
			})
		});//end ajax

	});//end click
</script>


</html>