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

							<c:set var="sales" value="${sList }" />
							<c:if test="${empty sales }">
									판매내역이 없습니다.
								</c:if>
							<c:if test="${!empty sales }">
								<thead>
									<tr>
										<th style="width: 100px">판매번호</th>
										<th style="width: 400px; text-align: center;">광고명</th>
										<th style="width: 200px">결제자</th>
										<th style="width: 100px">결제금액</th>
										<th style="width: 100px">거래상태</th>
										<th style="width: 200px">판매일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="sales" items="${sList }">
										<tr>
											<td><a href="#" onclick="advertisedetail(${sales.ps_code })">${sales.ps_code }</a></td>
											<td style="text-align: center;"><a href="#" onclick="advertisedetail(${sales.ps_adcode})">${sales.ad_title}</a></td>
											<td><a href="#" onclick="advertisedetail(${sales.m_id})">${sales.m_name }(${sales.m_id})</a></td>
											<td style="text-align: right;">${sales.ps_price }</td>
											<td>결제완료</td>
											<th>${sales.ps_date }</th>
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
</script>
</html>

