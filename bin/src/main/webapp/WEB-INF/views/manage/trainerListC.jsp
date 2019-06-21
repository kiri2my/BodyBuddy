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
#trainersearch {
	height: 40px;
	width: 200px;
}

#tainerbtn {
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
						<input type="text" class="span2" id="trainersearch"
							placeholder="트레이너 검색">
						<button type="button" onclick="trainerSearch()" class="btn" id="tainerbtn">검색</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							
							<c:set var="trainer" value="${tList }" /> 
								<c:if test="${empty trainer }">
									트레이너가 없습니다.
								</c:if> 
							<c:if test="${!empty trainer }">
							
							<thead>
								<tr>
									<th>이름</th>
                                    <th>입사일자</th>
                                    <th>연락처</th>
                                    <th>근태현황</th>
                                    <th>실적보기</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="trainer" items="${tList }">
								<tr>
									<td><a href="#">${trainer.m_name }</a></td>
									<td>2018-5-8</td>
									<td>${trainer.m_phone }</td>
									<td><a href="#">근태현황</a></td>
									<td><a href="#">실적보기</a></td>
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
	function trainerSearch(){
		var name = $('#trainersearch').val();
		$.ajax({
			type : "get",
			url : "trainersearch",
			data : {
				name : name, id : 'company1'
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('트레이너 검색 실패');
			}
		});
	}
	
</script>
</html>