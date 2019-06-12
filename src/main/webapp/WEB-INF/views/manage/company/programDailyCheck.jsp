<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

<style type="text/css">
#memsearch {
	height: 40px;
	width: 200px;
}

#membtn {
	vertical-align: bottom;
}
</style>
</head>
<body>
	<div class="row" style="height: 100%">
		<div class="col-md-12 stretch-card">
			<div class="card">
				<div class="card-body">
					<p class="card-title">
						<br>
					</p>
					<input type="text" class="span2" id="memsearch"
						placeholder="일반회원 검색">
					<button type="button" onclick="memberSearch()" class="btn"
						id="membtn">검색</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">

							<c:set var="program" value="${pList }" />
							<c:if test="${empty program }">
									회원이 없습니다.
								</c:if>
							<c:if test="${!empty program }">
								<thead>
									<tr>
										<th>프로그램명</th>
										<th style="width: 200px">트레이너</th>
										<!-- 	<th style="width: 100px">회원수</th> -->
										<th>기간</th>
										<th>상태</th>
										<th style="width: 200px">모집 회원</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="program" items="${pList }">
										<tr>
											<td><a href="#">${program.ad_title }</a></td>
											<td>${program.m_name}(${program.op_trainer })</td>
											<!-- <td>32</td> -->
											<td>${program.op_period }</td>
											<td>진행중</td>
											<th><button class="btn btn-danger"
													onclick="programMember(${program.op_adcode})">회원
													보기</button></th>
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

	<div class="modal" id="modal"
		style="width: 30%; height: inherit; left: 50%; top: 20%;">
		<div class="modal-header"
			style="text-align: center; align-content: center;">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3>모집 회원</h3>
		</div>
		<div class="modal-body" id="modalBody"></div>
	</div>

	<!-- plugins:js -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/template.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

</body>

<script type="text/javascript">
	function memberSearch() {
		var name = $('#memsearch').val();
		$.ajax({
			type : "get",
			url : "normalmembersearch",
			data : {
				name : name,
				id : '3333'
			},
			dataType : "html",
			success : function(data) {
				alert(data);
				$('#main').html(data);
			},
			error : function() {
				alert('일반회원 검색 실패');
			}
		});
	}
	
	function programMember(adcode) {
		var adCode = adcode;
		var cid = 'company1';
		$.ajax({
				type : "post",
				url : "programmember",
				data : {
						code : adCode,
						cid : cid
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						 var str = "";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>이름</th><th style='width: 10%'>연락처</th><th style='width: 10%'>생년월일</th><th style='width: 10%'>출결</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].m_name+"("+data[i].m_id+")" + "</td><td>" + data[i].m_phone + "</td><td>" + data[i].m_birth + "</td><td><button class='btn btn-danger'	onclick='programCheck("+data[i].ps_code+")'>출석</button></td></tr>";
						}
						str += "</tbody></table>";
						$('#modalBody').html(str);
						$('#modal').modal('toggle');
					},
					error : function(error) {
						alert('근태 목록 로드 실패');
						console.log(error);
					}
		});
	}
	
	programCheck
	
	function programCheck(psCode) {
		var code = psCode;
		alert(code);
		
		$.ajax({
			type : "POST",
			url : "programcheck",
			data : {
				code : code,
			},
			dataType : 'html',
			success : function(data) {
				alert("출석 입력 성공");
			},
			error : function() {
				alert('출석 입력 실패');
			}
		});
	}
	
	
	
</script>
</html>